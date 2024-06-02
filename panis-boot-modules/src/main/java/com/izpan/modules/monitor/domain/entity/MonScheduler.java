package com.izpan.modules.monitor.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.common.domain.KVPairs;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * 调度任务 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonScheduler
 * @CreateTime 2024/5/21 - 11:36
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mon_scheduler")
public class MonScheduler extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -4732204370005416544L;

    /**
     * 任务名称(唯一)
     */
    private String jobName;

    /**
     * 任务组别
     */
    private String jobGroup;

    /**
     * 任务参数
     */
    private List<KVPairs> jobData;

    /**
     * 触发器名称
     */
    private String triggerName;

    /**
     * 触发器组
     */
    private String triggerGroup;

    /**
     * 触发器参数
     */
    private List<KVPairs> triggerData;

}
