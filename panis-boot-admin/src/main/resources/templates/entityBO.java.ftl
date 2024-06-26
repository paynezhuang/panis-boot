package ${package.Parent}.domain.bo;

<#if entityLombokModel>
import lombok.Data;
</#if>
import ${package.Entity}.${entity};
import java.util.List;

/**
 * ${table.comment!} BO 业务处理对象
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName ${package.Parent}.domain.bo.${entity}BO;
 * @CreateTime ${date}
 */

<#if entityLombokModel>
@Data
</#if>
<#if entitySerialVersionUID>
public class ${entity}BO extends ${entity} {
<#else>
public class ${entity} {
</#if>

    /**
    * Ids
    */
    private List<Long> ids;

}