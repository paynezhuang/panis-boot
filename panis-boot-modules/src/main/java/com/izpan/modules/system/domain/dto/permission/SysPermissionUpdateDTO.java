package com.izpan.modules.system.domain.dto.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 权限(按钮)管理 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName SysPermissionUpdateDTO
 * @CreateTime 2023-12-29
 */

@Getter
@Setter
@Schema(name = "SysPermissionUpdateDTO", description = "权限(按钮)管理 编辑更新 DTO 对象")
public class SysPermissionUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6596713066238342256L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "菜单ID")
    private Long menuId;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "权限(按钮)名称")
    private String name;

    @Schema(description = "权限资源")
    private String resource;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;

}