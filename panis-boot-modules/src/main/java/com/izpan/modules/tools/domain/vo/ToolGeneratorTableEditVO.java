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

package com.izpan.modules.tools.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 代码生成表管理 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.domain.vo.ToolGeneratorTableVO
 * @CreateTime 2024-08-26 - 16:14:53
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ToolGeneratorTableVO", description = "代码生成表管理 VO 对象")
public class ToolGeneratorTableEditVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 3184498581112810919L;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "表描述")
    private String tableComment;

    @Schema(description = "表前缀")
    private String tablePrefix;

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