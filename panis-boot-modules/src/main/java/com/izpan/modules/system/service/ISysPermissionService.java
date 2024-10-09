package com.izpan.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysPermissionBO;
import com.izpan.modules.system.domain.entity.SysPermission;

import java.util.List;

/**
 * 权限管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysPermission
 * @CreateTime 2023-08-05
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 权限管理 - 分页查询
     *
     * @param pageQuery       分页对象
     * @param sysPermissionBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    IPage<SysPermission> listSysPermissionPage(PageQuery pageQuery, SysPermissionBO sysPermissionBO);

    /**
     * 添加权限
     *
     * @param sysPermissionBO 添加 BO 对象
     * @return {@link Boolean} 添加结果
     * @author payne.zhuang
     * @CreateTime 2024-10-09 16:40:37
     */
    boolean add(SysPermissionBO sysPermissionBO);

    /**
     * 根据角色ID查询权限列表
     *
     * @param roleId 角色ID
     * @return {@link List} 权限列表
     * @author payne.zhuang
     * @CreateTime 2024-04-19 22:07
     */
    List<SysPermissionBO> queryPermissionListWithRoleId(Long roleId);

    /**
     * 根据角色ID列表查询权限按钮列表
     *
     * @param roleIds 角色Ids
     * @return {@linkplain List} 权限按钮列表集合
     * @author payne.zhuang
     * @CreateTime 2024-04-15 21:33
     */
    List<SysPermissionBO> queryPermissionListWithRoleIds(List<Long> roleIds);

    /**
     * 获取所有权限信息
     *
     * @return {@link List} 权限列表
     * @author payne.zhuang
     * @CreateTime 2024-04-18 11:40
     */
    List<SysPermissionBO> queryList();

    /**
     * 保存角色权限到缓存
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID列表
     * @author payne.zhuang
     * @CreateTime 2024-04-19 21:52
     */
    void saveRolePermissionToCache(Long roleId, List<Long> permissionIds);
}
