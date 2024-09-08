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

package com.izpan.admin.controller.tools;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.infrastructure.util.DownloadUtil;
import com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableAddDTO;
import com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableDeleteDTO;
import com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableSearchDTO;
import com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableUpdateDTO;
import com.izpan.modules.tools.domain.vo.ToolGeneratorTableVO;
import com.izpan.modules.tools.facade.IToolGeneratorTableFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 代码生成表管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.tools.ToolGeneratorTableController
 * @CreateTime 2024-08-26 - 16:14:53
 */

@RestController
@Tag(name = "代码生成表管理")
@RequiredArgsConstructor
@RequestMapping("/tool_generator_table")
public class ToolGeneratorTableController {

    @NonNull
    private IToolGeneratorTableFacade toolGeneratorTableFacade;

    @GetMapping("/page")
    @SaCheckPermission("tools:generator:table:page")
    @Operation(operationId = "1", summary = "获取代码生成表管理列表")
    public Result<RPage<ToolGeneratorTableVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                                    @Parameter(description = "查询对象") ToolGeneratorTableSearchDTO toolGeneratorTableSearchDTO) {
        return Result.data(toolGeneratorTableFacade.listToolGeneratorTablePage(pageQuery, toolGeneratorTableSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("tools:generator:table:get")
    @Operation(operationId = "2", summary = "根据ID获取代码生成表管理详细信息")
    public Result<ToolGeneratorTableVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(toolGeneratorTableFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("tools:generator:table:add")
    @Operation(operationId = "3", summary = "新增代码生成表管理")
    public Result<ToolGeneratorTableVO> add(@Parameter(description = "新增对象") @RequestBody ToolGeneratorTableAddDTO toolGeneratorTableAddDTO) {
        return Result.data(toolGeneratorTableFacade.add(toolGeneratorTableAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("tools:generator:table:update")
    @Operation(operationId = "4", summary = "更新代码生成表管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody ToolGeneratorTableUpdateDTO toolGeneratorTableUpdateDTO) {
        return Result.status(toolGeneratorTableFacade.update(toolGeneratorTableUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("tools:generator:table:delete")
    @Operation(operationId = "5", summary = "批量删除代码生成表管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody ToolGeneratorTableDeleteDTO toolGeneratorTableDeleteDTO) {
        return Result.status(toolGeneratorTableFacade.batchDelete(toolGeneratorTableDeleteDTO));
    }

    @SaIgnore
    @PostMapping("/zip/{id}")
    @SaCheckPermission("tools:generator:table:zip")
    @Operation(operationId = "6", summary = "代码生成压缩包")
    public void zipCodeGenerate(HttpServletResponse response, @Parameter(description = "ID") @PathVariable("id") Long tableId) {
        byte[] bytes = toolGeneratorTableFacade.zipCodeGenerate(tableId);
        DownloadUtil.binary(response, bytes, "generated-code.zip");
    }
}