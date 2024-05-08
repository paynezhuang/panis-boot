package com.izpan.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.google.common.collect.Streams;
import com.izpan.common.constants.SystemCacheConstant;
import com.izpan.common.exception.BizException;
import com.izpan.common.pool.StringPools;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.util.GsonUtil;
import com.izpan.infrastructure.util.RedisUtil;
import com.izpan.modules.system.domain.bo.SysPermissionBO;
import com.izpan.modules.system.domain.entity.SysPermission;
import com.izpan.modules.system.repository.mapper.SysPermissionMapper;
import com.izpan.modules.system.service.ISysPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 权限管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysPermission
 * @CreateTime 2023-08-05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public IPage<SysPermission> listSysPermissionPage(PageQuery pageQuery, SysPermissionBO sysPermissionBO) {
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getMenuId, sysPermissionBO.getMenuId())
                .like(ObjectUtils.isNotEmpty(sysPermissionBO.getName()), SysPermission::getName, sysPermissionBO.getName())
                .orderByAsc(SysPermission::getSort);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

    @Override
    public boolean save(SysPermission entity) {
        LambdaQueryWrapper<SysPermission> eq = new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getResource, entity.getResource());
        SysPermission one = super.getOne(eq);
        if (ObjectUtils.isNotEmpty(one)) {
            throw new BizException("已存在相同权限资源 %s 按钮".formatted(entity.getResource()));
        }
        return super.save(entity);
    }

    @Override
    public List<SysPermissionBO> queryPermissionListWithRoleId(Long roleId) {
        if (ObjectUtils.isEmpty(roleId)) return Collections.emptyList();
        // 根据角色查找所有权限信息
        List<SysPermission> sysPermissions = baseMapper.queryPermissionListWithRoleId(roleId);
        return CglibUtil.convertList(sysPermissions, SysPermissionBO::new);
    }

    @Override
    public List<SysPermissionBO> queryPermissionListWithRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) return Collections.emptyList();
        // 根据角色查找所有权限信息
        List<SysPermission> sysPermissions = baseMapper.queryPermissionListWithRoleIds(roleIds);
        return CglibUtil.convertList(sysPermissions, SysPermissionBO::new);
    }

    @Override
    public List<SysPermissionBO> queryList() {
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getStatus, StringPools.ONE);
        return CglibUtil.convertList(baseMapper.selectList(queryWrapper), SysPermissionBO::new);
    }

    @Override
    public void saveRolePermissionToCache(Long roleId, List<Long> permissionIds) {
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getStatus, StringPools.ONE)
                .in(SysPermission::getId, permissionIds);
        List<SysPermission> sysPermissions = baseMapper.selectList(queryWrapper);
        List<String> permissionResources = sysPermissions.stream()
                .map(SysPermission::getResource)
                .flatMap(resource -> Streams.stream(Splitter.on(StringPools.SEMICOLON)
                        .trimResults().omitEmptyStrings().split(resource)))
                .distinct().toList();
        // 保存角色权限到缓存
        String rolePermissionKey = SystemCacheConstant.rolePermissionResourcesKey(roleId);
        RedisUtil.set(rolePermissionKey, permissionResources);
    }
}
