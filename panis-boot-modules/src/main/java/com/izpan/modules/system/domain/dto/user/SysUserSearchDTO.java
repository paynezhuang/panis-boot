package com.izpan.modules.system.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysUserSearchDTO
 * @CreateTime 2023/7/10 - 15:04
 */

@Getter
@Setter
public class SysUserSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8914866620633001207L;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "启用状态(0:禁用,1:启用)")
    private String status;

}
