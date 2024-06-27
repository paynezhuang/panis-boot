package com.izpan.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.izpan.common.util.CollectionUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysUserRoleBO;
import com.izpan.modules.system.domain.entity.SysUserRole;
import com.izpan.modules.system.repository.mapper.SysUserRoleMapper;
import com.izpan.modules.system.service.ISysRoleService;
import com.izpan.modules.system.service.ISysUserRoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户角色管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.impl.SysUserRoleServiceImpl
 * @CreateTime 2023-07-24
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @NonNull
    private ISysRoleService sysRoleService;

    @Override
    public IPage<SysUserRole> listSysUserRolePage(PageQuery pageQuery, SysUserRoleBO sysUserRoleBO) {
        return baseMapper.selectPage(pageQuery.buildPage(), new LambdaQueryWrapper<>());
    }

    @Override
    public List<Long> queryRoleIdsWithUserId(Long userId) {
        List<SysUserRole> sysUserRoleList = baseMapper.listUserRoleByUserId(userId);
        return sysUserRoleList.stream().map(SysUserRole::getRoleId).toList();
    }

    @Override
    public List<String> queryRoleCodesWithUserId(Long userId) {
        return sysRoleService.queryRoleCodesWithUserId(userId);
    }

    @Override
    public void initUserRoleHandler(Long userId, List<Long> roleIds) {
        // 查询
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId);
        List<SysUserRole> sysUserRoles = baseMapper.selectList(queryWrapper);
        // 提取用户原有角色Ids
        Set<Long> userOriginRoleIds = sysUserRoles.stream()
                .map(SysUserRole::getRoleId).collect(Collectors.toSet());
        // 判断用户角色关联关系是否发生变化
        if (userOriginRoleIds.equals(Sets.newHashSet(roleIds))) {
            return;
        }
        if (CollectionUtil.isNotEmpty(userOriginRoleIds)) {
            // 删除用户角色关联关系
            baseMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        }
        // 批量新增用户角色关联关系
        List<SysUserRole> saveUserRoleList = roleIds.stream()
                .map(roleId -> new SysUserRole(userId, roleId)).toList();
        super.saveBatch(saveUserRoleList);
    }
}
