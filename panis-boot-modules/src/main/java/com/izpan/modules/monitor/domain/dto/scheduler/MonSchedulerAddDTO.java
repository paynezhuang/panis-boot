package com.izpan.modules.monitor.domain.dto.scheduler;

import com.izpan.common.domain.KVPairs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 调度管理 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerAddDTO
 * @CreateTime 2024/5/21 - 14:55
 */

@Getter
@Setter
@Schema(name = "MonSchedulerAddDTO", description = "调度管理 新增 DTO 对象")
public class MonSchedulerAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7945752988190156227L;

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

    @Schema(description = "状态")
    private String triggerState;

}
