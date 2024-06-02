package com.izpan.modules.system.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.menu.SysMenuAddDTO;
import com.izpan.modules.system.domain.dto.menu.SysMenuDeleteDTO;
import com.izpan.modules.system.domain.dto.menu.SysMenuSearchDTO;
import com.izpan.modules.system.domain.dto.menu.SysMenuUpdateDTO;
import com.izpan.modules.system.domain.vo.SysMenuEditVO;
import com.izpan.modules.system.domain.vo.SysMenuPermissionVO;
import com.izpan.modules.system.domain.vo.SysMenuTreeVO;

import java.util.List;

/**
 * 菜单管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysMenuFacade
 * @CreateTime 2023-08-05
 */

public interface ISysMenuFacade {

    /**
     * 菜单管理 - 分页查询
     *
     * @param pageQuery        分页对象
     * @param sysMenuSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    RPage<SysMenuTreeVO> listSysMenuPage(PageQuery pageQuery, SysMenuSearchDTO sysMenuSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 用户ID
     * @return {@link SysMenuEditVO} 菜单编辑 VO 对象
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    SysMenuEditVO get(Long id);

    /**
     * 新增用户
     *
     * @param sysMenuAddDTO 新增编辑 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    boolean add(SysMenuAddDTO sysMenuAddDTO);

    /**
     * 编辑更新用户信息
     *
     * @param sysMenuUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    boolean update(SysMenuUpdateDTO sysMenuUpdateDTO);

    /**
     * 批量删除用户信息
     *
     * @param sysMenuDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-08-05 15:10
     */
    boolean batchDelete(SysMenuDeleteDTO sysMenuDeleteDTO);

    /**
     * 所有菜单路由名称，提供前端，达到页面组件复用的效果
     *
     * @return {@linkplain List} 菜单路由名称 string 集合
     * @author payne.zhuang
     * @CreateTime 2024-04-07 23:24
     */
    List<String> queryAllPageRouteName();

    /**
     * 所有菜单数据，转为树形结构集合返回
     *
     * @return {@linkplain List} 树形结构返回
     * @author payne.zhuang
     * @CreateTime 2024-04-10 23:22
     */
    List<SysMenuTreeVO> queryAllMenuListConvertToTree();

    /**
     * 查询菜单权限列表
     *
     * @return {@linkplain List} 菜单权限列表
     * @author payne.zhuang
     * @CreateTime 2024-04-18 10:55
     */
    List<SysMenuPermissionVO> queryMenuPermissionList();
}