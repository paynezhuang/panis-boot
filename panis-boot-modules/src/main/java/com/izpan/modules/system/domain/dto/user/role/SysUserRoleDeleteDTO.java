package com.izpan.modules.system.domain.dto.user.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户角色管理 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysUserRoleDeleteDTO
 * @CreateTime 2023-07-24
 */
@Getter
@Setter
@Schema(name = "SysUserRoleDeleteDTO", description = "用户角色管理 删除 DTO 对象")
public class SysUserRoleDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1241505831374910667L;

    @Schema(description = "IDs")
    private List<Long> ids;

}