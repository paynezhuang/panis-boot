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
import com.izpan.modules.system.domain.bo.SysUserPositionBO;
import com.izpan.modules.system.domain.entity.SysUserPosition;

import java.util.List;

/**
 * 用户岗位管理 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.ISysUserPositionService
 * @CreateTime 2024-06-27 - 22:03:29
 */

public interface ISysUserPositionService extends IService<SysUserPosition> {

    /**
     * 用户岗位管理 - 分页查询
     *
     * @param pageQuery         分页对象
     * @param sysUserPositionBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-06-27 - 22:03:29
     */
    IPage<SysUserPosition> listSysUserPositionPage(PageQuery pageQuery, SysUserPositionBO sysUserPositionBO);

    /**
     * 查询用户岗位id
     *
     * @param userId 用户id
     * @return {@link List }<{@link Long }> 用户岗位id集合
     * @author payne.zhuang
     * @CreateTime 2024-07-20 - 15:10:00
     */
    List<Long> queryPositionIdsWithUserId(Long userId);

    /**
     * 更新用户岗位
     *
     * @param userId      用户id
     * @param positionIds 岗位id集合
     * @return {@link Boolean} 更新结果
     * @author payne.zhuang
     * @CreateTime 2024-07-20 - 23:21:19
     */
    boolean updateUserPosition(Long userId, List<Long> positionIds);
}
