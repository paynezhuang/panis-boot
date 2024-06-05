package com.izpan.common.util;

import com.google.common.collect.Sets;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * springframework 的 CollectionUtils 工具类扩展
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.util.CollectionUtil
 * @CreateTime 2024/1/10 - 23:26
 */
@SuppressWarnings("java:S2176")
public class CollectionUtil extends org.springframework.util.CollectionUtils {

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !org.springframework.util.CollectionUtils.isEmpty(collection);
    }

    /**
     * 处理两个集合的差异
     *
     * @param originSet 原始集合
     * @param newSet    新集合
     * @param handler   处理程序
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-06-04 - 23:47:43
     */
    public static <T> void handleDifference(Set<T> originSet, Set<T> newSet, BiConsumer<Set<T>, Set<T>> handler) {
        if (!originSet.equals(newSet)) {
            // 计算差异，需要新增的集合
            Set<T> addSet = Sets.difference(newSet, originSet);
            // 计算差异，需要删除的集合
            Set<T> removeSet = Sets.difference(originSet, newSet);
            handler.accept(addSet, removeSet);
        }
    }
}
