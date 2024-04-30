package com.izpan.modules.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 权限(按钮)管理 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysPermission
 * @CreateTime 2023-12-29
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_permission")
public class SysPermission extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6943903233879500624L;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 权限(按钮)名称
     */
    private String name;

    /**
     * 权限资源
     */
    private String resource;

    /**
     * 描述
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