package com.izpan.modules.monitor.domain.dto.logs.operation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 操作日志 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsOperationDeleteDTO
 * @CreateTime 2024-05-07
 */

@Getter
@Setter
@Schema(name = "MonLogsOperationDeleteDTO", description = "操作日志 删除 DTO 对象")
public class MonLogsOperationDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3752579170028183789L;

    @Schema(description = "IDs")
    private List<Long> ids;

}