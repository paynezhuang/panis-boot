package com.izpan.modules.monitor.domain.dto.logs.scheduler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 调度日志 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsSchedulerSearchDTO
 * @CreateTime 2024-05-30
 */

@Getter
@Setter
@Schema(name = "MonLogsSchedulerSearchDTO", description = "调度日志 查询 DTO 对象")
public class MonLogsSchedulerSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -216429072655610130L;

    @Schema(description = "任务名称(唯一)")
    private String jobName;

    @Schema(description = "任务组别")
    private String jobGroup;
}