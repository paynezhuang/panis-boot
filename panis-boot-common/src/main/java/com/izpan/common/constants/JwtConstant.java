package com.izpan.common.constants;

import java.io.Serial;
import java.io.Serializable;

/**
 * JWT 常量
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.constants.JwtConstant
 * @CreateTime 2023/8/12 - 10:27
 */
public class JwtConstant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1200521688596743449L;

    private JwtConstant() {

    }

    /**
     * 随机标识
     */
    public static final String NONCE = "nonce";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    /**
     * 用户ID
     */
    public static final String USER_ID = "userId";
}
