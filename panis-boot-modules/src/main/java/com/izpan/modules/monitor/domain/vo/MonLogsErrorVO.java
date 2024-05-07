package com.izpan.modules.monitor.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 错误异常日志 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsError
 * @CreateTime 2024-05-07
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MonLogsErrorVO", description = "错误异常日志 VO 对象")
public class MonLogsErrorVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = -6896573621300559511L;

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