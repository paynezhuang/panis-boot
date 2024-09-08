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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.izpan.common.exception.BizException;
import com.izpan.common.pool.StringPools;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.tools.domain.bo.TableColumnBO;
import com.izpan.modules.tools.domain.bo.ToolGeneratorTableColumnBO;
import com.izpan.modules.tools.domain.entity.ToolGeneratorTable;
import com.izpan.modules.tools.domain.entity.ToolGeneratorTableColumn;
import com.izpan.modules.tools.repository.mapper.ToolGeneratorTableColumnMapper;
import com.izpan.modules.tools.service.IDataTableService;
import com.izpan.modules.tools.service.IToolGeneratorTableColumnService;
import com.izpan.modules.tools.service.IToolGeneratorTableService;
import com.izpan.starter.code.generator.constants.GeneratorConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 代码生成表字段列管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.service.impl.ToolGeneratorTableColumnServiceImpl
 * @CreateTime 2024-08-26 - 22:13:50
 */

@Slf4j
@Service
public class ToolGeneratorTableColumnServiceImpl extends ServiceImpl<ToolGeneratorTableColumnMapper, ToolGeneratorTableColumn> implements IToolGeneratorTableColumnService {

    @Resource
    private IToolGeneratorTableService toolGeneratorTableService;

    @Resource
    private IDataTableService dataTableService;

