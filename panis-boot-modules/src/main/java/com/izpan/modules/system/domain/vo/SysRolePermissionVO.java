package com.izpan.modules.system.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 角色权限管理 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysRolePermissionVO
 * @CreateTime 2023-08-05
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysRolePermissionVO", description = "角色权限管理 VO 对象")
public class SysRolePermissionVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = -3405438924175128917L;

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "权限ID")
    private Long permissionId;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;
}