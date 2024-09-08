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

package com.izpan.modules.tools.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.tools.domain.bo.ToolGeneratorTableColumnBO;
import com.izpan.modules.tools.domain.entity.ToolGeneratorTableColumn;

import java.util.List;

/**
 * 代码生成表字段列管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.service.IToolGeneratorTableColumnService
 * @CreateTime 2024-08-26 - 22:13:50
 */

public interface IToolGeneratorTableColumnService extends IService<ToolGeneratorTableColumn> {

    /**
     * 代码生成表字段列管理 - 分页查询
     *
     * @param pageQuery                  分页对象
     * @param toolGeneratorTableColumnBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 22:13:50
     */
    IPage<ToolGeneratorTableColumn> listToolGeneratorTableColumnPage(PageQuery pageQuery, ToolGeneratorTableColumnBO toolGeneratorTableColumnBO);

    /**
     * 根据表id查询字段列列表
     *
     * @param tableId 表id
     * @return {@link List }<{@link ToolGeneratorTableColumn }> 字段列列表
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 22:19:21
     */
    List<ToolGeneratorTableColumn> queryListByTableId(Long tableId);

    /**
     * 根据表id删除字段列列表
     *
     * @param tableIds 表id集合
     * @return {@link Boolean} 是否成功删除
     * @author payne.zhuang
     * @CreateTime 2024-09-08 - 11:33:51
     */
    boolean removeByTableIds(List<Long> tableIds);

    /**
     * 批量更新字段列集合数据
     *
     * @param generatorTableColumnList 字段列集合
     * @return {@link Boolean} 是否成功批量更新字段列集合数据
     * @author payne.zhuang
     * @CreateTime 2024-09-03 - 11:31:34
     */
    boolean batchUpdateTableColumnList(List<ToolGeneratorTableColumn> generatorTableColumnList);

    /**
     * 清空表字段列列表
     *
     * @param tableId 表id
     * @return {@link Boolean} 是否成功清空表字段列列表
     * @author payne.zhuang
     * @CreateTime 2024-09-04 - 16:22:27
     */
    boolean cleanTableColumnList(Long tableId);

    /**
     * 同步数据表字段列，根据表id查询数据表字段列列表，同步到代码生成表字段列管理表
     *
     * @param tableId 表id
     * @return {@link List }<{@link ToolGeneratorTableColumn }> 字段列列表
     * @author payne.zhuang
     * @CreateTime 2024-09-03 - 15:59:19
     */
    List<ToolGeneratorTableColumn> syncDataTableColumns(Long tableId);
}
