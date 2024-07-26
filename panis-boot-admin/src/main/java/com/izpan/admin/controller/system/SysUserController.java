package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.user.*;
import com.izpan.modules.system.domain.vo.SysUserResponsibilitiesVO;
import com.izpan.modules.system.domain.vo.SysUserVO;
import com.izpan.modules.system.facade.ISysUserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 系统管理 - 用户管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysUserController
 * @CreateTime 2023/7/6 - 14:25
 */

@RestController
@Tag(name = "用户管理")
@RequiredArgsConstructor
@RequestMapping("/sys_user")
public class SysUserController {

    @NonNull
    private ISysUserFacade sysUserFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:user:page")
    @Operation(operationId = "1", summary = "获取用户管理列表")
    public Result<RPage<SysUserVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                         @Parameter(description = "查询对象") SysUserSearchDTO sysUserSearchDTO) {
        return Result.data(sysUserFacade.listSysUserPage(pageQuery, sysUserSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:user:get")
    @Operation(operationId = "2", summary = "根据ID获取用户详细信息")
    public Result<SysUserVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysUserFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:user:add")
    @Operation(operationId = "3", summary = "新增用户")
    public Result<Boolean> addUser(@Parameter(description = "新增用户对象") @RequestBody SysUserAddDTO sysUserAddDTO) {
        return Result.status(sysUserFacade.addUser(sysUserAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:user:update")
    @Operation(operationId = "4", summary = "更新用户信息")
    public Result<Boolean> updateUser(@Parameter(description = "更新用户对象") @RequestBody SysUserUpdateDTO sysUserUpdateDTO) {
        return Result.status(sysUserFacade.updateUser(sysUserUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:user:delete")
    @Operation(operationId = "5", summary = "批量删除用户信息")
    public Result<Boolean> batchDeleteUser(@Parameter(description = "删除用户对象") @RequestBody SysUserDeleteDTO sysUserDeleteDTO) {
        return Result.status(sysUserFacade.batchDeleteUser(sysUserDeleteDTO));
    }

    @PutMapping("/reset_password/{userId}")
    @SaCheckPermission("sys:user:resetPassword")
    @Operation(operationId = "6", summary = "重置密码")
    public Result<String> resetPassword(@Parameter(description = "用户ID") @PathVariable("userId") Long userId) {
        return Result.data(sysUserFacade.resetPassword(userId));
    }

    @GetMapping("/responsibilities/{userId}")
    @SaCheckPermission("sys:user:responsibilities")
    @Operation(operationId = "7", summary = "根据用户ID获取用户职责信息")
    public Result<SysUserResponsibilitiesVO> queryUserResponsibilities(@Parameter(description = "ID") @PathVariable("userId") Long userId) {
        return Result.data(sysUserFacade.queryUserResponsibilitiesWithUserId(userId));
    }

    @PutMapping("/responsibilities")
    @SaCheckPermission("sys:user:responsibilities")
    @Operation(operationId = "7", summary = "更新用户职责信息")
    public Result<Boolean> updateUserResponsibilities(@Parameter(description = "用户职责对象") @RequestBody SysUserResponsibilitiesUpdateDTO updateDTO) {
        return Result.data(sysUserFacade.updateUserResponsibilities(updateDTO));
    }

}
