package com.izpan.modules.monitor.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 调度日志 VO 展示类
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
@Schema(name = "MonLogsSchedulerVO", description = "调度日志 VO 对象")
public class MonLogsSchedulerVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = -3165189856005608739L;

    @Schema(description = "任务名称")
    private String jobName;

    @Schema(description = "任务组别")
    private String jobGroup;

    @Schema(description = "触发器名称")
    private String triggerName;

    @Schema(description = "触发器组别")
    private String triggerGroup;

    @Schema(description = "耗时")
    private Long useTime;

    @Schema(description = "状态")
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