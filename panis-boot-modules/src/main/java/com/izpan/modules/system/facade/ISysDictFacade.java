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
import com.izpan.modules.system.domain.dto.dict.SysDictAddDTO;
import com.izpan.modules.system.domain.dto.dict.SysDictDeleteDTO;
import com.izpan.modules.system.domain.dto.dict.SysDictSearchDTO;
import com.izpan.modules.system.domain.dto.dict.SysDictUpdateDTO;
import com.izpan.modules.system.domain.vo.SysDictVO;

import java.util.List;

/**
 * 数据字典管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysDictFacade
 * @CreateTime 2024-06-27 - 22:03:29
 */

public interface ISysDictFacade {

    /**
     * 数据字典管理 - 分页查询
     *
     * @param pageQuery        分页对象
     * @param sysDictSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    RPage<SysDictVO> listSysDictPage(PageQuery pageQuery, SysDictSearchDTO sysDictSearchDTO);


    /**
     * 数据字典管理 - 列表查询
     *
     * @param sysDictSearchDTO 搜索dto
     * @return {@link List }<{@link SysDictVO }> 数据字典列表
     * @author payne.zhuang <payne.zhuang@gmail.com>
     * @CreateTime 2024-06-29 - 14:48:46
     */
    List<SysDictVO> listSysDict(SysDictSearchDTO sysDictSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 数据字典管理ID
     * @return {@link SysDictVO} 数据字典管理 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    SysDictVO get(Long id);

    /**
     * 新增数据字典管理
     *
     * @param sysDictAddDTO 新增数据字典管理 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean add(SysDictAddDTO sysDictAddDTO);

    /**
     * 编辑更新数据字典管理信息
     *
     * @param sysDictUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean update(SysDictUpdateDTO sysDictUpdateDTO);

    /**
     * 批量删除数据字典管理信息
     *
     * @param sysDictDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean batchDelete(SysDictDeleteDTO sysDictDeleteDTO);

}