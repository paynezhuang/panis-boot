package com.izpan.infrastructure.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.izpan.common.api.Result;
import com.izpan.common.api.ResultCode;
import com.izpan.common.exception.BizException;
import com.izpan.common.exception.LoginException;
import com.izpan.common.exception.RouteException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 全局异常捕获
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.handler.GlobalExceptionHandler
 * @CreateTime 2023/7/8 - 18:42
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 对应:<a href="https://sa-token.cc/doc.html#/fun/not-login-scene">https://sa-token.cc/doc.html#/fun/not-login-scene</a>
     */
    private static final Map<Integer, ResultCode> NOT_LOGIN_RESULT_CODE_MAP = Map.of(
            11011, ResultCode.UNAUTHORIZED,
            11012, ResultCode.UNAUTHORIZED,
            11013, ResultCode.UNAUTHORIZED_REFRESH,
            11014, ResultCode.REPLACED_ANOTHER_LOGIN,
            11015, ResultCode.REPLACED_ANOTHER_LOGIN,
            11016, ResultCode.UNAUTHORIZED,
            11017, ResultCode.UNAUTHORIZED
    );

    /**
     * 业务异常
     *
     * @param bizException 异常对象
     * @return {@link Result} 统一返回结果
     * @author payne.zhuang
     * @CreateTime 2023-07-08 18:57
     */
    @ResponseBody
    @ExceptionHandler(BizException.class)
    public Result<Object> handleBizException(@NonNull BizException bizException) {
        log.error("[业务异常]{}", bizException.getMessage(), bizException);
        return Result.failure(bizException.getMsg());
    }

    /**
     * 用户路由异常
     *
     * @param routeException 异常对象
     * @return {@link Result} 统一返回结果
     * @author payne.zhuang
     * @CreateTime 2024-04-20 22:31:18
     */
    @ResponseBody
    @ExceptionHandler(RouteException.class)
    public Result<Object> handleRouteException(@NonNull RouteException routeException) {
        log.error("[路由异常]{}", routeException.getMessage(), routeException);
        return Result.failure(routeException.getCode(), routeException.getMsg());
    }

    /**
     * 全局异常
     *
     * @param exception 异常信息
     * @return {@link Result} 统一返回结果
     * @author payne.zhuang
     * @CreateTime 2023-07-08 18:57
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<Object> handleException(@NonNull Exception exception) {
        log.error("[系统异常]{}", exception.getMessage(), exception);
        return Result.failure(ResultCode.INTERNAL_SERVER_ERROR.getCode(), ResultCode.INTERNAL_SERVER_ERROR.getValue());
    }

    /**
     * 用户未登录异常
     *
     * @param notLoginException 异常对象
     * @return {@link Result} 统一返回结果
     * @author payne.zhuang
     * @CreateTime 2024-04-22 10:50:34
     */
    @ResponseBody
    @ExceptionHandler(NotLoginException.class)
    public Result<Object> handleNotLoginException(@NonNull NotLoginException notLoginException) {
        log.error("[未登录异常]{}", notLoginException.getMessage(), notLoginException);
        return Result.failure(NOT_LOGIN_RESULT_CODE_MAP.get(notLoginException.getCode()));
    }

    /**
     * 无权限登录异常
     *
     * @param notPermissionException 异常对象
     * @return {@link Result} 统一返回结果
     * @author payne.zhuang
     * @CreateTime 2024-04-22 10:50:34
     */
    @ResponseBody
    @ExceptionHandler(NotPermissionException.class)
    public Result<Object> handleNotLoginException(@NonNull NotPermissionException notPermissionException) {
        log.error("[无权限异常]{}", notPermissionException.getMessage(), notPermissionException);
        return Result.failure(ResultCode.FORBIDDEN.getValue().formatted(notPermissionException.getPermission()));
    }

    /**
     * jwt token 失效异常（可刷新）
     *
     * @param loginException 异常信息
     * @return {@link Result} 统一返回结果
     * @author payne.zhuang
     * @CreateTime 2023-08-15 18:02:02
     */
    @ResponseBody
    @ExceptionHandler(value = LoginException.class)
    public Result<Object> handleLoginException(@NonNull LoginException loginException) {
        log.error("[登录失效]{}", loginException.getMessage(), loginException);
        return Result.failure(ResultCode.UNAUTHORIZED_REFRESH.getCode(), ResultCode.UNAUTHORIZED.getValue());
    }

}
