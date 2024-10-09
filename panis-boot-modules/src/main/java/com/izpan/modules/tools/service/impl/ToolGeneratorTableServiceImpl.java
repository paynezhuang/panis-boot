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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.common.exception.BizException;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.domain.BaseEntity;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.tools.domain.bo.ToolGeneratorTableBO;
import com.izpan.modules.tools.domain.entity.ToolGeneratorTable;
import com.izpan.modules.tools.domain.entity.ToolGeneratorTableColumn;
import com.izpan.modules.tools.repository.mapper.ToolGeneratorTableMapper;
import com.izpan.modules.tools.service.IToolGeneratorTableColumnService;
import com.izpan.modules.tools.service.IToolGeneratorTableService;
import com.izpan.starter.code.generator.config.GeneratorConfig;
import com.izpan.starter.code.generator.entity.TableColumn;
import com.izpan.starter.code.generator.service.CodeGeneratorService;
import com.izpan.starter.common.util.ZipUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 代码生成表管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.service.impl.ToolGeneratorTableServiceImpl
 * @CreateTime 2024-08-26 - 16:14:53
 */

@Service
@RequiredArgsConstructor
public class ToolGeneratorTableServiceImpl extends ServiceImpl<ToolGeneratorTableMapper, ToolGeneratorTable> implements IToolGeneratorTableService {

    @Resource
    private DataSource dataSource;

    @Resource
    private IToolGeneratorTableColumnService toolGeneratorTableColumnService;

    @Override
    public IPage<ToolGeneratorTable> listToolGeneratorTablePage(PageQuery pageQuery, ToolGeneratorTableBO toolGeneratorTableBO) {
        LambdaQueryWrapper<ToolGeneratorTable> queryWrapper = new LambdaQueryWrapper<ToolGeneratorTable>()
                .eq(ObjectUtils.isNotEmpty(toolGeneratorTableBO.getTableName()), ToolGeneratorTable::getTableName, toolGeneratorTableBO.getTableName());
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

    @Override
    public ByteArrayOutputStream zipCodeGenerator(Long tableId) {
        // 去生成代码
        GeneratorConfig config = codeGeneratorById(tableId);
        // 进行压缩，返回流
        try (ByteArrayOutputStream zipFileAsStream = ZipUtil.createZipFileAsStream(config.getOutPutDir())) {
            return zipFileAsStream;
        } catch (IOException e) {
            throw new BizException("生成失败，压缩文件失败，请核实");
        }
    }

    /**
     * 根据生成表 ID 进行代码生成
     *
     * @param tableId 生成表ID
     * @return {@link GeneratorConfig } 生成配置
     * @author payne.zhuang
     * @CreateTime 2024-09-05 - 12:06:42
     */
    private GeneratorConfig codeGeneratorById(Long tableId) {
        // 查找表数据
        ToolGeneratorTable toolGeneratorTable = baseMapper.selectById(tableId);
        // 查找表列数据
        List<ToolGeneratorTableColumn> toolGeneratorTableColumns = toolGeneratorTableColumnService.queryListByTableId(tableId);
        if (toolGeneratorTableColumns.isEmpty()) {
            throw new BizException("生成失败，查找表[%s]列数据为空，请核实".formatted(toolGeneratorTable.getTableName()));
        }

        // 列属性转换
        List<TableColumn> tableColumnList = CglibUtil.convertList(toolGeneratorTableColumns, TableColumn::new);

        // 初始化配置
        GeneratorConfig config = GeneratorConfig.builder()
                .parentPackage(toolGeneratorTable.getParentPackage())
                .author(toolGeneratorTable.getAuthor())
                .tableColumnList(tableColumnList)
                .packageConfig(GeneratorConfig.Package.builder()
                        .module(toolGeneratorTable.getModuleName()).build())
                .strategyConfig(GeneratorConfig.Strategy.builder()
                        .tableName(toolGeneratorTable.getTableName())
                        .tablePrefix(toolGeneratorTable.getTablePrefix())
                        .superClass(BaseEntity.class)
                        .build())
                .injectionConfig(GeneratorConfig.Injection.builder().build())
                .build();

        // 生成代码
        CodeGeneratorService.create(dataSource, config);
        return config;
    }

}

