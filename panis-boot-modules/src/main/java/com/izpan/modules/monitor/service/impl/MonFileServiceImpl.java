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

package com.izpan.modules.monitor.service.impl;

import com.izpan.modules.monitor.domain.bo.MonFileBO;
import com.izpan.modules.monitor.domain.entity.MonFile;
import com.izpan.modules.monitor.repository.mapper.MonFileMapper;
import com.izpan.modules.monitor.service.IMonFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.infrastructure.page.PageQuery;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 文件管理 Service 服务接口实现层
 *
 * @Author monitor
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.service.impl.MonFileServiceImpl
 * @CreateTime 2024-11-20 - 17:16:20
 */

@Service
public class MonFileServiceImpl extends ServiceImpl<MonFileMapper, MonFile> implements IMonFileService {

    @Override
    public IPage<MonFile> listMonFilePage(PageQuery pageQuery, MonFileBO monFileBO) {
        LambdaQueryWrapper<MonFile> queryWrapper = new LambdaQueryWrapper<MonFile>()
            .like(ObjectUtils.isNotEmpty(monFileBO.getOrderNo()), MonFile::getOrderNo, monFileBO.getOrderNo())
            .eq(ObjectUtils.isNotEmpty(monFileBO.getCategory()), MonFile::getCategory, monFileBO.getCategory())
            .eq(ObjectUtils.isNotEmpty(monFileBO.getLocation()), MonFile::getLocation, monFileBO.getLocation())
            .like(ObjectUtils.isNotEmpty(monFileBO.getName()), MonFile::getName, monFileBO.getName()).orderByDesc(MonFile::getCreateTime);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

}

