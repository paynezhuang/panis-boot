package com.izpan.modules.monitor.facade.impl;

import cn.hutool.core.date.DatePattern;
import com.izpan.common.pool.StringPools;
import com.izpan.infrastructure.util.JacksonUtil;
import com.izpan.modules.monitor.domain.vo.MonSystemVO;
import com.izpan.modules.monitor.facade.IMonSystemFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.hardware.*;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

/**
 * 系统服务监控 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.facade.impl.MonSystemFacadeImpl
 * @CreateTime 2024/5/1 - 23:31
 */

@Slf4j
@Service
public class MonSystemFacadeImpl implements IMonSystemFacade {

    @Override
    public MonSystemVO getServerInfo() {
        // 创建系统信息对象
        oshi.SystemInfo si = new oshi.SystemInfo();
        // 获取硬件层信息
        HardwareAbstractionLayer hardware = si.getHardware();
        // 获取操作系统信息
        OperatingSystem operatingSystem = si.getOperatingSystem();
        // 获取计算机系统信息
        ComputerSystem computerSystem = hardware.getComputerSystem();
        // 构建服务器信息视图对象
        return MonSystemVO.builder()
                .operatingSystem(getOperatingSystemVO(operatingSystem, computerSystem))
                .centralProcessor(getCentralProcessorVO(hardware))
                .globalMemory(getGlobalMemoryVO(hardware))
                .jvm(getJvmVO())
                .processes(getProcessesList(operatingSystem))
                .fileStores(getFileStoresList(operatingSystem))
                .build();
    }

    /**
     * 构建操作系统信息视图对象
     *
     * @param operatingSystem 操作系统对象
     * @param computerSystem  计算机系统对象
     * @return {@linkplain MonSystemVO.OperatingSystemVO} 操作系统信息视图对象
     * @author payne.zhuang
     * @CreateTime 2024-05-01 22:52
     */
    private MonSystemVO.OperatingSystemVO getOperatingSystemVO(OperatingSystem operatingSystem, ComputerSystem computerSystem) {
        // 时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(DatePattern.NORM_DATETIME_PATTERN).withZone(ZoneId.systemDefault());
        Properties props = System.getProperties();
        // 构建并返回操作系统信息视图对象
        return MonSystemVO.OperatingSystemVO.builder()
                // 设置操作系统名称
                .name(props.getProperty("os.name"))
                // 设置操作系统架构
                .arch(props.getProperty("os.arch"))
                // 设置系统启动时间，格式化为人类可读的时间格式
                .systemBootTime(formatter.format(Instant.ofEpochSecond(operatingSystem.getSystemBootTime())))
                // 设置系统运行时间，转换为易于理解的时间格式（如：天、小时、分钟）
                .systemUptime(FormatUtil.formatElapsedSecs(operatingSystem.getSystemUptime()))
                // 设置计算机系统主板制造商的信息
                .manufacturer(computerSystem.getBaseboard().getManufacturer())
                .build();
    }

    /**
     * 构建中央处理器信息视图对象
     *
     * @param hardware 硬件抽象层对象
     * @return {@linkplain MonSystemVO.CentralProcessorVO} 中央处理器信息视图对象
     * @author payne.zhuang
     * @CreateTime 2024-05-01 22:52
     */
    private MonSystemVO.CentralProcessorVO getCentralProcessorVO(HardwareAbstractionLayer hardware) {
        CentralProcessor processor = hardware.getProcessor();
        CentralProcessor.ProcessorIdentifier identifier = processor.getProcessorIdentifier();
        // 获取系统CPU负载
        double[] loadAverage = processor.getSystemLoadAverage(3);
        // 获取CPU的系统使用情况
        long[] systemCpuLoadTicks = processor.getSystemCpuLoadTicks();
        long user = systemCpuLoadTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = systemCpuLoadTicks[CentralProcessor.TickType.NICE.getIndex()];
        long system = systemCpuLoadTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = systemCpuLoadTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long ioWait = systemCpuLoadTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = systemCpuLoadTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softIrq = systemCpuLoadTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = systemCpuLoadTicks[CentralProcessor.TickType.STEAL.getIndex()];

        long totalCpu = user + nice + system + idle + ioWait + irq + softIrq + steal;
        // 获取处理器信息并构建中央处理器视图对象
        return MonSystemVO.CentralProcessorVO.builder()
                // 设置处理器的名称
                .name(identifier.getName())
                // 设置处理器的标识符
                .identifier(identifier.getIdentifier())
                // 设置处理器的供应商频率，并格式化为易读的频率表示（如GHz）
                .vendorFreq(FormatUtil.formatHertz(identifier.getVendorFreq()))
                // 设置物理处理器的数量
                .physicalProcessorCount(processor.getPhysicalProcessorCount())
                // 设置逻辑处理器的数量
                .logicalProcessorCount(processor.getLogicalProcessorCount())
                // 设置系统平均负载
                .systemLoadAverage(loadAverage.length == 0 ? 0L : Math.round(loadAverage[0]))
                // 设置用户使用率
                .userPercent(Math.round(100.0 * user / totalCpu))
                // 设置系统使用率
                .systemPercent(Math.round(100.0 * system / totalCpu))
                // 设置闲置率
                .idlePercent(Math.round(100.0 * idle / totalCpu))
                .build();
    }

