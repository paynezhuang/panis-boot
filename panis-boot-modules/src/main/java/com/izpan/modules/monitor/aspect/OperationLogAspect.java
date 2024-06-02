package com.izpan.modules.monitor.aspect;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.google.common.collect.Lists;
import com.izpan.common.constants.RequestConstant;
import com.izpan.common.util.CglibUtil;
import com.izpan.common.util.IPUtil;
import com.izpan.infrastructure.util.GsonUtil;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorAddDTO;
import com.izpan.modules.monitor.domain.dto.logs.operation.MonLogsOperationAddDTO;
import com.izpan.modules.monitor.facade.IMonLogsErrorFacade;
import com.izpan.modules.monitor.facade.IMonLogsOperationFacade;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * 操作/错误异常日志切面
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.aspect.OperationLogAspect
 * @CreateTime 2024/5/6 - 22:31
 */

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    /**
     * 操作日志业务处理对象
     */
    private final ThreadLocal<MonLogsOperationAddDTO> logsOperationAddDTO = new ThreadLocal<>();

    /**
     * 定义一个开始时间
     */
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Resource
    private IMonLogsOperationFacade monLogsOperationFacade;

    @Resource
    private IMonLogsErrorFacade monLogsErrorFacade;

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.izpan.*.controller.*.*Controller.*(..))")
    public void controllerPoint() {
    }

    @SneakyThrows
    @Before("controllerPoint()")
    public void beforeLog(JoinPoint point) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取 request 信息
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        // 获取 请求方法 Method
        String requestMethod = request.getMethod();
        // 如果是 GET 或者 OPTIONS 请求，直接返回
        if ("OPTIONS".equals(requestMethod) || "GET".equals(requestMethod)) {
            return;
        }
        // 获取 contentType
        String contentType = request.getHeader(RequestConstant.CONTENT_TYPE_NAME);
        String requestId = request.getHeader(RequestConstant.X_REQUEST_ID);
        // 获取请求 URI
        String requestURI = request.getRequestURI();
        MethodSignature ms = (MethodSignature) point.getSignature();
        // 获取方法上的注解
        Operation operation = ms.getMethod().getDeclaredAnnotation(Operation.class);
        // 获取请求参数
        Object[] args = point.getArgs();
        // 将参数所在的数组转换成json
        List<String> arguments = Lists.newArrayList();
        // 过滤报异常的参数
        for (Object arg : args) {
            if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof MultipartFile || arg instanceof MultipartFile[]) {
                continue;
            }
            arguments.add(GsonUtil.toJson(arg));
        }
        String ip = JakartaServletUtil.getClientIP(request);
        logsOperationAddDTO.set(MonLogsOperationAddDTO.builder()
                .requestId(requestId)
                .requestUri(requestURI)
                .requestMethod(requestMethod)
                .methodName(ms.getName())
                .operation(operation.summary())
                .contentType(contentType)
                .methodParams(GsonUtil.toJson(arguments))
                .ip(ip)
                .ipAddr(IPUtil.getIpAddr(ip))
                .userAgent(request.getHeader(RequestConstant.USER_AGENT))
                .build());
    }

    /**
     * 后置操作
     */
    @AfterReturning(pointcut = "controllerPoint()", returning = "result")
    public void afterReturning(Object result) {
        try {
            MonLogsOperationAddDTO addDTO = logsOperationAddDTO.get();
            if (ObjectUtils.isNotEmpty(addDTO)) {
                addDTO.setUseTime(System.currentTimeMillis() - startTime.get());
                monLogsOperationFacade.add(addDTO);
            }
        } finally {
            remove();
        }
    }

    /**
     * 异常 方法
     */
    @AfterThrowing(pointcut = "controllerPoint()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        try {
            MonLogsOperationAddDTO addDTO = logsOperationAddDTO.get();
            if (ObjectUtils.isNotEmpty(addDTO)) {
                MonLogsErrorAddDTO logsErrorAddDTO = CglibUtil.convertObj(addDTO, MonLogsErrorAddDTO::new);
                StackTraceElement stackTraceElement = exception.getStackTrace()[0];
                logsErrorAddDTO.setLine(stackTraceElement.getLineNumber());
                logsErrorAddDTO.setExceptionMessage(exception.getMessage());
                logsErrorAddDTO.setExceptionClass(stackTraceElement.getClassName());
                logsErrorAddDTO.setStackTrace(Matcher.quoteReplacement(Arrays.toString(exception.getStackTrace())));
                logsErrorAddDTO.setUseTime(System.currentTimeMillis() - startTime.get());
                monLogsErrorFacade.add(logsErrorAddDTO);
            }
        } finally {
            remove();
        }
    }

    private void remove() {
        logsOperationAddDTO.remove();
        startTime.remove();
    }
}
