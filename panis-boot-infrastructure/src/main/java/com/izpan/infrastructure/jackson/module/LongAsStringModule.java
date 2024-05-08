package com.izpan.infrastructure.jackson.module;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serial;

/**
 * Long 大精度类型序列化，避免超过js的精度，造成精度丢失
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.jackson.module.LongAsStringModule
 * @CreateTime 2023/7/9 - 16:46
 */
@Slf4j
public class LongAsStringModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = -4168037594159276647L;

    public LongAsStringModule() {
        super();
        // 序列化时，当值为 Long 类型时，转换为字符串类型
        addSerializer(Long.class, new JsonSerializer<>() {
            @Override
            public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
                    gen.writeString(value.toString());
                } else {
                    gen.writeNumber(value);
                }
            }

            @Override
            public void serializeWithType(Long value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
                WritableTypeId typeIdDef = typeSer.typeId(value, JsonToken.VALUE_STRING);
                typeSer.writeTypePrefix(gen, typeIdDef);
                serialize(value, gen, serializers);
                typeSer.writeTypeSuffix(gen, typeIdDef);
            }
        });

        // 反序列化时，当值为字符串类型并且能转换为 Long 类型时，转换为 Long 类型
        addDeserializer(Long.class, new JsonDeserializer<>() {
            @Override
            public Long deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
                String value = p.getValueAsString();
                try {
                    return Long.parseLong(value);
                } catch (NumberFormatException e) {
                    throw new InvalidFormatException(p, "Invalid numeric value: " + value, value, Long.class);
                }
            }
        });
    }
}
