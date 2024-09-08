package com.izpan.modules.system.domain.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName SysMenuUpdateDTO
 * @CreateTime 2023-12-28
 */

@Getter
@Setter
@Schema(name = "SysMenuUpdateDTO", description = "菜单管理 编辑更新 DTO 对象")
public class SysMenuUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7590951415565070792L;

    @Schema(description = "ID")
    private Long id;

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

    @Schema(description = "图标类型")
    private String iconType;

    @Schema(description = "路由组件；详情可查阅：https://github.com/soybeanjs/elegant-router/blob/main/README.zh_CN.md")
    private String component;

    @Schema(description = "缓存路由(Y:是,N:否)")
    private String keepAlive;

    @Schema(description = "是否隐藏(Y:是,N:否)")
    private String hide;

    @Schema(description = "外部链接")
    private String href;

    @Schema(description = "内嵌链接 Iframe URL")
    private String iframeUrl;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "支持多标签(Y:是,N:否)")
    private String multiTab;

    @Schema(description = "固定在页签中的序号")
    private Integer fixedIndexInTab;

    @Schema(description = "路由查询参数 key value 集合")
    private List<SysMenuQueryKVPairsDTO> query;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;
}