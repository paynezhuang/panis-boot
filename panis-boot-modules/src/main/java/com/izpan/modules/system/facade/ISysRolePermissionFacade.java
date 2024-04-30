package com.izpan.modules.system.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionAddDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionDeleteDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionSearchDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionUpdateDTO;
import com.izpan.modules.system.domain.vo.SysRolePermissionVO;

import java.util.List;

/**
 * 角色权限管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysRolePermissionFacade
 * @CreateTime 2023-08-05
 */

public interface ISysRolePermissionFacade {

    /**
     * 角色权限管理 - 分页查询
     *
     * @param pageQuery                  分页对象
     * @param sysRolePermissionSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    RPage<SysRolePermissionVO> listSysRolePermissionPage(PageQuery pageQuery, SysRolePermissionSearchDTO sysRolePermissionSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 角色权限ID
     * @return {@link SysRolePermissionVO} 角色权限 VO 对象
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    SysRolePermissionVO get(Long id);

    /**
     * 新增角色权限
     *
     * @param sysRolePermissionAddDTO 新增 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    boolean add(SysRolePermissionAddDTO sysRolePermissionAddDTO);

    /**
     * 编辑更新角色权限信息
     *
     * @param sysRolePermissionUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    boolean update(SysRolePermissionUpdateDTO sysRolePermissionUpdateDTO);

    /**
     * 批量删除角色权限信息
     *
     * @param sysRolePermissionDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    boolean batchDelete(SysRolePermissionDeleteDTO sysRolePermissionDeleteDTO);

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
     * 保存角色Id及按钮权限Id集合
     *
     * @param roleId        角色Id
     * @param permissionIds 按钮权限Id集合
     * @return {@linkplain Boolean} 保存结果
     * @author payne.zhuang
     * @CreateTime 2024-04-17 20:37:40
     */
    boolean addPermissionForRoleId(Long roleId, List<Long> permissionIds);

}