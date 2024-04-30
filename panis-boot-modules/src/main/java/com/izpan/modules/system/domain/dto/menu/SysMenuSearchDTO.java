package com.izpan.modules.system.domain.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜单管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName SysMenuSearchDTO
 * @CreateTime 2023-12-28
 */

@Getter
@Setter
@Schema(name = "SysMenuSearchDTO", description = "菜单管理 查询 DTO 对象")
public class SysMenuSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3442419758076585181L;
}