    @Override
    public IPage<ToolGeneratorTableColumn> listToolGeneratorTableColumnPage(PageQuery pageQuery, ToolGeneratorTableColumnBO toolGeneratorTableColumnBO) {
        LambdaQueryWrapper<ToolGeneratorTableColumn> queryWrapper = new LambdaQueryWrapper<ToolGeneratorTableColumn>()
                .eq(ObjectUtils.isNotEmpty(toolGeneratorTableColumnBO.getColumnName()), ToolGeneratorTableColumn::getColumnName, toolGeneratorTableColumnBO.getColumnName());
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

    @Override
    public boolean save(ToolGeneratorTableColumn entity) {
        LambdaQueryWrapper<ToolGeneratorTableColumn> queryWrapper = new LambdaQueryWrapper<ToolGeneratorTableColumn>()
                .eq(ToolGeneratorTableColumn::getTableId, entity.getTableId())
                .eq(ToolGeneratorTableColumn::getColumnName, entity.getColumnName());
        if (baseMapper.selectCount(queryWrapper) >= 1L) {
            throw new BizException("新增失败，当前表[%s]配置已存在字段[%s]".formatted(entity.getTableName(), entity.getColumnName()));
        }
        return super.save(entity);
    }

    @Override
    public List<ToolGeneratorTableColumn> queryListByTableId(Long tableId) {
        // 1.优先查找字段表数据
        LambdaQueryWrapper<ToolGeneratorTableColumn> queryWrapper = new LambdaQueryWrapper<ToolGeneratorTableColumn>()
                .eq(ToolGeneratorTableColumn::getTableId, tableId)
                .orderByAsc(ToolGeneratorTableColumn::getOrdinalPosition);
        List<ToolGeneratorTableColumn> toolGeneratorTableColumns = baseMapper.selectList(queryWrapper);
        if (!toolGeneratorTableColumns.isEmpty()) {
            return toolGeneratorTableColumns;
        }
        // 2.查找实体数据库的字段信息
        ToolGeneratorTable generatorTable = toolGeneratorTableService.getById(tableId);
        List<TableColumnBO> tableColumnBOList = dataTableService.queryTableColumns(generatorTable.getTableName());
        toolGeneratorTableColumns = tableColumnBOList.stream().map(item -> build(generatorTable, item)).toList();
        // 保存至数据库
        super.saveBatch(toolGeneratorTableColumns);
        return toolGeneratorTableColumns;
    }

    @Override
    public boolean removeByTableIds(List<Long> tableIds) {
        LambdaUpdateWrapper<ToolGeneratorTableColumn> updateWrapper = new LambdaUpdateWrapper<ToolGeneratorTableColumn>()
                .set(ToolGeneratorTableColumn::getDeleted, true)
                .in(ToolGeneratorTableColumn::getTableId, tableIds);
        return baseMapper.update(updateWrapper) > 0;
    }

    @Override
    public boolean cleanTableColumnList(Long tableId) {
        LambdaQueryWrapper<ToolGeneratorTableColumn> queryWrapper = new LambdaQueryWrapper<ToolGeneratorTableColumn>()
                .eq(ToolGeneratorTableColumn::getTableId, tableId);
        List<ToolGeneratorTableColumn> toolGeneratorTableColumns = baseMapper.selectList(queryWrapper);
        if (toolGeneratorTableColumns.isEmpty()) {
            throw new BizException("当前字段列为空，无需清空");
        }
        List<Long> idsList = toolGeneratorTableColumns.stream().map(ToolGeneratorTableColumn::getId).toList();
        return super.removeBatchByIds(idsList, true);
    }

    @Override
    public boolean batchUpdateTableColumnList(List<ToolGeneratorTableColumn> generatorTableColumnList) {
        return super.updateBatchById(generatorTableColumnList);
    }

    @Override
    public List<ToolGeneratorTableColumn> syncDataTableColumns(Long tableId) {
        // 获取表数据
        ToolGeneratorTable generatorTable = toolGeneratorTableService.getById(tableId);
        // 查找表的所有列
        List<TableColumnBO> tableColumnBOList = dataTableService.queryTableColumns(generatorTable.getTableName());

        // 查找已有的表字段列信息
        LambdaQueryWrapper<ToolGeneratorTableColumn> queryWrapper = new LambdaQueryWrapper<ToolGeneratorTableColumn>()
                .eq(ToolGeneratorTableColumn::getTableId, tableId);
        List<ToolGeneratorTableColumn> toolGeneratorTableColumns = baseMapper.selectList(queryWrapper);

        // 提取已有字段列的column_name到Set中
        Set<String> existingColumnNames = toolGeneratorTableColumns.stream()
                .map(ToolGeneratorTableColumn::getColumnName)
                .collect(Collectors.toSet());

        // 新增的字段列
        List<ToolGeneratorTableColumn> newColumnsList = Lists.newArrayList();
        // 遍历表字段列信息，如果不存在则新增
        tableColumnBOList.forEach(tableColumn -> {
            if (!existingColumnNames.contains(tableColumn.getColumnName())) {
                ToolGeneratorTableColumn column = build(generatorTable, tableColumn);
                newColumnsList.add(column);
            }
        });

        // 保存新增的字段列
        baseMapper.insert(newColumnsList);
        // 返回同步后的字段列信息
        toolGeneratorTableColumns.addAll(newColumnsList);
        toolGeneratorTableColumns.sort(Comparator.comparing(ToolGeneratorTableColumn::getOrdinalPosition));

        return toolGeneratorTableColumns;
    }

    /**
     * 构建表列，填充默认值
     *
     * @param generatorTable 生成表
     * @param tableColumnBO  表列
     * @return {@link ToolGeneratorTableColumn }
     * @author payne.zhuang
     * @CreateTime 2024-09-04 - 17:19:43
     */
    private ToolGeneratorTableColumn build(ToolGeneratorTable generatorTable, TableColumnBO tableColumnBO) {
        ToolGeneratorTableColumn column = CglibUtil.convertObj(tableColumnBO, ToolGeneratorTableColumn::new);
        column.setTableId(generatorTable.getId());
        column.setTableName(generatorTable.getTableName());
        column.setList(StringPools.ONE);
        column.setI18n(StringPools.ONE);
        column.setRenderType(GeneratorConstants.INPUT);
        column.setStatus(StringPools.ONE);
        return column;
    }
}

