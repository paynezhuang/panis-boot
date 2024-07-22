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

package com.izpan.modules.system.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户管理 - 职责 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.user.SysUserResponsibilitiesDTO
 * @CreateTime 2024/7/20 - 16:08
 */

@Getter
@Setter
public class SysUserResponsibilitiesUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3682144343909390429L;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "用户角色 IDs")
    private List<Long> roleIds;

    @Schema(description = "用户岗位 IDs")
    private List<Long> positionIds;

    @Schema(description = "用户组织 IDs")
    private List<Long> orgUnitsIds;

    @Schema(description = "用户组织负责人 IDs")
    private List<Long> orgUnitsPrincipalIds;
}
