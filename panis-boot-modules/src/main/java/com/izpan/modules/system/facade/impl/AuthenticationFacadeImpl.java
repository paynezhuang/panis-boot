package com.izpan.modules.system.facade.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import com.izpan.common.api.ResultCode;
import com.izpan.common.constants.SystemCacheConstant;
import com.izpan.common.exception.BizException;
import com.izpan.common.exception.RouteException;
import com.izpan.common.pool.StringPools;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.holder.GlobalUserHolder;
import com.izpan.infrastructure.util.GsonUtil;
import com.izpan.infrastructure.util.RedisUtil;
import com.izpan.modules.system.domain.bo.SysMenuBO;
import com.izpan.modules.system.domain.bo.SysPermissionBO;
import com.izpan.modules.system.domain.bo.SysUserBO;
import com.izpan.modules.system.domain.dto.LoginFormDTO;
import com.izpan.modules.system.domain.dto.menu.SysUserRouteVO;
import com.izpan.modules.system.domain.dto.user.SysUserUpdateCurrentInfoDTO;
import com.izpan.modules.system.domain.entity.SysPermission;
import com.izpan.modules.system.domain.vo.SysUserVO;
import com.izpan.modules.system.facade.IAuthenticationFacade;
import com.izpan.modules.system.service.ISysRoleMenuService;
import com.izpan.modules.system.service.ISysRolePermissionService;
import com.izpan.modules.system.service.ISysUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户认证门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.AuthenticationFacadeImpl
 * @CreateTime 2023/7/17 - 18:34
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements IAuthenticationFacade {

    @NonNull
    private ISysRoleMenuService sysRoleMenuService;

    @NonNull
    private ISysRolePermissionService sysRolePermissionService;

    @NonNull
    private ISysUserService sysUserService;

    /**
     * 初始化菜单路由
     *
     * @param parentId          父级菜单ID
     * @param sysMenus          菜单列表
     * @param menuPermissionMap 菜单对应的权限按钮 Map 结构
     * @return {@link SysUserRouteVO.Route} 路由对象列表
     * @author payne.zhuang
     * @CreateTime 2024-02-04 23:42
     */
    private static List<SysUserRouteVO.Route> initMenuRoute(Long parentId, List<SysMenuBO> sysMenus,
                                                            Map<Long, List<String>> menuPermissionMap) {
        // 根据 parentId 获取菜单列表
        List<SysMenuBO> parentMenuList = sysMenus.stream()
                .filter(item -> item.getParentId().equals(parentId)).toList();
        List<SysUserRouteVO.Route> routes = Lists.newArrayList();
        parentMenuList.forEach(menu -> {
            // 路由元数据
            SysUserRouteVO.Meta routeMeta = SysUserRouteVO.Meta.builder()
                    .title(menu.getName())
                    .i18nKey(menu.getI18nKey())
                    .order(menu.getSort())
                    .keepAlive(StringPools.Y.equals(menu.getKeepAlive()))
                    .hideInMenu(StringPools.Y.equals(menu.getHide()))
                    .multiTab(StringPools.Y.equals(menu.getMultiTab()))
                    .fixedIndexInTab(menu.getFixedIndexInTab())
                    .query(GsonUtil.fromJsonList(menu.getQuery()))
                    .permissions(menuPermissionMap.getOrDefault(menu.getId(), Lists.newArrayList()))
                    .build();
            if (menu.getIconType().equals(StringPools.TWO)) {
                routeMeta.setLocalIcon(menu.getIcon());
            } else {
                routeMeta.setIcon(menu.getIcon());
            }
            // 路由对象
            SysUserRouteVO.Route route = SysUserRouteVO.Route.builder()
                    .name(menu.getRouteName())
                    .path(menu.getRoutePath())
                    .component(menu.getComponent().replace(StringPools.HASH, StringPools.DOLLAR))
                    .meta(routeMeta)
                    .children(initMenuRoute(menu.getId(), sysMenus, menuPermissionMap))
                    .build();
            // 添加到路由列表
            routes.add(route);
        });
        // 按照排序值排序
        routes.sort(Comparator.comparing(route -> route.getMeta().getOrder()));
        return routes;
    }

    @Override
    public Map<String, String> userNameLogin(LoginFormDTO loginFormDTO) {
        SysUserBO sysUserBO = CglibUtil.convertObj(loginFormDTO, SysUserBO::new);
        return sysUserService.userLogin(sysUserBO);
    }

    @Override
    public boolean logout() {
        RedisUtil.del(SystemCacheConstant.userRouteKey(GlobalUserHolder.getUserId()));
        StpUtil.logout();
        return true;
    }

    @Override
    public Map<String, String> refreshToken(String refreshToken) {
        return sysUserService.refreshToken(refreshToken, null, null);
    }

    @Override
    public SysUserVO getCurrentUserInfo() {
        SysUserBO sysUserBO = sysUserService.currentUserInfo();
        return CglibUtil.convertObj(sysUserBO, SysUserVO::new);
    }

    @Override
    @Transactional
    public SysUserVO updateCurrentUserInfo(SysUserUpdateCurrentInfoDTO currentInfoDTO) {
        SysUserBO sysUserBO = CglibUtil.convertObj(currentInfoDTO, SysUserBO::new);
        boolean updated = sysUserService.updateCurrentUserInfo(sysUserBO);
        if (Boolean.FALSE.equals(updated)) {
            throw new BizException("更新用户信息异常");
        }
        return getCurrentUserInfo();
    }

    @Override
    @Cacheable(value = SystemCacheConstant.SYSTEM_USER_ROUTE, key = "#userId")
    public SysUserRouteVO queryUserRouteWithUserId(Long userId) {
        try {
            List<Long> currentUserRoleIds = GlobalUserHolder.getRoleIds();
            // 获取当前用户的菜单列表以及权限按钮列表
            List<SysMenuBO> sysMenuBOS = Lists.newArrayList();
            currentUserRoleIds.stream()
                    .map(sysRoleMenuService::queryMenuListWithRoleId)
                    .forEach(sysMenuBOS::addAll);
            List<SysPermissionBO> sysPermissionBOS = Lists.newArrayList();
            currentUserRoleIds.stream()
                    .map(sysRolePermissionService::queryPermissionListWithRoleId)
                    .forEach(sysPermissionBOS::addAll);
            // 将权限集合分组成菜单对应按钮集合
            Map<Long, List<String>> menuPermissionMap = transform(sysPermissionBOS);
            // 返回路由对象
            return SysUserRouteVO.builder()
                    .home("home")
                    // 组装路由集合
                    .routes(initMenuRoute(0L, sysMenuBOS, menuPermissionMap))
                    .build();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new RouteException(ResultCode.USER_ROUTE_ERROR.getCode(), ResultCode.USER_ROUTE_ERROR.getValue());
        }
    }

    /**
     * 将权限集合分组成菜单对应按钮集合
     *
     * @param sysPermissionBOS 权限集合
     * @return {@linkplain Map} 菜单对应按钮集合
     * @author payne.zhuang
     * @CreateTime 2024-04-27 19:33
     */
    private Map<Long, List<String>> transform(List<SysPermissionBO> sysPermissionBOS) {
        return sysPermissionBOS.stream()
                .collect(Collectors.groupingBy(
                        // 以menuId作为分组依据
                        SysPermission::getMenuId,
                        Collectors.mapping(
                                // 将每个SysPermission对象的resource属性按照分号分割后进行处理
                                permission -> Arrays.stream(permission.getResource().split(StringPools.SEMICOLON))
                                        // 去除空格，过滤空字符串
                                        .map(String::trim).filter(s -> !s.isEmpty())
                                        // 去除重复元素
                                        .distinct().toList(),
                                // 对List<String>进行处理
                                Collectors.collectingAndThen(Collectors.toList(),
                                        // 将多个List<String>合并为一个流
                                        list -> list.stream()
                                                .flatMap(Collection::stream)
                                                // 去除重复元素，排序
                                                .distinct().sorted(String::compareTo).toList()
                                )
                        )
                ));
    }
}
