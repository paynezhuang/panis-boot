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
import com.izpan.modules.monitor.domain.dto.file.MonFileAddDTO;
import com.izpan.modules.monitor.domain.dto.file.MonFileDeleteDTO;
import com.izpan.modules.monitor.domain.dto.file.MonFileSearchDTO;
import com.izpan.modules.monitor.domain.dto.file.MonFileUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonFileVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理 门面接口层
 *
 * @Author monitor
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.facade.IMonFileFacade
 * @CreateTime 2024-11-20 - 17:16:20
 */

public interface IMonFileFacade {

    /**
     * 文件管理 - 分页查询
     *
     * @param pageQuery        分页对象
     * @param monFileSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 17:16:20
     */
    RPage<MonFileVO> listMonFilePage(PageQuery pageQuery, MonFileSearchDTO monFileSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 文件管理ID
     * @return {@link MonFileVO} 文件管理 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 17:16:20
     */
    MonFileVO get(Long id);

    /**
     * 新增文件管理
     *
     * @param monFileAddDTO 新增文件管理 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 17:16:20
     */
    boolean add(MonFileAddDTO monFileAddDTO);

    /**
     * 编辑更新文件管理信息
     *
     * @param monFileUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 17:16:20
     */
    boolean update(MonFileUpdateDTO monFileUpdateDTO);

    /**
     * 批量删除文件管理信息
     *
     * @param monFileDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 17:16:20
     */
    boolean batchDelete(MonFileDeleteDTO monFileDeleteDTO);

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-11-25 - 21:18:48
     */
    boolean putFile(MultipartFile file);

    /**
     * 获取文件外链链接
     *
     * @param id ID
     * @return {@link String } 文件外链链接
     * @author payne.zhuang
     * @CreateTime 2024-11-26 - 22:08:25
     */
    String preview(Long id);
}