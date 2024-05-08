package com.izpan.modules.system.domain.bo;

import com.izpan.modules.system.domain.entity.SysRolePermission;
import lombok.Data;

import java.io.Serial;
import java.util.List;

/**
 * 角色权限管理 BO 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.bo.SysRolePermissionBO
 * @CreateTime 2023-08-05
 */
@Data
public class SysRolePermissionBO extends SysRolePermission {

    @Serial
    private static final long serialVersionUID = 6150233512901751397L;

    /**
     * Ids
     */
    private List<Long> ids;

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 权限 IDs
     */
    private List<Long> permissionIds;

}