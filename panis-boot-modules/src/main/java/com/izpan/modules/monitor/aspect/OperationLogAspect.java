package com.izpan.modules.monitor.aspect;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.google.common.collect.Lists;
import com.izpan.common.constants.RequestConstant;
import com.izpan.common.util.IPUtil;
import com.izpan.infrastructure.util.GsonUtil;
import com.izpan.modules.monitor.domain.dto.logs.operation.MonLogsOperationAddDTO;
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

import java.util.List;
import java.util.Objects;

/**
 * 操作日志切面
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
    MonLogsOperationAddDTO logsOperationAddDTO;

    @Resource
    private IMonLogsOperationFacade monLogsOperationFacade;

    /**
     * 定义一个开始时间
     */
    Long startTime = null;

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.izpan.*.controller.*.*Controller.*(..))")
    public void controllerPoint() {
    }

    @SneakyThrows
    @Before("controllerPoint()")
    public void beforeLog(JoinPoint point) {
        log.info("controller beforeLog...");
        startTime = System.currentTimeMillis();
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
        logsOperationAddDTO = MonLogsOperationAddDTO.builder()
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
                .build();
    }

    /**
     * 后置操作
     */
    @AfterReturning(pointcut = "controllerPoint()", returning = "result")
    public void afterReturning(Object result) {
        log.info("controller after returning...");
        if (ObjectUtils.isNotEmpty(logsOperationAddDTO)) {
            long useTime = System.currentTimeMillis() - startTime;
            logsOperationAddDTO.setUseTime(useTime);
            monLogsOperationFacade.add(logsOperationAddDTO);
        }
    }

    /**
     * controller 方法
     */
    @AfterThrowing(pointcut = "controllerPoint()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.info("controller after throwing...");
    }
}