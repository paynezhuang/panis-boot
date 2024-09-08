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

package com.izpan.modules.tools.domain.dto.generator.table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 代码生成表管理 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.domain.dto.generator.table.ToolGeneratorTableUpdateDTO
 * @CreateTime 2024-08-26 - 16:14:53
 */

@Getter
@Setter
@Schema(name = "ToolGeneratorTableUpdateDTO", description = "代码生成表管理 编辑更新 DTO 对象")
public class ToolGeneratorTableUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -860438122970858825L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "表前缀")
    private String tablePrefix;

    @Schema(description = "表描述")
    private String tableComment;

    @Schema(description = "生成父包名")
    private String parentPackage;

    @Schema(description = "生成模块名")
    private String moduleName;

    @Schema(description = "父级菜单ID")
    private Long parentMenuId;

    @Schema(description = "生成作者")
    private String author;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;

}