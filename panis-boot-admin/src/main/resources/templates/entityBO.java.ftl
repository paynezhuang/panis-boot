
<#if entityLombokModel>
    import lombok.Data;
    <#if chainModel>
        import lombok.experimental.Accessors;
    </#if>
</#if>
import ${package.Entity}.${entity};

/**
* ${table.comment!} BO 业务处理对象
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${package.Entity}.${entity}
* @CreateTime ${date}
*/

<#if entityLombokModel>
    @Data
    <#if chainModel>
        @Accessors(chain = true)
    </#if>
</#if>
<#if activeRecord>
    public class ${entity} extends Model<${entity}> {
<#elseif entitySerialVersionUID>
    public class ${entity}BO extends ${entity} {
<#else>
    public class ${entity} {
</#if>

<#if entitySerialVersionUID>

</#if>

    /**
    * Ids
    */
    private List<Long> ids;

}