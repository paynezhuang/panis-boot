package com.izpan.modules.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 角色管理 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysRole
 * @CreateTime 2023-07-15
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class SysRole extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 4606307811712928938L;

    /**
     * 父主键
     */
    private Long parentId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否启用(0:禁用,1:启用)
     */
    private String status;
}