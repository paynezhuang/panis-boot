package com.izpan.modules.system.domain.dto.role.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单管理 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysRoleMenuDeleteDTO
 * @CreateTime 2023-08-05
 */
@Getter
@Setter
@Schema(name = "SysRoleMenuDeleteDTO", description = "角色菜单管理 删除 DTO 对象")
public class SysRoleMenuDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3557124244207483502L;

    @Schema(description = "角色 ID")
    private Long roleId;

    @Schema(description = "菜单 IDs")
    private List<Long> menuIds;

}