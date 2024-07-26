package com.izpan.admin.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorAddDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorDeleteDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorSearchDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonLogsErrorVO;
import com.izpan.modules.monitor.facade.IMonLogsErrorFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 错误异常日志 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.monitor.MonLogsErrorController
 * @CreateTime 2024-05-07
 */

@RestController
@Tag(name = "错误异常日志")
@RequiredArgsConstructor
@RequestMapping("/mon_logs_error")
public class MonLogsErrorController {

    @NonNull
    private IMonLogsErrorFacade monLogsErrorFacade;

    @GetMapping("/page")
    @SaCheckPermission("mon:logs:error:page")
    @Operation(operationId = "1", summary = "获取错误异常日志列表")
    public Result<RPage<MonLogsErrorVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                              @Parameter(description = "查询对象") MonLogsErrorSearchDTO monLogsErrorSearchDTO) {
        return Result.data(monLogsErrorFacade.listMonLogsErrorPage(pageQuery, monLogsErrorSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("mon:logs:error:get")
    @Operation(operationId = "2", summary = "根据ID获取错误异常日志详细信息")
    public Result<MonLogsErrorVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monLogsErrorFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("mon:logs:error:add")
    @Operation(operationId = "3", summary = "新增错误异常日志")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody MonLogsErrorAddDTO monLogsErrorAddDTO) {
        return Result.status(monLogsErrorFacade.add(monLogsErrorAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("mon:logs:error:update")
    @Operation(operationId = "4", summary = "更新错误异常日志信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody MonLogsErrorUpdateDTO monLogsErrorUpdateDTO) {
        return Result.status(monLogsErrorFacade.update(monLogsErrorUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("mon:logs:error:delete")
    @Operation(operationId = "5", summary = "批量删除错误异常日志信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody MonLogsErrorDeleteDTO monLogsErrorDeleteDTO) {
        return Result.status(monLogsErrorFacade.batchDelete(monLogsErrorDeleteDTO));
    }

}
