
import org.springframework.stereotype.Service;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;
/**
* ${table.comment!} 门面接口实现层
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${entity}FacadeImpl
* @CreateTime ${date}
*/

@Service
@RequiredArgsConstructor
public class ${entity}FacadeImpl implements I${entity}Facade {

@NonNull
private I${entity}Service ${table.entityPath!}Service;

@Override
public RPage<${entity}VO> list${entity}Page(PageQuery pageQuery, ${entity}SearchDTO ${table.entityPath!}SearchDTO) {
    ${entity}BO ${table.entityPath!}BO = CglibUtil.convertObj(${table.entityPath!}SearchDTO, ${entity}BO::new);
    IPage<${entity}> ${table.entityPath!}IPage = ${table.entityPath!}Service.list${entity}Page(pageQuery, ${table.entityPath!}BO);
        return RPage.build(${table.entityPath!}IPage, ${entity}VO::new);
    }

    @Override
    public ${entity}VO get(Long id) {
    ${entity} byId = ${table.entityPath!}Service.getById(id);
        return CglibUtil.convertObj(byId, ${entity}VO::new);
    }

    @Override
    @Transactional
    public boolean add(${entity}AddDTO ${table.entityPath!}AddDTO) {
    ${entity}BO ${table.entityPath!}BO = CglibUtil.convertObj(${table.entityPath!}AddDTO, ${entity}BO::new);
        return ${table.entityPath!}Service.save(${table.entityPath!}BO);
    }

    @Override
    @Transactional
    public boolean update(${entity}UpdateDTO ${table.entityPath!}UpdateDTO) {
    ${entity}BO ${table.entityPath!}BO = CglibUtil.convertObj(${table.entityPath!}UpdateDTO, ${entity}BO::new);
        return ${table.entityPath!}Service.updateById(${table.entityPath!}BO);
    }

    @Override
    @Transactional
    public boolean batchDelete(${entity}DeleteDTO ${table.entityPath!}DeleteDTO) {
    ${entity}BO ${table.entityPath!}BO = CglibUtil.convertObj(${table.entityPath!}DeleteDTO, ${entity}BO::new);
        return ${table.entityPath!}Service.removeBatchByIds(${table.entityPath!}BO.getIds(), true);
    }

}