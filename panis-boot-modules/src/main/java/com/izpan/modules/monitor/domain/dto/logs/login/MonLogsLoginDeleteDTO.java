package com.izpan.modules.monitor.domain.dto.logs.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 登录日志 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsLoginDeleteDTO
 * @CreateTime 2024-05-05
 */

@Getter
@Setter
@Schema(name = "MonLogsLoginDeleteDTO", description = "登录日志 删除 DTO 对象")
public class MonLogsLoginDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5317128483340643631L;

    @Schema(description = "IDs")
    private List<Long> ids;

}