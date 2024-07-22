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

package com.izpan.modules.system.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户职责信息
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.bo.SysUserResponsibilitiesBO
 * @CreateTime 2024/7/20 - 15:24
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserResponsibilitiesBO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8725149072188272628L;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 用户角色Ids
     */
    private List<Long> roleIds;

    /**
     * 用户岗位Ids
     */
    private List<Long> positionIds;

    /**
     * 用户组织Ids
     */
    private List<Long> orgUnitsIds;

    /**
     * 用户组织负责人Ids
     */
    private List<Long> orgUnitsPrincipalIds;
}
