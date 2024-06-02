package com.izpan.modules.monitor.domain.vo;

import com.izpan.common.domain.KVPairs;
import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * 调度管理
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.vo.MonSchedulerVO
 * @CreateTime 2024/5/21 - 10:39
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MonSchedulerVO", description = "调度管理 VO 对象")
public class MonSchedulerVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 4622210440174055213L;

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
