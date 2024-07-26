package com.izpan.admin.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.common.domain.Options;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerAddDTO;
import com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerDeleteDTO;
import com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerSearchDTO;
import com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonSchedulerVO;
import com.izpan.modules.monitor.facade.IMonSchedulerFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度任务 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.monitor.MonSchedulerController
 * @CreateTime 2024/5/18 - 15:42
 */

@RestController
@Tag(name = "调度任务")
@RequiredArgsConstructor
@RequestMapping("/mon_scheduler")
public class MonSchedulerController {

    @NonNull
    private IMonSchedulerFacade monSchedulerFacade;

    @GetMapping("/page")
    @SaCheckPermission("mon:scheduler:page")
    @Operation(operationId = "1", summary = "获取调度管理列表")
    public Result<RPage<MonSchedulerVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                              @Parameter(description = "查询对象") MonSchedulerSearchDTO monSchedulerSearchDTO) {
        return Result.data(monSchedulerFacade.listMonSchedulerPage(pageQuery, monSchedulerSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("mon:scheduler:get")
    @Operation(operationId = "2", summary = "根据ID获取调度任务详细信息")
    public Result<MonSchedulerVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monSchedulerFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("mon:scheduler:add")
    @Operation(operationId = "3", summary = "新增调度管理")
    public Result<Boolean> addScheduler(@Parameter(description = "新增调度管理对象") @RequestBody MonSchedulerAddDTO monSchedulerAddDTO) {
        return Result.status(monSchedulerFacade.addMonScheduler(monSchedulerAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("mon:scheduler:update")
    @Operation(operationId = "4", summary = "更新调度管理信息")
    public Result<Boolean> updateScheduler(@Parameter(description = "更新调度管理对象") @RequestBody MonSchedulerUpdateDTO monSchedulerUpdateDTO) {
        return Result.status(monSchedulerFacade.updateMonScheduler(monSchedulerUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("mon:scheduler:delete")
    @Operation(operationId = "5", summary = "批量删除调度管理信息")
    public Result<Boolean> batchDeleteScheduler(@Parameter(description = "删除调度管理对象") @RequestBody MonSchedulerDeleteDTO monSchedulerDeleteDTO) {
        return Result.status(monSchedulerFacade.batchDeleteMonScheduler(monSchedulerDeleteDTO));
    }

    @PutMapping("/immediate/{id}")
    @SaCheckPermission("mon:scheduler:immediate")
    @Operation(operationId = "7", summary = "根据ID查找并立即执行调度任务")
    public Result<Boolean> immediate(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monSchedulerFacade.immediateMonScheduler(id));
    }

    @PutMapping("/pause/{id}")
    @SaCheckPermission("mon:scheduler:pause")
    @Operation(operationId = "7", summary = "根据ID查找并暂停调度任务")
    public Result<Boolean> pause(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monSchedulerFacade.pauseMonScheduler(id));
    }

    @PutMapping("/pause_group/{id}")
    @SaCheckPermission("mon:scheduler:pauseGroup")
    @Operation(operationId = "8", summary = "根据ID查找并按组暂停调度任务")
    public Result<Boolean> pauseGroup(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monSchedulerFacade.pauseMonSchedulerGroup(id));
    }

    @PutMapping("/resume/{id}")
    @SaCheckPermission("mon:scheduler:resume")
    @Operation(operationId = "9", summary = "根据ID查找并恢复调度任务")
    public Result<Boolean> resume(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monSchedulerFacade.resumeMonScheduler(id));
    }

    @PutMapping("/resume_group/{id}")
    @SaCheckPermission("mon:scheduler:resumeGroup")
    @Operation(operationId = "10", summary = "根据ID查找并按组恢复调度任务")
    public Result<Boolean> resumeGroup(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monSchedulerFacade.resumeMonSchedulerGroup(id));
    }

    @GetMapping("/all_job_names")
    @SaCheckPermission("mon:scheduler:allJobNames")
    @Operation(operationId = "11", summary = "获取所有调度任务名称集合")
    public Result<List<Options<String>>> getAllJobNames() {
        return Result.data(monSchedulerFacade.getAllJobNames());
    }

}
