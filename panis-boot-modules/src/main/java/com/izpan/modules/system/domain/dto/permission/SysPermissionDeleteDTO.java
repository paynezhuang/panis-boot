package com.izpan.modules.system.domain.dto.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 权限(按钮)管理 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName SysPermissionDeleteDTO
 * @CreateTime 2023-12-29
 */

@Getter
@Setter
@Schema(name = "SysPermissionDeleteDTO", description = "权限(按钮)管理 删除 DTO 对象")
public class SysPermissionDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5010465526591949755L;

    @Schema(description = "IDs")
    private List<Long> ids;

}