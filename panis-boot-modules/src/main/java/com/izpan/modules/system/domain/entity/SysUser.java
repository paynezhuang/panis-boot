package com.izpan.modules.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 用户管理 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysUser
 * @CreateTime 2023/7/6 - 15:55
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -8785649008167413252L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 性别 0保密 1男 2女
     */
    private String gender;

    /**
     * 是否启用(0:禁用,1:启用)
     */
    private String status;

    /**
     * MD5的盐值，混淆密码
     */
    private String salt;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 修改密码时间
     */
    private LocalDateTime updatePasswordTime;
}
