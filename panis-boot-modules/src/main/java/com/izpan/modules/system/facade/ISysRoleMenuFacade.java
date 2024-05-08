package com.izpan.modules.system.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.role.menu.SysRoleMenuAddDTO;
import com.izpan.modules.system.domain.dto.role.menu.SysRoleMenuDeleteDTO;
import com.izpan.modules.system.domain.dto.role.menu.SysRoleMenuSearchDTO;
import com.izpan.modules.system.domain.dto.role.menu.SysRoleMenuUpdateDTO;
import com.izpan.modules.system.domain.vo.SysRoleMenuVO;

import java.util.List;

/**
 * 角色菜单管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysRoleMenuFacade
 * @CreateTime 2023-08-05
 */

public interface ISysRoleMenuFacade {

    /**
     * 角色菜单管理 - 分页查询
     *
     * @param pageQuery            分页对象
     * @param sysRoleMenuSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    RPage<SysRoleMenuVO> listSysRoleMenuPage(PageQuery pageQuery, SysRoleMenuSearchDTO sysRoleMenuSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 角色菜单ID
     * @return {@link SysRoleMenuVO} 角色菜单 VO 对象
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    SysRoleMenuVO get(Long id);

    /**
     * 添加角色菜单
     *
     * @param sysRoleMenuAddDTO 添加 DTO 对象
     * @return {@link Boolean} 添加结果
     * @author payne.zhuang
     * @CreateTime 2024-01-26 15:49
     */
    boolean add(SysRoleMenuAddDTO sysRoleMenuAddDTO);

    /**
     * 编辑更新角色菜单信息
     *
     * @param sysRoleMenuUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    boolean update(SysRoleMenuUpdateDTO sysRoleMenuUpdateDTO);

    /**
     * 批量删除角色菜单信息
     *
     * @param sysRoleMenuDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    boolean batchDelete(SysRoleMenuDeleteDTO sysRoleMenuDeleteDTO);

    /**
     * 根据角色Id获取菜单Id集合
     *
     * @param roleId 角色Id
     * @return {@linkplain List}菜单Id 集合
     * @author payne.zhuang
     * @CreateTime 2024-04-17 11:47
     */
    List<Long> queryMenuIdsWithRoleId(Long roleId);

    /**
     * 保存角色Id及菜单Id
     *
     * @param roleId  角色Id
     * @param menuIds 菜单Ids 集合
     * @return {@linkplain Boolean} 保存结果
     * @author payne.zhuang
     * @CreateTime 2024-04-17 11:55
     */
    boolean addMenuForRoleId(Long roleId, List<Long> menuIds);
}