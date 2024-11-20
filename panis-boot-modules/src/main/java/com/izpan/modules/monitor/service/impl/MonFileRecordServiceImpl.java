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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.monitor.domain.bo.MonFileRecordBO;
import com.izpan.modules.monitor.domain.entity.MonFileRecord;
import com.izpan.modules.monitor.repository.mapper.MonFileRecordMapper;
import com.izpan.modules.monitor.service.IMonFileRecordService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * 文件记录 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.service.impl.MonFileRecordServiceImpl
 * @CreateTime 2024-11-20 - 14:27:48
 */

@Service
public class MonFileRecordServiceImpl extends ServiceImpl<MonFileRecordMapper, MonFileRecord> implements IMonFileRecordService {

    @Override
    public IPage<MonFileRecord> listMonFileRecordPage(PageQuery pageQuery, MonFileRecordBO monFileRecordBO) {
        LambdaQueryWrapper<MonFileRecord> queryWrapper = new LambdaQueryWrapper<MonFileRecord>()
                .like(ObjectUtils.isNotEmpty(monFileRecordBO.getOrderNo()), MonFileRecord::getOrderNo, monFileRecordBO.getOrderNo())
                .eq(ObjectUtils.isNotEmpty(monFileRecordBO.getCategory()), MonFileRecord::getCategory, monFileRecordBO.getCategory())
                .like(ObjectUtils.isNotEmpty(monFileRecordBO.getName()), MonFileRecord::getName, monFileRecordBO.getName()).orderByDesc(MonFileRecord::getCreateTime);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

}

