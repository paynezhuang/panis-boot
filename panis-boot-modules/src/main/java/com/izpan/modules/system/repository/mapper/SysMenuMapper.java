package com.izpan.modules.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izpan.modules.system.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理 Mapper 接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysMenu
 * @CreateTime 2023-12-29
 */

public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 根据角色ID查询菜单列表
     *
     * @param roleId 角色ID
     * @return {@linkplain List} 菜单列表集合
     * @author payne.zhuang
     * @CreateTime 2024-04-25 16:05
     */
    List<SysMenu> queryMenuListWithRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色ID列表查询菜单列表
     *
     * @param roleIds 角色Ids
     * @return {@linkplain List} 菜单列表集合
     * @author payne.zhuang
     * @CreateTime 2024-04-15 21:48
     */
    List<SysMenu> listMenuByRoleIds(@Param("roleIds") List<Long> roleIds);

}
