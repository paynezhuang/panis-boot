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

package ${package.Parent}.domain.bo;

<#if entityLombokModel>
import lombok.Data;
</#if>
import ${package.Entity}.${entity};
import java.util.List;

/**
 * ${table.comment!} BO 业务处理对象
 *
 * @Author ${author}
 * @ProjectName panis-boot
 * @ClassName ${package.Parent}.domain.bo.${entity}BO
 * @CreateTime ${date}
 */

<#if entityLombokModel>
@Data
</#if>
<#if entitySerialVersionUID>
public class ${entity}BO extends ${entity} {
<#else>
public class ${entity} {
</#if>

    /**
    * Ids
    */
    private List<Long> ids;

}