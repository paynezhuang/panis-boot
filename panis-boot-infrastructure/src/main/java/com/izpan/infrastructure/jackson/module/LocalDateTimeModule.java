package com.izpan.infrastructure.jackson.module;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.izpan.infrastructure.annotation.Timestamp;
import com.izpan.infrastructure.util.AnnotationUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.io.Serial;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Jackson LocalDateTime 序列化处理
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.jackson.module.LocalDateTimeModule
 * @CreateTime 2023/7/10 - 13:58
 */
@Slf4j
public class LocalDateTimeModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = -4581480922782892740L;

    public LocalDateTimeModule() {
        super();
        // 序列化时，当值为 LocalDateTime 类型时，转换为数值类型
        addSerializer(LocalDateTime.class, new JsonSerializer<>() {

            @Override
            @SneakyThrows
            public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) {
                gen.writeNumber(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            }

            @Override
            public void serializeWithType(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
                WritableTypeId typeIdDef = typeSer.typeId(value, JsonToken.VALUE_NUMBER_INT);
                typeSer.writeTypePrefix(gen, typeIdDef);
                serialize(value, gen, serializers);
                typeSer.writeTypeSuffix(gen, typeIdDef);
            }
        });


        // 反序列化时，当值为字符串类型并且能转换为 LocalDateTime 类型时，转换为 LocalDateTime 类型
        addDeserializer(LocalDateTime.class, new JsonDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
                if (ObjectUtils.isEmpty(p.getValueAsString())) {
                    return null;
                }
                // 如果当前 JSON 令牌是字符串类型，则将其解析为 LocalDateTime 对象。
                if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
                    return LocalDateTime.parse(p.getValueAsString(), ofPattern(DatePattern.NORM_DATETIME_PATTERN));
                }
                // 默认 JSON 令牌是数值类型，则将其解析为 LocalDateTime 对象。
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(p.getLongValue()), ZoneId.systemDefault());
            }
        });


    }

    /**
     * 是否有时间戳注解
     *
     * @param gen JsonGenerator
     * @return boolean 是否有时间戳注解
     * @author payne.zhuang
     * @CreateTime 2024-11-12 - 11:04:20
     */
    @SneakyThrows
    private boolean withTimestampAnnotation(JsonGenerator gen) {
        String fieldName = gen.getOutputContext().getCurrentName();
        Class<?> currentClass = gen.currentValue().getClass();
        return AnnotationUtil.hasAnnotation(currentClass, fieldName, Timestamp.class);
    }

}
