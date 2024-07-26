package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionAddDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionDeleteDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionSearchDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionUpdateDTO;
import com.izpan.modules.system.domain.vo.SysRolePermissionVO;
import com.izpan.modules.system.facade.ISysRolePermissionFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色权限管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysRolePermissionController
 * @CreateTime 2023-08-05
 */
@RestController
@Tag(name = "角色权限管理")
@RequiredArgsConstructor
@RequestMapping("/sys_role_permission")
public class SysRolePermissionController {

    @NonNull
    private ISysRolePermissionFacade sysRolePermissionFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:role:permission:page")
    @Operation(operationId = "1", summary = "获取角色权限管理列表")
    public Result<RPage<SysRolePermissionVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                                   @Parameter(description = "查询对象") SysRolePermissionSearchDTO sysRolePermissionSearchDTO) {
        return Result.data(sysRolePermissionFacade.listSysRolePermissionPage(pageQuery, sysRolePermissionSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:role:permission:get")
    @Operation(operationId = "2", summary = "根据ID获取角色权限管理详细信息")
    public Result<SysRolePermissionVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysRolePermissionFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:role:permission:add")
    @Operation(operationId = "3", summary = "根据角色 ID 保存权限")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysRolePermissionAddDTO sysRolePermissionAddDTO) {
        boolean added = sysRolePermissionFacade.addPermissionForRoleId(
                sysRolePermissionAddDTO.getRoleId(), sysRolePermissionAddDTO.getPermissionIds());
        return Result.status(added);
    }

    @PutMapping("/")
    @SaCheckPermission("sys:role:permission:update")
    @Operation(operationId = "4", summary = "更新角色权限管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysRolePermissionUpdateDTO sysRolePermissionUpdateDTO) {
        return Result.status(sysRolePermissionFacade.update(sysRolePermissionUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:role:permission:delete")
    @Operation(operationId = "5", summary = "批量删除角色权限管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysRolePermissionDeleteDTO sysRolePermissionDeleteDTO) {
        return Result.status(sysRolePermissionFacade.batchDelete(sysRolePermissionDeleteDTO));
    }

    @GetMapping("/permission/{roleId}")
    @SaCheckPermission("sys:role:permission:queryPermsWithRoleId")
    @Operation(operationId = "7", summary = "根据角色 ID 获取按钮权限Ids")
    public Result<List<Long>> queryPermissionIdsWithRoleId(@Parameter(description = "角色ID", required = true) @PathVariable("roleId") Long roleId) {
        return Result.data(sysRolePermissionFacade.queryPermissionIdsWithRoleId(roleId));
    }

}
