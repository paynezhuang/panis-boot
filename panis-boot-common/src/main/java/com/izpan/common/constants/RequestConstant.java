package com.izpan.common.constants;

/**
 * 请求常量
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.constants.RequestConstant
 * @CreateTime 2023/7/19 - 17:08
 */
public final class RequestConstant {

    private RequestConstant() {

    }

    // 头部 token 标识
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String REFRESH_NONCE = "Refresh-Nonce";
    public static final String REFRESH = "P-Request-Refresh";

    public static final String CONTENT_TYPE_NAME = "Content-Type";

    public static final String CONTENT_TYPE = "application/json;charset=utf-8";

    public static final String REQUEST_ID = "P-Request-Id";

    public static final String LANGUAGE = "P-Language";

    public static final String USER_AGENT = "User-Agent";
}
