package com.izpan.infrastructure.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.izpan.infrastructure.jackson.module.BigDecimalAsStringModule;
import com.izpan.infrastructure.jackson.module.CustomJavaTimeModule;
import com.izpan.infrastructure.jackson.module.LocalDateTimeModule;
import com.izpan.infrastructure.jackson.module.LongAsStringModule;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Jackson ObjectMapper 工厂类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.factory.ObjectMapperFactory
 * @CreateTime 2024/4/21 - 20:23
 */

@SuppressWarnings("java:S125")
public class ObjectMapperFactory {

    private static ObjectMapper objectMapper;

    private ObjectMapperFactory() {

    }

    public static synchronized ObjectMapper getCustomObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            // 设置日期格式
            objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
            // 设置地点为中国
            objectMapper.setLocale(Locale.CHINA);
            // 配置 Jackson 序列化和反序列化时使用的时区
            objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            // Java对象为空的字段不拼接JSON
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            // 忽略 NULL 值序列化
            // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            // 开启特定的序列化和反序列化特性
            objectMapper.enable(
                    // 开启单引号特性，允许单引号包裹字段名和String类型的值
                    JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                    // 开启不带引号的字段名，允许字段名不使用双引号包裹
                    JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
                    // 忽略未定义的字段，解决反序列化时输入 JSON 中包含未定义字段导致的异常问题
                    JsonParser.Feature.IGNORE_UNDEFINED);

            // 当序列化完成后自动关闭生成器的目标输出流，防止资源泄漏
            objectMapper.enable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
            // 序列化枚举时使用 toUpperCase() 方法输出大写字母
            objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

            objectMapper.enable(
                    // 反序列化枚举时，允许使用枚举的 toString() 方法来识别枚举值
                    DeserializationFeature.READ_ENUMS_USING_TO_STRING,
                    // 允许单个值作为数组处理，解决反序列化时期望是数组但实际只有单个值的情况
                    DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

            // 禁用特定的序列化和反序列化特性
            // 去掉默认的时间戳格式
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            // 忽略入参没有任何属性导致的序列化报错
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            // 反序列化时，忽略Javabean中不存在的属性，而不是抛出异常
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            // 设置 ObjectMapper 启用默认类型信息，并指定类型验证器为LaissezFaireSubTypeValidator.instance，
            // 默认类型为NON_FINAL，类型信息序列化为属性。这样可以在序列化和反序列化过程中保留类型信息，使得可以正确地还原对象的类型。
            objectMapper.activateDefaultTyping(
                    LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

            // 注册模块
            objectMapper.registerModules(
                    new CustomJavaTimeModule(),
                    new LocalDateTimeModule(),
                    new LongAsStringModule(),
                    new BigDecimalAsStringModule());
        }
        return objectMapper;
    }
}
