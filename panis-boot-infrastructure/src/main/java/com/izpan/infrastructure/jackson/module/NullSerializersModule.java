package com.izpan.infrastructure.jackson.module;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.izpan.common.pool.StringPools;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serial;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Jackson Null 值处理
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.jackson.module.NullSerializersModule
 * @CreateTime 2023/7/9 - 20:59
 */
@Slf4j
public class NullSerializersModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = -240900050355011960L;

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new BeanSerializerModifier() {
            @Override
            public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
                beanProperties.forEach(propertyWriter -> {
                    JavaType javaType = propertyWriter.getType();
                    if (javaType.isTypeOrSubTypeOf(String.class) || javaType.isTypeOrSubTypeOf(CharTypes.class) ||
                            javaType.isTypeOrSubTypeOf(OffsetDateTime.class) || javaType.isTypeOrSubTypeOf(Date.class) ||
                            javaType.isTypeOrSubTypeOf(TemporalAccessor.class)) {
                        propertyWriter.assignNullSerializer(JsonSerializerConstants.STRING_JSON_SERIALIZER);
                    } else if (javaType.isTypeOrSubTypeOf(Number.class)) {
                        propertyWriter.assignNullSerializer(JsonSerializerConstants.NUMBER_JSON_SERIALIZER);
                    } else if (javaType.getRawClass().equals(boolean.class) || javaType.getRawClass().equals(Boolean.class)) {
                        propertyWriter.assignNullSerializer(JsonSerializerConstants.BOOLEAN_JSON_SERIALIZER);
                    } else if ((javaType.isArrayType() || javaType.isTypeOrSubTypeOf(Collection.class) || javaType.isCollectionLikeType())) {
                        propertyWriter.assignNullSerializer(JsonSerializerConstants.ARRAY_JSON_SERIALIZER);
                    } else if (javaType.isMapLikeType() || javaType.getRawClass().equals(Map.class)) {
                        propertyWriter.assignNullSerializer(JsonSerializerConstants.MAP_JSON_SERIALIZER);
                    } else {
                        propertyWriter.assignNullSerializer(JsonSerializerConstants.OBJECT_JSON_SERIALIZER);
                    }
                });
                return super.changeProperties(config, beanDesc, beanProperties);
            }
        });
    }

    /**
     * JSON 序列化字段常量累
     *
     * @author payne.zhuang
     * @CreateTime 2023-07-10 12:11
     */
    private static final class JsonSerializerConstants {

        /**
         * map
         */
        public static final JsonSerializer<Object> MAP_JSON_SERIALIZER = new JsonSerializer<>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                gen.writeEndObject();
            }
        };
        /**
         * string
         */
        public static final JsonSerializer<Object> STRING_JSON_SERIALIZER = new JsonSerializer<>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(StringPools.EMPTY);
            }
        };
        /**
         * number
         */
        public static final JsonSerializer<Object> NUMBER_JSON_SERIALIZER = new JsonSerializer<>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeNumber(0);
            }
        };
        /**
         * boolean
         */
        public static final JsonSerializer<Object> BOOLEAN_JSON_SERIALIZER = new JsonSerializer<>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeBoolean(false);
            }
        };
        /**
         * array
         */
        public static final JsonSerializer<Object> ARRAY_JSON_SERIALIZER = new JsonSerializer<>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartArray();
                gen.writeEndArray();
            }
        };
        /**
         * object
         */
        public static final JsonSerializer<Object> OBJECT_JSON_SERIALIZER = new JsonSerializer<>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                gen.writeEndObject();
            }
        };

        private JsonSerializerConstants() {

        }
    }
}
