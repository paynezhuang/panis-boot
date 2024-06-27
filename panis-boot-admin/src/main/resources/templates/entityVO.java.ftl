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

package ${cfg.voPackageName};

import com.izpan.infrastructure.domain.BaseVO;
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>
<#if entityLombokModel>
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
</#if>

/**
 * ${table.comment!} VO 展示类
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName ${cfg.voPackageName}.${entity}VO
 * @CreateTime ${date}
 */

<#if entityLombokModel>
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
</#if>
<#if springdoc>
@Schema(name = "${entity}VO", description = "${table.comment!} VO 对象")
<#elseif swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
<#if activeRecord>
public class ${entity} extends Model<${entity}> {
<#elseif entitySerialVersionUID>
public class ${entity}VO extends BaseVO {
<#else>
public class ${entity} {
</#if>
<#if entitySerialVersionUID>
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#if field.keyFlag>
    <#assign keyPropertyName="${field.propertyName}"/>
</#if>

<#if field.comment!?length gt 0>
    <#if springdoc>
    @Schema(description = "${field.comment}")
    <#elseif swagger>
    @ApiModelProperty("${field.comment}")
    <#else>
    /**
    * ${field.comment}
    */
    </#if>
</#if>
<#if field.keyFlag>
<#-- 主键 -->
    <#if field.keyIdentityFlag>
    @TableId(value = "${field.annotationColumnName}", type = IdType.AUTO)
    <#elseif idType??>
    @TableId(value = "${field.annotationColumnName}", type = IdType.${idType})
    <#elseif field.convert>
    @TableId("${field.annotationColumnName}")
    </#if>
<#-- 普通字段 -->
<#elseif field.fill??>
<#-- -----   存在字段填充设置   ----->
    <#if field.convert>
    @TableField(value = "${field.annotationColumnName}", fill = FieldFill.${field.fill})
    <#else>
    @TableField(fill = FieldFill.${field.fill})
    </#if>
<#elseif field.convert>
    @TableField("${field.annotationColumnName}")
</#if>
<#-- 乐观锁注解 -->
<#if field.versionField>
    @Version
</#if>
<#-- 逻辑删除注解 -->
<#if field.logicDeleteField>
    @TableLogic
</#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
<#if !entityLombokModel>
<#list table.fields as field>
    <#if field.propertyType == "boolean">
        <#assign getprefix="is"/>
    <#else>
        <#assign getprefix="get"/>
    </#if>

    public ${field.propertyType} ${getprefix}${field.capitalName}() {
    return ${field.propertyName};
    }

    <#if chainModel>
        public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    <#else>
        public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    </#if>
    this.${field.propertyName} = ${field.propertyName};
    <#if chainModel>
        return this;
    </#if>
    }
</#list>
</#if>
<#if entityColumnConstant>
<#list table.fields as field>

    public static final String ${field.name?upper_case} = "${field.name}";
</#list>
</#if>
<#if activeRecord>

@Override
public Serializable pkVal() {
<#if keyPropertyName??>
    return this.${keyPropertyName};
<#else>
    return null;
</#if>
}
</#if>
<#if !entityLombokModel>

@Override
public String toString() {
return "${entity}{" +
<#list table.fields as field>
    <#if field_index==0>
        "${field.propertyName} = " + ${field.propertyName} +
    <#else>
        ", ${field.propertyName} = " + ${field.propertyName} +
    </#if>
</#list>
"}";
}
</#if>
}