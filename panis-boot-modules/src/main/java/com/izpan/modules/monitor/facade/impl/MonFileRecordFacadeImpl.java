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

package com.izpan.modules.monitor.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.bo.MonFileRecordBO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordAddDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordDeleteDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordSearchDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordUpdateDTO;
import com.izpan.modules.monitor.domain.entity.MonFileRecord;
import com.izpan.modules.monitor.domain.vo.MonFileRecordVO;
import com.izpan.modules.monitor.facade.IMonFileRecordFacade;
import com.izpan.modules.monitor.service.IMonFileRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件记录 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName  com.izpan.modules.monitor.facade.impl.MonFileRecordFacadeImpl
 * @CreateTime 2024-11-20 - 14:27:48
 */

@Service
@RequiredArgsConstructor
public class MonFileRecordFacadeImpl implements IMonFileRecordFacade {

    @NonNull
    private IMonFileRecordService monFileRecordService;

    @Override
    public RPage<MonFileRecordVO> listMonFileRecordPage(PageQuery pageQuery, MonFileRecordSearchDTO monFileRecordSearchDTO) {
        MonFileRecordBO monFileRecordBO = CglibUtil.convertObj(monFileRecordSearchDTO, MonFileRecordBO::new);
        IPage<MonFileRecord> monFileRecordIPage = monFileRecordService.listMonFileRecordPage(pageQuery, monFileRecordBO);
        return RPage.build(monFileRecordIPage, MonFileRecordVO::new);
    }

    @Override
    public MonFileRecordVO get(Long id) {
        MonFileRecord byId = monFileRecordService.getById(id);
        return CglibUtil.convertObj(byId, MonFileRecordVO::new);
    }

    @Override
    @Transactional
    public boolean add(MonFileRecordAddDTO monFileRecordAddDTO) {
        MonFileRecordBO monFileRecordBO = CglibUtil.convertObj(monFileRecordAddDTO, MonFileRecordBO::new);
        return monFileRecordService.save(monFileRecordBO);
    }

    @Override
    @Transactional
    public boolean update(MonFileRecordUpdateDTO monFileRecordUpdateDTO) {
        MonFileRecordBO monFileRecordBO = CglibUtil.convertObj(monFileRecordUpdateDTO, MonFileRecordBO::new);
        return monFileRecordService.updateById(monFileRecordBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(MonFileRecordDeleteDTO monFileRecordDeleteDTO) {
        MonFileRecordBO monFileRecordBO = CglibUtil.convertObj(monFileRecordDeleteDTO, MonFileRecordBO::new);
        return monFileRecordService.removeBatchByIds(monFileRecordBO.getIds(), true);
    }

}