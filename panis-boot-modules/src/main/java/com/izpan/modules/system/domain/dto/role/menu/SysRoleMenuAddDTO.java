package com.izpan.modules.system.domain.dto.role.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单管理 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysRoleMenuAddDTO
 * @CreateTime 2023-08-05
 */
@Getter
@Setter
@Schema(name = "SysRoleMenuAddDTO", description = "角色菜单管理 新增 DTO 对象")
public class SysRoleMenuAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4168402391804109767L;

    @Schema(description = "角色 ID")
    private Long roleId;

    @Schema(description = "菜单 IDs")
    private List<Long> menuIds;

}