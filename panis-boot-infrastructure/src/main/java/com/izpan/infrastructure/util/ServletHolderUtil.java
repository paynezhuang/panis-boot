package com.izpan.infrastructure.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 客户端工具类
 * 获取前请求的HttpServletRequest和HttpServletResponse对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.util.ServletHolderUtil
 * @CreateTime 2023/7/18 - 10:29
 */
public class ServletHolderUtil {

    private ServletHolderUtil() {
    }

    /**
     * 获取当前请求的HttpServletRequest对象
     *
     * @return {@link HttpServletRequest} 当前请求的HttpServletRequest对象
     * @throws IllegalStateException 如果当前上下文中没有RequestAttributes
     * @author payne.zhuang
     * @CreateTime 2023-07-18 10:34
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取当前请求的HttpServletResponse对象
     *
     * @return {@link HttpServletResponse} 当前请求的HttpServletResponse对象
     * @throws IllegalStateException 如果当前上下文中没有RequestAttributes
     * @author payne.zhuang
     * @CreateTime 2023-07-18 10:37
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 从RequestContextHolder中获取ServletRequestAttributes
     *
     * @return {@link ServletRequestAttributes} ServletRequestAttributes对象
     * @throws IllegalStateException 如果获取不到RequestAttributes
     * @author payne.zhuang
     * @CreateTime 2023-07-18 10:32
     */
    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new IllegalStateException("当前请求上下文中不存在RequestAttributes");
        }
        return (ServletRequestAttributes) attributes;
    }

}
