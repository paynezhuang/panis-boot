package com.izpan.modules.monitor.domain.dto.logs.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录日志 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsLoginUpdateDTO
 * @CreateTime 2024-05-05
 */

@Getter
@Setter
@Schema(name = "MonLogsLoginUpdateDTO", description = "登录日志 编辑更新 DTO 对象")
public class MonLogsLoginUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6494913671734283178L;

}