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
import com.izpan.modules.system.domain.dto.notice.SysNoticeAddDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeDeleteDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeSearchDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeUpdateDTO;
import com.izpan.modules.system.domain.vo.SysNoticeVO;

/**
 * 通知公告 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.ISysNoticeFacade
 * @CreateTime 2024-11-10 - 12:55:52
 */

public interface ISysNoticeFacade {

    /**
     * 通知公告 - 分页查询
     *
     * @param pageQuery          分页对象
     * @param sysNoticeSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-11-10 - 12:55:52
     */
    RPage<SysNoticeVO> listSysNoticePage(PageQuery pageQuery, SysNoticeSearchDTO sysNoticeSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 通知公告ID
     * @return {@link SysNoticeVO} 通知公告 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-11-10 - 12:55:52
     */
    SysNoticeVO get(Long id);

    /**
     * 新增通知公告
     *
     * @param sysNoticeAddDTO 新增通知公告 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-10 - 12:55:52
     */
    boolean add(SysNoticeAddDTO sysNoticeAddDTO);

    /**
     * 编辑更新通知公告信息
     *
     * @param sysNoticeUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-10 - 12:55:52
     */
    boolean update(SysNoticeUpdateDTO sysNoticeUpdateDTO);

    /**
     * 批量删除通知公告信息
     *
     * @param sysNoticeDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-10 - 12:55:52
     */
    boolean batchDelete(SysNoticeDeleteDTO sysNoticeDeleteDTO);

}