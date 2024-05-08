package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.common.domain.Options;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.role.SysRoleAddDTO;
import com.izpan.modules.system.domain.dto.role.SysRoleDeleteDTO;
import com.izpan.modules.system.domain.dto.role.SysRoleSearchDTO;
import com.izpan.modules.system.domain.dto.role.SysRoleUpdateDTO;
import com.izpan.modules.system.domain.vo.SysRoleVO;
import com.izpan.modules.system.facade.ISysRoleFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysRoleController
 * @CreateTime 2023-07-23
 */
@RestController
@Tag(name = "角色管理")
@RequiredArgsConstructor
@RequestMapping("/sys_role")
public class SysRoleController {

    @NonNull
    private ISysRoleFacade sysRoleFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:role:page")
    @Operation(operationId = "1", summary = "获取角色管理列表")
    public Result<RPage<SysRoleVO>> page(@Parameter(description = "分页对象", required = true) PageQuery pageQuery,
                                         @Parameter(description = "查询对象") SysRoleSearchDTO sysRoleSearchDTO) {
        return Result.data(sysRoleFacade.listSysRolePage(pageQuery, sysRoleSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:role:get")
    @Operation(operationId = "2", summary = "根据ID获取角色管理详细信息")
    public Result<SysRoleVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysRoleFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:role:add")
    @Operation(operationId = "3", summary = "新增角色管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysRoleAddDTO sysRoleAddDTO) {
        return Result.status(sysRoleFacade.add(sysRoleAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:role:update")
    @Operation(operationId = "4", summary = "更新角色管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysRoleUpdateDTO sysRoleUpdateDTO) {
        return Result.status(sysRoleFacade.update(sysRoleUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:role:delete")
    @Operation(operationId = "5", summary = "批量删除角色管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysRoleDeleteDTO sysRoleDeleteDTO) {
        return Result.status(sysRoleFacade.batchDelete(sysRoleDeleteDTO));
    }

    @GetMapping("/all_roles")
    @SaCheckPermission("sys:role:allRoles")
    @Operation(operationId = "6", summary = "获取所有角色信息集合")
    public Result<List<Options<Long>>> queryAllRoleListConvertOptions() {
        return Result.data(sysRoleFacade.queryAllRoleListConvertOptions());
    }

}
