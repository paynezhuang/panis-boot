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

package com.izpan.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysOrgUnitsBO;
import com.izpan.modules.system.domain.entity.SysOrgUnits;

import java.util.List;

/**
 * 组织/部门/子部门管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.ISysOrgUnitsService
 * @CreateTime 2024-07-16 - 16:35:30
 */

public interface ISysOrgUnitsService extends IService<SysOrgUnits> {

    /**
     * 组织/部门/子部门管理 - 分页查询
     *
     * @param pageQuery     分页对象
     * @param sysOrgUnitsBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    IPage<SysOrgUnits> listSysOrgUnitsPage(PageQuery pageQuery, SysOrgUnitsBO sysOrgUnitsBO);

    /**
     * 查询所有子组织
     *
     * @param parentIds 父id
     * @return {@link List }<{@link SysOrgUnits }> 子组织列表
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 09:21:02
     */
    List<SysOrgUnits> listAllDescendants(List<Long> parentIds);

    /**
     * 查询组织/部门/子部门列表
     *
     * @param status 状态
     * @return {@link IPage }<{@link SysOrgUnits }> 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-07-11 - 10:39:02
     */
    List<SysOrgUnits> querySysOrgUnitsListWithStatus(String status);

}
