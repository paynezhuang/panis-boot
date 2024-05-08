package com.izpan.modules.monitor.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 登录日志 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsLogin
 * @CreateTime 2024-05-05
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mon_logs_login")
public class MonLogsLogin extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -6235698848827858230L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String userRealName;

    /**
     * IP
     */
    private String ip;

    /**
     * IP所属地
     */
    private String ipAddr;

    /**
     * User Agent
     */
    private String userAgent;

    /**
     * 登录状态(0:失败 1:成功)
     */
    private String status;

    /**
     * 登录消息
     */
    private String message;
}