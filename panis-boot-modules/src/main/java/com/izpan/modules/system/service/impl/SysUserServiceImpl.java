package com.izpan.modules.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.common.domain.LoginUser;
import com.izpan.common.exception.BizException;
import com.izpan.common.pool.StringPools;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.holder.GlobalUserHolder;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.util.RedisUtil;
import com.izpan.infrastructure.util.ServletHolderUtil;
import com.izpan.modules.system.domain.bo.SysRoleBO;
import com.izpan.modules.system.domain.bo.SysUserBO;
import com.izpan.modules.system.domain.entity.SysUser;
import com.izpan.modules.system.repository.mapper.SysUserMapper;
import com.izpan.modules.system.service.ISysRoleService;
import com.izpan.modules.system.service.ISysUserRoleService;
import com.izpan.modules.system.service.ISysUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 用户管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.impl.SysUserServiceImpl
 * @CreateTime 2023/7/6 - 16:04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @NonNull
    private ISysRoleService sysRoleService;

    @NonNull
    private ISysUserRoleService sysUserRoleService;

    @Override
    public IPage<SysUser> listSysUserPage(PageQuery pageQuery, SysUserBO sysUserBO) {
        var queryWrapper = new LambdaQueryWrapper<SysUser>()
                .like(ObjectUtils.isNotEmpty(sysUserBO.getUserName()), SysUser::getUserName, sysUserBO.getUserName())
                .like(ObjectUtils.isNotEmpty(sysUserBO.getRealName()), SysUser::getRealName, sysUserBO.getRealName())
                .like(ObjectUtils.isNotEmpty(sysUserBO.getEmail()), SysUser::getEmail, sysUserBO.getEmail())
                .eq(ObjectUtils.isNotEmpty(sysUserBO.getStatus()), SysUser::getStatus, sysUserBO.getStatus());
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

    @Override
    public SysUserBO currentUserInfo() {
        SysUser byId = super.getById(GlobalUserHolder.getUserId());
        return CglibUtil.convertObj(byId, SysUserBO::new);
    }

    @Override
    public boolean addUser(SysUserBO sysUserBO) {
        // 密码盐值
        sysUserBO.setSalt(RandomStringUtils.randomAlphabetic(6));
        // 默认随机12位密码
        String sha256HexPwd = DigestUtils.sha256Hex(RandomStringUtils.randomAlphabetic(12));
        String password = DigestUtils.sha256Hex(sha256HexPwd + sysUserBO.getSalt());
        sysUserBO.setPassword(password);
        boolean save = super.save(sysUserBO);
        sysUserRoleService.initUserRoleHandler(sysUserBO.getId(), sysUserBO.getRoleIds());
        return save;
    }

    @Override
    public boolean updateUser(SysUserBO sysUserBO) {
        boolean updateById = super.updateById(sysUserBO);
        sysUserRoleService.initUserRoleHandler(sysUserBO.getId(), sysUserBO.getRoleIds());
        // 用户管理修改用户，则退出用户，要求重登
        StpUtil.logout(sysUserBO.getId());
        return updateById;
    }

    @Override
    public boolean updateCurrentUserInfo(SysUserBO sysUserBO) {
        boolean updateById = super.updateById(sysUserBO);
        // 自我更新个人资料，需要更新缓存资料
        saveUserToSession(sysUserBO, true);
        return updateById;
    }

    @Override
    public boolean removeBatchByIds(List<Long> ids) {
        if (!StpUtil.hasRole("ADMIN")) {
            throw new BizException("非管理员禁止删除用户");
        }
        boolean containAdmin = baseMapper.queryIsContainAdmin(ids);
        if (containAdmin) {
            throw new BizException("禁止删除《管理员》用户");
        }
        return super.removeBatchByIds(ids, true);
    }

    @Override
    public Map<String, String> userLogin(SysUserBO sysUserBO) {
        SysUser userForUserName = baseMapper.getUserByUserName(sysUserBO.getUserName());
        if (ObjectUtils.isEmpty(userForUserName)) {
            throw new BizException("查找不到用户名 %s".formatted(sysUserBO.getUserName()));
        }
        if (StringPools.ZERO.equals(userForUserName.getStatus())) {
            throw new BizException("当前用户 %s 已被禁止登录".formatted(sysUserBO.getUserName()));
        }
        // 密码拼接
        String inputPassword = sysUserBO.getPassword() + userForUserName.getSalt();
        // 密码比对
        if (!DigestUtils.sha256Hex(inputPassword).equals(userForUserName.getPassword())) {
            throw new BizException("登录失败，请核实用户名以及密码");
        }
        // sa token 进行登录
        StpUtil.login(userForUserName.getId());
        // 更新用户登录时间
        userForUserName.setLastLoginTime(LocalDateTime.now());
        // 用户登录 IP
        log.info("登录IP:{}", JakartaServletUtil.getClientIP(ServletHolderUtil.getRequest()));
        saveUserToSession(userForUserName, false);
        super.updateById(userForUserName);
        return Map.of("token", StpUtil.getTokenValue());
    }

    /**
     * 将用户信息存入 Session
     *
     * @param sysUser   用户对象
     * @param needCheck 是否需要查找数据库用户信息
     * @author payne.zhuang
     * @CreateTime 2024-04-21 22:19
     */
    private void saveUserToSession(SysUser sysUser, boolean needCheck) {
        if (needCheck) {
            sysUser = super.getById(sysUser.getId());
        }
        // 用户转换
        LoginUser loginUser = CglibUtil.convertObj(sysUser, LoginUser::new);
        // 获取用户角色
        List<SysRoleBO> sysRoleBOS = sysRoleService.queryRoleListWithUserId(sysUser.getId());
        loginUser.setRoleIds(sysRoleBOS.stream().map(SysRoleBO::getId).toList());
        loginUser.setRoleCodes(sysRoleBOS.stream().map(SysRoleBO::getRoleCode).toList());
        // Session 放入用户对象
        StpUtil.getSessionByLoginId(sysUser.getId()).set("user", loginUser);
    }

    @Override
    public Map<String, String> refreshToken(String refreshToken, String refreshTokenCacheKey, LoginUser loginUser) {
        // 删除 旧的 refresh token
        RedisUtil.del(refreshTokenCacheKey);
        return Map.of();
    }

    @Override
    public String resetPassword(Long userId) {
        if (!StpUtil.hasRole("ADMIN")) {
            throw new BizException("非管理员禁止重置用户密码");
        }
        SysUser sysUser = baseMapper.selectById(userId);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new BizException("查找不到用户信息");
        }
        // 密码盐值
        sysUser.setSalt(RandomStringUtils.randomAlphabetic(6));
        // 默认随机12位密码
        String randomPwd = RandomStringUtils.randomAlphabetic(12);
        String sha256HexPwd = DigestUtils.sha256Hex(randomPwd);
        String password = DigestUtils.sha256Hex(sha256HexPwd + sysUser.getSalt());
        sysUser.setPassword(password);
        super.updateById(sysUser);
        return randomPwd;
    }
}