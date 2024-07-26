/*
 * All Rights Reserved: Copyright [2024] [Zhuang Pan (paynezhuang@gmail.com)]
 * Open Source Agreement: Apache License, Version 2.0
 * For educational purposes only, commercial use shall comply with the author's copyright information.
 * The author does not guarantee or assume any responsibility for the risks of using software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.user.org.SysUserOrgAddDTO;
import com.izpan.modules.system.domain.dto.user.org.SysUserOrgDeleteDTO;
import com.izpan.modules.system.domain.dto.user.org.SysUserOrgSearchDTO;
import com.izpan.modules.system.domain.dto.user.org.SysUserOrgUpdateDTO;
import com.izpan.modules.system.domain.vo.SysUserOrgVO;
import com.izpan.modules.system.facade.ISysUserOrgFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户组织/部门/子部门管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysUserOrgController
 * @CreateTime 2024-07-16 - 16:35:30
 */

@RestController
@Tag(name = "用户组织/部门/子部门管理")
@RequiredArgsConstructor
@RequestMapping("/sys_user_org")
public class SysUserOrgController {

    @NonNull
    private ISysUserOrgFacade sysUserOrgFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:user:org:page")
    @Operation(operationId = "1", summary = "获取用户组织/部门/子部门管理列表")
    public Result<RPage<SysUserOrgVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                            @Parameter(description = "查询对象") SysUserOrgSearchDTO sysUserOrgSearchDTO) {
        return Result.data(sysUserOrgFacade.listSysUserOrgPage(pageQuery, sysUserOrgSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:user:org:get")
    @Operation(operationId = "2", summary = "根据ID获取用户组织/部门/子部门管理详细信息")
    public Result<SysUserOrgVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysUserOrgFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:user:org:add")
    @Operation(operationId = "3", summary = "新增用户组织/部门/子部门管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysUserOrgAddDTO sysUserOrgAddDTO) {
        return Result.status(sysUserOrgFacade.add(sysUserOrgAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:user:org:update")
    @Operation(operationId = "4", summary = "更新用户组织/部门/子部门管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysUserOrgUpdateDTO sysUserOrgUpdateDTO) {
        return Result.status(sysUserOrgFacade.update(sysUserOrgUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:user:org:delete")
    @Operation(operationId = "5", summary = "批量删除用户组织/部门/子部门管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysUserOrgDeleteDTO sysUserOrgDeleteDTO) {
        return Result.status(sysUserOrgFacade.batchDelete(sysUserOrgDeleteDTO));
    }

}