package com.izpan.modules.monitor.domain.dto.logs.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 错误异常日志 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsErrorSearchDTO
 * @CreateTime 2024-05-07
 */

@Getter
@Setter
@Schema(name = "MonLogsErrorSearchDTO", description = "错误异常日志 查询 DTO 对象")
public class MonLogsErrorSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6586813041757307040L;

}