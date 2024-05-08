package com.izpan.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysPermissionBO;
import com.izpan.modules.system.domain.bo.SysRolePermissionBO;
import com.izpan.modules.system.domain.entity.SysRolePermission;

import java.util.List;

/**
 * 角色权限管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysRolePermission
 * @CreateTime 2023-08-05
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {
    /**
     * 角色权限管理 - 分页查询
     *
     * @param pageQuery           分页对象
     * @param sysRolePermissionBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    IPage<SysRolePermission> listSysRolePermissionPage(PageQuery pageQuery, SysRolePermissionBO sysRolePermissionBO);

    /**
     * 添加角色权限
     *
     * @param sysRolePermissionBO 添加 BO 对象
     * @return {@link Boolean} 添加结果
     * @author payne.zhuang
     * @CreateTime 2024-01-26 16:17:56
     */
    boolean add(SysRolePermissionBO sysRolePermissionBO);

    /**
     * 保存角色Id及按钮权限Id集合
     *
     * @param roleId        角色Id
     * @param permissionIds 按钮权限Id集合
     * @return {@linkplain Boolean} 保存结果
     * @author payne.zhuang
     * @CreateTime 2024-04-17 20:37:40
     */
    boolean addPermissionForRoleId(Long roleId, List<Long> permissionIds);

    /**
     * 根据角色Id获取按钮权限Id集合
     *
     * @param roleId 角色Id
     * @return {@linkplain List} 按钮权限 Id 集合
     * @author payne.zhuang
     * @CreateTime 2024-04-17 20:31:30
     */
    List<Long> queryPermissionIdsWithRoleId(Long roleId);

    /**
     * 根据角色Id获取按钮权限资源集合
     *
     * @param roleId 角色Id
     * @return {@linkplain List} 按钮权限资源集合
     * @author payne.zhuang
     * @CreateTime 2024-04-17 20:37:40
     */
    List<String> queryPermissionResourcesWithRoleId(Long roleId);

    /**
     * 根据角色Id获取按钮权限资源集合
     *
     * @param roleId 角色Id
     * @return {@linkplain List} 按钮权限资源集合
     * @author payne.zhuang
     * @CreateTime 2024-04-20 20:54
     */
    List<SysPermissionBO> queryPermissionListWithRoleId(Long roleId);

    /**
     * 删除角色权限缓存
     *
     * @param permissionId 按钮权限ID
     * @author payne.zhuang
     * @CreateTime 2024-04-20 22:00
     */
    void deleteRolePermissionCacheWithRoleId(Long permissionId);
}
