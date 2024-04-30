package com.izpan.common.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Cglib 工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.util.CglibUtil
 * @CreateTime 2023/7/10 - 16:44
 */
@Slf4j
public class CglibUtil {

    // 单例模式，私有化构造函数
    private CglibUtil() {

    }

    // 使用线程安全的ConcurrentHashMap缓存BeanCopier实例，提高性能
    private static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * 将源对象列表转换为目标对象列表
     *
     * @param sources  源对象列表
     * @param supplier 目标对象的供应商，用于创建目标对象实例
     * @param <S>      源对象类型
     * @param <T>      目标对象类型
     * @return 目标对象列表
     * @author payne.zhuang
     * @CreateTime 2023-07-10 17:50
     */
    @SneakyThrows
    public static <S, T> List<T> convertList(List<S> sources, Supplier<T> supplier) {
        // 初始化目标列表，预设大小为源列表大小
        List<T> list = new ArrayList<>(sources.size());
        // 源列表为空直接返回空的目标列表
        if (CollectionUtils.isEmpty(sources)) {
            return list;
        }
        // 为源对象和目标对象的类生成BeanCopier，只生成一次
        BeanCopier beanCopier = getBeanCopier(sources.getFirst().getClass(), supplier.get().getClass());
        sources.forEach(source -> {
            // 创建一个新的目标对象
            T t = supplier.get();
            // 复制源对象到目标对象
            beanCopier.copy(source, t, null);
            // 添加到目标列表
            list.add(t);
        });
        return list;
    }

    /**
     * 将源对象复制到目标对象
     *
     * @param supplier 目标对象
     * @param <S>      源对象类型
     * @param <T>      目标对象类型
     * @return 转换的函数，接受源对象作为参数，返回目标对象
     * @author payne.zhuang
     * @CreateTime 2023-07-10 17:48
     */
    public static <S, T> Function<S, T> convertObj(Supplier<T> supplier) {
        return source -> convertObj(source, supplier);
    }

    /**
     * 将源对象复制到目标对象
     *
     * @param source   原对象
     * @param supplier 目标对象
     * @return 目标对象
     * @author payne.zhuang
     * @CreateTime 2023-07-10 17:47
     */
    public static <T> T convertObj(Object source, Supplier<T> supplier) {
        return Optional.ofNullable(source)
                .map(s -> {
                    // 创建一个新的目标对象
                    T t = supplier.get();
                    // 获得相应的BeanCopier
                    BeanCopier beanCopier = getBeanCopier(s.getClass(), t.getClass());
                    // 使用BeanCopier复制源对象到目标对象
                    beanCopier.copy(s, t, null);
                    // 返回目标对象
                    return t;
                })
                .orElse(null);
    }

    /**
     * 获取对应源类和目标类的BeanCopier，如果不存在则创建一个并加入到缓存中
     *
     * @param sourceClass 源类
     * @param targetClass 目标类
     * @return {@link BeanCopier} 实例
     * @author payne.zhuang
     * @CreateTime 2023-07-10 17:49
     */
    private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        String key = getKey(sourceClass.getName(), targetClass.getName());
        // ConcurrentHashMap的computeIfAbsent方法保证原子操作，无需额外的同步控制
        return BEAN_COPIER_MAP.computeIfAbsent(key, k ->
                BeanCopier.create(sourceClass, targetClass, false));
    }

    /**
     * 生成缓存的key，简单的将源类名和目标类名进行字符串拼接
     *
     * @param sourceClassName 源对象名称
     * @param targetClassName 目标对象名称
     * @return {@link String} Key
     * @author payne.zhuang
     * @CreateTime 2023-07-10 17:48
     */
    private static String getKey(String sourceClassName, String targetClassName) {
        return sourceClassName + targetClassName;
    }
}
