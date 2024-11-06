package com.izpan.infrastructure.util;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringUtil 是一个standalone的工具类,用于获取Spring容器中的Bean
 * 它实现了ApplicationContextAware接口,可以在应用启动时
 * 通过调用setApplicationContext方法设置ApplicationContext
 * 来完成初始化
 * <p>
 * 在应用启动后,就可以通过getBean方法从静态方法获取容器中的Bean
 * 避免在类中注入Spring Bean,保持纯静态工具类的特性
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.util.SpringUtil
 * @CreateTime 2023/8/15 - 13:58
 */
@Component
@SuppressWarnings({"unchecked", "java:S1905"})
public class SpringUtil implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    private SpringUtil() {

    }

    /*
     * 通过传递applicationContext参数初始化成员变量applicationContext
     */
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }


    public static <T> T getBean(String name) throws BeansException {
        return (T) getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return (T) getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return (T) getApplicationContext().getBean(name, clazz);
    }
}
