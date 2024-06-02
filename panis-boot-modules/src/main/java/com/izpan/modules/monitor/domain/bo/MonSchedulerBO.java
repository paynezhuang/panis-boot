package com.izpan.modules.monitor.domain.bo;

import com.izpan.modules.monitor.domain.entity.MonScheduler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 调度任务 BO
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.bo.MonSchedulerBO
 * @CreateTime 2024/5/20 - 17:54
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MonSchedulerBO extends MonScheduler {

    @Serial
    private static final long serialVersionUID = 6353660075412135770L;

    /**
     * 任务ID
     */
    private Long id;

    /**
     * 任务类名
     */
    private String jobClassName;

    /**
     * 任务描述
     */
    private String description;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 触发器描述
     */
    private String triggerDescription;

    /**
     * 触发器状态
     */
    private String triggerState;
}
