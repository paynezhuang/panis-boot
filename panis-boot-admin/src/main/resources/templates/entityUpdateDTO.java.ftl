<#if springdoc>
    import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
    import lombok.Getter;
    import lombok.Setter;
    <#if chainModel>
        import lombok.experimental.Accessors;
    </#if>
</#if>

/**
* ${table.comment!} 编辑更新 DTO 对象
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${entity}UpdateDTO
* @CreateTime ${date}
*/

<#if entityLombokModel>
    @Getter
    @Setter
    <#if chainModel>
        @Accessors(chain = true)
    </#if>
</#if>
<#if springdoc>
    @Schema(name = "${entity}UpdateDTO", description = "${table.comment!} 编辑更新 DTO 对象")
<#elseif swagger>
    @ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
<#if activeRecord>
    public class ${entity} extends Model<${entity}> {
<#elseif entitySerialVersionUID>
    public class ${entity}UpdateDTO implements Serializable{
<#else>
    public class ${entity} {
</#if>

<#if entitySerialVersionUID>

</#if>

}