package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.holder.GlobalUserHolder;
import com.izpan.modules.system.domain.dto.LoginFormDTO;
import com.izpan.modules.system.domain.dto.RefreshTokenDTO;
import com.izpan.modules.system.domain.dto.menu.SysUserRouteVO;
import com.izpan.modules.system.domain.dto.user.SysUserUpdateCurrentInfoDTO;
import com.izpan.modules.system.domain.vo.SysUserVO;
import com.izpan.modules.system.facade.IAuthenticationFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 认证管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.AuthenticationController
 * @CreateTime 2023/7/17 - 18:33
 */
@RestController
@Tag(name = "登录管理")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    @NonNull
    private IAuthenticationFacade authenticationFacade;

    @PostMapping("/user_name")
    @Operation(operationId = "1", summary = "用户密码登录")
    public Result<Map<String, String>> userNameLogin(@Parameter(description = "登录对象") @RequestBody LoginFormDTO loginFormDTO) {
        return Result.data(authenticationFacade.userNameLogin(loginFormDTO));
    }

    @PostMapping("/refresh_token")
    @Operation(operationId = "2", summary = "刷新用户Token")
    public Result<Map<String, String>> userNameLogin(@Parameter(description = "刷新TOKEN") @RequestBody RefreshTokenDTO refreshToken) {
        return Result.data(authenticationFacade.refreshToken(refreshToken.getRefreshToken()));
    }

    @PostMapping("/logout")
    @Operation(operationId = "3", summary = "用户退出登录")
    public Result<Boolean> logout() {
        return Result.data(authenticationFacade.logout());
    }

    @GetMapping("/user_info")
    @SaCheckPermission("auth:userInfo")
    @Operation(operationId = "10", summary = "获取当前用户详情信息")
    public Result<SysUserVO> getCurrentUserInfo() {
        return Result.data(authenticationFacade.getCurrentUserInfo());
    }

    @PutMapping("/user_info")
    @SaCheckPermission("auth:updateUserInfo")
    @Operation(operationId = "11", summary = "修改当前用户个人资料")
    public Result<SysUserVO> updateCurrentUserInfo(@Parameter(description = "更新用户对象") @RequestBody SysUserUpdateCurrentInfoDTO currentInfoDTO) {
        return Result.data(authenticationFacade.updateCurrentUserInfo(currentInfoDTO));
    }

    @GetMapping("/user_route")
    @SaCheckPermission("auth:userRoute")
    @Operation(operationId = "12", summary = "获取当前用户的权限路由")
    public Result<SysUserRouteVO> queryUserRoute() {
        return Result.data(authenticationFacade.queryUserRouteWithUserId(GlobalUserHolder.getUserId()));
    }

}
