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
import com.izpan.common.domain.Options;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.dto.position.SysPositionAddDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionDeleteDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionSearchDTO;
import com.izpan.modules.system.domain.dto.position.SysPositionUpdateDTO;
import com.izpan.modules.system.domain.vo.SysPositionVO;
import com.izpan.modules.system.facade.ISysPositionFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysPositionController
 * @CreateTime 2024-06-27 - 21:26:12
 */

@RestController
@Tag(name = "岗位管理")
@RequiredArgsConstructor
@RequestMapping("/sys_position")
public class SysPositionController {

    @NonNull
    private ISysPositionFacade sysPositionFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:position:page")
    @Operation(operationId = "1", summary = "获取岗位管理列表")
    public Result<RPage<SysPositionVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                             @Parameter(description = "查询对象") SysPositionSearchDTO sysPositionSearchDTO) {
        return Result.data(sysPositionFacade.listSysPositionPage(pageQuery, sysPositionSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:position:get")
    @Operation(operationId = "2", summary = "根据ID获取岗位管理详细信息")
    public Result<SysPositionVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysPositionFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:position:add")
    @Operation(operationId = "3", summary = "新增岗位管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysPositionAddDTO sysPositionAddDTO) {
        return Result.status(sysPositionFacade.add(sysPositionAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:position:update")
    @Operation(operationId = "4", summary = "更新岗位管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysPositionUpdateDTO sysPositionUpdateDTO) {
        return Result.status(sysPositionFacade.update(sysPositionUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:position:delete")
    @Operation(operationId = "5", summary = "批量删除岗位管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysPositionDeleteDTO sysPositionDeleteDTO) {
        return Result.status(sysPositionFacade.batchDelete(sysPositionDeleteDTO));
    }

    @GetMapping("/all_positions")
    @SaCheckPermission("sys:position:allPositions")
    @Operation(operationId = "7", summary = "获取所有岗位信息集合")
    public Result<List<Options<Long>>> queryAllPositionOptions() {
        return Result.data(sysPositionFacade.queryAllPositionListConvertOptions());
    }
}