    /**
     * 构建全局内存信息视图对象
     *
     * @param hardware 硬件抽象层对象
     * @return {@linkplain MonSystemVO.GlobalMemoryVO} 全局内存信息视图对象
     * @author payne.zhuang
     * @CreateTime 2024-05-01 22:52
     */
    private MonSystemVO.GlobalMemoryVO getGlobalMemoryVO(HardwareAbstractionLayer hardware) {
        // 获取硬件的全局内存信息
        GlobalMemory memory = hardware.getMemory();
        // 获取虚拟内存信息
        VirtualMemory virtualMemory = memory.getVirtualMemory();
        // 获取总内存量
        long totalMemory = memory.getTotal();
        // 获取可用内存量
        long availableMemory = memory.getAvailable();
        // 计算已用内存量
        long usedMemory = totalMemory - availableMemory;
        // 获取总交换区大小
        long totalSwap = virtualMemory.getSwapTotal();
        // 获取已用交换区大小
        long usedSwap = virtualMemory.getSwapUsed();
        // 计算空闲交换区大小
        long freeSwap = totalSwap - usedSwap;
        // 计算内存使用率
        long memoryUsedRate = (usedMemory * 100) / totalMemory;
        // 计算交换区使用率
        long swapUsedRate = (totalSwap != 0) ? (usedSwap * 100) / totalSwap : 0;
        // 格式化内存大小为可读的字符串
        String totalFormatted = FormatUtil.formatBytes(totalMemory);
        String availableFormatted = FormatUtil.formatBytes(availableMemory);
        String usedFormatted = FormatUtil.formatBytes(usedMemory);
        String swapTotalFormatted = FormatUtil.formatBytes(totalSwap);
        String swapUsedFormatted = FormatUtil.formatBytes(usedSwap);
        String swapFreeFormatted = FormatUtil.formatBytes(freeSwap);
        // 构建并返回全局内存信息视图对象
        return MonSystemVO.GlobalMemoryVO.builder()
                // 设置总内存
                .total(totalFormatted)
                // 设置已用内存
                .used(usedFormatted)
                // 设置可用内存
                .available(availableFormatted)
                // 设置总交换区大小
                .swapTotal(swapTotalFormatted)
                // 设置已用交换区大小
                .swapUsed(swapUsedFormatted)
                // 设置空闲交换区大小
                .swapFree(swapFreeFormatted)
                // 设置内存使用率
                .memoryUsedRate(memoryUsedRate)
                // 设置交换区使用率
                .swapUsedRate(swapUsedRate)
                .build();
    }

