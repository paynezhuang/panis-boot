package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if mapperAnnotationClass??>
    import ${mapperAnnotationClass.name};
</#if>

/**
* ${table.comment!} Mapper 接口层
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${package.Entity}.${entity}
* @CreateTime ${date}
*/

<#if mapperAnnotationClass??>
    @${mapperAnnotationClass.simpleName}
</#if>
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
    public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    }
</#if>