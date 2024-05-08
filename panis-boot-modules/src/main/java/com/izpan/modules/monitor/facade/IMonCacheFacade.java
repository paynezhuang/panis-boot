package com.izpan.modules.monitor.facade;

import com.izpan.modules.monitor.domain.vo.MonCacheRedisVO;

/**
 * 缓存服务监控 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.facade.IMonCacheFacade
 * @CreateTime 2024/5/4 - 16:01
 */
public interface IMonCacheFacade {

    /**
     * 获取 Redis 信息
     *
     * @return {@link MonCacheRedisVO} Redis 信息
     * @author payne.zhuang
     * @CreateTime 2024-05-04 17:15
     */
    MonCacheRedisVO redisInfo();
}
