package com.izpan.modules.system.domain.bo;

import com.izpan.modules.system.domain.entity.SysUser;
import lombok.Data;

import java.io.Serial;
import java.util.List;

/**
 * 用户管理 BO 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.bo.SysUserBO
 * @CreateTime 2023/7/10 - 15:03
 */
@Data
public class SysUserBO extends SysUser {

    @Serial
    private static final long serialVersionUID = 6909973742798325514L;

    /**
     * Ids
     */
    private List<Long> ids;

    /**
     * 角色 Ids 集合
     */
    private List<Long> roleIds;

    /**
     * 路由权限按钮
     */
    private List<String> permissions;
}
