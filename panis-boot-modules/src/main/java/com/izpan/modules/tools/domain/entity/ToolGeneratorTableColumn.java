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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 代码生成表字段列管理 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.tools.domain.entity.ToolGeneratorTableColumn
 * @CreateTime 2024-08-26 - 22:13:50
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tool_generator_table_column")
public class ToolGeneratorTableColumn extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -1132571567736867555L;

    /**
     * 生成关联表ID
     */
    private Long tableId;

    /**
     * 生成关联表名称
     */
    private String tableName;

    /**
     * 表列名称
     */
    private String columnName;

    /**
     * 字段名称
     */
    private String propertyName;

    /**
     * 表列描述
     */
    private String columnComment;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * JAVA 字段类型
     */
    private String javaType;

    /**
     * TypeScript 字段类型
     */
    @TableField("typescript_type")
    private String typeScriptType;

    /**
     * 表列位置
     */
    private Integer ordinalPosition;

    /**
     * 多语言(0:否,1:是)
     */
    @TableField("is_i18n")
    private String i18n;

    /**
     * 必填(0:否,1:是)
     */
    @TableField("is_required")
    private String required;

    /**
     * 列表(0:否,1:是)
     */
    @TableField("is_list")
    private String list;

    /**
     * 查询(0:否,1:是)
     */
    @TableField("is_search")
    private String search;

    /**
     * 查询方式(等于,不等于,模糊等)
     */
    private String searchType;

    /**
     * 新增(0:否,1:是)
     */
    @TableField("is_added")
    private String added;

    /**
     * 编辑更新(0:否,1:是)
     */
    @TableField("is_edit")
    private String edit;

    /**
     * 关联字典代码
     */
    private String dictCode;

    /**
     * 渲染类型(select,radio)
     */
    private String renderType;

    /**
     * 是否启用(0:禁用,1:启用)
     */
    private String status;
}