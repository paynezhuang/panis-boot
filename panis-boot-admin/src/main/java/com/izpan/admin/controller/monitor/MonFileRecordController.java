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

package com.izpan.admin.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordAddDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordDeleteDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordSearchDTO;
import com.izpan.modules.monitor.domain.dto.file.record.MonFileRecordUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonFileRecordVO;
import com.izpan.modules.monitor.facade.IMonFileRecordFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 文件记录 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.monitor.MonFileRecordController
 * @CreateTime 2024-11-20 - 14:27:48
 */

@RestController
@Tag(name = "文件记录")
@RequiredArgsConstructor
@RequestMapping("mon_file_record")
public class MonFileRecordController {

    @NonNull
    private IMonFileRecordFacade monFileRecordFacade;

    @GetMapping("/page")
    @SaCheckPermission("mon:file:record:page")
    @Operation(operationId = "1", summary = "获取文件记录列表")
    public Result<RPage<MonFileRecordVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                               @Parameter(description = "查询对象") MonFileRecordSearchDTO monFileRecordSearchDTO) {
        return Result.data(monFileRecordFacade.listMonFileRecordPage(pageQuery, monFileRecordSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("mon:file:record:get")
    @Operation(operationId = "2", summary = "根据ID获取文件记录详细信息")
    public Result<MonFileRecordVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monFileRecordFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("mon:file:record:add")
    @Operation(operationId = "3", summary = "新增文件记录")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody MonFileRecordAddDTO monFileRecordAddDTO) {
        return Result.status(monFileRecordFacade.add(monFileRecordAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("mon:file:record:update")
    @Operation(operationId = "4", summary = "更新文件记录信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody MonFileRecordUpdateDTO monFileRecordUpdateDTO) {
        return Result.status(monFileRecordFacade.update(monFileRecordUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("mon:file:record:delete")
    @Operation(operationId = "5", summary = "批量删除文件记录信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody MonFileRecordDeleteDTO monFileRecordDeleteDTO) {
        return Result.status(monFileRecordFacade.batchDelete(monFileRecordDeleteDTO));
    }

}