package com.izpan.modules.monitor.domain.dto.scheduler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 调度管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerSearchDTO
 * @CreateTime 2024/5/21 - 10:40
 */

@Getter
@Setter
@Schema(name = "MonSchedulerSearchDTO", description = "调度管理 查询 DTO 对象")
public class MonSchedulerSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5245621458116404836L;

    @Schema(description = "任务名称(唯一)")
    private String jobName;

    @Schema(description = "任务组别")
    private String jobGroup;

    @Schema(description = "任务类名")
    private String jobClassName;

    @Schema(description = "触发器名称")
    private String triggerName;

    @Schema(description = "触发器组")
    private String triggerGroup;
}
