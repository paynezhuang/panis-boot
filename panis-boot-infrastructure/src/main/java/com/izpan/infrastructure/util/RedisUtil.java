package com.izpan.infrastructure.util;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis 工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.util.RedisUtil
 * @CreateTime 2023/7/21 - 21:35
 */
@Slf4j
@SuppressWarnings({"unchecked", "rawtypes", "java:S3740"})
public class RedisUtil {

    private static final RedisTemplate redisTemplate;
    private static final String DEL_LOG = "[Redis][String]删除缓存:{},{}";

    static {
        redisTemplate = SpringUtil.getBean("redisTemplate");
    }

    // ============================= common ============================

    private RedisUtil() {

    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public static void set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 设置缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public static void expire(String key, long time) {
        try {
            if (time <= 0) {
                throw new IllegalArgumentException("失效时间不能小于等于0");
            }
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 设置缓存失效时间
     *
     * @param key      键
     * @param time     时间(秒)
     * @param timeUnit 时间单位
     */
    public static void expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time <= 0) {
                throw new IllegalArgumentException("失效时间不能小于等于0");
            }
            redisTemplate.expire(key, time, timeUnit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取缓存剩余过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public static Long getExpire(String key) {
        try {
            Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            return expire != null ? expire : 0;
        } catch (Exception e) {
            log.error("[Redis][String]获取缓存剩余过期时间异常:{}", e.getMessage(), e);
        }
        return 0L;
    }

    // ============================ String =============================

    /**
     * 根据前缀匹配出所有的 key 信息
     *
     * @param prefix system:user:login:userId:*
     * @return {@link Set} 匹配结果
     * @author payne.zhuang
     * @CreateTime 2024-01-25 15:15
     */
    public static Set<String> getKeysByPrefix(String prefix) {
        Set<String> keys = Sets.newHashSet();
        redisTemplate.execute((RedisConnection connection) -> {
            ScanOptions options = ScanOptions.scanOptions().match(prefix).build();
            RedisKeyCommands keyCommands = connection.keyCommands();
            try (Cursor<byte[]> cursor = keyCommands.scan(options)) {
                while (cursor.hasNext()) {
                    byte[] keyBytes = cursor.next();
                    keys.add(new String(keyBytes, StandardCharsets.UTF_8));
                }
            }
            return null;
        });
        return keys;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在，false 不存在
     */
    public static boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    public static void del(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                Boolean delete = redisTemplate.delete(keys[0]);
                log.info(DEL_LOG, delete, keys[0]);
            } else {
                Long delete = redisTemplate.delete(Arrays.asList(keys));
                log.info(DEL_LOG, delete, Arrays.toString(keys));
            }
        }
    }

    /**
     * 删除缓存
     *
     * @param keys 集合 Keys
     */
    public static void del(Collection<String> keys) {
        Long delete = redisTemplate.delete(keys);
        log.info(DEL_LOG, delete, keys);
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 对象缓存获取
     *
     * @param key    键
     * @param tClass 类型
     * @param <T>    类型
     * @return 值
     */
    public static <T> T get(String key, Class<T> tClass) {
        try {
            Object object = get(key);
            return object == null ? null : JacksonUtil.toObject(object, tClass);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 对象集合缓存获取
     *
     * @param key    键
     * @param tClass 类型
     * @param <T>    类型
     * @return 值
     */
    public static <T> List<T> getList(String key, Class<T> tClass) {
        try {
            Object object = get(key);
            if (object instanceof List<?> list) {
                return list.stream()
                        .filter(tClass::isInstance)
                        .map(tClass::cast)
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @param unit  时间单位
     */
    public static void set(String key, Object value, long time, TimeUnit unit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, unit);
            } else {
                set(key, value);
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 新的值
     * @throws IllegalArgumentException 递增因子必须大于0，抛出递增因子异常
     */
    public static Long incr(String key, long delta) {
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 新的值
     * @throws IllegalArgumentException 递减因子必须大于0，抛出递减因子异常
     */
    public static Long decr(String key, long delta) {
        try {
            return incr(key, -delta);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    // ================================ Map =================================

    /**
     * 获取指定 key 中指定 item 的值
     *
     * @param key  键
     * @param item 项
     * @return 值
     */
    public static Object hmGet(String key, String item) {
        try {
            return redisTemplate.opsForHash().get(key, item);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyMap();
    }

    /**
     * 获取指定 key 中的所有键值对
     *
     * @param key 键
     * @return 对应的所有键值对
     */
    public static Map<String, Object> hmGet(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyMap();
    }

    /**
     * 设置指定 key 中的多个键值对
     *
     * @param key 键
     * @param map 对应多个键值对
     */
    public static void hmSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 设置指定 key 中的多个键值对，并设置过期时间
     *
     * @param key  键
     * @param map  对应多个键值对
     * @param time 过期时间（秒）
     */
    public static void hmSetAndTime(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 设置指定 key 中指定 item 的值
     *
     * @param key   键
     * @param item  项
     * @param value 值
     */
    public static void hmSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 设置指定 key 中指定 item 的值，并设置过期时间
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  过期时间（秒）注意：如果已存在的 hash 表有过期时间，则会替换原有的过期时间
     */
    public static void hmSetAndTime(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 删除指定 key 中指定的一个或多个 item
     *
     * @param key  键
     * @param item 项，可以是多个
     */
    public static void hmDel(String key, Object... item) {
        try {
            Long delete = redisTemplate.opsForHash().delete(key, item);
            log.error("[Redis][Map]删除缓存:{},{}", delete, Arrays.toString(item));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 判断指定 key 中是否存在指定的 item
     *
     * @param key  键
     * @param item 项
     * @return true 存在 false不存在
     */
    public static boolean hmExists(String key, String item) {
        try {
            return redisTemplate.opsForHash().hasKey(key, item);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 给指定的 item 值增加指定数值（必须大于0）
     *
     * @param key  键
     * @param item 项
     * @param by   增加的数值
     * @return 增加后的值（double 类型）
     */
    public static Double hmIncr(String key, String item, double by) {
        try {
            return redisTemplate.opsForHash().increment(key, item, by);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0D;
    }

    /**
     * 给指定的 item 值减少指定数值（必须小于0）
     *
     * @param key  键
     * @param item 项
     * @param by   减少的数值（必须小于0）
     * @return 减少后的值（double 类型）
     */
    public static Double hmDecr(String key, String item, double by) {
        try {
            return redisTemplate.opsForHash().increment(key, item, -by);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0D;
    }

    // ============================ Set =============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return Set集合中的所有元素
     */
    public static Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptySet();
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public static boolean sExists(String key, Object value) {
        try {
            return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static Long sSet(String key, Object... values) {
        try {
            Long result = redisTemplate.opsForSet().add(key, values);
            return result != null ? result : 0L;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    /**
     * 将set数据放入缓存并设置过期时间
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static Long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count == null ? 0L : count;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return 缓存中set的长度
     */
    public static Long sGetSetSize(String key) {
        try {
            Long size = redisTemplate.opsForSet().size(key);
            return size == null ? 0L : size;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    /**
     * 移除值为value的元素
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的元素个数
     */
    public static Long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            log.error("[Redis][Set]删除缓存:{},{}", count, Arrays.toString(values));
            return count == null ? 0L : count;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    // =============================== List =================================

    /**
     * 获取list缓存的内容
     *
     * @param key   缓存键
     * @param start 开始位置
     * @param end   结束位置，0 到 -1代表所有值
     * @return List集合
     */
    public static List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 缓存键
     * @return 缓存中元素数量
     */
    public static Long lGetSize(String key) {
        try {
            Long size = redisTemplate.opsForList().size(key);
            return size == null ? 0L : size;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    /**
     * 通过索引获取list中的值
     *
     * @param key   缓存键
     * @param index 索引，index>=0 时， 0 表头，1 第二个元素，依次类推；index<0 时，-1，表尾，-2 倒数第二个元素，依次类推
     * @return 获取到的值
     */
    public static Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将值插入list缓存尾部
     *
     * @param key   缓存键
     * @param value 值
     * @param <T>   类型
     */
    public static <T> void lSet(String key, T value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 将值插入list缓存尾部，并设置过期时间
     *
     * @param key   缓存键
     * @param value 值
     * @param time  过期时间（单位：秒）
     * @param <T>   类型
     */
    public static <T> void lSet(String key, T value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   缓存键
     * @param index 修改的索引
     * @param value 修改后的值
     */
    public static <T> void lUpdateIndex(String key, long index, T value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            log.error("[Redis][List]修改缓存:{},{}", index, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 移除N个值为value的元素
     *
     * @param key   缓存键
     * @param count 移除的数量
     * @param value 值
     * @return 成功移除的数量
     */
    public static Long lRemove(String key, long count, Object value) {
        try {
            Long removeCount = redisTemplate.opsForList().remove(key, count, value);
            log.error("[Redis][List]删除缓存:{},{}", removeCount, value);
            return removeCount == null ? 0L : removeCount;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    /**
     * 获取 Redis 信息
     *
     * @return {@linkplain Properties} Redis 信息
     * @author payne.zhuang
     * @CreateTime 2024-05-04 13:57
     */
    public static Properties getRedisInfo(String section) {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        assert connectionFactory != null;
        try (RedisConnection connection = connectionFactory.getConnection()) {
            RedisServerCommands serverCommands = connection.serverCommands();
            // 如果提供了节名称，请求特定节的信息
            if (section != null && !section.isEmpty()) {
                return serverCommands.info(section);
            } else {
                return serverCommands.info();
            }
        }
    }

}
