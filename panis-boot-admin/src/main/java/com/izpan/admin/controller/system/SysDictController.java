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
import com.izpan.modules.system.domain.dto.dict.SysDictAddDTO;
import com.izpan.modules.system.domain.dto.dict.SysDictDeleteDTO;
import com.izpan.modules.system.domain.dto.dict.SysDictSearchDTO;
import com.izpan.modules.system.domain.dto.dict.SysDictUpdateDTO;
import com.izpan.modules.system.domain.vo.SysDictVO;
import com.izpan.modules.system.facade.ISysDictFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.SysDictController
 * @CreateTime 2024-06-27 - 21:26:12
 */

@RestController
@Tag(name = "数据字典管理")
@RequiredArgsConstructor
@RequestMapping("/sys_dict")
public class SysDictController {

    @NonNull
    private ISysDictFacade sysDictFacade;

    @GetMapping("/page")
    @SaCheckPermission("sys:dict:page")
    @Operation(operationId = "1", summary = "获取数据字典管理列表")
    public Result<RPage<SysDictVO>> page(@Parameter(description = "分页对象", required = true) PageQuery pageQuery,
                                         @Parameter(description = "查询对象") SysDictSearchDTO sysDictSearchDTO) {
        return Result.data(sysDictFacade.listSysDictPage(pageQuery, sysDictSearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("sys:dict:get")
    @Operation(operationId = "2", summary = "根据ID获取数据字典管理详细信息")
    public Result<SysDictVO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(sysDictFacade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("sys:dict:add")
    @Operation(operationId = "3", summary = "新增数据字典管理")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody SysDictAddDTO sysDictAddDTO) {
        return Result.status(sysDictFacade.add(sysDictAddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("sys:dict:update")
    @Operation(operationId = "4", summary = "更新数据字典管理信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody SysDictUpdateDTO sysDictUpdateDTO) {
        return Result.status(sysDictFacade.update(sysDictUpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("sys:dict:delete")
    @Operation(operationId = "5", summary = "批量删除数据字典管理信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody SysDictDeleteDTO sysDictDeleteDTO) {
        return Result.status(sysDictFacade.batchDelete(sysDictDeleteDTO));
    }

    @GetMapping("/list")
    @SaCheckPermission("sys:dict:list")
    @Operation(operationId = "6", summary = "获取数据字典管理列表")
    public Result<List<SysDictVO>> list(@Parameter(description = "查询对象") SysDictSearchDTO sysDictSearchDTO) {
        return Result.data(sysDictFacade.listSysDict(sysDictSearchDTO));
    }

    @PostMapping("/load_cache")
    @SaCheckPermission("sys:dict:loadCache")
    @Operation(operationId = "7", summary = "加载数据字典至缓存中")
    public Result<List<SysDictVO>> loadCache(@Parameter(description = "查询对象") SysDictSearchDTO sysDictSearchDTO) {
        // TODO 待做
        return Result.data(sysDictFacade.listSysDict(sysDictSearchDTO));
    }

}