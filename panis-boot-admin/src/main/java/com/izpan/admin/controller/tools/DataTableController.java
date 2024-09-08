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
import com.izpan.modules.tools.domain.vo.DataTableVO;
import com.izpan.modules.tools.domain.vo.TableColumnVO;
import com.izpan.modules.tools.facade.IDataTableFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据库表 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.tools.DataTableController
 * @CreateTime 2024/8/22 - 16:33
 */

@RestController
@Tag(name = "数据库表管理")
@RequiredArgsConstructor
@RequestMapping("/data_table")
public class DataTableController {

    @Resource
    private IDataTableFacade dataTableFacade;

    @GetMapping("/all_tables")
    @SaCheckPermission("tools:data:table:allTables")
    @Operation(operationId = "1", summary = "获取所有数据库表")
    public Result<List<DataTableVO>> allDataTable() {
        return Result.data(dataTableFacade.queryAllDataTables());
    }

    @Deprecated(since = "已弃用")
    @GetMapping("/table_columns/{tableName}")
    @SaCheckPermission("tools:data:table:columns")
    @Operation(operationId = "2", summary = "获取数据库表列信息")
    public Result<List<TableColumnVO>> queryTableColumns(@Parameter(description = "数据库表名") @PathVariable("tableName") String tableName) {
        return Result.data(dataTableFacade.queryTableColumns(tableName));
    }
}
