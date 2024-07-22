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
import com.izpan.modules.system.domain.dto.user.org.SysUserOrgAddDTO;
import com.izpan.modules.system.domain.dto.user.org.SysUserOrgDeleteDTO;
import com.izpan.modules.system.domain.dto.user.org.SysUserOrgSearchDTO;
import com.izpan.modules.system.domain.dto.user.org.SysUserOrgUpdateDTO;
import com.izpan.modules.system.domain.vo.SysUserOrgVO;

/**
 * 用户组织/部门/子部门管理 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysUserOrgFacade
 * @CreateTime 2024-07-16 - 16:35:30
 */

public interface ISysUserOrgFacade {

    /**
     * 用户组织/部门/子部门管理 - 分页查询
     *
     * @param pageQuery           分页对象
     * @param sysUserOrgSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    RPage<SysUserOrgVO> listSysUserOrgPage(PageQuery pageQuery, SysUserOrgSearchDTO sysUserOrgSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 用户组织/部门/子部门管理ID
     * @return {@link SysUserOrgVO} 用户组织/部门/子部门管理 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    SysUserOrgVO get(Long id);

    /**
     * 新增用户组织/部门/子部门管理
     *
     * @param sysUserOrgAddDTO 新增用户组织/部门/子部门管理 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    boolean add(SysUserOrgAddDTO sysUserOrgAddDTO);

    /**
     * 编辑更新用户组织/部门/子部门管理信息
     *
     * @param sysUserOrgUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    boolean update(SysUserOrgUpdateDTO sysUserOrgUpdateDTO);

    /**
     * 批量删除用户组织/部门/子部门管理信息
     *
     * @param sysUserOrgDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 16:35:30
     */
    boolean batchDelete(SysUserOrgDeleteDTO sysUserOrgDeleteDTO);

}