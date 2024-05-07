package com.izpan.common.util;

import com.izpan.common.pool.StringPools;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.concurrent.TimeUnit;

/**
 * IP 工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.util.IPUtil
 * @CreateTime 2024/5/5 - 16:40
 */

@Slf4j
public class IPUtil {

    private IPUtil() {

    }

    /**
     * 获取 IP 地址（xdb模式实现）
     *
     * @param ip IP 地址
     * @return {@linkplain String} IP 地址
     * @author payne.zhuang
     * @CreateTime 2024-05-05 19:12
     */
    @SneakyThrows
    public static String getIpAddr(String ip) {
//        URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource("ip/ip2region.xdb");
//        assert resourceUrl != null;
//        String path = resourceUrl.getPath();
        Resource resource = new ClassPathResource("ip/ip2region.xdb");
        String dbPath = resource.getFile().getPath();
        // 1、从 dbPath 加载整个 xdb 到内存。
        byte[] cBuff;
        try {
            cBuff = Searcher.loadContentFromFile(dbPath);
        } catch (Exception e) {
            log.error("failed to load content from {}", dbPath, e);
            return StringPools.EMPTY;
        }

        // 2、使用上述的 cBuff 创建一个完全基于内存的查询对象。
        Searcher searcher;
        try {
            searcher = Searcher.newWithBuffer(cBuff);
        } catch (Exception e) {
            log.error("failed to create content cached searcher", e);
            return StringPools.EMPTY;
        }

        // 3、查询
        try {
            long sTime = System.nanoTime();
            String ipAddr = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - sTime);
            log.info("region: {}, ioCount: {}, took: {} μs\n", ipAddr, searcher.getIOCount(), cost);
            return ipAddr;
        } catch (Exception e) {
            log.error("failed to search({})", ip, e);
        }
        return StringPools.EMPTY;
    }
}
