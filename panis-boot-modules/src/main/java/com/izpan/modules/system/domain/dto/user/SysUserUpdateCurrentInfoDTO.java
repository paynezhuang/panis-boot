package com.izpan.modules.system.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户管理 - 当前用户更新个人信息 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.user.SysUserUpdateCurrentInfoDTO
 * @CreateTime 2024/1/26 - 14:54
 */
@Getter
@Setter
public class SysUserUpdateCurrentInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2935137173366166569L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "真名")
    private String realName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "性别 0保密 1男 2女")
    private String gender;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;
}
