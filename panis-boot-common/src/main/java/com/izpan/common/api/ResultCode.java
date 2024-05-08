package com.izpan.common.api;

import lombok.Getter;

/**
 * 自定义返回对象枚举信息
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.api.ResultCode
 * @CreateTime 2023/7/8 - 17:01
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "操作失败"),
    UNAUTHORIZED(401, "登录状态未登录或已过期，请重新登陆"),
    REPLACED_ANOTHER_LOGIN(401, "您的账号在其他地方登录"),
    UNAUTHORIZED_REFRESH(40101, "Token expired"),
    FORBIDDEN(403, "无权访问 %s"),
    NOT_FOUND(404, "请求资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    USER_ROUTE_ERROR(600, "用户路由异常"),
    ;

    private final int code;
    private final String value;

    ResultCode(int code, String value) {
        this.code = code;
        this.value = value;
    }

}
