package com.izpan.modules.system.domain.bo;

import com.izpan.modules.system.domain.entity.SysRole;
import lombok.Data;

import java.io.Serial;
import java.util.List;

/**
 * 角色管理 BO 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.bo.SysRoleBO
 * @CreateTime 2023-07-23
 */
@Data
public class SysRoleBO extends SysRole {

    @Serial
    private static final long serialVersionUID = 6875872605962699422L;

    /**
     * Ids
     */
    private List<Long> ids;

}