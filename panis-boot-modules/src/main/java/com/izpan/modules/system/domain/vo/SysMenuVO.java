package com.izpan.modules.system.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 菜单管理 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysMenu
 * @CreateTime 2023-12-28
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysMenuVO", description = "菜单管理 VO 对象")
public class SysMenuVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 8285981486599719027L;

    @Schema(description = "父菜单ID")
    private Long parentId;

    @Schema(description = "菜单类型 1:目录 2:菜单")
    private String type;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "多语言标题")
    private String i18nKey;

    @Schema(description = "路由名称")
    private String routeName;

    @Schema(description = "路由路径")
    private String routePath;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "图标类型 1:iconify icon 2:local icon")
    private String iconType;

    @Schema(description = "路由组件")
    private String component;

    @Schema(description = "缓存路由(Y:是,N:否)")
    private String keepAlive;

    @Schema(description = "是否隐藏(Y:是,N:否)")
    private String hide;

    @Schema(description = "外部链接")
    private String href;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "支持多标签(Y:是,N:否)")
    private String multiTab;

    @Schema(description = "固定在页签中的序号")
    private Integer fixedIndexInTab;

    @Schema(description = "路由查询参数 JSON 字符串")
    private String query;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;

}