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
 * 代码生成表字段列管理 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.domain.vo.ToolGeneratorTableColumnVO
 * @CreateTime 2024-08-26 - 22:13:50
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ToolGeneratorTableColumnVO", description = "代码生成表字段列管理 VO 对象")
public class ToolGeneratorTableColumnVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = -5002513968172404091L;

    @Schema(description = "生成关联表ID")
    private Long tableId;

    @Schema(description = "生成关联表名称")
    private String tableName;

    @Schema(description = "字段名称")
    private String columnName;

    @Schema(description = "属性名称")
    private String propertyName;

    @Schema(description = "字段描述")
    private String columnComment;

    @Schema(description = "字段数据类型")
    private String dataType;

    @Schema(description = "JAVA 字段类型")
    private String javaType;

    @Schema(description = "TypeScript 字段类型")
    private String typeScriptType;

    @Schema(description = "表列位置")
    private Integer ordinalPosition;

    @Schema(description = "多语言(0:否,1:是)")
    private String i18n;

    @Schema(description = "必填(0:否,1:是)")
    private String required;

    @Schema(description = "列表(0:否,1:是)")
    private String list;

    @Schema(description = "查询(0:否,1:是)")
    private String search;

    @Schema(description = "查询类型")
    private String searchType;

    @Schema(description = "新增(0:否,1:是)")
    private String added;

    @Schema(description = "编辑更新(0:否,1:是)")
    private String edit;

    @Schema(description = "关联字典代码")
    private String dictCode;

    @Schema(description = "渲染类型(select,radio)")
    private String renderType;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;
}