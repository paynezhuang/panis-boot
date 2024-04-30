
/**
* ${table.comment!} 门面接口层
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${package.Entity}.I${entity}Facade
* @CreateTime ${date}
*/

public interface I${entity}Facade {

    /**
    * ${table.comment!} - 分页查询
    *
    * @param pageQuery        分页对象
    * @param ${table.entityPath!}SearchDTO 查询对象
    * @return {@link RPage} 查询结果
    * @author payne.zhuang
    * @CreateTime ${date} 15:10
    */
    RPage<${entity}VO> list${entity}Page(PageQuery pageQuery, ${entity}SearchDTO ${table.entityPath!}SearchDTO);

    /**
    * 根据 ID 获取详情信息
    *
    * @param id 用户ID
    * @return {@link ${entity}VO} 用户 VO 对象
    * @author payne.zhuang
    * @CreateTime ${date} 15:10
    */
    ${entity}VO get(Long id);

    /**
    * 新增用户
    *
    * @param ${table.entityPath!}AddDTO 新增用户 DTO 对象
    * @return {@link Boolean} 结果
    * @author payne.zhuang
    * @CreateTime ${date} 15:10
    */
    boolean add(${entity}AddDTO ${table.entityPath!}AddDTO);

    /**
    * 编辑更新用户信息
    *
    * @param ${table.entityPath!}UpdateDTO 编辑更新 DTO 对象
    * @return {@link Boolean} 结果
    * @author payne.zhuang
    * @CreateTime ${date} 15:10
    */
    boolean update(${entity}UpdateDTO ${table.entityPath!}UpdateDTO);

    /**
    * 批量删除用户信息
    *
    * @param ${table.entityPath!}DeleteDTO 删除 DTO 对象
    * @return @return {@link Boolean} 结果
    * @author payne.zhuang
    * @CreateTime ${date} 15:10
    */
    boolean batchDelete(${entity}DeleteDTO ${table.entityPath!}DeleteDTO);

}