package com.izpan.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysUserRoleBO;
import com.izpan.modules.system.domain.entity.SysUserRole;

import java.util.List;

/**
 * 用户角色管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.ISysUserRoleService
 * @CreateTime 2023-07-24
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    /**
     * 用户角色管理 - 分页查询
     *
     * @param pageQuery     分页对象
     * @param sysUserRoleBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2023-07-24 15:10
     */
    IPage<SysUserRole> listSysUserRolePage(PageQuery pageQuery, SysUserRoleBO sysUserRoleBO);

    /**
     * 根据用户ID查询用户角色Ids列表
     *
     * @param userId 用户ID
     * @return {@link List<Long>} 用户角色Ids列表
     * @author payne.zhuang
     * @CreateTime 2023-08-07 17:50
     */
    List<Long> queryRoleIdsWithUserId(Long userId);

    /**
     * 根据用户ID查询用户角色Code列表
     *
     * @param userId 用户ID
     * @return {@link List<String>} 用户角色Code列表
     * @author payne.zhuang
     * @CreateTime 2024-04-19 22:22
     */
    List<String> queryRoleCodesWithUserId(Long userId);

    /**
     * 更新用户角色
     *
     * @param userId  用户ID
     * @param roleIds 角色ID列表
     * @return {@link Boolean} 更新结果
     * @author payne.zhuang
     * @CreateTime 2024-04-18 14:52
     */
    boolean updateUserRole(Long userId, List<Long> roleIds);
}
