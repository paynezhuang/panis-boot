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

package com.izpan.modules.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izpan.modules.system.domain.entity.SysUserOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户组织/部门/子部门管理 Mapper 接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.repository.mapper.SysUserOrgMapper
 * @CreateTime 2024-07-16 - 16:35:30
 */

public interface SysUserOrgMapper extends BaseMapper<SysUserOrg> {

    /**
     * 根据用户id查询用户组织
     *
     * @param userId 用户id
     * @return {@link List }<{@link SysUserOrg }> 用户组织列表
     * @author payne.zhuang
     * @CreateTime 2024-07-20 - 15:15:01
     */
    List<SysUserOrg> listUserOrgWithUserId(@Param("userId") Long userId);

    /**
     * 更新用户组织负责人
     *
     * @param userId 用户id
     * @param orgIds 组织id
     * @return {@link Boolean } 是否更新成功 true:成功 false:失败
     * @author payne.zhuang
     * @CreateTime 2024-07-22 - 11:35:13
     */
    boolean updatePrincipal(@Param("userId") Long userId, @Param("orgIds") Set<Long> orgIds);
}