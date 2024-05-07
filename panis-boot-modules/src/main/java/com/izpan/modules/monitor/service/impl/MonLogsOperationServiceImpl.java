package com.izpan.modules.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.monitor.domain.bo.MonLogsOperationBO;
import com.izpan.modules.monitor.domain.entity.MonLogsOperation;
import com.izpan.modules.monitor.repository.mapper.MonLogsOperationMapper;
import com.izpan.modules.monitor.service.IMonLogsOperationService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * 操作日志 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsOperation
 * @CreateTime 2024-05-07
 */

@Service
public class MonLogsOperationServiceImpl extends ServiceImpl<MonLogsOperationMapper, MonLogsOperation> implements IMonLogsOperationService {
    @Override
    public IPage<MonLogsOperation> listMonLogsOperationPage(PageQuery pageQuery, MonLogsOperationBO monLogsOperationBO) {
        LambdaQueryWrapper<MonLogsOperation> queryWrapper = new LambdaQueryWrapper<MonLogsOperation>()
                .eq(ObjectUtils.isNotEmpty(monLogsOperationBO.getCreateUser()), MonLogsOperation::getCreateUser, monLogsOperationBO.getCreateUser())
                .orderByDesc(MonLogsOperation::getCreateTime);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }
}
