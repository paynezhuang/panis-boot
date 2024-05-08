package com.izpan.modules.monitor.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 登录日志 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsLogin
 * @CreateTime 2024-05-05
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MonLogsLoginVO", description = "登录日志 VO 对象")
public class MonLogsLoginVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 4646102214924958309L;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "真实姓名")
    private String userRealName;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "IP所属地")
    private String ipAddr;

    @Schema(description = "User Agent")
    private String userAgent;

    @Schema(description = "登录状态(0:失败 1:成功)")
    private String status;

    @Schema(description = "登录消息")
    private String message;
}