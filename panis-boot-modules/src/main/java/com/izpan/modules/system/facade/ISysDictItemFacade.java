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

package com.izpan.modules.system.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.dict.item.*;
import com.izpan.modules.system.domain.vo.SysDictItemOptionsVO;
import com.izpan.modules.system.domain.vo.SysDictItemVO;

import java.util.List;
import java.util.Map;

/**
 * 数据字典子项管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysDictItemFacade
 * @CreateTime 2024-06-27 - 22:03:29
 */

public interface ISysDictItemFacade {

    /**
     * 数据字典子项管理 - 分页查询
     *
     * @param pageQuery            分页对象
     * @param sysDictItemSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    RPage<SysDictItemVO> listSysDictItemPage(PageQuery pageQuery, SysDictItemSearchDTO sysDictItemSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 数据字典子项管理ID
     * @return {@link SysDictItemVO} 数据字典子项管理 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    SysDictItemVO get(Long id);

    /**
     * 新增数据字典子项管理
     *
     * @param sysDictItemAddDTO 新增数据字典子项管理 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean add(SysDictItemAddDTO sysDictItemAddDTO);

    /**
     * 编辑更新数据字典子项管理信息
     *
     * @param sysDictItemUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean update(SysDictItemUpdateDTO sysDictItemUpdateDTO);

    /**
     * 批量删除数据字典子项管理信息
     *
     * @param sysDictItemDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean batchDelete(SysDictItemDeleteDTO sysDictItemDeleteDTO);

    /**
     * 查询所有的数据字典子项 Map 结构
     *
     * @return {@link Map }<{@link String }, {@link List }<{@link SysDictItemOptionsVO }>> 查询结果集合
     * @author payne.zhuang
     * @CreateTime 2024-08-01 - 23:44:12
     */
    Map<String, List<SysDictItemOptionsVO>> queryAllDictItemMap();

    /**
     * 查询字典项Map集合
     *
     * @param searchDTO 查询对象
     * @return @link Map }<{@link String }, {@link List }<{@link SysDictItemOptionsVO }>> 查询结果集合
     * @author payne.zhuang
     * @CreateTime 2024-07-27 - 18:24:03
     */
    Map<String, List<SysDictItemOptionsVO>> queryDictItemMapOptions(SysDictItemStoreSearchDTO searchDTO);

}