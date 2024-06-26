package ${cfg.dtoPackageName};

<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
</#if>
import java.io.Serializable;
import java.util.List;

/**
 * ${table.comment!} 删除 DTO 对象
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName ${cfg.dtoPackageName}.${entity}DeleteDTO
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
@Schema(name = "${entity}DeleteDTO", description = "${table.comment!} 删除 DTO 对象")
<#elseif swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
public class ${entity}DeleteDTO implements Serializable {

    @Schema(description = "IDs")
    private List<Long> ids;

}