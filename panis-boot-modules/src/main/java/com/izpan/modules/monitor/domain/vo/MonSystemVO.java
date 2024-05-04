package com.izpan.modules.monitor.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 系统服务监控 VO
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.vo.MonSystemVO
 * @CreateTime 2024/5/1 - 21:35
 */
@Data
@Builder
@Schema(name = "MonSystemVO", description = "系统服务监控 VO 对象")
public class MonSystemVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6911476596639057720L;

    @Schema(description = "操作系统信息")
    private OperatingSystemVO operatingSystem;

    @Schema(description = "处理器信息")
    private CentralProcessorVO centralProcessor;

    @Schema(description = "全局内存信息")
    private GlobalMemoryVO globalMemory;

    @Schema(description = "JVM信息")
    private JvmVO jvm;

    @Schema(description = "系统进程")
    private transient List<OSProcessVO> processes;

    @Schema(description = "文件系统信息")
    private transient List<OSFileStoreVO> fileStores;


    @Data
    @Builder
    @Schema(name = "OperatingSystemVO", description = "操作系统信息 VO 对象")
    public static class OperatingSystemVO implements Serializable {
        @Serial
        private static final long serialVersionUID = 6911476596639057720L;

        @Schema(description = "操作系统")
        private String name;

        @Schema(description = "操作系统架构")
        private String arch;

        @Schema(description = "系统启动时间")
        private String systemBootTime;

        @Schema(description = "系统运行时间")
        private String systemUptime;

        @Schema(description = "系统制造商")
        private String manufacturer;
    }

    @Data
    @Builder
    @Schema(name = "CentralProcessorVO", description = "处理器信息 VO 对象")
    public static class CentralProcessorVO implements Serializable {

        @Serial
        private static final long serialVersionUID = -5478007807389598091L;

        @Schema(description = "处理器名称")
        private String name;

        @Schema(description = "处理器ID")
        private String identifier;

        @Schema(description = "处理器频率")
        private String vendorFreq;

        @Schema(description = "CPU物理核心数")
        private int physicalProcessorCount;

        @Schema(description = "CPU逻辑核心数（包括超线程）")
        private int logicalProcessorCount;

        @Schema(description = "系统平均负载")
        private double systemLoadAverage;

        @Schema(description = "用户使用率")
        private double userPercent;

        @Schema(description = "系统使用率")
        private double systemPercent;

        @Schema(description = "闲置率")
        private double idlePercent;
    }

    @Data
    @Builder
    @Schema(name = "GlobalMemoryVO", description = "全局内存信息 VO 对象")
    public static class GlobalMemoryVO implements Serializable {

        @Serial
        private static final long serialVersionUID = -5478007807389598091L;

        @Schema(description = "内存总量")
        private String total;

        @Schema(description = "已使用内存")
        private String used;

        @Schema(description = "剩余内存")
        private String available;

        @Schema(description = "交换区总量")
        private String swapTotal;

        @Schema(description = "已使用交换区")
        private String swapUsed;

        @Schema(description = "剩余交换区")
        private String swapFree;

        @Schema(description = "内存使用率")
        private double memoryUsedRate;

        @Schema(description = "交换区使用率")
        private double swapUsedRate;
    }

    @Data
    @Builder
    @Schema(name = "JvmVO", description = "JVM信息 VO 对象")
    public static class JvmVO implements Serializable {

        @Serial
        private static final long serialVersionUID = 5125802998845641687L;

        @Schema(description = "JVM名称")
        private String vmName;

        @Schema(description = "JVM版本")
        private String vmVersion;

        @Schema(description = "JVM厂商")
        private String vmVendor;

        @Schema(description = "启动时间")
        private String startTime;

        @Schema(description = "JVM运行时间")
        private String uptime;

        @Schema(description = "输入参数")
        private String inputArguments;

        @Schema(description = "JVM内存使用")
        private String heapMemoryUsed;

        @Schema(description = "JVM内存最大")
        private String heapMemoryMax;

        @Schema(description = "内存使用率")
        private double memoryUsageRate;

        @Schema(description = "非堆内存使用")
        private String nonHeapMemoryUsed;

    }

    @Data
    @Builder
    @Schema(name = "OSProcessVO", description = "系统进程 VO 对象")
    public static class OSProcessVO implements Serializable {
        @Serial
        private static final long serialVersionUID = -2337523169269426941L;

        @Schema(description = "PID")
        private int processID;

        @Schema(description = "名称")
        private String name;

        @Schema(description = "CPU Load")
        private double cpuLoad;
    }

    @Data
    @Builder
    @Schema(name = "OSFileStoreVO", description = "文件系统信息 VO 对象")
    public static class OSFileStoreVO implements Serializable {

        @Serial
        private static final long serialVersionUID = -5186131067527801288L;

        @Schema(description = "卷名")
        private String name;

        @Schema(description = "卷类型")
        private String type;

        @Schema(description = "卷挂载点")
        private String mount;

        @Schema(description = "卷总大小")
        private String totalSpace;

        @Schema(description = "卷可用空间")
        private String usableSpace;

        @Schema(description = "卷已使用空间")
        private String usedSpace;

        @Schema(description = "卷使用率")
        private long usedPercentage;
    }

}

