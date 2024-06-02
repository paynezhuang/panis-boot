package com.izpan.modules.monitor.scheduler;

import com.izpan.modules.monitor.facade.IMonLogsSchedulerFacade;
import com.izpan.modules.monitor.scheduler.listener.SchedulerJobListener;
import com.izpan.quartz.service.ISchedulerService;
import com.izpan.quartz.service.impl.SchedulerServiceImpl;
import jakarta.annotation.Resource;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz 调度器配置
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.scheduler.SchedulerConfiguration
 * @CreateTime 2024/5/25 - 14:15
 */

@Configuration
public class SchedulerConfiguration {

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    @Bean("schedulerService")
    public ISchedulerService schedulerService(@Qualifier("schedulerBean") Scheduler scheduler) {
        SchedulerServiceImpl schedulerService = new SchedulerServiceImpl();
        schedulerService.setScheduler(scheduler);
        return schedulerService;
    }

    @Bean("schedulerBean")
    public Scheduler schedulerBean(@Autowired IMonLogsSchedulerFacade monLogsSchedulerFacade) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.getListenerManager().addJobListener(new SchedulerJobListener(monLogsSchedulerFacade));
        return scheduler;
    }

}
