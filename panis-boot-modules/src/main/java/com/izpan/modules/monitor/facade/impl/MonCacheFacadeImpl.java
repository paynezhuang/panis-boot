package com.izpan.modules.monitor.facade.impl;

import com.google.common.collect.Lists;
import com.izpan.common.pool.StringPools;
import com.izpan.infrastructure.util.RedisUtil;
import com.izpan.modules.monitor.domain.vo.MonCacheRedisVO;
import com.izpan.modules.monitor.facade.IMonCacheFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.util.FormatUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Properties;

/**
 * 缓存服务监控 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.facade.impl.MonCacheFacadeImpl
 * @CreateTime 2024/5/4 - 16:01
 */
@Slf4j
@Service
public class MonCacheFacadeImpl implements IMonCacheFacade {
    @Override
    public MonCacheRedisVO redisInfo() {
        Properties redisInfo = RedisUtil.getRedisInfo(null);

        // 获取 Redis 命令统计
        String totalCommandsProcessed = redisInfo.getProperty("total_commands_processed");
        // 获取总命令数量
        long totalCommands = Long.parseLong(totalCommandsProcessed);
        // 使用内存
        long usedMemory = Long.parseLong(redisInfo.getProperty("used_memory"));
        // 最大内存
        String maxMemory = redisInfo.getProperty("maxmemory");
        // 计算内存使用率
        BigDecimal memoryUsageRate = BigDecimal.ZERO;
        if (StringPools.ZERO.equals(maxMemory)) {
            maxMemory = "无限制";
        } else {
            memoryUsageRate = BigDecimal.valueOf(((double) usedMemory / Long.parseLong(maxMemory)) * 100).setScale(6, RoundingMode.HALF_UP);
            maxMemory = FormatUtil.formatBytes(Long.parseLong(maxMemory));
        }
        MonCacheRedisVO redisVO = MonCacheRedisVO.builder()
                // 版本号
                .version(redisInfo.getProperty("redis_version"))
                // 运行时间
                .uptime(FormatUtil.formatElapsedSecs(Long.parseLong(redisInfo.getProperty("uptime_in_seconds"))))
                // 连接数
                .connectedClients(Long.parseLong(redisInfo.getProperty("connected_clients")))
                // 使用内存
                .usedMemory(FormatUtil.formatBytes(usedMemory))
                // 最大内存
                .maxMemory(maxMemory)
                // 内存使用率
                .memoryUsageRate(memoryUsageRate)
                // 内存碎片比率
                .memFragmentationRatio(redisInfo.getProperty("mem_fragmentation_ratio"))
                // 命令处理总数
                .totalCommandsProcessed(totalCommands)
                .build();
        // 获取 Redis 命令统计信息
        Properties commandStats = RedisUtil.getRedisInfo("commandstats");
        // 命令统计信息
        List<MonCacheRedisVO.CommandVO> commandVOS = Lists.newArrayList();
        // 计算每个命令的占比
        for (String commandKey : commandStats.stringPropertyNames()) {
            // 去除前缀 "cmdstat_"
            String commandName = commandKey.replace("cmdstat_", StringPools.EMPTY);
            String commandStat = commandStats.getProperty(commandKey);
            // 命令统计信息通常以类似 "cmdstat_get:calls=10,usec=80,..." 的形式呈现，我们需要从中提取调用次数
            long commandCalls = Long.parseLong(commandStat.split(StringPools.COMMA)[0].split(StringPools.EQUAL)[1]);
            // 计算命令调用占比
            double commandPercentage = (double) commandCalls / totalCommands * 100;
            // 构建命令统计对象
            commandVOS.add(MonCacheRedisVO.CommandVO.builder()
                    .name(commandName)
                    .value(commandCalls)
                    .percentage(BigDecimal.valueOf(commandPercentage).setScale(4, RoundingMode.HALF_UP)).build());
        }
        redisVO.setCommandStats(commandVOS);
        return redisVO;
    }
}
