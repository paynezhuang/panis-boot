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
import com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableAddDTO;
import com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableDeleteDTO;
import com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableSearchDTO;
import com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableUpdateDTO;
import com.izpan.modules.tools.domain.vo.ToolGeneratorTableVO;

/**
 * 代码生成表管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.facade.IToolGeneratorTableFacade
 * @CreateTime 2024-08-26 - 16:14:53
 */

public interface IToolGeneratorTableFacade {

    /**
     * 代码生成表管理 - 分页查询
     *
     * @param pageQuery                   分页对象
     * @param toolGeneratorTableSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 16:14:53
     */
    RPage<ToolGeneratorTableVO> listToolGeneratorTablePage(PageQuery pageQuery, ToolGeneratorTableSearchDTO toolGeneratorTableSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 代码生成表管理ID
     * @return {@link ToolGeneratorTableVO} 代码生成表管理 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 16:14:53
     */
    ToolGeneratorTableVO get(Long id);

    /**
     * 新增代码生成表管理
     *
     * @param toolGeneratorTableAddDTO 新增代码生成表管理 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 16:14:53
     */
    ToolGeneratorTableVO add(ToolGeneratorTableAddDTO toolGeneratorTableAddDTO);

    /**
     * 编辑更新代码生成表管理信息
     *
     * @param toolGeneratorTableUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 16:14:53
     */
    boolean update(ToolGeneratorTableUpdateDTO toolGeneratorTableUpdateDTO);

    /**
     * 批量删除代码生成表管理信息
     *
     * @param toolGeneratorTableDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 16:14:53
     */
    boolean batchDelete(ToolGeneratorTableDeleteDTO toolGeneratorTableDeleteDTO);

    /**
     * 代码生成后生成压缩包返回文件流
     *
     * @param tableId 表id
     * @return {@link byte[] } 文件流数组
     * @author payne.zhuang
     * @CreateTime 2024-09-05 - 11:38:43
     */
    byte[] zipCodeGenerate(Long tableId);

}