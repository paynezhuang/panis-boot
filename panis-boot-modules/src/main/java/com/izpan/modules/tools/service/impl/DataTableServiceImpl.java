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

package com.izpan.modules.tools.service.impl;

import com.izpan.modules.tools.domain.bo.TableColumnBO;
import com.izpan.modules.tools.domain.entity.DataTable;
import com.izpan.modules.tools.domain.entity.TableColumn;
import com.izpan.modules.tools.repository.mapper.DataTableMapper;
import com.izpan.modules.tools.service.IDataTableService;
import com.izpan.starter.code.generator.constants.GeneratorConstants;
import com.izpan.starter.common.util.StringUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 数据库表管理 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.service.impl.DataTableServiceImpl
 * @CreateTime 2024/8/22 - 16:51
 */

@Service
@RequiredArgsConstructor
public class DataTableServiceImpl implements IDataTableService {

    @NonNull
    private DataTableMapper dataTableMapper;

    @Override
    public List<DataTable> queryAllDataTables(String tableName) {
        return dataTableMapper.queryAllDataTables(tableName);
    }

    @Override
    public List<TableColumnBO> queryTableColumns(String tableName) {
        List<TableColumn> tableColumnList = dataTableMapper.queryTableColumns(tableName);
        List<String> strings = Arrays.asList(GeneratorConstants.IGNORE_COLUMNS);
        List<TableColumn> collect = tableColumnList.stream()
                .filter(tableColumn -> !strings.contains(tableColumn.getColumnName())).toList();
        return collect.stream().map(this::transformTableColumn).toList();
    }

    /**
     * 转换表列
     *
     * @param tableColumn 表列
     * @return {@link TableColumnBO } 表列视图对象
     * @author payne.zhuang
     * @CreateTime 2024-09-02 - 16:45:05
     */
    @SuppressWarnings("java:S3252")
    public TableColumnBO transformTableColumn(TableColumn tableColumn) {
        // 移除 is_ 前缀
        String removePrefix = StringUtil.removeIsPrefix(tableColumn.getColumnName());
        String javaType = StringUtil.convertMySQLTypeToJavaType(tableColumn.getDataType());
        return TableColumnBO.builder()
                .columnName(tableColumn.getColumnName())
                .propertyName(StringUtil.toLowerCamel(removePrefix))
                .columnComment(tableColumn.getColumnComment())
                .ordinalPosition(tableColumn.getOrdinalPosition())
                .dataType(tableColumn.getDataType())
                .javaType(javaType)
                .typeScriptType(StringUtil.convertJavaTypeToTypeScriptType(javaType))
                .characterMaximumLength(tableColumn.getCharacterMaximumLength())
                .build();
    }

}
