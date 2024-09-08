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

package com.izpan.modules.tools.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库表列 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.domain.entity.TableColumn
 * @CreateTime 2024/8/24 - 00:12
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TableColumn implements Serializable {

    @Serial
    private static final long serialVersionUID = -2450654232437946848L;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 列描述
     */
    private String columnComment;

    /**
     * 列顺序位置
     */
    private Integer ordinalPosition;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 字符最大长度
     */
    private String characterMaximumLength;
}
