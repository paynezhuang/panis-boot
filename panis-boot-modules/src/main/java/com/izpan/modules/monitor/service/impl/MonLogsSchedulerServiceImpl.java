package com.izpan.modules.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.monitor.domain.bo.MonLogsSchedulerBO;
import com.izpan.modules.monitor.domain.entity.MonLogsScheduler;
import com.izpan.modules.monitor.repository.mapper.MonLogsSchedulerMapper;
import com.izpan.modules.monitor.service.IMonLogsSchedulerService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * 调度日志 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsScheduler
 * @CreateTime 2024-05-30
 */

@Service
public class MonLogsSchedulerServiceImpl extends ServiceImpl<MonLogsSchedulerMapper, MonLogsScheduler> implements IMonLogsSchedulerService {

    @Override
    public IPage<MonLogsScheduler> listMonLogsSchedulerPage(PageQuery pageQuery, MonLogsSchedulerBO monLogsSchedulerBO) {
        LambdaQueryWrapper<MonLogsScheduler> queryWrapper = new LambdaQueryWrapper<MonLogsScheduler>()
                .eq(ObjectUtils.isNotEmpty(monLogsSchedulerBO.getJobName()), MonLogsScheduler::getJobName, monLogsSchedulerBO.getJobName())
                .eq(ObjectUtils.isNotEmpty(monLogsSchedulerBO.getJobGroup()), MonLogsScheduler::getJobGroup, monLogsSchedulerBO.getJobGroup())
                .orderByDesc(MonLogsScheduler::getCreateTime);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }
}
