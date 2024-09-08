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
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.tools.domain.dto.generator.table.column.ToolGeneratorTableColumnAddDTO;
import com.izpan.modules.tools.domain.dto.generator.table.column.ToolGeneratorTableColumnDeleteDTO;
import com.izpan.modules.tools.domain.dto.generator.table.column.ToolGeneratorTableColumnSearchDTO;
import com.izpan.modules.tools.domain.dto.generator.table.column.ToolGeneratorTableColumnUpdateDTO;
import com.izpan.modules.tools.domain.vo.ToolGeneratorTableColumnVO;
import com.izpan.modules.tools.facade.IToolGeneratorTableColumnFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代码生成表字段列管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.tools.ToolGeneratorTableColumnController
 * @CreateTime 2024-08-26 - 22:13:50
 */

@RestController
@Tag(name = "代码生成表字段列管理")
@RequiredArgsConstructor
@RequestMapping("/tool_generator_table_column")
public class ToolGeneratorTableColumnController {

    @NonNull
    private IToolGeneratorTableColumnFacade toolGeneratorTableColumnFacade;

    @GetMapping("/page")
    @SaCheckPermission("tools:generator:table:column:page")
    @Operation(operationId = "1", summary = "获取代码生成表字段列管理列表")
    public Result<RPage<ToolGeneratorTableColumnVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                                          @Parameter(description = "查询对象") ToolGeneratorTableColumnSearchDTO toolGeneratorTableColumnSearchDTO) {
        return Result.data(toolGeneratorTableColumnFacade.listToolGeneratorTableColumnPage(pageQuery, toolGeneratorTableColumnSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("tools:generator:table:column:get")
    @Operation(operationId = "2", summary = "根据ID获取代码生成表字段列管理详细信息")
    public Result<ToolGeneratorTableColumnVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(toolGeneratorTableColumnFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("tools:generator:table:column:add")
    @Operation(operationId = "3", summary = "新增代码生成表字段列管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody ToolGeneratorTableColumnAddDTO toolGeneratorTableColumnAddDTO) {
        return Result.status(toolGeneratorTableColumnFacade.add(toolGeneratorTableColumnAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("tools:generator:table:column:update")
    @Operation(operationId = "4", summary = "更新代码生成表字段列管理信息")
    public Result<Boolean> update(@Parameter(description = "表字段列集合") @RequestBody List<ToolGeneratorTableColumnUpdateDTO> generatorTableColumnUpdateDTOList) {
        return Result.status(toolGeneratorTableColumnFacade.batchUpdateTableColumnList(generatorTableColumnUpdateDTOList));
    }

    @DeleteMapping("/")
    @SaCheckPermission("tools:generator:table:column:delete")
    @Operation(operationId = "5", summary = "批量删除代码生成表字段列管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody ToolGeneratorTableColumnDeleteDTO toolGeneratorTableColumnDeleteDTO) {
        return Result.status(toolGeneratorTableColumnFacade.batchDelete(toolGeneratorTableColumnDeleteDTO));
    }

    @GetMapping("/list/{tableId}")
    @SaCheckPermission("tools:generator:table:column:list")
    @Operation(operationId = "6", summary = "根据生成表ID查询表字段列信息")
    public Result<List<ToolGeneratorTableColumnVO>> queryTableColumnListWithTableId(@Parameter(description = "生成表ID") @PathVariable("tableId") Long tableId) {
        return Result.data(toolGeneratorTableColumnFacade.queryListWithTableId(tableId));
    }

    @PutMapping("/clean_columns/{tableId}")
    @SaCheckPermission("tools:generator:table:column:clean:columns")
    @Operation(operationId = "7", summary = "清空表字段列信息")
    public Result<Boolean> cleanTableColumns(@Parameter(description = "生成表ID") @PathVariable("tableId") Long tableId) {
        return Result.status(toolGeneratorTableColumnFacade.cleanTableColumnList(tableId));
    }

    @PutMapping("/sync_columns/{tableId}")
    @SaCheckPermission("tools:generator:table:column:sync:columns")
    @Operation(operationId = "8", summary = "同步表字段列信息")
    public Result<List<ToolGeneratorTableColumnVO>> syncDataTableColumns(@Parameter(description = "生成表ID") @PathVariable("tableId") Long tableId) {
        return Result.data(toolGeneratorTableColumnFacade.syncDataTableColumns(tableId));
    }

}