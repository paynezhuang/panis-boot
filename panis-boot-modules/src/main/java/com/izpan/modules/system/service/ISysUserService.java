package com.izpan.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.common.domain.LoginUser;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysUserBO;
import com.izpan.modules.system.domain.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 用户管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.ISysUserService
 * @CreateTime 2023/7/6 - 16:03
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 用户管理 - 分页查询
     *
     * @param pageQuery 分页对象
     * @param sysUserBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2023-07-13 15:10
     */
    IPage<SysUser> listSysUserPage(PageQuery pageQuery, SysUserBO sysUserBO);

    /**
     * 获取当前用户信息
     *
     * @return {@link SysUserBO} 用户信息
     * @author payne.zhuang
     * @CreateTime 2024-04-18 15:08
     */
    SysUserBO currentUserInfo();

    /**
     * 新增用户
     *
     * @param sysUserBO 用户对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2023-11-21 22:34:23
     */
    boolean addUser(SysUserBO sysUserBO);

    /**
     * 更新用户
     *
     * @param sysUserBO 用户对象
     * @return {@link Boolean} 更新结果
     * @author payne.zhuang
     * @CreateTime 2024-01-10 21:59
     */
    boolean updateUser(SysUserBO sysUserBO);

    /**
     * 当前用户修改个人资料
     *
     * @param sysUserBO 修改对象
     * @return {@link Boolean} 更新结果
     * @author payne.zhuang
     * @CreateTime 2024-01-25 15:20
     */
    boolean updateCurrentUserInfo(SysUserBO sysUserBO);

    /**
     * 用户登录
     *
     * @param sysUserBO BO 对象
     * @return {@link String} 用户 Token
     * @author payne.zhuang
     * @CreateTime 2023-07-17 17:55
     */
    Map<String, String> userLogin(SysUserBO sysUserBO);

    /**
     * 刷新用户Token
     *
     * @param refreshToken         刷新TOKEN
     * @param loginCacheKey        登录缓存Key
     * @param refreshTokenCacheKey 刷新缓存Key
     * @param loginUser            缓存用户信息
     * @return {@link Map<>} 用户 Token Map
     * @author payne.zhuang
     * @CreateTime 2023-08-12 11:00
     */
    Map<String, String> refreshToken(String refreshToken, String refreshTokenCacheKey, LoginUser loginUser);

    /**
     * 根据用户ID进行重置密码，并返回加密密码
     *
     * @param userId 用户 Id
     * @return {@link String} 加密后的密码
     * @author payne.zhuang
     * @CreateTime 2023-12-18 22:04
     */
    String resetPassword(Long userId);

    /**
     * 批量删除用户
     * @author payne.zhuang
     * @CreateTime 2024-04-23 12:01
     * @param ids 用户 ID 集合
     * @return {@link Boolean} 删除结果
     */
    boolean removeBatchByIds(List<Long> ids);
}
