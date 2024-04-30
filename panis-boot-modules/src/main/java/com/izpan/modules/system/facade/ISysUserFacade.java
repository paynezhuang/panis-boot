package com.izpan.modules.system.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.user.*;
import com.izpan.modules.system.domain.vo.SysUserInfoVO;
import com.izpan.modules.system.domain.vo.SysUserVO;

/**
 * 用户管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysUserFacade
 * @CreateTime 2023/7/6 - 16:06
 */
public interface ISysUserFacade {

    /**
     * 用户管理 - 分页查询
     *
     * @param pageQuery        分页对象
     * @param sysUserSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2023-07-13 15:10
     */
    RPage<SysUserVO> listSysUserPage(PageQuery pageQuery, SysUserSearchDTO sysUserSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 用户ID
     * @return {@link SysUserInfoVO} 用户个人信息 VO 对象
     * @author payne.zhuang
     * @CreateTime 2023-07-15 16:33
     */
    SysUserInfoVO get(Long id);

    /**
     * 新增用户
     *
     * @param sysUserAddDTO 新增用户 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-07-17 17:26
     */
    boolean addUser(SysUserAddDTO sysUserAddDTO);

    /**
     * 编辑更新用户信息
     *
     * @param sysUserUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-07-17 17:27
     */
    boolean updateUser(SysUserUpdateDTO sysUserUpdateDTO);

    /**
     * 批量删除用户信息
     *
     * @param sysUserDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-07-17 17:27
     */
    boolean batchDeleteUser(SysUserDeleteDTO sysUserDeleteDTO);


    /**
     * 根据用户ID进行重置密码，并返回加密密码
     *
     * @param userId 用户 Id
     * @return {@link String} 加密后的密码
     * @author payne.zhuang
     * @CreateTime 2023-12-18 22:04
     */
    String resetPassword(Long userId);
}
