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
import com.izpan.common.domain.Options;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.bo.SysPositionBO;
import com.izpan.modules.system.domain.dto.position.SysPositionAddDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionDeleteDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionSearchDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionUpdateDTO;
import com.izpan.modules.system.domain.entity.SysPosition;
import com.izpan.modules.system.domain.vo.SysPositionVO;
import com.izpan.modules.system.facade.ISysPositionFacade;
import com.izpan.modules.system.service.ISysPositionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 岗位管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysPositionFacadeImpl
 * @CreateTime 2024-06-27 - 22:03:29
 */

@Service
@RequiredArgsConstructor
public class SysPositionFacadeImpl implements ISysPositionFacade {

    @NonNull
    private ISysPositionService sysPositionService;

    @Override
    public RPage<SysPositionVO> listSysPositionPage(PageQuery pageQuery, SysPositionSearchDTO sysPositionSearchDTO) {
        SysPositionBO sysPositionBO = CglibUtil.convertObj(sysPositionSearchDTO, SysPositionBO::new);
        IPage<SysPosition> sysPositionIPage = sysPositionService.listSysPositionPage(pageQuery, sysPositionBO);
        return RPage.build(sysPositionIPage, SysPositionVO::new);
    }

    @Override
    public SysPositionVO get(Long id) {
        SysPosition byId = sysPositionService.getById(id);
        return CglibUtil.convertObj(byId, SysPositionVO::new);
    }

    @Override
    @Transactional
    public boolean add(SysPositionAddDTO sysPositionAddDTO) {
        SysPositionBO sysPositionBO = CglibUtil.convertObj(sysPositionAddDTO, SysPositionBO::new);
        return sysPositionService.save(sysPositionBO);
    }

    @Override
    @Transactional
    public boolean update(SysPositionUpdateDTO sysPositionUpdateDTO) {
        SysPositionBO sysPositionBO = CglibUtil.convertObj(sysPositionUpdateDTO, SysPositionBO::new);
        return sysPositionService.updateById(sysPositionBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysPositionDeleteDTO sysPositionDeleteDTO) {
        SysPositionBO sysPositionBO = CglibUtil.convertObj(sysPositionDeleteDTO, SysPositionBO::new);
        return sysPositionService.removeBatchByIds(sysPositionBO.getIds(), true);
    }

    @Override
    public List<Options<Long>> queryAllPositionListConvertOptions() {
        List<SysPositionBO> allRole = sysPositionService.queryAllPositionList();
        return allRole.stream()
                .map(item -> Options.<Long>builder()
                        .label(item.getName())
                        .value(item.getId())
                        .build())
                .toList();
    }

}