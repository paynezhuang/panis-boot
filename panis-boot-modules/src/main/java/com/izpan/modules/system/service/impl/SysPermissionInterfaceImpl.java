package com.izpan.modules.system.service.impl;


import cn.dev33.satoken.stp.StpInterface;
import com.google.common.collect.Lists;
import com.izpan.common.pool.StringPools;
import com.izpan.infrastructure.holder.GlobalUserHolder;
import com.izpan.modules.system.service.ISysRolePermissionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限加载接口实现类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.impl.SysPermissionInterfaceImpl
 * @CreateTime 2024/4/19 - 12:16
 */

@Slf4j
@Component
@AllArgsConstructor
public class SysPermissionInterfaceImpl implements StpInterface {

    @NonNull
    private ISysRolePermissionService sysRolePermissionService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        if (GlobalUserHolder.getUserName().equals(StringPools.ADMIN)) {
            return List.of(StringPools.STAR);
        }
        List<String> permissionList = Lists.newArrayList();
        GlobalUserHolder.getRoleIds().stream()
                .map(sysRolePermissionService::queryPermissionResourcesWithRoleId)
                .forEach(permissionList::addAll);
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return GlobalUserHolder.getRoleCodes();
    }
}
