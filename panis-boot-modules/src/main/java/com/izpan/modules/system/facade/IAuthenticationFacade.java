package com.izpan.modules.system.facade;

import com.izpan.modules.system.domain.dto.LoginFormDTO;
import com.izpan.modules.system.domain.dto.menu.SysUserRouteVO;
import com.izpan.modules.system.domain.dto.user.SysUserUpdateCurrentInfoDTO;
import com.izpan.modules.system.domain.vo.SysUserInfoVO;
import com.izpan.modules.system.domain.vo.SysUserVO;

import java.util.Map;

/**
 * 用户认证门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.IAuthenticationFacade
 * @CreateTime 2023/7/17 - 18:34
 */
public interface IAuthenticationFacade {

    /**
     * 账号密码登录
     *
     * @param loginFormDTO 登录对象
     * @return {@link String} 用户 Token
     * @author payne.zhuang
     * @CreateTime 2023-07-17 18:42
     */
    Map<String, String> userNameLogin(LoginFormDTO loginFormDTO);

    /**
     * 刷新用户Token
     *
     * @param refreshToken 刷新TOKEN
     * @return {@link String} 用户 Token
     * @author payne.zhuang
     * @CreateTime 2023-07-17 18:42
     */
    Map<String, String> refreshToken(String refreshToken);

    /**
     * 用户退出登录
     *
     * @return {@link Boolean} 是否退出成功
     * @author payne.zhuang
     * @CreateTime 2024-04-21 23:07
     */
    boolean logout();

    /**
     * 获取当前登录用户信息
     *
     * @return {@link SysUserVO} 用户 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-02-06 15:01:35
     */
    SysUserVO getCurrentUserInfo();

    /**
     * 当前用户修改个人资料
     *
     * @param currentInfoDTO 修改对象
     * @return {@link SysUserVO} 返回个人信息
     * @author payne.zhuang
     * @CreateTime 2024-02-06 15:01:31
     */
    SysUserVO updateCurrentUserInfo(SysUserUpdateCurrentInfoDTO currentInfoDTO);

    /**
     * 获取用户路由
     *
     * @return {@link SysUserRouteVO} 用户路由信息
     * @author payne.zhuang
     * @CreateTime 2024-02-04 22:07
     */
    SysUserRouteVO queryUserRouteWithUserId(Long userId);
}
