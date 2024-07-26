package com.izpan.admin.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerAddDTO;
import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerDeleteDTO;
import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerSearchDTO;
import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonLogsSchedulerVO;
import com.izpan.modules.monitor.facade.IMonLogsSchedulerFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 调度日志 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.monitor.MonLogsSchedulerController
 * @CreateTime 2024-05-30
 */

@RestController
@Tag(name = "调度日志")
@RequiredArgsConstructor
@RequestMapping("/mon_logs_scheduler")
public class MonLogsSchedulerController {

    @NonNull
    private IMonLogsSchedulerFacade monLogsSchedulerFacade;

    @GetMapping("/page")
    @SaCheckPermission("mon:logs:scheduler:page")
    @Operation(operationId = "1", summary = "获取调度日志列表")
    public Result<RPage<MonLogsSchedulerVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                                  @Parameter(description = "查询对象") MonLogsSchedulerSearchDTO monLogsSchedulerSearchDTO) {
        return Result.data(monLogsSchedulerFacade.listMonLogsSchedulerPage(pageQuery, monLogsSchedulerSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("mon:logs:scheduler:get")
    @Operation(operationId = "2", summary = "根据ID获取调度日志详细信息")
    public Result<MonLogsSchedulerVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monLogsSchedulerFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("mon:logs:scheduler:add")
    @Operation(operationId = "3", summary = "新增调度日志")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody MonLogsSchedulerAddDTO monLogsSchedulerAddDTO) {
        return Result.status(monLogsSchedulerFacade.add(monLogsSchedulerAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("mon:logs:scheduler:update")
    @Operation(operationId = "4", summary = "更新调度日志信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody MonLogsSchedulerUpdateDTO monLogsSchedulerUpdateDTO) {
        return Result.status(monLogsSchedulerFacade.update(monLogsSchedulerUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("mon:logs:scheduler:delete")
    @Operation(operationId = "5", summary = "批量删除调度日志信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody MonLogsSchedulerDeleteDTO monLogsSchedulerDeleteDTO) {
        return Result.status(monLogsSchedulerFacade.batchDelete(monLogsSchedulerDeleteDTO));
    }

}
