package com.izpan.infrastructure.gson.adapter;

import cn.hutool.core.date.DatePattern;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime 类型适配器
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.gson.adapter.LocalDateTimeTypeAdapter
 * @CreateTime 2024/4/27 - 19:56
 */
public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);

    @Override
    public JsonElement serialize(final LocalDateTime date, final Type typeOfSrc,
                                 final JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DATE_TIME_FORMATTER));
    }

    @Override
    public LocalDateTime deserialize(final JsonElement json, final Type typeOfT,
                                     final JsonDeserializationContext context) throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(), DATE_TIME_FORMATTER);
    }
}
