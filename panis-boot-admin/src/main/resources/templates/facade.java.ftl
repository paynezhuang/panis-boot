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

/**
 * ${table.comment!} 门面接口层
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName  ${cfg.facadePackageName}.I${entity}Facade
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
     * @CreateTime ${date}
     */
    RPage<${entity}VO> list${entity}Page(PageQuery pageQuery, ${entity}SearchDTO ${table.entityPath!}SearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id ${table.comment!}ID
     * @return {@link ${entity}VO} ${table.comment!} VO 对象
     * @author payne.zhuang
     * @CreateTime ${date}
     */
    ${entity}VO get(Long id);

    /**
     * 新增${table.comment!}
     *
     * @param ${table.entityPath!}AddDTO 新增${table.comment!} DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime ${date}
     */
    boolean add(${entity}AddDTO ${table.entityPath!}AddDTO);

    /**
     * 编辑更新${table.comment!}信息
     *
     * @param ${table.entityPath!}UpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime ${date}
     */
    boolean update(${entity}UpdateDTO ${table.entityPath!}UpdateDTO);

    /**
     * 批量删除${table.comment!}信息
     *
     * @param ${table.entityPath!}DeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime ${date}
     */
    boolean batchDelete(${entity}DeleteDTO ${table.entityPath!}DeleteDTO);

}