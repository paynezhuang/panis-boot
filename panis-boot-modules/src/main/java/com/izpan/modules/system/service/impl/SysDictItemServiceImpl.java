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
import com.google.common.collect.Maps;
import com.izpan.common.exception.BizException;
import com.izpan.common.pool.StringPools;
import com.izpan.common.util.CollectionUtil;
import com.izpan.infrastructure.holder.ContextHolder;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.system.domain.bo.SysDictItemBO;
import com.izpan.modules.system.domain.bo.SysDictItemOptions;
import com.izpan.modules.system.domain.entity.SysDictItem;
import com.izpan.modules.system.repository.mapper.SysDictItemMapper;
import com.izpan.modules.system.service.ISysDictItemService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据字典子项管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.service.impl.SysDictItemServiceImpl
 * @CreateTime 2024-06-27 - 22:03:29
 */

@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements ISysDictItemService {

    @Override
    public IPage<SysDictItem> listSysDictItemPage(PageQuery pageQuery, SysDictItemBO sysDictItemBO) {
        LambdaQueryWrapper<SysDictItem> queryWrapper = new LambdaQueryWrapper<SysDictItem>()
                .eq(ObjectUtils.isNotEmpty(sysDictItemBO.getDictId()), SysDictItem::getDictId, sysDictItemBO.getDictId())
                .eq(ObjectUtils.isNotEmpty(sysDictItemBO.getValue()), SysDictItem::getValue, sysDictItemBO.getValue())
                .eq(ObjectUtils.isNotEmpty(sysDictItemBO.getDescription()), SysDictItem::getDescription, sysDictItemBO.getDescription())
                .like(ObjectUtils.isNotEmpty(sysDictItemBO.getZhCN()), SysDictItem::getZhCN, sysDictItemBO.getZhCN())
                .like(ObjectUtils.isNotEmpty(sysDictItemBO.getEnUS()), SysDictItem::getEnUS, sysDictItemBO.getEnUS())
                .orderByAsc(SysDictItem::getSort);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

    @Override
    public boolean save(SysDictItem entity) {
        // 校验字典项是否存在
        LambdaQueryWrapper<SysDictItem> queryWrapper = new LambdaQueryWrapper<SysDictItem>()
                .eq(SysDictItem::getDictId, entity.getDictId())
                .eq(SysDictItem::getValue, entity.getValue());
        Long count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BizException("字典[%s],[%s]已存在".formatted(entity.getDictCode(), entity.getValue()));
        }
        return super.save(entity);
    }

    @Override
    public List<SysDictItem> queryAllDictItemList(String code) {
        List<SysDictItem> sysDictItems = baseMapper.queryAllDictItemList(code);
        sysDictItems.sort(Comparator.comparing(SysDictItem::getSort));
        return sysDictItems;
    }

    @Override
    public Map<String, List<SysDictItemOptions>> queryAllDictItemMap() {
        // 查询所有字典项
        List<SysDictItem> sysDictItems = queryAllDictItemList(StringPools.EMPTY);
        return buildMapOptions(sysDictItems);
    }

    @Override
    public Map<String, List<SysDictItemOptions>> queryDictItemMapOptions(SysDictItemBO sysDictItemBO) {
        // 根据 Code 查询字典项
        List<SysDictItem> sysDictItems = queryAllDictItemList(sysDictItemBO.getDictCode());
        return buildMapOptions(sysDictItems);
    }

    /**
     * 构建字典项 Map 集合
     *
     * @param sysDictItems 字典项集合
     * @return {@link Map }<{@link String }, {@link List }<{@link SysDictItemOptions }>> 字典项 Map 集合
     * @author payne.zhuang
     * @CreateTime 2024-08-01 - 23:26:44
     */
    private Map<String, List<SysDictItemOptions>> buildMapOptions(List<SysDictItem> sysDictItems) {
        // 根据字典 Code 进行分组
        Map<String, List<SysDictItem>> collect = sysDictItems.stream().collect(Collectors.groupingBy(SysDictItem::getDictCode));
        // 返回结果
        Map<String, List<SysDictItemOptions>> dictMap = Maps.newLinkedHashMap();
        // 遍历分组结果，转换为 Options 对象
        collect.forEach((k, v) -> dictMap.put(k, transformSysDictItemOptions(v)));
        return dictMap;
    }

    @Override
    public List<SysDictItemOptions> transformSysDictItemOptions(List<SysDictItem> sysDictItems) {
        // 排序
        sysDictItems.sort(Comparator.comparing(SysDictItem::getSort));
        // 转换
        return CollectionUtil.toMutableList(sysDictItems.stream().map(item ->
                SysDictItemOptions.builder()
                        .label(StringPools.EN_US.equalsIgnoreCase(ContextHolder.language()) ? item.getEnUS() : item.getZhCN())
                        .value(item.getValue())
                        .sort(item.getSort())
                        .type(item.getType())
                        .build()));
    }
}