    /**
     * 构建Java虚拟机信息视图对象
     *
     * @return {@linkplain MonSystemVO.JvmVO} Java虚拟机信息视图对象
     * @author payne.zhuang
     * @CreateTime 2024-05-01 22:53
     */
    private MonSystemVO.JvmVO getJvmVO() {
        // 获取Java虚拟机运行时和内存使用信息
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(DatePattern.NORM_DATETIME_PATTERN).withZone(ZoneId.systemDefault());
        // 获取堆内存使用信息
        long heapMemoryUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        long heapMemoryMax = memoryMXBean.getHeapMemoryUsage().getMax();
        // 计算内存使用率
        double memoryUsageRate = Math.round(((double) heapMemoryUsed / heapMemoryMax) * 100);
        // 构建并返回Java虚拟机信息视图对象
        return MonSystemVO.JvmVO.builder()
                // 设置Java虚拟机的名称
                .vmName(runtimeMXBean.getVmName())
                // 设置Java虚拟机的运行时间，并格式化为易于理解的时间格式（如：天、小时、分钟）
                .uptime(FormatUtil.formatElapsedSecs(runtimeMXBean.getUptime() / 1000))
                // 设置Java虚拟机的版本
                .vmVersion(runtimeMXBean.getVmVersion())
                // 设置Java虚拟机的供应商
                .vmVendor(runtimeMXBean.getVmVendor())
                // 设置Java虚拟机的启动时间，转换为ISO-8601日期时间格式
                .startTime(formatter.format(Instant.ofEpochMilli(runtimeMXBean.getStartTime())))
                // 设置Java虚拟机启动时的输入参数列表
                .inputArguments(runtimeMXBean.getInputArguments().toString())
                // 设置堆内存的当前使用量，并格式化为易于阅读的大小表示（如MB、GB）
                .heapMemoryUsed(FormatUtil.formatBytes(heapMemoryUsed))
                // 设置堆内存的最大可用量，并格式化为易于阅读的大小表示（如MB、GB）
                .heapMemoryMax(FormatUtil.formatBytes(heapMemoryMax))
                // 设置内存使用率
                .memoryUsageRate(memoryUsageRate)
                // 设置非堆内存的当前使用量，并格式化为易于阅读的大小表示（如MB、GB）
                .nonHeapMemoryUsed(FormatUtil.formatBytes(memoryMXBean.getNonHeapMemoryUsage().getUsed()))
                .build();
    }

    /**
     * 获取并构建操作系统进程信息视图对象列表。
     *
     * @param operatingSystem 操作系统对象
     * @return {@linkplain List <MonSystemVO.OSProcessVO>} 操作系统进程信息视图对象列表
     * @author payne.zhuang
     * @CreateTime 2024-05-01 22:53
     */
    private List<MonSystemVO.OSProcessVO> getProcessesList(OperatingSystem operatingSystem) {
        // 获取并排序进程信息
        List<OSProcess> processes = operatingSystem.getProcesses(
                OperatingSystem.ProcessFiltering.ALL_PROCESSES, OperatingSystem.ProcessSorting.CPU_DESC, 6);
        // 构建并返回进程信息视图对象列表
        return processes.stream().map(process -> MonSystemVO.OSProcessVO.builder()
                // 设置进程ID
                .processID(process.getProcessID())
                // 设置进程名称
                .name(process.getName())
                // 计算进程CPU负载百分比，并格式化为字符串
                .cpuLoad(Math.round(process.getProcessCpuLoadCumulative() * 100))
                .build()).toList();
    }

    /**
     * 获取并构建文件存储信息视图对象列表。
     *
     * @param operatingSystem 操作系统对象
     * @return {@linkplain List<MonSystemVO.OSFileStoreVO>} 文件存储信息视图对象列表
     * @author payne.zhuang
     * @CreateTime 2024-05-01 22:53
     */
    private List<MonSystemVO.OSFileStoreVO> getFileStoresList(OperatingSystem operatingSystem) {
        // 获取文件存储信息并构建文件存储信息视图对象列表
        return operatingSystem.getFileSystem().getFileStores().stream().map(fs -> MonSystemVO.OSFileStoreVO.builder()
                // 设置文件存储的名称
                .name(fs.getName())
                // 设置文件存储的类型
                .type(fs.getType())
                // 挂载点
                .mount(fs.getMount())
                // 计算总空间大小，并格式化为易读的形式
                .totalSpace(FormatUtil.formatBytesDecimal(fs.getTotalSpace()))
                // 计算可用空间大小，并格式化为易读的形式
                .usableSpace(FormatUtil.formatBytesDecimal(fs.getUsableSpace()))
                // 计算已用空间大小，并格式化为易读的形式
                .usedSpace(FormatUtil.formatBytesDecimal(fs.getTotalSpace() - fs.getUsableSpace()))
                // 计算已用空间的百分比，并格式化为字符串
                .usedPercentage(Math.round(((double) (fs.getTotalSpace() - fs.getUsableSpace()) / (double) fs.getTotalSpace()) * 100))
                .build()).toList();
    }
}
