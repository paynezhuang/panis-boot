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

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 组织/部门/子部门管理 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysOrgUnits
 * @CreateTime 2024-07-16 - 16:35:30
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_org_units")
public class SysOrgUnits extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -2436387192101768501L;

    /**
     * 父组织/部门/子部门ID
     */
    private Long parentId;

    /**
     * 组织/部门/子部门名称
     */
    private String name;

    /**
     * 组织/部门/子部门编码
     */
    private String code;

    /**
     * 组织/部门/子部门名称简写
     */
    private String abbr;

    /**
     * 组织/部门/子部门层级
     */
    private Integer level;

    /**
     * 祖先节点
     */
    private String ancestors;

    /**
     * 组织/部门/子部门描述
     */
    private String description;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 是否启用(0:禁用,1:启用)
     */
    private String status;
}