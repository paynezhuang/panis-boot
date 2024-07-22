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
import com.izpan.modules.system.domain.bo.SysUserPositionBO;
import com.izpan.modules.system.domain.dto.user.position.SysUserPositionAddDTO;
import com.izpan.modules.system.domain.dto.user.position.SysUserPositionDeleteDTO;
import com.izpan.modules.system.domain.dto.user.position.SysUserPositionSearchDTO;
import com.izpan.modules.system.domain.dto.user.position.SysUserPositionUpdateDTO;
import com.izpan.modules.system.domain.entity.SysUserPosition;
import com.izpan.modules.system.domain.vo.SysUserPositionVO;
import com.izpan.modules.system.facade.ISysUserPositionFacade;
import com.izpan.modules.system.service.ISysUserPositionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户岗位管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysUserPositionFacadeImpl
 * @CreateTime 2024-06-27 - 22:03:29
 */

@Service
@RequiredArgsConstructor
public class SysUserPositionFacadeImpl implements ISysUserPositionFacade {

    @NonNull
    private ISysUserPositionService sysUserPositionService;

    @Override
    public RPage<SysUserPositionVO> listSysUserPositionPage(PageQuery pageQuery, SysUserPositionSearchDTO sysUserPositionSearchDTO) {
        SysUserPositionBO sysUserPositionBO = CglibUtil.convertObj(sysUserPositionSearchDTO, SysUserPositionBO::new);
        IPage<SysUserPosition> sysUserPositionIPage = sysUserPositionService.listSysUserPositionPage(pageQuery, sysUserPositionBO);
        return RPage.build(sysUserPositionIPage, SysUserPositionVO::new);
    }

    @Override
    public SysUserPositionVO get(Long id) {
        SysUserPosition byId = sysUserPositionService.getById(id);
        return CglibUtil.convertObj(byId, SysUserPositionVO::new);
    }

    @Override
    @Transactional
    public boolean add(SysUserPositionAddDTO sysUserPositionAddDTO) {
        SysUserPositionBO sysUserPositionBO = CglibUtil.convertObj(sysUserPositionAddDTO, SysUserPositionBO::new);
        return sysUserPositionService.save(sysUserPositionBO);
    }

    @Override
    @Transactional
    public boolean update(SysUserPositionUpdateDTO sysUserPositionUpdateDTO) {
        SysUserPositionBO sysUserPositionBO = CglibUtil.convertObj(sysUserPositionUpdateDTO, SysUserPositionBO::new);
        return sysUserPositionService.updateById(sysUserPositionBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysUserPositionDeleteDTO sysUserPositionDeleteDTO) {
        SysUserPositionBO sysUserPositionBO = CglibUtil.convertObj(sysUserPositionDeleteDTO, SysUserPositionBO::new);
        return sysUserPositionService.removeBatchByIds(sysUserPositionBO.getIds(), true);
    }

}