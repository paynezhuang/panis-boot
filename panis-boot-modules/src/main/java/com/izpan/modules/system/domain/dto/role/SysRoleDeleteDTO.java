package com.izpan.modules.system.domain.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 角色管理 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysRoleDeleteDTO
 * @CreateTime 2023-07-23
 */
@Getter
@Setter
@Schema(name = "SysRoleDeleteDTO", description = "角色管理 删除 DTO 对象")
public class SysRoleDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6610996829573298531L;

    @Schema(description = "IDs")
    private List<Long> ids;

}