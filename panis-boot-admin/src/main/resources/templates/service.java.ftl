package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* ${table.comment!} Service 服务接口层
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${package.Entity}.${entity}
* @CreateTime ${date}
*/

<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
        /**
        * ${table.comment!} - 分页查询
        *
        * @param pageQuery 分页对象
        * @param ${table.entityPath!}BO BO 查询对象
        * @return {@link IPage} 分页结果
        * @author payne.zhuang
        * @CreateTime ${date} 15:10
        */
        IPage<${entity}> list${entity}Page(PageQuery pageQuery, ${entity}BO ${table.entityPath!}BO);
    }
</#if>