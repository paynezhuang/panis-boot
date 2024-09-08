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

package com.izpan.modules.tools.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.tools.domain.dto.generator.table.column.ToolGeneratorTableColumnAddDTO;
import com.izpan.modules.tools.domain.dto.generator.table.column.ToolGeneratorTableColumnDeleteDTO;
import com.izpan.modules.tools.domain.dto.generator.table.column.ToolGeneratorTableColumnSearchDTO;
import com.izpan.modules.tools.domain.dto.generator.table.column.ToolGeneratorTableColumnUpdateDTO;
import com.izpan.modules.tools.domain.vo.ToolGeneratorTableColumnVO;

import java.util.List;

/**
 * 代码生成表字段列管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.facade.IToolGeneratorTableColumnFacade
 * @CreateTime 2024-08-26 - 22:13:50
 */

public interface IToolGeneratorTableColumnFacade {

    /**
     * 代码生成表字段列管理 - 分页查询
     *
     * @param pageQuery                         分页对象
     * @param toolGeneratorTableColumnSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 22:13:50
     */
    RPage<ToolGeneratorTableColumnVO> listToolGeneratorTableColumnPage(PageQuery pageQuery, ToolGeneratorTableColumnSearchDTO toolGeneratorTableColumnSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 代码生成表字段列管理ID
     * @return {@link ToolGeneratorTableColumnVO} 代码生成表字段列管理 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 22:13:50
     */
    ToolGeneratorTableColumnVO get(Long id);

    /**
     * 新增代码生成表字段列管理
     *
     * @param toolGeneratorTableColumnAddDTO 新增代码生成表字段列管理 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 22:13:50
     */
    boolean add(ToolGeneratorTableColumnAddDTO toolGeneratorTableColumnAddDTO);

    /**
     * 编辑更新代码生成表字段列管理信息
     *
     * @param toolGeneratorTableColumnUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 22:13:50
     */
    boolean update(ToolGeneratorTableColumnUpdateDTO toolGeneratorTableColumnUpdateDTO);

    /**
     * 批量删除代码生成表字段列管理信息
     *
     * @param toolGeneratorTableColumnDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 22:13:50
     */
    boolean batchDelete(ToolGeneratorTableColumnDeleteDTO toolGeneratorTableColumnDeleteDTO);

    /**
     * 根据生成表ID查询表字段列信息
     *
     * @param tableId 生成表ID
     * @return {@link List }<{@link ToolGeneratorTableColumnVO }> 查询集合
     * @author payne.zhuang
     * @CreateTime 2024-09-02 - 18:21:19
     */
    List<ToolGeneratorTableColumnVO> queryListWithTableId(Long tableId);

    /**
     * 批量更新表字段列集合
     *
     * @param generatorTableColumnUpdateDTOList 数据列表
     * @return boolean
     * @author payne.zhuang
     * @CreateTime 2024-09-03 - 11:30:27
     */
    boolean batchUpdateTableColumnList(List<ToolGeneratorTableColumnUpdateDTO> generatorTableColumnUpdateDTOList);

    /**
     * 清空表字段列信息
     *
     * @param tableId 表id
     * @return {@link boolean }  是否清空成功
     * @author payne.zhuang
     * @CreateTime 2024-09-03 - 15:58:07
     */
    boolean cleanTableColumnList(Long tableId);

    /**
     * 同步表字段列信息，并返回完整的表字段列信息集合
     *
     * @param tableId 表id
     * @return {@link List }<{@link ToolGeneratorTableColumnVO }> 同步后，返回的表字段列信息集合
     * @author payne.zhuang
     * @CreateTime 2024-09-03 - 15:58:07
     */
    List<ToolGeneratorTableColumnVO> syncDataTableColumns(Long tableId);
}