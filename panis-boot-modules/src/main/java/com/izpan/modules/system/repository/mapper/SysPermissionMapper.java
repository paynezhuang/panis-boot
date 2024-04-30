package com.izpan.modules.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izpan.modules.system.domain.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限(按钮)管理 Mapper 接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysPermission
 * @CreateTime 2023-12-29
 */

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据角色ID查询权限列表
     *
     * @param roleId 角色ID
     * @return {@link List} 权限列表
     * @author payne.zhuang
     * @CreateTime 2024-04-19 22:08
     */
    List<SysPermission> queryPermissionListWithRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色ID列表查询权限按钮列表
     *
     * @param roleIds 角色Ids
     * @return {@linkplain List} 权限按钮列表集合
     * @author payne.zhuang
     * @CreateTime 2024-04-15 21:48
     */
    List<SysPermission> queryPermissionListWithRoleIds(@Param("roleIds") List<Long> roleIds);
}
