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

package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import lombok.NonNull;

/**
 * ${table.comment!} Controller 控制层
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName ${package.Controller}.${table.controllerName}
 * @CreateTime ${date}
 */

<#if restControllerStyle>
@RestController
@Tag(name = "${table.comment!}")
@RequiredArgsConstructor
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.name}</#if>")
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @NonNull
    private I${entity}Facade ${table.entityPath}Facade;

    @GetMapping("/page")
    @SaCheckPermission("${cfg.permission!}:page")
    @Operation(operationId = "1", summary = "获取${table.comment!}列表")
    public Result<RPage<${entity}VO>> page(@Parameter(description = "分页对象", required = true) PageQuery pageQuery,
                                           @Parameter(description = "查询对象") ${entity}SearchDTO ${table.entityPath!}SearchDTO) {
        return Result.data(${table.entityPath!}Facade.list${entity}Page(pageQuery, ${table.entityPath!}SearchDTO));
    }

    @GetMapping("/{id}")
    @SaCheckPermission("${cfg.permission!}:get")
    @Operation(operationId = "2", summary = "根据ID获取${table.comment!}详细信息")
    public Result<${entity}VO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(${table.entityPath!}Facade.get(id));
    }

    @PostMapping("/")
    @SaCheckPermission("${cfg.permission!}:add")
    @Operation(operationId = "3", summary = "新增${table.comment!}")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody ${entity}AddDTO ${table.entityPath!}AddDTO) {
        return Result.status(${table.entityPath!}Facade.add(${table.entityPath!}AddDTO));
    }

    @PutMapping("/")
    @SaCheckPermission("${cfg.permission!}:update")
    @Operation(operationId = "4", summary = "更新${table.comment!}信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody ${entity}UpdateDTO ${table.entityPath!}UpdateDTO) {
        return Result.status(${table.entityPath!}Facade.update(${table.entityPath!}UpdateDTO));
    }

    @DeleteMapping("/")
    @SaCheckPermission("${cfg.permission!}:delete")
    @Operation(operationId = "5", summary = "批量删除${table.comment!}信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody ${entity}DeleteDTO ${table.entityPath!}DeleteDTO) {
        return Result.status(${table.entityPath!}Facade.batchDelete(${table.entityPath!}DeleteDTO));
    }

}