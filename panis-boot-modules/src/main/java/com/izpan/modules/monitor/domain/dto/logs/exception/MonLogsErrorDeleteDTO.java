package com.izpan.modules.monitor.domain.dto.logs.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 错误异常日志 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsErrorDeleteDTO
 * @CreateTime 2024-05-07
 */

@Getter
@Setter
@Schema(name = "MonLogsErrorDeleteDTO", description = "错误异常日志 删除 DTO 对象")
public class MonLogsErrorDeleteDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = 1462819401753317837L;

    @Schema(description = "IDs")
    private List<Long> ids;

}