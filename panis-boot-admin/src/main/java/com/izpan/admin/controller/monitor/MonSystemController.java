package com.izpan.admin.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.modules.monitor.domain.vo.MonSystemVO;
import com.izpan.modules.monitor.facade.IMonSystemFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统服务监控 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.monitor.MonSystemController
 * @CreateTime 2024/5/1 - 23:29
 */

@RestController
@Tag(name = "系统服务监控")
@RequiredArgsConstructor
@RequestMapping("/mon_system")
public class MonSystemController {

    @NonNull
    private IMonSystemFacade monSystemFacade;

    @GetMapping("/info")
    @SaCheckPermission("mon:system:info")
    @Operation(operationId = "1", summary = "获取系统服务器系统信息")
    public Result<MonSystemVO> getServerInfo() {
        return Result.data(monSystemFacade.getServerInfo());
    }
}
