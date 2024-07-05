package com.izpan.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysMenuBO;
import com.izpan.modules.system.domain.entity.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysMenu
 * @CreateTime 2023-08-05
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 菜单管理 - 分页查询
     *
     * @param pageQuery 分页对象
     * @param sysMenuBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    IPage<SysMenu> listSysMenuPage(PageQuery pageQuery, SysMenuBO sysMenuBO);

    /**
     * 获取所有菜单信息
     *
     * @return {@link List} 菜单列表
     * @author payne.zhuang
     * @CreateTime 2024-04-17 21:05
     */
    List<SysMenuBO> queryList();

    /**
     * 获取所有菜单信息
     *
     * @param type 菜单类型
     * @return {@link List} 菜单列表
     * @author payne.zhuang
     * @CreateTime 2024-04-17 21:05
     */
    List<SysMenuBO> queryListWithType(String type);

    /**
     * 根据角色ID查询菜单列表
     *
     * @param roleId 角色ID
     * @return {@link List} 菜单列表
     * @author payne.zhuang
     * @CreateTime 2024-04-20 20:40
     */
    List<SysMenuBO> queryMenuListWithRoleId(Long roleId);

    /**
     * 根据角色ID列表查询菜单列表
     *
     * @param roleIds 角色ID列表
     * @return {@link List} 菜单列表
     * @author payne.zhuang
     * @CreateTime 2024-02-04 22:01
     */
    List<SysMenuBO> queryMenuListWithRoleIds(List<Long> roleIds);

    /**
     * 所有菜单路由名称，提供前端，达到页面组件复用的效果
     *
     * @return {@linkplain List} 菜单路由名称 string 集合
     * @author payne.zhuang
     * @CreateTime 2024-04-07 23:24
     */
    List<String> queryAllPageRouteName();

    /**
     * 根据菜单ID列表查询目录菜单列表
     *
     * @param menuIds 菜单ID列表
     * @return {@linkplain List} 目录菜单列表
     * @author payne.zhuang
     * @CreateTime 2024-04-18 14:56
     */
    List<SysMenuBO> queryWithDirectoryList(List<Long> menuIds);

    /**
     * 保存角色菜单到缓存
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     * @author payne.zhuang
     * @CreateTime 2024-04-20 21:29
     */
    void saveRoleMenuToCache(Long roleId, Set<Long> menuIds);

}
