package com.izpan.modules.monitor.scheduler.listener;

import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerAddDTO;
import com.izpan.modules.monitor.facade.IMonLogsSchedulerFacade;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.regex.Matcher;

/**
 * 调度任务监听器
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.scheduler.listener.SchedulerJobListener
 * @CreateTime 2024/5/25 - 01:20
 */

@Slf4j
@Component
public class SchedulerJobListener implements JobListener {

    private final IMonLogsSchedulerFacade monLogsSchedulerFacade;

    public SchedulerJobListener(IMonLogsSchedulerFacade monLogsSchedulerFacade) {
        this.monLogsSchedulerFacade = monLogsSchedulerFacade;
        log.info("SchedulerJobListener constructed with monLogsSchedulerFacade: {}", monLogsSchedulerFacade);
    }

    @Override
    public String getName() {
        return "QuartzJobListener";
    }

    /**
     * 作业即将被执行
     *
     * @param context 作业执行上下文
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("Job : {} is going to be executed.", context.getJobDetail().getKey());
        // 记录作业开始执行的时间，用于后续计算执行耗时
        context.put("startTime", System.currentTimeMillis());
    }

    /**
     * 作业执行被否决
     * 如果Scheduler决定不执行作业（如Trigger监听器中止），则调用此方法
     *
     * @param context 作业执行上下文
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("Job : {} is vetoed.", context.getJobDetail().getKey());
    }

    /**
     * 作业执行结果
     * 作业执行完成后调用此方法，无论作业执行是否成功
     *
     * @param context 作业执行上下文
     * @param e       作业执行异常
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        // 从上下文中获取作业开始时间
        long startTime = (Long) context.get("startTime");
        // 获取作业的 Key
        JobKey jobKey = context.getJobDetail().getKey();
        // 此时 TriggerKey 是Quartz 内部实现的随机key。不是用户定义的 key
        // 如需获取，可在 QuartzJobBean 中通过 MergedJobDataMap 进行 put，到这边获取即可
        TriggerKey triggerKey = context.getTrigger().getKey();
        // 构建调度日志新增 DTO 对象
        MonLogsSchedulerAddDTO build = MonLogsSchedulerAddDTO.builder()
                .jobName(jobKey.getName())
                .jobGroup(jobKey.getGroup())
                .triggerName(triggerKey.getName())
                .triggerGroup(triggerKey.getGroup())
                .status("SUCCESS")
                .build();
        // 如果作业执行异常，记录异常信息
        if (null != e) {
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            build.setStatus("FAIL");
            build.setLine(stackTraceElement.getLineNumber());
            build.setExceptionMessage(e.getMessage());
            build.setExceptionClass(e.getClass().getName());
            build.setStackTrace(Matcher.quoteReplacement(Arrays.toString(e.getStackTrace())));
        }
        // 计算作业执行耗时
        build.setUseTime(System.currentTimeMillis() - startTime);
        // 添加调度日志
        monLogsSchedulerFacade.add(build);
    }
}
