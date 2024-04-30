package com.izpan.modules.system.domain.dto.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 权限(按钮)管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName SysPermissionSearchDTO
 * @CreateTime 2023-12-29
 */

@Getter
@Setter
@Schema(name = "SysPermissionSearchDTO", description = "权限(按钮)管理 查询 DTO 对象")
public class SysPermissionSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7243066371902982967L;

    @Schema(description = "菜单ID")
    private Long menuId;

    @Schema(description = "按钮名称")
    private String name;

}