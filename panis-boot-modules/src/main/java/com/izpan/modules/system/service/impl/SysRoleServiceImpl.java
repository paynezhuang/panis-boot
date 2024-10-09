package com.izpan.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.common.exception.BizException;
import com.izpan.common.pool.StringPools;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysRoleBO;
import com.izpan.modules.system.domain.entity.SysRole;
import com.izpan.modules.system.repository.mapper.SysRoleMapper;
import com.izpan.modules.system.service.ISysRoleService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 角色管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.impl.SysRoleServiceImpl
 * @CreateTime 2023-07-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public IPage<SysRole> listSysRolePage(PageQuery pageQuery, SysRoleBO sysRoleBO) {
        var queryWrapper = new LambdaQueryWrapper<SysRole>()
                .like(ObjectUtils.isNotEmpty(sysRoleBO.getRoleName()), SysRole::getRoleName, sysRoleBO.getRoleName())
                .like(ObjectUtils.isNotEmpty(sysRoleBO.getRoleCode()), SysRole::getRoleCode, sysRoleBO.getRoleCode())
                .eq(ObjectUtils.isNotEmpty(sysRoleBO.getStatus()), SysRole::getStatus, sysRoleBO.getStatus())
                .orderByAsc(SysRole::getSort);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>().in(SysRole::getId, list);
        baseMapper.selectList(queryWrapper)
                .stream().filter(sysRole -> StringPools.ADMIN.equalsIgnoreCase(sysRole.getRoleCode())).findFirst()
                .ifPresent(sysRole -> {
                    throw new BizException("系统管理员角色不允许删除");
                });
        return super.removeByIds(list, true);
    }

    @Override
    public List<SysRoleBO> queryAllRoleList() {
        var queryWrapper = new LambdaQueryWrapper<SysRole>()
                .orderByAsc(SysRole::getSort);
        return CglibUtil.convertList(baseMapper.selectList(queryWrapper), SysRoleBO::new);
    }

    @Override
    public List<String> queryRoleCodesWithUserId(Long userId) {
        List<SysRole> sysRoles = baseMapper.queryRoleListWithUserId(userId);
        return sysRoles.stream().map(SysRole::getRoleCode).toList();
    }

    @Override
    public List<SysRoleBO> queryRoleListWithUserId(Long userId) {
        List<SysRole> sysRoles = baseMapper.queryRoleListWithUserId(userId);
        return CglibUtil.convertList(sysRoles, SysRoleBO::new);
    }
}
