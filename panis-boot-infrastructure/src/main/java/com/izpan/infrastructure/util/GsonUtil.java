package com.izpan.infrastructure.util;

import cn.hutool.core.date.DatePattern;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.izpan.infrastructure.gson.adapter.LocalDateTimeTypeAdapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Google Gson 工具类
 *
 * @Author payne.zhuang <payne.zhuang@gosonic.com.cn>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.util.GsonUtil
 * @CreateTime 2024/4/26 - 16:47
 * @Copyright (C), 2024 Gosonic <<a href="https://www.gosonic.com.cn">https://www.gosonic.com.cn</a>>
 */
public class GsonUtil {

    private static final Gson GSON = new GsonBuilder()
            // 序列化日期格式
            .setDateFormat(DatePattern.NORM_DATETIME_PATTERN)
            // 防止特殊字符出现乱码
            .disableHtmlEscaping()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .create();

    private GsonUtil() {
    }

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 要转换的对象
     * @return JSON 字符串
     */
    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象
     *
     * @param json JSON 字符串
     * @param cls  目标对象类型
     * @param <T>  目标对象类型
     * @return 对应的对象实例
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        return GSON.fromJson(json, cls);
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象列表
     *
     * @param json JSON 字符串
     * @param <T>  目标对象类型
     * @return 对象列表
     */
    public static <T> List<T> fromJsonList(String json) {
        return GSON.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 将对象列表转换为 JSON 字符串
     *
     * @param list 对象列表
     * @param <T>  对象类型
     * @return JSON 字符串
     */
    public static <T> String toJsonList(List<T> list) {
        return GSON.toJson(list);
    }

    /**
     * 将 JSON 字符串转换为 Map 对象
     *
     * @param json JSON 字符串
     * @param <T>  Map 值类型
     * @return Map 对象
     */
    public static <T> Map<String, T> fromJsonMap(String json) {
        return GSON.fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    /**
     * 从 JSON 对象中获取指定字段的值
     *
     * @param json      JSON 对象字符串
     * @param fieldName 字段名
     * @param <T>       字段值类型
     * @return 字段值, 如果不存在该字段或者字段值为 null,则返回 null
     */
    public static <T> T getFieldValue(String json, String fieldName) {
        JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
        if (jsonObj.has(fieldName)) {
            JsonElement jsonElement = jsonObj.get(fieldName);
            if (!jsonElement.isJsonNull()) {
                return GSON.fromJson(jsonElement, new TypeToken<T>() {
                }.getType());
            }
        }
        return null;
    }
}
