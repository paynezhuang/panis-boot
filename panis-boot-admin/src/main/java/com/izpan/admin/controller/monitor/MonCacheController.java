package com.izpan.admin.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.modules.monitor.domain.vo.MonCacheRedisVO;
import com.izpan.modules.monitor.facade.IMonCacheFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统服务监控
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.monitor.MonCacheController
 * @CreateTime 2024/5/4 - 15:49
 */
@RestController
@Tag(name = "缓存服务监控")
@RequiredArgsConstructor
@RequestMapping("/mon_cache")
public class MonCacheController {

    @NonNull
    private IMonCacheFacade monCacheFacade;

    @GetMapping("/redis")
    @SaCheckPermission("mon:cache:redis")
    @Operation(operationId = "1", summary = "获取 Redis 信息")
    public Result<MonCacheRedisVO> getRedisInfo() {
        return Result.data(monCacheFacade.redisInfo());
    }
}
