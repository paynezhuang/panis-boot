package com.izpan.common.constants;

import com.izpan.common.pool.StringPools;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统管理缓存常量
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.constants.SystemCacheConstant
 * @CreateTime 2023/7/18 - 13:45
 */
public class SystemCacheConstant implements Serializable {

    @Serial
    private static final long serialVersionUID = 5416352129963529591L;

    private SystemCacheConstant() {

    }

    private static final String SYSTEM = "system";

    // ====================== 用户管理 Begin ======================

    private static final String USER = "user";

    private static final String LOGIN = "login";

    private static final String REFRESH_TOKEN = "refresh_token";

    // 用户路由
    public static final String SYSTEM_USER_ROUTE = SYSTEM + ":user:route";

    // 统一前缀
    private static final String USER_PREFIX = SYSTEM + StringPools.COLON + USER + StringPools.COLON;

    /**
     * 获取登录用户缓存key
     *
     * @param userId 用户 ID
     * @return {@link String} system:user:login:userId
     * @author payne.zhuang
     * @CreateTime 2023-07-18 13:48
     */
    public static String userLoginKey(Long userId) {
        return USER_PREFIX + LOGIN + StringPools.COLON + userId;
    }

    /**
     * 用户登录记录缓存 Key
     *
     * @param userId 用户 ID
     * @param token  access token or refresh token
     * @return {@link String} system:user:login:userId:token
     * @author payne.zhuang
     * @CreateTime 2024-01-25 11:01
     */
    public static String userLoginTokenKey(Long userId, String token) {
        return userLoginKey(userId) + StringPools.COLON + token;
    }

    /**
     * 获取刷新token缓存Key
     *
     * @param nonce 随机数
     * @return {@link String} system:refresh_token:nonce
     * @author payne.zhuang
     * @CreateTime 2023-08-15 15:10
     */
    public static String userRefreshTokenNonceKey(String nonce) {
        return USER_PREFIX + REFRESH_TOKEN + StringPools.COLON + nonce;
    }

    /**
     * 用户路由缓存 Key
     *
     * @param userId 用户 ID
     * @return {@linkplain String} system:user:route::userId
     * @author payne.zhuang
     * @CreateTime 2024-04-22 09:25
     */
    public static String userRouteKey(Long userId) {
        return SYSTEM_USER_ROUTE + StringPools.DOUBLE_COLON + userId;
    }

    // ====================== 用户管理 End ======================

    // ====================== 角色权限 Begin ======================

    public static final String SYSTEM_ROLE_PERMISSION_LIST = SYSTEM + ":role:permission:list";
    public static final String SYSTEM_ROLE_MENU_LIST = SYSTEM + ":role:menu:list";
    public static final String SYSTEM_ROLE_PERMISSION_RESOURCES = SYSTEM + ":role:permission:resources";

    /**
     * 角色权限列表缓存 Key
     *
     * @param roleId 角色 ID
     * @return {@linkplain String} system:role:permission:list:roleId
     * @author payne.zhuang
     * @CreateTime 2024-04-20 20:59
     */
    public static String rolePermissionListKey(Long roleId) {
        return SYSTEM_ROLE_PERMISSION_LIST + StringPools.DOUBLE_COLON + roleId;
    }

    /**
     * 角色权限资源缓存 Key
     *
     * @param roleId 角色 ID
     * @return {@linkplain String} system:role:permission:resources:roleId
     * @author payne.zhuang
     * @CreateTime 2024-04-20 20:59
     */
    public static String rolePermissionResourcesKey(Long roleId) {
        return SYSTEM_ROLE_PERMISSION_RESOURCES + StringPools.DOUBLE_COLON + roleId;
    }

    /**
     * 角色菜单列表缓存 Key
     *
     * @param roleId 角色 ID
     * @return {@linkplain String} system:role:menu:list:roleId
     * @author payne.zhuang
     * @CreateTime 2024-04-20 20:59
     */
    public static String roleMenuListKey(Long roleId) {
        return SYSTEM_ROLE_MENU_LIST + StringPools.DOUBLE_COLON + roleId;
    }

    // ====================== 用户管理 End ======================
}
