package com.izpan.modules.system.domain.dto.role.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色菜单管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysRoleMenuSearchDTO
 * @CreateTime 2023-08-05
 */
@Getter
@Setter
@Schema(name = "SysRoleMenuSearchDTO", description = "角色菜单管理 查询 DTO 对象")
public class SysRoleMenuSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4025574605079232344L;

}