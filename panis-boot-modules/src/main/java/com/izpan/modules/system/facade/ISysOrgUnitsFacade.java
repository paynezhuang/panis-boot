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
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsAddDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsDeleteDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsSearchDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsUpdateDTO;
import com.izpan.modules.system.domain.vo.SysOrgUnitsTreeVO;
import com.izpan.modules.system.domain.vo.SysOrgUnitsVO;

import java.util.List;

/**
 * 组织/部门/子部门管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysOrgUnitsFacade
 * @CreateTime 2024-07-16 - 16:35:30
 */

public interface ISysOrgUnitsFacade {

    /**
     * 组织/部门/子部门管理 - 分页查询
     *
     * @param pageQuery            分页对象
     * @param sysOrgUnitsSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    RPage<SysOrgUnitsTreeVO> listSysOrgUnitsPage(PageQuery pageQuery, SysOrgUnitsSearchDTO sysOrgUnitsSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 组织/部门/子部门管理ID
     * @return {@link SysOrgUnitsVO} 组织/部门/子部门管理 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    SysOrgUnitsVO get(Long id);

    /**
     * 新增组织/部门/子部门管理
     *
     * @param sysOrgUnitsAddDTO 新增组织/部门/子部门管理 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    boolean add(SysOrgUnitsAddDTO sysOrgUnitsAddDTO);

    /**
     * 编辑更新组织/部门/子部门管理信息
     *
     * @param sysOrgUnitsUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    boolean update(SysOrgUnitsUpdateDTO sysOrgUnitsUpdateDTO);

    /**
     * 批量删除组织/部门/子部门管理信息
     *
     * @param sysOrgUnitsDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    boolean batchDelete(SysOrgUnitsDeleteDTO sysOrgUnitsDeleteDTO);

    /**
     * 查询所有组织/部门/子部门列表并转换为树形结构
     *
     * @return {@link List }<{@link SysOrgUnitsTreeVO }> 组织/部门/子部门管理树形结构对象集合
     * @author payne.zhuang
     * @CreateTime 2024-07-11 - 10:31:23
     */
    List<SysOrgUnitsTreeVO> queryAllOrgUnitsListConvertToTree();

}