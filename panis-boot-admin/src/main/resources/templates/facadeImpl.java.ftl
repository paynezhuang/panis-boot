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

package ${cfg.facadePackageName};

import org.springframework.stereotype.Service;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${table.comment!} 门面接口实现层
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName  ${cfg.facadePackageName}.${entity}FacadeImpl
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