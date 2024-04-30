package com.izpan.modules.system.domain.dto.role.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 角色权限管理 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysRolePermissionUpdateDTO
 * @CreateTime 2023-08-05
 */
@Getter
@Setter
@Schema(name = "SysRolePermissionUpdateDTO", description = "角色权限管理 编辑更新 DTO 对象")
public class SysRolePermissionUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2483606965670788372L;

    @Schema(description = "角色 ID")
    private Long roleId;

    @Schema(description = "权限 IDs")
    private List<Long> permissionIds;
}