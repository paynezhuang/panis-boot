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

import com.izpan.modules.tools.domain.entity.DataTable;
import com.izpan.modules.tools.domain.vo.DataTableVO;

import java.util.List;

/**
 * 数据库表管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.facade.IDataTableFacade
 * @CreateTime 2024/8/22 - 16:48
 */

public interface IDataTableFacade {

    /**
     * 查询所有数据表
     *
     * @return {@link List }<{@link DataTable }> 数据表列表
     * @author payne.zhuang
     * @CreateTime 2024-08-22 - 20:04:36
     */
    List<DataTableVO> queryAllDataTables();

    /**
     * 查询所有数据表
     *
     * @param tableName 表名称
     * @return {@link List }<{@link DataTable }> 数据表列表
     * @author payne.zhuang
     * @CreateTime 2024-08-22 - 20:04:36
     */
    List<DataTableVO> queryAllDataTables(String tableName);

}
