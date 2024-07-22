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
import com.izpan.common.util.CollectionUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysUserPositionBO;
import com.izpan.modules.system.domain.entity.SysUserPosition;
import com.izpan.modules.system.repository.mapper.SysUserPositionMapper;
import com.izpan.modules.system.service.ISysUserPositionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 用户岗位管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.impl.SysUserPositionServiceImpl
 * @CreateTime 2024-06-27 - 22:03:29
 */

@Service
public class SysUserPositionServiceImpl extends ServiceImpl<SysUserPositionMapper, SysUserPosition> implements ISysUserPositionService {

    @Override
    public IPage<SysUserPosition> listSysUserPositionPage(PageQuery pageQuery, SysUserPositionBO sysUserPositionBO) {
        return baseMapper.selectPage(pageQuery.buildPage(), new LambdaQueryWrapper<>());
    }

    @Override
    public List<Long> queryPositionIdsWithUserId(Long userId) {
        List<SysUserPosition> sysUserPositionList = baseMapper.listUserPositionWithUserId(userId);
        return sysUserPositionList.stream().map(SysUserPosition::getPositionId).toList();
    }

    @Override
    public boolean updateUserPosition(Long userId, List<Long> positionIds) {
        List<Long> originPositionIds = queryPositionIdsWithUserId(userId);
        // 处理结果
        AtomicBoolean saveResult = new AtomicBoolean(true);
        Set<Long> positionIdSet = Sets.newHashSet(positionIds);
        CollectionUtil.handleDifference(
                Sets.newHashSet(originPositionIds),
                positionIdSet,
                // 处理增加和删除的岗位
                (addPositionIdSet, removePositionIdSet) -> {
                    // 如有删除，则进行删除数据
                    if (!CollectionUtils.isEmpty(removePositionIdSet)) {
                        LambdaQueryWrapper<SysUserPosition> removeQueryWrapper = new LambdaQueryWrapper<SysUserPosition>()
                                .eq(SysUserPosition::getUserId, userId)
                                .in(SysUserPosition::getPositionId, removePositionIdSet);
                        baseMapper.delete(removeQueryWrapper);
                    }
                    // 进行新增数据
                    if (!CollectionUtils.isEmpty(addPositionIdSet)) {
                        List<SysUserPosition> sysUserPositions = addPositionIdSet.stream()
                                .map(positionId -> new SysUserPosition(userId, positionId))
                                .toList();
                        saveResult.set(Db.saveBatch(sysUserPositions));
                    }
                }
        );
        return saveResult.get();
    }

}

