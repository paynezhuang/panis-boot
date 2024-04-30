package com.izpan.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.izpan.infrastructure.factory.ObjectMapperFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.util.List;
import java.util.Map;

/**
 * Jackson 序列化反序列化工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.util.JacksonUtil
 * @CreateTime 2023/7/18 - 14:06
 */

@Slf4j
public class JacksonUtil {

    private static final ObjectMapper objectMapper = ObjectMapperFactory.getCustomObjectMapper();

    private JacksonUtil() {

    }

    /**
     * 将对象序列化为 JSON 字符串
     *
     * @param object 要序列化的对象
     * @return {@link String} 序列化后的 JSON 字符串
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:37
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e);
        }
    }

    /**
     * 将对象数组序列化为 JSON 字符串
     *
     * @param array 要序列化的对象数组
     * @return {@link String} 序列化后的 JSON 字符串
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:37
     */
    public static String toJson(Object[] array) {
        try {
            return objectMapper.writeValueAsString(array);
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e);
        }
    }

    /**
     * 将 List 对象序列化为 JSON 字符串
     *
     * @param list 要序列化的 List 对象
     * @return {@link String} 序列化后的 JSON 字符串
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:38
     */
    public static String toJson(List<?> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e);
        }
    }

    /**
     * 将字节数组序列化为 JSON 字符串
     *
     * @param bytes 要序列化的字节数组
     * @return {@link String} 序列化后的 JSON 字符串
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:38
     */
    public static String toJson(byte[] bytes) {
        try {
            return objectMapper.writeValueAsString(bytes);
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e);
        }
    }

    /**
     * 将对象转换为对象
     *
     * @param object    要序列化的对象
     * @param valueType 要序列化的对象类型
     * @param <T>       泛型标记
     * @return {@link T} 反序列化后的对象
     * @author payne.zhuang
     * @CreateTime 2023-08-13 17:20
     */
    public static <T> T toObject(Object object, Class<T> valueType) {
        return objectMapper.convertValue(object, valueType);
    }

    /**
     * 将 JSON 字符串反序列化为指定类型的对象
     *
     * @param json      JSON 字符串
     * @param <T>       泛型标记
     * @param valueType 要反序列化的对象类型
     * @return {@link T} 反序列化后的对象
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:39
     */
    public static <T> T jsonToObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e);
        }
    }

    /**
     * 将 JSON 字符串反序列化为指定类型的对象数组
     *
     * @param json      JSON 字符串
     * @param <T>       泛型标记
     * @param valueType 要反序列化的对象类型
     * @return {@link List<T>} 反序列化后的对象数组
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:39
     */
    public static <T> List<T> jsonToList(String json, Class<T> valueType) {
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, valueType);
        return objectMapper.convertValue(json, javaType);
    }


    /**
     * 将 object 对象反序列化为指定类型的对象数组
     *
     * @param object    object 对象
     * @param <T>       泛型标记
     * @param valueType 要反序列化的对象类型
     * @return {@link List<T>} 反序列化后的对象数组
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:39
     */
    public static <T> List<T> objectToList(Object object, Class<T> valueType) {
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, valueType);
        return objectMapper.convertValue(object, javaType);
    }

    /**
     * 将 JSON 字符串反序列化为指定类型的对象数组
     *
     * @param json      JSON 字符串
     * @param <T>       泛型标记
     * @param valueType 要反序列化的对象类型
     * @return {@link T[]} 反序列化后的对象数组
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:40
     */
    public static <T> T[] jsonToArray(String json, Class<T> valueType) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructArrayType(valueType);
            return objectMapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e);
        }
    }

    /**
     * 将 object 对象反序列化为指定类型的对象数组
     *
     * @param object    object 对象
     * @param <T>       泛型标记
     * @param valueType 要反序列化的对象类型
     * @return {@link T[]} 反序列化后的对象数组
     * @author payne.zhuang
     * @CreateTime 2023-07-18 14:40
     */
    public static <T> T[] objectToArray(Object object, Class<T> valueType) {
        JavaType javaType = objectMapper.getTypeFactory().constructArrayType(valueType);
        return objectMapper.convertValue(object, javaType);
    }

    /**
     * 将JSON数据转换成Map集合
     *
     * @param object    JSON数据
     * @param keyType   键类型
     * @param valueType 值类型
     * @return Map集合
     */
    public static <K, V> Map<K, V> objectToMap(Object object, Class<K> keyType, Class<V> valueType) {
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        return objectMapper.convertValue(object, javaType);
    }

    // 自定义异常
    static class JsonProcessException extends RuntimeException {

        @Serial
        private static final long serialVersionUID = -8613304082968236155L;

        public JsonProcessException(Throwable cause) {
            super(cause);
        }
    }
}
