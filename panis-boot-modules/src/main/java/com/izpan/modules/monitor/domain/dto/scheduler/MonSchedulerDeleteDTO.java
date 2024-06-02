package com.izpan.modules.monitor.domain.dto.scheduler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 调度管理 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerDeleteDTO
 * @CreateTime 2024/5/21 - 14:55
 */

@Getter
@Setter
@Schema(name = "MonSchedulerDeleteDTO", description = "调度管理 删除 DTO 对象")
public class MonSchedulerDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5008539954239623054L;

    @Schema(description = "IDs")
    private List<Long> ids;
}
