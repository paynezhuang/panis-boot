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
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.google.common.collect.Sets;
import com.izpan.common.pool.StringPools;
import com.izpan.common.util.CglibUtil;
import com.izpan.common.util.CollectionUtil;
import com.izpan.common.util.StringUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysUserOrgBO;
import com.izpan.modules.system.domain.entity.SysUserOrg;
import com.izpan.modules.system.repository.mapper.SysUserOrgMapper;
import com.izpan.modules.system.service.ISysUserOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 用户组织/部门/子部门管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.impl.SysUserOrgServiceImpl
 * @CreateTime 2024-07-16 - 16:35:30
 */

@Slf4j
@Service
public class SysUserOrgServiceImpl extends ServiceImpl<SysUserOrgMapper, SysUserOrg> implements ISysUserOrgService {

    @Override
    public IPage<SysUserOrg> listSysUserOrgPage(PageQuery pageQuery, SysUserOrgBO sysUserOrgBO) {
        return baseMapper.selectPage(pageQuery.buildPage(), new LambdaQueryWrapper<>());
    }

    @Override
    public List<SysUserOrgBO> queryOrgUnitsListWithUserId(Long userId) {
        List<SysUserOrg> sysUserOrgList = baseMapper.listUserOrgWithUserId(userId);
        return CglibUtil.convertList(sysUserOrgList, SysUserOrgBO::new);
    }

    @Override
    public List<Long> queryOrgUnitsIdsWithUserId(Long userId) {
        List<SysUserOrgBO> sysUserOrgBOList = queryOrgUnitsListWithUserId(userId);
        return sysUserOrgBOList.stream().map(SysUserOrg::getOrgId).toList();
    }

    @Override
    public List<Long> queryOrgUnitsPrincipalWithUserId(Long userId) {
        List<SysUserOrgBO> sysUserOrgBOList = queryOrgUnitsListWithUserId(userId);
        return sysUserOrgBOList.stream()
                .filter(item -> StringPools.ONE.equals(item.getPrincipal()))
                .map(SysUserOrg::getOrgId).toList();
    }

    @Override
    public boolean updateUserOrg(Long userId, List<Long> orgIds, List<Long> principalIds) {
        List<Long> originUserOrgIds = queryOrgUnitsIdsWithUserId(userId);
        // 处理数据
        Set<Long> orgIdSet = Sets.newHashSet(orgIds);
        Set<Long> principalSet = Sets.newHashSet(principalIds);
        // 处理结果
        AtomicBoolean saveResult = new AtomicBoolean(true);
        CollectionUtil.handleDifference(
                Sets.newHashSet(originUserOrgIds),
                orgIdSet,
                // 处理增加和删除的数据
                (addOrgIdSet, removeOrgIdSet) -> {
                    // 如有删除，则进行删除数据
                    if (!CollectionUtils.isEmpty(removeOrgIdSet)) {
                        LambdaQueryWrapper<SysUserOrg> removeQueryWrapper = new LambdaQueryWrapper<SysUserOrg>()
                                .eq(SysUserOrg::getUserId, userId)
                                .in(SysUserOrg::getOrgId, removeOrgIdSet);
                        baseMapper.delete(removeQueryWrapper);
                    }
                    // 进行新增数据
                    if (!CollectionUtils.isEmpty(addOrgIdSet)) {
                        List<SysUserOrg> sysUserOrgList = addOrgIdSet.stream()
                                .map(orgId -> new SysUserOrg(userId, orgId, StringUtil.boolToString(principalSet.contains(orgId))))
                                .toList();
                        saveResult.set(Db.saveBatch(sysUserOrgList));
                    }
                }
        );
        baseMapper.updatePrincipal(userId, principalSet);
        return saveResult.get();
    }

}

