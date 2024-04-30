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

import java.io.IOException;
import java.io.Serial;
import java.math.BigDecimal;

/**
 * BigDecimal 大精度类型序列化，避免超过js的精度，造成精度丢失
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.jackson.module.BigDecimalAsStringModule
 * @CreateTime 2023/7/9 - 16:48
 */
public class BigDecimalAsStringModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = 8383882851973093L;

    public BigDecimalAsStringModule() {
        super();
        // 序列化时，当值为 BigDecimal 类型时，转换为字符串类型
        addSerializer(BigDecimal.class, new JsonSerializer<>() {
            @Override
            public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if (value.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) > 0
                        || (value).compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) < 0) {
                    gen.writeString(value.toString());
                } else {
                    gen.writeNumber(value.toString());
                }
            }

            @Override
            public void serializeWithType(BigDecimal value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
                WritableTypeId typeIdDef = typeSer.typeId(value, JsonToken.VALUE_STRING);
                typeSer.writeTypePrefix(gen, typeIdDef);
                serialize(value, gen, serializers);
                typeSer.writeTypeSuffix(gen, typeIdDef);
            }
        });

        // 反序列化时，当值为字符串类型并且能转换为 BigDecimal 类型时，转换为 BigDecimal 类型
        addDeserializer(BigDecimal.class, new JsonDeserializer<>() {
            @Override
            public BigDecimal deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
                String value = p.getValueAsString();
                try {
                    return new BigDecimal(value);
                } catch (NumberFormatException e) {
                    throw new InvalidFormatException(p, "Invalid numeric value: " + value, value, BigDecimal.class);
                }
            }
        });
    }
}