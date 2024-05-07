package com.izpan.modules.monitor.domain.dto.logs.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 错误异常日志 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsErrorAddDTO
 * @CreateTime 2024-05-07
 */

@Getter
@Setter
@Schema(name = "MonLogsErrorAddDTO", description = "错误异常日志 新增 DTO 对象")
public class MonLogsErrorAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 9214057413441264523L;


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

    @Schema(description = "异常信息")
    private String exceptionMessage;

    @Schema(description = "异常类")
    private String exceptionClass;

    @Schema(description = "异常行号")
    private Integer line;

    @Schema(description = "堆栈信息")
    private String stackTrace;

}