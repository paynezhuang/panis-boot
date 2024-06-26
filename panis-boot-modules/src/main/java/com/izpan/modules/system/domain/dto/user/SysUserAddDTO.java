package com.izpan.modules.system.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户管理 新增用户 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysUserAddDTO
 * @CreateTime 2023/7/10 - 15:04
 */
@Getter
@Setter
public class SysUserAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 545804531288389450L;

    @Schema(description = "账号")
    private String userName;

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

    @Schema(description = "角色 Ids 集合")
    private List<Long> roleIds;
}
