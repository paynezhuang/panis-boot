package com.izpan.modules.system.domain.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName SysMenuDeleteDTO
 * @CreateTime 2023-12-28
 */

@Getter
@Setter
@Schema(name = "SysMenuDeleteDTO", description = "菜单管理 删除 DTO 对象")
public class SysMenuDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4520134930449003433L;

    @Schema(description = "IDs")
    private List<Long> ids;

}