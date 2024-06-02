package com.izpan.modules.monitor.domain.dto.logs.scheduler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 调度日志 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsSchedulerAddDTO
 * @CreateTime 2024-05-30
 */

@Getter
@Setter
@Builder
@Schema(name = "MonLogsSchedulerAddDTO", description = "调度日志 新增 DTO 对象")
public class MonLogsSchedulerAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8429816941845804811L;

    @Schema(description = "任务名称(唯一)")
    private String jobName;

    @Schema(description = "任务组别")
    private String jobGroup;

    @Schema(description = "触发器名称")
    private String triggerName;

    @Schema(description = "触发器组")
    private String triggerGroup;

    @Schema(description = "耗时时间")
    private Long useTime;

    @Schema(description = "任务执行结果")
    private String status;

    @Schema(description = "异常信息")
    private String exceptionMessage;

    @Schema(description = "异常类")
    private String exceptionClass;

    @Schema(description = "异常行号")
    private Integer line;

    @Schema(description = "堆栈信息")
    private String stackTrace;
}