package com.izpan.modules.system.domain.dto.user.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户角色管理 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysUserRoleUpdateDTO
 * @CreateTime 2023-07-24
 */
@Getter
@Setter
@Schema(name = "SysUserRoleUpdateDTO", description = "用户角色管理 编辑更新 DTO 对象")
public class SysUserRoleUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 9045282097376993032L;
}