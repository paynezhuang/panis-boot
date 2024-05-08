package com.izpan.modules.monitor.domain.dto.logs.operation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 操作日志 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsOperationAddDTO
 * @CreateTime 2024-05-07
 */

@Getter
@Setter
@Builder
@Schema(name = "MonLogsOperationAddDTO", description = "操作日志 新增 DTO 对象")
public class MonLogsOperationAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8518349960545896766L;

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "IP所属地")
    private String ipAddr;

    @Schema(description = "登录代理")
    private String userAgent;

    @Schema(description = "请求URI")
    private String requestUri;

    @Schema(description = "请求方式")
    private String requestMethod;

    @Schema(description = "请求内容类型")
    private String contentType;

    @Schema(description = "接口说明")
    private String operation;

    @Schema(description = "方法名称")
    private String methodName;

    @Schema(description = "请求参数")
    private String methodParams;

    @Schema(description = "请求耗时")
    private Long useTime;
}