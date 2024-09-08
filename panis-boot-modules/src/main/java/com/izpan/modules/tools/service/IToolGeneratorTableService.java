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
import com.izpan.modules.tools.domain.bo.ToolGeneratorTableBO;
import com.izpan.modules.tools.domain.entity.ToolGeneratorTable;

import java.io.ByteArrayOutputStream;

/**
 * 代码生成表管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.service.IToolGeneratorTableService
 * @CreateTime 2024-08-26 - 16:14:53
 */

public interface IToolGeneratorTableService extends IService<ToolGeneratorTable> {

    /**
     * 代码生成表管理 - 分页查询
     *
     * @param pageQuery            分页对象
     * @param toolGeneratorTableBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-08-26 - 16:14:53
     */
    IPage<ToolGeneratorTable> listToolGeneratorTablePage(PageQuery pageQuery, ToolGeneratorTableBO toolGeneratorTableBO);

    /**
     * 代码生成，返回zip文件流
     *
     * @param tableId 表id
     * @return {@link ByteArrayOutputStream } zip 压缩包文件流
     * @author payne.zhuang
     * @CreateTime 2024-09-05 - 12:06:09
     */
    ByteArrayOutputStream zipCodeGenerator(Long tableId);

}
