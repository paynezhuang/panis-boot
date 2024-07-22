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
import com.izpan.common.pool.StringPools;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.bo.SysOrgUnitsBO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsAddDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsDeleteDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsSearchDTO;
import com.izpan.modules.system.domain.dto.org.units.SysOrgUnitsUpdateDTO;
import com.izpan.modules.system.domain.entity.SysOrgUnits;
import com.izpan.modules.system.domain.vo.SysOrgUnitsTreeVO;
import com.izpan.modules.system.domain.vo.SysOrgUnitsVO;
import com.izpan.modules.system.facade.ISysOrgUnitsFacade;
import com.izpan.modules.system.service.ISysOrgUnitsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 组织/部门/子部门管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysOrgUnitsFacadeImpl
 * @CreateTime 2024-07-16 - 16:35:30
 */

@Service
@RequiredArgsConstructor
public class SysOrgUnitsFacadeImpl implements ISysOrgUnitsFacade {

    @NonNull
    private ISysOrgUnitsService sysOrgUnitsService;

    /**
     * 初始化组织单位子单位
     *
     * @param parentId    父id
     * @param orgUnitsMap 所有单位数据 Map 结构
     * @return {@link List }<{@link SysOrgUnitsTreeVO }> 子组织集合
     * @author payne.zhuang
     * @CreateTime 2024-07-16 - 22:03:21
     */
    private static List<SysOrgUnitsTreeVO> initOrgUnitsChild(Long parentId, Map<Long, List<SysOrgUnits>> orgUnitsMap) {
        // 获取子单位
        List<SysOrgUnits> childOrgUnits = orgUnitsMap.get(parentId);
        if (CollectionUtils.isEmpty(childOrgUnits)) {
            return Collections.emptyList();
        }
        // 递归初始化子单位
        return childOrgUnits.stream()
                .map(unit -> {
                    SysOrgUnitsTreeVO orgUnitsTreeVO = CglibUtil.convertObj(unit, SysOrgUnitsTreeVO::new);
                    orgUnitsTreeVO.setChildren(initOrgUnitsChild(unit.getId(), orgUnitsMap));
                    return orgUnitsTreeVO;
                })
                .sorted(Comparator.comparing(SysOrgUnitsTreeVO::getSort))
                .toList();
    }

    @Override
    public RPage<SysOrgUnitsTreeVO> listSysOrgUnitsPage(PageQuery pageQuery, SysOrgUnitsSearchDTO sysOrgUnitsSearchDTO) {
        SysOrgUnitsBO sysOrgUnitsBO = CglibUtil.convertObj(sysOrgUnitsSearchDTO, SysOrgUnitsBO::new);
        IPage<SysOrgUnits> sysOrgUnitsIPage = sysOrgUnitsService.listSysOrgUnitsPage(pageQuery, sysOrgUnitsBO);
        List<SysOrgUnits> topOrgUnits = sysOrgUnitsIPage.getRecords();
        if (topOrgUnits.isEmpty()) {
            return RPage.build(sysOrgUnitsIPage, SysOrgUnitsTreeVO::new);
        }
        // 查询所有数据
        List<Long> topLevelIds = topOrgUnits.stream().map(SysOrgUnits::getId).toList();
        List<SysOrgUnits> allOrgUnits = sysOrgUnitsService.listAllDescendants(topLevelIds);
        // 按 parentId 分组
        Map<Long, List<SysOrgUnits>> orgUnitsMap = allOrgUnits.stream()
                .collect(Collectors.groupingBy(SysOrgUnits::getParentId));
        // 初始化顶级单位的子单位
        List<SysOrgUnitsTreeVO> topOrgUnitsTreeVOList = topOrgUnits.stream()
                .map(unit -> {
                    SysOrgUnitsTreeVO orgUnitsTreeVO = CglibUtil.convertObj(unit, SysOrgUnitsTreeVO::new);
                    orgUnitsTreeVO.setChildren(initOrgUnitsChild(unit.getId(), orgUnitsMap));
                    return orgUnitsTreeVO;
                }).toList();

        RPage<SysOrgUnitsTreeVO> build = RPage.build(sysOrgUnitsIPage, SysOrgUnitsTreeVO::new);
        build.setRecords(topOrgUnitsTreeVOList);
        return build;
    }

    @Override
    public SysOrgUnitsVO get(Long id) {
        SysOrgUnits byId = sysOrgUnitsService.getById(id);
        return CglibUtil.convertObj(byId, SysOrgUnitsVO::new);
    }

    @Override
    @Transactional
    public boolean add(SysOrgUnitsAddDTO sysOrgUnitsAddDTO) {
        SysOrgUnitsBO sysOrgUnitsBO = CglibUtil.convertObj(sysOrgUnitsAddDTO, SysOrgUnitsBO::new);
        return sysOrgUnitsService.save(sysOrgUnitsBO);
    }

    @Override
    @Transactional
    public boolean update(SysOrgUnitsUpdateDTO sysOrgUnitsUpdateDTO) {
        SysOrgUnitsBO sysOrgUnitsBO = CglibUtil.convertObj(sysOrgUnitsUpdateDTO, SysOrgUnitsBO::new);
        return sysOrgUnitsService.updateById(sysOrgUnitsBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysOrgUnitsDeleteDTO sysOrgUnitsDeleteDTO) {
        SysOrgUnitsBO sysOrgUnitsBO = CglibUtil.convertObj(sysOrgUnitsDeleteDTO, SysOrgUnitsBO::new);
        return sysOrgUnitsService.removeBatchByIds(sysOrgUnitsBO.getIds(), true);
    }

    @Override
    public List<SysOrgUnitsTreeVO> queryAllOrgUnitsListConvertToTree() {
        // 查询所有数据
        List<SysOrgUnits> allOrgUnits = sysOrgUnitsService.querySysOrgUnitsListWithStatus(StringPools.ONE);
        // 按 parentId 分组
        Map<Long, List<SysOrgUnits>> orgUnitsMap = allOrgUnits.stream()
                .collect(Collectors.groupingBy(SysOrgUnits::getParentId));
        // 组装对应结构
        return initOrgUnitsChild(0L, orgUnitsMap);
    }

}