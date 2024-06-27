package com.izpan.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 统一API响应结果对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.api.Result
 * @CreateTime 2023/7/8 - 16:49
 */
@Data
public class Result<T> {
    @Schema(description = "返回码")
    private int code;
    @Schema(description = "提示语")
    private String message;
    @Schema(description = "数据源")
    private T data;
    @Schema(description = "时间戳")
    private long timestamp;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功操作
     *
     * @param code    code
     * @param message message 信息
     * @param data    返回数据
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-08 17:10
     */
    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 成功操作
     *
     * @param message message 信息
     * @param data    返回数据
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-08 17:10
     */
    public static <T> Result<T> success(String message, T data) {
        return success(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 成功操作
     *
     * @param message message 信息
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-08 17:10
     */
    public static <T> Result<T> success(String message) {
        return success(ResultCode.SUCCESS.getCode(), message, null);
    }

    /**
     * 返回数据
     *
     * @param data – 返回数据
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-18 19:14
     */
    public static <T> Result<T> data(T data) {
        if (null == data) {
            return status(false);
        }
        return success(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getValue(), data);
    }

    /**
     * 成功操作
     *
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-08 17:10
     */
    public static <T> Result<T> success() {
        return success(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getValue(), null);
    }


    /**
     * 失败操作
     *
     * @param code    code
     * @param message message 信息
     * @param data    返回数据
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-08 17:10
     */
    public static <T> Result<T> failure(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 失败操作
     *
     * @param code    code
     * @param message message 信息
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-08 17:10
     */
    public static <T> Result<T> failure(int code, String message) {
        return failure(code, message, null);
    }

    /**
     * 失败操作
     *
     * @param resultCode resultCode
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-08 17:10
     */
    public static <T> Result<T> failure(ResultCode resultCode) {
        return failure(resultCode.getCode(), resultCode.getValue(), null);
    }

    /**
     * 失败操作
     *
     * @param message message 信息
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-08 17:10
     */
    public static <T> Result<T> failure(String message) {
        return failure(ResultCode.BAD_REQUEST.getCode(), message, null);
    }

    /**
     * 根据状态判断返回不同的回执对象
     *
     * @param status 状态
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-17 18:31
     */
    public static <T> Result<T> status(boolean status) {
        return Boolean.TRUE.equals(status) ? success() : failure(ResultCode.BAD_REQUEST.getValue());
    }

    /**
     * 根据状态判断返回不同的回执对象
     *
     * @param status  状态
     * @param message message 信息
     * @return {@link Result}
     * @author payne.zhuang
     * @CreateTime 2023-07-17 18:31
     */
    public static <T> Result<T> status(boolean status, String message) {
        return Boolean.TRUE.equals(status) ? success(message) : failure(ResultCode.BAD_REQUEST.getValue());
    }
}
