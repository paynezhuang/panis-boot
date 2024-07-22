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
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsAddDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsDeleteDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsSearchDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsUpdateDTO;
import com.izpan.modules.system.domain.vo.SysOrgUnitsTreeVO;
import com.izpan.modules.system.domain.vo.SysOrgUnitsVO;
import com.izpan.modules.system.facade.ISysOrgUnitsFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织/部门/子部门管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysOrgUnitsController
 * @CreateTime 2024-07-16 - 16:35:30
 */

@RestController
@Tag(name = "组织/部门/子部门管理")
@RequiredArgsConstructor
@RequestMapping("/sys_org_units")
public class SysOrgUnitsController {

    @NonNull
    private ISysOrgUnitsFacade sysOrgUnitsFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:org:units:page")
    @Operation(operationId = "1", summary = "获取组织/部门/子部门管理列表")
    public Result<RPage<SysOrgUnitsTreeVO>> page(@Parameter(description = "分页对象", required = true) PageQuery pageQuery,
                                                 @Parameter(description = "查询对象") SysOrgUnitsSearchDTO sysOrgUnitsSearchDTO) {
        return Result.data(sysOrgUnitsFacade.listSysOrgUnitsPage(pageQuery, sysOrgUnitsSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:org:units:get")
    @Operation(operationId = "2", summary = "根据ID获取组织/部门/子部门管理详细信息")
    public Result<SysOrgUnitsVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysOrgUnitsFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:org:units:add")
    @Operation(operationId = "3", summary = "新增组织/部门/子部门管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysOrgUnitsAddDTO sysOrgUnitsAddDTO) {
        return Result.status(sysOrgUnitsFacade.add(sysOrgUnitsAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:org:units:update")
    @Operation(operationId = "4", summary = "更新组织/部门/子部门管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysOrgUnitsUpdateDTO sysOrgUnitsUpdateDTO) {
        return Result.status(sysOrgUnitsFacade.update(sysOrgUnitsUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:org:units:delete")
    @Operation(operationId = "5", summary = "批量删除组织/部门/子部门管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysOrgUnitsDeleteDTO sysOrgUnitsDeleteDTO) {
        return Result.status(sysOrgUnitsFacade.batchDelete(sysOrgUnitsDeleteDTO));
    }

    @GetMapping("/tree")
    @SaCheckPermission("sys:org:units:tree")
    @Operation(operationId = "6", summary = "获取组织/部门/子部门管理树结构数据")
    public Result<List<SysOrgUnitsTreeVO>> tree() {
        return Result.data(sysOrgUnitsFacade.queryAllOrgUnitsListConvertToTree());
    }

}