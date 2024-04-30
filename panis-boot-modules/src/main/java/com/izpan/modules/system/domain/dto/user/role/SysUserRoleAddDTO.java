package com.izpan.modules.system.domain.dto.user.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户角色管理 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysUserRoleAddDTO
 * @CreateTime 2023-07-24
 */
@Getter
@Setter
@Schema(name = "SysUserRoleAddDTO", description = "用户角色管理 新增 DTO 对象")
public class SysUserRoleAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4307478914923322730L;

}