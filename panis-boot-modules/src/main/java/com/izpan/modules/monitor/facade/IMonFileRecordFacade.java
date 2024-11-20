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

package com.izpan.modules.monitor.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordAddDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordDeleteDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordSearchDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonFileRecordVO;

/**
 * 文件记录 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.facade.IMonFileRecordFacade
 * @CreateTime 2024-11-20 - 14:27:48
 */

public interface IMonFileRecordFacade {

    /**
     * 文件记录 - 分页查询
     *
     * @param pageQuery              分页对象
     * @param monFileRecordSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 14:27:48
     */
    RPage<MonFileRecordVO> listMonFileRecordPage(PageQuery pageQuery, MonFileRecordSearchDTO monFileRecordSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 文件记录ID
     * @return {@link MonFileRecordVO} 文件记录 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 14:27:48
     */
    MonFileRecordVO get(Long id);

    /**
     * 新增文件记录
     *
     * @param monFileRecordAddDTO 新增文件记录 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 14:27:48
     */
    boolean add(MonFileRecordAddDTO monFileRecordAddDTO);

    /**
     * 编辑更新文件记录信息
     *
     * @param monFileRecordUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 14:27:48
     */
    boolean update(MonFileRecordUpdateDTO monFileRecordUpdateDTO);

    /**
     * 批量删除文件记录信息
     *
     * @param monFileRecordDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 14:27:48
     */
    boolean batchDelete(MonFileRecordDeleteDTO monFileRecordDeleteDTO);

}