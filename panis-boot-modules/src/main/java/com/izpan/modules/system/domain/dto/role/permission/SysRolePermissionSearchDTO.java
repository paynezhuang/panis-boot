package com.izpan.modules.system.domain.dto.role.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色权限管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysRolePermissionSearchDTO
 * @CreateTime 2023-08-05
 */
@Getter
@Setter
@Schema(name = "SysRolePermissionSearchDTO", description = "角色权限管理 查询 DTO 对象")
public class SysRolePermissionSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1680614225501397826L;

}