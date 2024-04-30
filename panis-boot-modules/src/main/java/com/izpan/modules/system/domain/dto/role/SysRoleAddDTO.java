package com.izpan.modules.system.domain.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色管理 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysRoleAddDTO
 * @CreateTime 2023-07-23
 */
@Getter
@Setter
@Schema(name = "SysRoleAddDTO", description = "角色管理 新增 DTO 对象")
public class SysRoleAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6188568292899155057L;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;
}