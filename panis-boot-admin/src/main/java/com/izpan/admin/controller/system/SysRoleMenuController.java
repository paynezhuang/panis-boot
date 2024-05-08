package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.role.menu.SysRoleMenuAddDTO;
import com.izpan.modules.system.domain.dto.role.menu.SysRoleMenuDeleteDTO;
import com.izpan.modules.system.domain.dto.role.menu.SysRoleMenuSearchDTO;
import com.izpan.modules.system.domain.dto.role.menu.SysRoleMenuUpdateDTO;
import com.izpan.modules.system.domain.vo.SysRoleMenuVO;
import com.izpan.modules.system.facade.ISysRoleMenuFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色菜单管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysRoleMenuController
 * @CreateTime 2023-08-05
 */
@RestController
@Tag(name = "角色菜单管理")
@RequiredArgsConstructor
@RequestMapping("/sys_role_menu")
public class SysRoleMenuController {

    @NonNull
    private ISysRoleMenuFacade sysRoleMenuFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:role:menu:page")
    @Operation(operationId = "1", summary = "获取角色菜单管理列表")
    public Result<RPage<SysRoleMenuVO>> page(@Parameter(description = "分页对象", required = true) PageQuery pageQuery,
                                             @Parameter(description = "查询对象") SysRoleMenuSearchDTO sysRoleMenuSearchDTO) {
        return Result.data(sysRoleMenuFacade.listSysRoleMenuPage(pageQuery, sysRoleMenuSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:role:menu:get")
    @Operation(operationId = "2", summary = "根据ID获取角色菜单管理详细信息")
    public Result<SysRoleMenuVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysRoleMenuFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:role:menu:add")
    @Operation(operationId = "3", summary = "根据角色 ID 保存菜单")
    public Result<Boolean> add(@Parameter(description = "更新对象") @RequestBody SysRoleMenuAddDTO sysRoleMenuAddDTO) {
        return Result.data(sysRoleMenuFacade.addMenuForRoleId(sysRoleMenuAddDTO.getRoleId(), sysRoleMenuAddDTO.getMenuIds()));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:role:menu:update")
    @Operation(operationId = "4", summary = "更新角色菜单管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysRoleMenuUpdateDTO sysRoleMenuUpdateDTO) {
        return Result.status(sysRoleMenuFacade.update(sysRoleMenuUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:role:menu:delete")
    @Operation(operationId = "5", summary = "批量删除角色菜单管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysRoleMenuDeleteDTO sysRoleMenuDeleteDTO) {
        return Result.status(sysRoleMenuFacade.batchDelete(sysRoleMenuDeleteDTO));
    }

    @GetMapping("/menu/{roleId}")
    @SaCheckPermission("sys:role:menu:queryMenuIdsWithRoleId")
    @Operation(operationId = "6", summary = "根据角色 ID 获取菜单Ids")
    public Result<List<Long>> queryMenuIdsWithRoleId(@Parameter(description = "角色ID", required = true) @PathVariable("roleId") Long roleId) {
        return Result.data(sysRoleMenuFacade.queryMenuIdsWithRoleId(roleId));
    }

}
