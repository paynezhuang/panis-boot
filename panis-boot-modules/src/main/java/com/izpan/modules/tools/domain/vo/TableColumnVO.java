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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据库表列 VO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.domain.vo.TableColumnVO
 * @CreateTime 2024/8/23 - 23:37
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "TableColumnVO", description = "数据库表列 VO 对象")
public class TableColumnVO {

    @Schema(description = "列名称")
    private String columnName;

    @Schema(description = "属性名称")
    private String propertyName;

    @Schema(description = "列描述")
    private String columnComment;

    @Schema(description = "列顺序位置")
    private Integer ordinalPosition;

    @Schema(description = "数据类型")
    private String dataType;

    @Schema(description = "Java 类型")
    private String javaType;

    @Schema(description = "字符最大长度")
    private String characterMaximumLength;

}
