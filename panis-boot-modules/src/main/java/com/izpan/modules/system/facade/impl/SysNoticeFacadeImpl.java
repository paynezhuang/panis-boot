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

package com.izpan.modules.system.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.bo.SysNoticeBO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeAddDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeDeleteDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeSearchDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeUpdateDTO;
import com.izpan.modules.system.domain.entity.SysNotice;
import com.izpan.modules.system.domain.vo.SysNoticeVO;
import com.izpan.modules.system.facade.ISysNoticeFacade;
import com.izpan.modules.system.service.ISysNoticeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通知公告 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysNoticeFacadeImpl
 * @CreateTime 2024-11-10 - 12:55:52
 */

@Service
@RequiredArgsConstructor
public class SysNoticeFacadeImpl implements ISysNoticeFacade {

    @NonNull
    private ISysNoticeService sysNoticeService;

    @Override
    public RPage<SysNoticeVO> listSysNoticePage(PageQuery pageQuery, SysNoticeSearchDTO sysNoticeSearchDTO) {
        SysNoticeBO sysNoticeBO = CglibUtil.convertObj(sysNoticeSearchDTO, SysNoticeBO::new);
        IPage<SysNotice> sysNoticeIPage = sysNoticeService.listSysNoticePage(pageQuery, sysNoticeBO);
        return RPage.build(sysNoticeIPage, SysNoticeVO::new);
    }

    @Override
    public SysNoticeVO get(Long id) {
        SysNotice byId = sysNoticeService.getById(id);
        return CglibUtil.convertObj(byId, SysNoticeVO::new);
    }

    @Override
    @Transactional
    public boolean add(SysNoticeAddDTO sysNoticeAddDTO) {
        SysNoticeBO sysNoticeBO = CglibUtil.convertObj(sysNoticeAddDTO, SysNoticeBO::new);
        return sysNoticeService.save(sysNoticeBO);
    }

    @Override
    @Transactional
    public boolean update(SysNoticeUpdateDTO sysNoticeUpdateDTO) {
        SysNoticeBO sysNoticeBO = CglibUtil.convertObj(sysNoticeUpdateDTO, SysNoticeBO::new);
        return sysNoticeService.updateById(sysNoticeBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysNoticeDeleteDTO sysNoticeDeleteDTO) {
        SysNoticeBO sysNoticeBO = CglibUtil.convertObj(sysNoticeDeleteDTO, SysNoticeBO::new);
        return sysNoticeService.removeBatchByIds(sysNoticeBO.getIds(), true);
    }

}