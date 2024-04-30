package com.izpan.modules.system.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 角色管理 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysRole
 * @CreateTime 2023-07-15
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysRoleVO", description = "角色管理 VO 对象")
public class SysRoleVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "父主键")
    private Long parentId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;
}