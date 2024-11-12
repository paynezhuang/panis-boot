/*
 * All Rights Reserved: Copyright [2024] [Zhuang Pan (paynezhuang@gmail.com)]
 * Open Source Agreement: Apache License, Version 2.0
 * For educational purposes only, commercial use shall comply with the author's copyright information.
 * The author does not guarantee or assume any responsibility for the risks of using software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.notice.SysNoticeAddDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeDeleteDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeSearchDTO;
import com.izpan.modules.system.domain.dto.notice.SysNoticeUpdateDTO;
import com.izpan.modules.system.domain.vo.SysNoticeVO;
import com.izpan.modules.system.facade.ISysNoticeFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.NonNull;
import jakarta.validation.Valid;

/**
 * 通知公告 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysNoticeController
 * @CreateTime 2024-11-10 - 12:55:52
 */

@RestController
@Tag(name = "通知公告")
@RequiredArgsConstructor
@RequestMapping("sys_notice")
public class SysNoticeController {

    @NonNull
    private ISysNoticeFacade sysNoticeFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:notice:page")
    @Operation(operationId = "1", summary = "获取通知公告列表")
    public Result<RPage<SysNoticeVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                           @Parameter(description = "查询对象") SysNoticeSearchDTO sysNoticeSearchDTO) {
        return Result.data(sysNoticeFacade.listSysNoticePage(pageQuery, sysNoticeSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:notice:get")
    @Operation(operationId = "2", summary = "根据ID获取通知公告详细信息")
    public Result<SysNoticeVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysNoticeFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:notice:add")
    @Operation(operationId = "3", summary = "新增通知公告")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysNoticeAddDTO sysNoticeAddDTO) {
        return Result.status(sysNoticeFacade.add(sysNoticeAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:notice:update")
    @Operation(operationId = "4", summary = "更新通知公告信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysNoticeUpdateDTO sysNoticeUpdateDTO) {
        return Result.status(sysNoticeFacade.update(sysNoticeUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:notice:delete")
    @Operation(operationId = "5", summary = "批量删除通知公告信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysNoticeDeleteDTO sysNoticeDeleteDTO) {
        return Result.status(sysNoticeFacade.batchDelete(sysNoticeDeleteDTO));
    }

}