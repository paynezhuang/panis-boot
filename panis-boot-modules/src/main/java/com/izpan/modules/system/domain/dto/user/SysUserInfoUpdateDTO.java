package com.izpan.modules.system.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 个人资料修改DTO对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysUserInfoUpdateDTO
 * @CreateTime 2023/12/19 - 22:40
 */

@Getter
@Setter
public class SysUserInfoUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1468612040566150394L;

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
}
