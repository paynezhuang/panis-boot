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
import com.izpan.modules.system.domain.bo.SysDictItemBO;
import com.izpan.modules.system.domain.entity.SysDictItem;
import com.izpan.modules.system.repository.mapper.SysDictItemMapper;
import com.izpan.modules.system.service.ISysDictItemService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

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
                .like(ObjectUtils.isNotEmpty(sysDictItemBO.getZhCN()), SysDictItem::getZhCN, sysDictItemBO.getZhCN())
                .like(ObjectUtils.isNotEmpty(sysDictItemBO.getEnUS()), SysDictItem::getEnUS, sysDictItemBO.getEnUS());
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

}

