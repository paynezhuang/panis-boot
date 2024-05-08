package com.izpan.modules.monitor.domain.dto.logs.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录日志 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsLoginSearchDTO
 * @CreateTime 2024-05-05
 */

@Getter
@Setter
@Schema(name = "MonLogsLoginSearchDTO", description = "登录日志 查询 DTO 对象")
public class MonLogsLoginSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7995588408618765662L;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "用户真实姓名")
    private String userRealName;
}