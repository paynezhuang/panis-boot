package com.izpan.modules.monitor.domain.dto.logs.operation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 操作日志 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsOperationUpdateDTO
 * @CreateTime 2024-05-07
 */

@Getter
@Setter
@Schema(name = "MonLogsOperationUpdateDTO", description = "操作日志 编辑更新 DTO 对象")
public class MonLogsOperationUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2196743528993798805L;
}