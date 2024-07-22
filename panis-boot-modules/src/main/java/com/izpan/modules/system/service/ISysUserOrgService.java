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
import com.izpan.modules.system.domain.bo.SysUserOrgBO;
import com.izpan.modules.system.domain.entity.SysUserOrg;

import java.util.List;

/**
 * 用户组织/部门/子部门管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.ISysUserOrgService
 * @CreateTime 2024-07-16 - 16:35:30
 */

public interface ISysUserOrgService extends IService<SysUserOrg> {

    /**
     * 用户组织/部门/子部门管理 - 分页查询
     *
     * @param pageQuery    分页对象
     * @param sysUserOrgBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    IPage<SysUserOrg> listSysUserOrgPage(PageQuery pageQuery, SysUserOrgBO sysUserOrgBO);

    /**
     * 查询用户组织
     *
     * @param userId 用户id
     * @return {@link List }<{@link SysUserOrgBO }> 用户组织列表
     * @author payne.zhuang
     * @CreateTime 2024-07-21 - 18:22:14
     */
    List<SysUserOrgBO> queryOrgUnitsListWithUserId(Long userId);

    /**
     * 查询用户组织id
     *
     * @param userId 用户id
     * @return {@link List }<{@link Long }> 用户组织id集合
     * @author payne.zhuang
     * @CreateTime 2024-07-20 - 15:10:55
     */
    List<Long> queryOrgUnitsIdsWithUserId(Long userId);

    /**
     * 查询用户组织负责人
     *
     * @param userId 用户id
     * @return {@link List }<{@link Long }> 用户组织负责人id集合
     * @author payne.zhuang
     * @CreateTime 2024-07-21 - 18:23:42
     */
    List<Long> queryOrgUnitsPrincipalWithUserId(Long userId);

    /**
     * 更新用户组织
     *
     * @param userId       用户id
     * @param orgIds       组织id
     * @param principalIds 负责人id
     * @return {@link Boolean} 更新结果
     * @author payne.zhuang
     * @CreateTime 2024-07-20 - 23:19:25
     */
    boolean updateUserOrg(Long userId, List<Long> orgIds, List<Long> principalIds);

}
