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

package com.izpan.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysNoticeBO;
import com.izpan.modules.system.domain.entity.SysNotice;
import com.izpan.modules.system.repository.mapper.SysNoticeMapper;
import com.izpan.modules.system.service.ISysNoticeService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * 通知公告 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.impl.SysNoticeServiceImpl
 * @CreateTime 2024-11-10 - 12:55:52
 */

@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {

    @Override
    public IPage<SysNotice> listSysNoticePage(PageQuery pageQuery, SysNoticeBO sysNoticeBO) {
        LambdaQueryWrapper<SysNotice> queryWrapper = new LambdaQueryWrapper<SysNotice>()
                .eq(ObjectUtils.isNotEmpty(sysNoticeBO.getCategory()), SysNotice::getCategory, sysNoticeBO.getCategory())
                .like(ObjectUtils.isNotEmpty(sysNoticeBO.getTitle()), SysNotice::getTitle, sysNoticeBO.getTitle())
                .orderByDesc(SysNotice::getCreateTime);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

}

