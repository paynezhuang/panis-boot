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
import com.izpan.modules.monitor.domain.dto.file.MonFileAddDTO;
import com.izpan.modules.monitor.domain.dto.file.MonFileDeleteDTO;
import com.izpan.modules.monitor.domain.dto.file.MonFileSearchDTO;
import com.izpan.modules.monitor.domain.dto.file.MonFileUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonFileVO;
import com.izpan.modules.monitor.facade.IMonFileFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理 Controller 控制层
 *
 * @Author monitor
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.monitor.MonFileController
 * @CreateTime 2024-11-20 - 17:16:20
 */

@RestController
@Tag(name = "文件管理")
@RequiredArgsConstructor
@RequestMapping("mon_file")
public class MonFileController {

    @NonNull
    private IMonFileFacade monFileFacade;

    @GetMapping("/page")
    @SaCheckPermission("mon:file:page")
    @Operation(operationId = "1", summary = "获取文件管理列表")
    public Result<RPage<MonFileVO>> page(@Parameter(description = "分页对象", required = true) @Valid PageQuery pageQuery,
                                         @Parameter(description = "查询对象") MonFileSearchDTO monFileSearchDTO) {
        return Result.data(monFileFacade.listMonFilePage(pageQuery, monFileSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("mon:file:get")
    @Operation(operationId = "2", summary = "根据ID获取文件管理详细信息")
    public Result<MonFileVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monFileFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("mon:file:add")
    @Operation(operationId = "3", summary = "新增文件管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody MonFileAddDTO monFileAddDTO) {
        return Result.status(monFileFacade.add(monFileAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("mon:file:update")
    @Operation(operationId = "4", summary = "更新文件管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody MonFileUpdateDTO monFileUpdateDTO) {
        return Result.status(monFileFacade.update(monFileUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("mon:file:delete")
    @Operation(operationId = "5", summary = "批量删除文件管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody MonFileDeleteDTO monFileDeleteDTO) {
        return Result.status(monFileFacade.batchDelete(monFileDeleteDTO));
    }

    @PostMapping("/upload")
    @SaCheckPermission("mon:file:upload")
    @Operation(operationId = "6", summary = "上传文件")
    public Result<Boolean> batchDelete(@Parameter(description = "文件对象") @RequestParam("file") MultipartFile file) {
        return Result.status(monFileFacade.putFile(file));
    }

    @GetMapping("/preview/{id}")
    @SaCheckPermission("mon:file:preview")
    @Operation(operationId = "7", summary = "获取文件外链链接")
    public Result<String> preview(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(monFileFacade.preview(id));
    }

}