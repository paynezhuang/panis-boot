package com.izpan.modules.monitor.domain.dto.logs.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录日志 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsLoginAddDTO
 * @CreateTime 2024-05-05
 */

@Getter
@Setter
@Schema(name = "MonLogsLoginAddDTO", description = "登录日志 新增 DTO 对象")
public class MonLogsLoginAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5656544017810098853L;
}