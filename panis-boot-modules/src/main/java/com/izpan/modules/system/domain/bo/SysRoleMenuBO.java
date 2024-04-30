package com.izpan.modules.system.domain.bo;

import com.izpan.modules.system.domain.entity.SysRoleMenu;
import lombok.Data;

import java.io.Serial;
import java.util.List;

/**
 * 角色菜单管理 BO 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.bo.SysRoleMenuBO
 * @CreateTime 2023-08-05
 */
@Data
public class SysRoleMenuBO extends SysRoleMenu {

    @Serial
    private static final long serialVersionUID = -1447245677812383240L;

    /**
     * Ids
     */
    private List<Long> ids;

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 菜单 IDs
     */
    private List<Long> menuIds;

}