package com.izpan.modules.monitor.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 调度日志 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsScheduler
 * @CreateTime 2024-05-30
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mon_logs_scheduler")
public class MonLogsScheduler extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -6087323734087393764L;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组别
     */
    private String jobGroup;

    /**
     * 触发器名称
     */
    private String triggerName;

    /**
     * 触发器组别
     */
    private String triggerGroup;

    /**
     * 耗时
     */
    private Long useTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    /**
     * 异常类
     */
    private String exceptionClass;

    /**
     * 异常行号
     */
    private Integer line;

    /**
     * 堆栈信息
     */
    private String stackTrace;
}