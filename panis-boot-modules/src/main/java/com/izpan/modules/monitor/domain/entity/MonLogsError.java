package com.izpan.modules.monitor.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 错误异常日志 Entity 实体类
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
@TableName("mon_logs_error")
public class MonLogsError extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1882454550101779208L;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * IP
     */
    private String ip;

    /**
     * IP所属地
     */
    private String ipAddr;

    /**
     * 登录代理
     */
    private String userAgent;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求内容类型
     */
    private String contentType;

    /**
     * 接口说明
     */
    private String operation;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求参数
     */
    private String methodParams;

    /**
     * 请求耗时
     */
    private Long useTime;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    /**
     * 异常类
     */
    private String exceptionClass;

    /**
     * 异常行号
     */
    private Integer line;

    /**
     * 堆栈信息
     */
    private String stackTrace;
}