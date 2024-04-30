package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.permission.SysPermissionAddDTO;
import com.izpan.modules.system.domain.dto.permission.SysPermissionDeleteDTO;
import com.izpan.modules.system.domain.dto.permission.SysPermissionSearchDTO;
import com.izpan.modules.system.domain.dto.permission.SysPermissionUpdateDTO;
import com.izpan.modules.system.domain.vo.SysPermissionVO;
import com.izpan.modules.system.facade.ISysPermissionFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 权限管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysPermissionController
 * @CreateTime 2023-08-05
 */
@RestController
@Tag(name = "权限(按钮)管理")
@RequiredArgsConstructor
@RequestMapping("/sys_permission")
public class SysPermissionController {

    @NonNull
    private ISysPermissionFacade sysPermissionFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:permission:page")
    @Operation(operationId = "1", summary = "获取权限管理列表")
    public Result<RPage<SysPermissionVO>> page(@Parameter(description = "分页对象", required = true) PageQuery pageQuery, @Parameter(description = "查询对象") SysPermissionSearchDTO sysPermissionSearchDTO) {
        return Result.data(sysPermissionFacade.listSysPermissionPage(pageQuery, sysPermissionSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:permission:get")
    @Operation(operationId = "2", summary = "根据ID获取权限管理详细信息")
    public Result<SysPermissionVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysPermissionFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:permission:add")
    @Operation(operationId = "3", summary = "新增权限管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysPermissionAddDTO sysPermissionAddDTO) {
        return Result.status(sysPermissionFacade.add(sysPermissionAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:permission:update")
    @Operation(operationId = "4", summary = "更新权限管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysPermissionUpdateDTO sysPermissionUpdateDTO) {
        return Result.status(sysPermissionFacade.update(sysPermissionUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:permission:delete")
    @Operation(operationId = "5", summary = "批量删除权限管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysPermissionDeleteDTO sysPermissionDeleteDTO) {
        return Result.status(sysPermissionFacade.batchDelete(sysPermissionDeleteDTO));
    }

}
