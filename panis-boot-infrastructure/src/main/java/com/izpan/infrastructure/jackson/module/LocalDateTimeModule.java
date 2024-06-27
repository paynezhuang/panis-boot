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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.io.Serial;
import java.time.LocalDateTime;

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
        // 序列化时，当值为 LocalDateTime 类型时，转换为字符串类型
        addSerializer(LocalDateTime.class, new JsonSerializer<>() {
            @Override
            public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.format(ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
            }

            @Override
            public void serializeWithType(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
                WritableTypeId typeIdDef = typeSer.typeId(value, JsonToken.VALUE_STRING);
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
                return LocalDateTime.parse(p.getValueAsString(), ofPattern(DatePattern.NORM_DATETIME_PATTERN));
            }
        });
    }

}
