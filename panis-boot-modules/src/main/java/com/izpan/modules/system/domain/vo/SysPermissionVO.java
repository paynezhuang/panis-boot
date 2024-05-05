package com.izpan.modules.system.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * 权限管理 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysPermissionVO
 * @CreateTime 2023-08-05
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysPermissionVO", description = "权限管理 VO 对象")
public class SysPermissionVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 5331489496230761655L;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "权限标识")
    private String resource;

    @Schema(description = "所属菜单ID")
    private Long menuId;

    @Schema(description = "所属菜单名称")
    private String menuName;

    @Schema(description = "权限描述")
    private String description;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;

    private List<SysPermissionVO> children;
}