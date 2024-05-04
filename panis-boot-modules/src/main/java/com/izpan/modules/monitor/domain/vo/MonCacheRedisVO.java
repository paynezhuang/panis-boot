package com.izpan.modules.monitor.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 系统缓存 Redis VO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.vo.MonCacheRedisVO
 * @CreateTime 2024/5/4 - 16:44
 */

@Data
@Builder
@Schema(name = "MonCacheRedisVO", description = "系统缓存 Redis VO 对象")
public class MonCacheRedisVO {

    @Schema(description = "版本")
    private String version;

    @Schema(description = "运行时间")
    private String uptime;

    @Schema(description = "连接数")
    private long connectedClients;

    @Schema(description = "使用内存")
    private String usedMemory;

    @Schema(description = "最大内存")
    private String maxMemory;

    @Schema(description = "内存使用率")
    private BigDecimal memoryUsageRate;

    @Schema(description = "内存碎片比率")
    private String memFragmentationRatio;

    @Schema(description = "命令处理总数")
    private long totalCommandsProcessed;

    @Schema(description = "命令统计")
    private List<CommandVO> commandStats;

    @Data
    @Builder
    @Schema(description = "命令统计")
    public static class CommandVO {
        @Schema(description = "命令名称")
        private String name;

        @Schema(description = "调用次数")
        private long value;

        @Schema(description = "调用占比")
        private BigDecimal percentage;
    }
}
