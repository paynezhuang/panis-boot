package com.izpan.modules.monitor.domain.dto.scheduler;

import com.izpan.common.domain.KVPairs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 调度管理 更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerUpdateDTO
 * @CreateTime 2024/5/21 - 14:55
 */

@Getter
@Setter
@Schema(name = "MonSchedulerUpdateDTO", description = "调度管理 更新 DTO 对象")
public class MonSchedulerUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8161240292026133655L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "任务名称(唯一)")
    private String jobName;

    @Schema(description = "任务组别")
    private String jobGroup;

    @Schema(description = "任务类名")
    private String jobClassName;

    @Schema(description = "任务描述")
    private String description;

    @Schema(description = "任务参数")
    private List<KVPairs> jobData;

    @Schema(description = "触发器名称")
    private String triggerName;

    @Schema(description = "触发器组")
    private String triggerGroup;

    @Schema(description = "触发器描述")
    private String triggerDescription;

    @Schema(description = "触发器参数")
    private List<KVPairs> triggerData;

    @Schema(description = "cron表达式")
    private String cronExpression;
}
