package com.izpan.modules.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户管理 VO 类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysUserVO
 * @CreateTime 2023/7/10 - 14:45
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysUserVO", description = "用户管理 VO 对象")
public class SysUserVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = -3621474283154332722L;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "头像URL")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "性别 0保密 1男 2女 ")
    private String gender;

    @Schema(description = "状态 0禁用 1启用")
    private String status;

    @Schema(description = "最近登录时间")
    private LocalDateTime lastLoginTime;

    @Schema(description = "最近修改密码时间")
    private LocalDateTime updatePasswordTime;

    @Schema(description = "更新用户名称")
    private String updateUser;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "路由权限按钮")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> permissions;
}
