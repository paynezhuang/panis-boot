package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.infrastructure.page.PageQuery;

/**
 * ${table.comment!} Service 服务接口实现层
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName ${package.Service}.impl.${entity}ServiceImpl
 * @CreateTime ${date}
 */

@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public IPage<${entity}> list${entity}Page(PageQuery pageQuery, ${entity}BO ${table.entityPath!}BO) {
        return baseMapper.selectPage(pageQuery.buildPage(), new LambdaQueryWrapper<>());
    }

}

