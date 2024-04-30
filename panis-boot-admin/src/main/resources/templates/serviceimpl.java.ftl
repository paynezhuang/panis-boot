package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
* ${table.comment!} Service 服务接口实现层
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${package.Entity}.${entity}
* @CreateTime ${date}
*/

@Service
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

    }
<#else>
    public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
        @Override
        public IPage<${entity}> list${entity}Page(PageQuery pageQuery, ${entity}BO ${table.entityPath!}BO) {
            return baseMapper.selectPage(pageQuery.buildPage(), new LambdaQueryWrapper<>());
        }
    }
</#if>