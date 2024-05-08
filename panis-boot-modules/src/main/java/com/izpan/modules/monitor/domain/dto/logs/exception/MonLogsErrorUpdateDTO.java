package com.izpan.modules.monitor.domain.dto.logs.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 错误异常日志 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsErrorUpdateDTO
 * @CreateTime 2024-05-07
 */

@Getter
@Setter
@Schema(name = "MonLogsErrorUpdateDTO", description = "错误异常日志 编辑更新 DTO 对象")
public class MonLogsErrorUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 731196523482804187L;

}