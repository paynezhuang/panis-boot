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

package com.izpan.modules.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.monitor.domain.bo.MonFileBO;
import com.izpan.modules.monitor.domain.entity.MonFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件管理 Service 服务接口层
 *
 * @Author monitor
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.service.IMonFileService
 * @CreateTime 2024-11-20 - 17:16:20
 */

public interface IMonFileService extends IService<MonFile> {

    /**
     * 文件管理 - 分页查询
     *
     * @param pageQuery 分页对象
     * @param monFileBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-11-20 - 17:16:20
     */
    IPage<MonFile> listMonFilePage(PageQuery pageQuery, MonFileBO monFileBO);

    /**
     * 文件管理 - 新增
     *
     * @param file 文件
     * @return {@link boolean} 是否成功
     * @author payne.zhuang
     * @CreateTime 2024-11-25 - 21:19:40
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

    /**
     * 同步删除 OSS 文件
     *
     * @param ids IDs 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-26 - 19:04:03
     */
    void syncDeleteWithOSS(List<Long> ids);
}
