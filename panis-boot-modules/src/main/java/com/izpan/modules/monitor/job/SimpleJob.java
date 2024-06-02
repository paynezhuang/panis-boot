package com.izpan.modules.monitor.job;

import com.izpan.infrastructure.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 示例调度任务
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.job.SimpleJob
 * @CreateTime 2024-05-22 11:34:01
 */

@Slf4j
@DisallowConcurrentExecution
public class SimpleJob extends QuartzJobBean {

    // @DisallowConcurrentExecution 不允许并发

    @Override
    public void executeInternal(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        log.info("示例调度任务 : {}", JacksonUtil.toJson(jobDetail));
        log.info("示例调度任务 : %s".formatted(jobDetail.getKey().getName()));
    }
}
