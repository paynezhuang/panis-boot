package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.menu.SysMenuAddDTO;
import com.izpan.modules.system.domain.dto.menu.SysMenuDeleteDTO;
import com.izpan.modules.system.domain.dto.menu.SysMenuSearchDTO;
import com.izpan.modules.system.domain.dto.menu.SysMenuUpdateDTO;
import com.izpan.modules.system.domain.vo.SysMenuEditVO;
import com.izpan.modules.system.domain.vo.SysMenuPermissionVO;
import com.izpan.modules.system.domain.vo.SysMenuTreeVO;
import com.izpan.modules.system.facade.ISysMenuFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysMenuController
 * @CreateTime 2023-08-05
 */
@RestController
@Tag(name = "菜单管理")
@RequiredArgsConstructor
@RequestMapping("/sys_menu")
public class SysMenuController {

    @NonNull
    private ISysMenuFacade sysMenuFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:menu:page")
    @Operation(operationId = "1", summary = "获取菜单管理列表")
    public Result<RPage<SysMenuTreeVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                             @Parameter(description = "查询对象") SysMenuSearchDTO sysMenuSearchDTO) {
        return Result.data(sysMenuFacade.listSysMenuPage(pageQuery, sysMenuSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:menu:get")
    @Operation(operationId = "2", summary = "根据ID获取菜单管理详细信息")
    public Result<SysMenuEditVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysMenuFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:menu:add")
    @Operation(operationId = "3", summary = "新增菜单管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysMenuAddDTO sysMenuAddDTO) {
        return Result.status(sysMenuFacade.add(sysMenuAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:menu:update")
    @Operation(operationId = "4", summary = "更新菜单管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysMenuUpdateDTO sysMenuUpdateDTO) {
        return Result.status(sysMenuFacade.update(sysMenuUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:menu:delete")
    @Operation(operationId = "5", summary = "批量删除菜单管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysMenuDeleteDTO sysMenuDeleteDTO) {
        return Result.status(sysMenuFacade.batchDelete(sysMenuDeleteDTO));
    }

    @GetMapping("/all_pages")
    @SaCheckPermission("sys:menu:allPages")
    @Operation(operationId = "6", summary = "所有菜单路由名称，提供前端，达到页面组件复用的效果")
    public Result<List<String>> queryAllPageRouteName() {
        return Result.data(sysMenuFacade.queryAllPageRouteName());
    }

    @GetMapping("/tree")
    @SaCheckPermission("sys:menu:tree")
    @Operation(operationId = "7", summary = "菜单路由树形结构")
    public Result<List<SysMenuTreeVO>> tree() {
        return Result.data(sysMenuFacade.queryAllMenuListConvertToTree());
    }

    @GetMapping("/permission")
    @SaCheckPermission("sys:menu:permission")
    @Operation(operationId = "8", summary = "菜单权限列表")
    public Result<List<SysMenuPermissionVO>> queryMenuPermission() {
        return Result.data(sysMenuFacade.queryMenuPermissionList());
    }

}
