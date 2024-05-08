package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
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
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>

    @NonNull
    private I${entity}Facade ${table.entityPath}Facade;

    @GetMapping("/page")
    @Operation(operationId = "1", summary = "获取${table.comment!}列表")
    public Result<RPage<${entity}VO>> page(@Parameter(description = "分页对象", required = true) PageQuery pageQuery,
    @Parameter(description = "查询对象") ${entity}SearchDTO ${table.entityPath!}SearchDTO) {
        return Result.data(${table.entityPath!}Facade.list${entity}Page(pageQuery, ${table.entityPath!}SearchDTO));
    }

    @GetMapping("/{id}")
    @Operation(operationId = "2", summary = "根据ID获取${table.comment!}详细信息")
    public Result<${entity}VO> get(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return Result.data(${table.entityPath!}Facade.get(id));
    }

    @PostMapping("/")
    @Operation(operationId = "3", summary = "新增${table.comment!}")
    public Result<Boolean> add(@Parameter(description = "新增对象") @RequestBody ${entity}AddDTO ${table.entityPath!}AddDTO) {
        return Result.status(${table.entityPath!}Facade.add(${table.entityPath!}AddDTO));
    }

    @PutMapping("/")
    @Operation(operationId = "4", summary = "更新${table.comment!}信息")
    public Result<Boolean> update(@Parameter(description = "更新对象") @RequestBody ${entity}UpdateDTO ${table.entityPath!}UpdateDTO) {
        return Result.status(${table.entityPath!}Facade.update(${table.entityPath!}UpdateDTO));
    }

    @DeleteMapping("/")
    @Operation(operationId = "5", summary = "批量删除${table.comment!}信息")
    public Result<Boolean> batchDelete(@Parameter(description = "删除对象") @RequestBody ${entity}DeleteDTO ${table.entityPath!}DeleteDTO) {
        return Result.status(${table.entityPath!}Facade.batchDelete(${table.entityPath!}DeleteDTO));
    }

}
</#if>