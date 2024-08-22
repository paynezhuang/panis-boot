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
import com.izpan.modules.system.domain.bo.SysDictItemBO;
import com.izpan.modules.system.domain.bo.SysDictItemOptions;
import com.izpan.modules.system.domain.dto.dict.item.*;
import com.izpan.modules.system.domain.entity.SysDictItem;
import com.izpan.modules.system.domain.vo.SysDictItemOptionsVO;
import com.izpan.modules.system.domain.vo.SysDictItemVO;
import com.izpan.modules.system.facade.ISysDictItemFacade;
import com.izpan.modules.system.service.ISysDictItemService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据字典子项管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysDictItemFacadeImpl
 * @CreateTime 2024-06-27 - 22:03:29
 */

@Service
@RequiredArgsConstructor
public class SysDictItemFacadeImpl implements ISysDictItemFacade {

    @NonNull
    private ISysDictItemService sysDictItemService;

    @Override
    public RPage<SysDictItemVO> listSysDictItemPage(PageQuery pageQuery, SysDictItemSearchDTO sysDictItemSearchDTO) {
        SysDictItemBO sysDictItemBO = CglibUtil.convertObj(sysDictItemSearchDTO, SysDictItemBO::new);
        IPage<SysDictItem> sysDictItemIPage = sysDictItemService.listSysDictItemPage(pageQuery, sysDictItemBO);
        return RPage.build(sysDictItemIPage, SysDictItemVO::new);
    }

    @Override
    public SysDictItemVO get(Long id) {
        SysDictItem byId = sysDictItemService.getById(id);
        return CglibUtil.convertObj(byId, SysDictItemVO::new);
    }

    @Override
    @Transactional
    public boolean add(SysDictItemAddDTO sysDictItemAddDTO) {
        SysDictItemBO sysDictItemBO = CglibUtil.convertObj(sysDictItemAddDTO, SysDictItemBO::new);
        return sysDictItemService.save(sysDictItemBO);
    }

    @Override
    @Transactional
    public boolean update(SysDictItemUpdateDTO sysDictItemUpdateDTO) {
        SysDictItemBO sysDictItemBO = CglibUtil.convertObj(sysDictItemUpdateDTO, SysDictItemBO::new);
        return sysDictItemService.updateById(sysDictItemBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysDictItemDeleteDTO sysDictItemDeleteDTO) {
        SysDictItemBO sysDictItemBO = CglibUtil.convertObj(sysDictItemDeleteDTO, SysDictItemBO::new);
        return sysDictItemService.removeBatchByIds(sysDictItemBO.getIds(), true);
    }

    @Override
    public Map<String, List<SysDictItemOptionsVO>> queryAllDictItemMap() {
        Map<String, List<SysDictItemOptions>> mapOptions = sysDictItemService.queryAllDictItemMap();
        return mapOptions.entrySet().parallelStream()
                .collect(Collectors.toConcurrentMap(
                        Map.Entry::getKey,
                        entry -> CglibUtil.convertList(entry.getValue(), SysDictItemOptionsVO::new)
                ));
    }

    @Override
    public Map<String, List<SysDictItemOptionsVO>> queryDictItemMapOptions(SysDictItemStoreSearchDTO searchDTO) {
        SysDictItemBO sysDictItemBO = CglibUtil.convertObj(searchDTO, SysDictItemBO::new);
        Map<String, List<SysDictItemOptions>> mapOptions = sysDictItemService.queryDictItemMapOptions(sysDictItemBO);
        return mapOptions.entrySet().parallelStream()
                .collect(Collectors.toConcurrentMap(
                        Map.Entry::getKey,
                        entry -> CglibUtil.convertList(entry.getValue(), SysDictItemOptionsVO::new)
                ));
    }

}