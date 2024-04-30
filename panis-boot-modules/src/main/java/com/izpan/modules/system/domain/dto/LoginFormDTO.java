package com.izpan.modules.system.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户登录对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.LoginFormDTO
 * @CreateTime 2023/7/17 - 18:19
 */

@Getter
@Setter
@Schema(description = "登录对象")
public class LoginFormDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3098906381389407972L;

    @NotBlank
    @Schema(description = "用户名")
    private String userName;

    @NotBlank
    @Schema(description = "密码")
    private String password;
}
