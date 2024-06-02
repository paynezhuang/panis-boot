package com.izpan.modules.monitor.domain.dto.logs.scheduler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 调度日志 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsSchedulerUpdateDTO
 * @CreateTime 2024-05-30
 */

@Getter
@Setter
@Schema(name = "MonLogsSchedulerUpdateDTO", description = "调度日志 编辑更新 DTO 对象")
public class MonLogsSchedulerUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1508014913880143075L;
}