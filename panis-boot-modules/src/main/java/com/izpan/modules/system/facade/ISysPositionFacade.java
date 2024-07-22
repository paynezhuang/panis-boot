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

import com.izpan.common.domain.Options;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.position.SysPositionAddDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionDeleteDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionSearchDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionUpdateDTO;
import com.izpan.modules.system.domain.vo.SysPositionVO;

import java.util.List;

/**
 * 岗位管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysPositionFacade
 * @CreateTime 2024-06-27 - 22:03:29
 */

public interface ISysPositionFacade {

    /**
     * 岗位管理 - 分页查询
     *
     * @param pageQuery            分页对象
     * @param sysPositionSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    RPage<SysPositionVO> listSysPositionPage(PageQuery pageQuery, SysPositionSearchDTO sysPositionSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 岗位管理ID
     * @return {@link SysPositionVO} 岗位管理 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    SysPositionVO get(Long id);

    /**
     * 新增岗位管理
     *
     * @param sysPositionAddDTO 新增岗位管理 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean add(SysPositionAddDTO sysPositionAddDTO);

    /**
     * 编辑更新岗位管理信息
     *
     * @param sysPositionUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean update(SysPositionUpdateDTO sysPositionUpdateDTO);

    /**
     * 批量删除岗位管理信息
     *
     * @param sysPositionDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    boolean batchDelete(SysPositionDeleteDTO sysPositionDeleteDTO);

    /**
     * 获取所有岗位信息集合
     *
     * @return {@link List }<{@link Options }<{@link Long }>> 岗位信息集合
     * @author payne.zhuang
     * @CreateTime 2024-07-19 - 18:34:31
     */
    List<Options<Long>> queryAllPositionListConvertOptions();

}