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

package com.izpan.modules.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 数据字典子项管理 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysDictItem
 * @CreateTime 2024-06-27 - 21:26:12
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_item")
public class SysDictItem extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -6143591628651067086L;

    /**
     * 父字典ID
     */
    private Long dictId;

    /**
     * 父字典编码
     */
    private String dictCode;

    /**
     * 数据值
     */
    private String value;

    /**
     * 中文名称
     */
    @TableField("zh_cn")
    private String zhCN;

    /**
     * 英文名称
     */
    @TableField("en_us")
    private String enUS;

    /**
     * 类型(前端渲染类型)
     */
    private String type;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 字典描述
     */
    private String description;

    /**
     * 是否启用(0:禁用,1:启用)
     */
    private String status;
}