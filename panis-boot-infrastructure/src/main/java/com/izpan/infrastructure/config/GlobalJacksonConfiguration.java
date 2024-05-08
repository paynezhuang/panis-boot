package com.izpan.infrastructure.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.izpan.infrastructure.jackson.module.*;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Jackson 全局配置
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.config.GlobalJacksonConfiguration
 * @CreateTime 2023/7/9 - 15:13
 */
@Configuration
public class GlobalJacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder ->
                builder
                        // 设置日期格式
                        .dateFormat(new StdDateFormat().withColonInTimeZone(true))
                        // 设置地点为中国
                        .locale(Locale.CHINA)
                        // 配置 Jackson 序列化和反序列化时使用的时区
                        // 默认使用系统默认时区，可以根据需求设置
                        .timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                        // Java对象为空的字段不拼接JSON
                        .serializationInclusion(JsonInclude.Include.ALWAYS)

                        .featuresToEnable(
                                // 开启单引号特性，允许单引号包裹字段名和String类型的值
                                JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                                // 开启不带引号的字段名，允许字段名不使用双引号包裹
                                JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
                                // 忽略未定义的字段，解决反序列化时输入 JSON 中包含未定义字段导致的异常问题
                                JsonParser.Feature.IGNORE_UNDEFINED,
                                // 当序列化完成后自动关闭生成器的目标输出流，防止资源泄漏
                                JsonGenerator.Feature.AUTO_CLOSE_TARGET,
                                // 序列化枚举时使用 toUpperCase() 方法输出大写字母
                                SerializationFeature.WRITE_ENUMS_USING_TO_STRING,
                                // 反序列化枚举时，允许使用枚举的 toString() 方法来识别枚举值
                                DeserializationFeature.READ_ENUMS_USING_TO_STRING,
                                // 允许单个值作为数组处理，解决反序列化时期望是数组但实际只有单个值的情况
                                DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY
                        )

                        .featuresToDisable(
                                // 去掉默认的时间戳格式
                                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                                // 忽略入参没有任何属性导致的序列化报错
                                SerializationFeature.FAIL_ON_EMPTY_BEANS,
                                // 反序列化时，忽略Javabean中不存在的属性，而不是抛出异常
                                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
                        )

                        // Java 8 时间类型 , 避免大精度数字丢失 Module , LocalDateTime 时间 Module, NULL 值 Module
                        .modulesToInstall(
                                new CustomJavaTimeModule(),
                                new LocalDateTimeModule(),
                                new NullSerializersModule(),
                                new LongAsStringModule(),
                                new BigDecimalAsStringModule());

    }

}
