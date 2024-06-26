package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * ${table.comment!} Service 服务接口层
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName ${package.Service}.I${entity}Service
 * @CreateTime ${date}
 */

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
