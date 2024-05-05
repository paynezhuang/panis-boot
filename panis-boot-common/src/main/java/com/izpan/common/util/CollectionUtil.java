package com.izpan.common.util;

import org.springframework.lang.Nullable;

import java.util.Collection;

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
}
