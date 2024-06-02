package com.izpan.modules.monitor.domain.dto.logs.scheduler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 调度日志 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsSchedulerDeleteDTO
 * @CreateTime 2024-05-30
 */

@Getter
@Setter
@Schema(name = "MonLogsSchedulerDeleteDTO", description = "调度日志 删除 DTO 对象")
public class MonLogsSchedulerDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6080633535419198939L;

    @Schema(description = "IDs")
    private List<Long> ids;

}