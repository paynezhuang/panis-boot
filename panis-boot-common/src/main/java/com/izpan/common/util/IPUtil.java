package com.izpan.common.util;

import com.izpan.common.pool.StringPools;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

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

    private static Searcher searcher = null;

    static {
        try (InputStream ris = IPUtil.class.getResourceAsStream("/ip2region/data.xdb")) {
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(ris);
            searcher = Searcher.newWithBuffer(dbBinStr);
            log.info("Create content cached searcher success");
        } catch (IOException e) {
            log.error("Failed to create content cached searcher", e);
        }
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
        // 3、查询
        try {
            return searcher.search(ip);
        } catch (Exception e) {
            log.error("Failed to search({})", ip, e);
        }
        return StringPools.EMPTY;
    }
}
