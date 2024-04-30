package com.izpan.modules.system.domain.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysRoleSearchDTO
 * @CreateTime 2023-07-23
 */
@Getter
@Setter
@Schema(name = "SysRoleSearchDTO", description = "角色管理 查询 DTO 对象")
public class SysRoleSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4866365375026833747L;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;

}