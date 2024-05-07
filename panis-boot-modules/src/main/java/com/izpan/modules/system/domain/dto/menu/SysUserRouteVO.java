package com.izpan.modules.system.domain.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户权限路由对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.menu.SysUserRouteVO
 * @CreateTime 2024/2/4 - 18:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysUserRouteVO", description = "用户权限路由对象")
public class SysUserRouteVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2968348016258383993L;

    @Schema(description = "首页")
    private String home;

    @Schema(description = "路由列表")
    private List<Route> routes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "Route", description = "路由对象")
    public static class Route implements Serializable {

        @Serial
        private static final long serialVersionUID = 831533769271669224L;

        @Schema(description = "菜单名称(唯一)")
        private String name;

        @Schema(description = "菜单路径")
        private String path;

        @Schema(description = "路由组件；详情可查阅：https://github.com/soybeanjs/elegant-router/blob/main/README.zh_CN.md")
        private String component;

        @Schema(description = "路由元数据")
        private Meta meta;

        @Schema(description = "子菜单")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private List<Route> children;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "Meta", description = "路由元数据")
    public static class Meta implements Serializable {

        @Serial
        private static final long serialVersionUID = -6246685532247396266L;

        @Schema(description = "菜单标题")
        private String title;

        @Schema(description = "多语言标题")
        private String i18nKey;

        @Schema(description = "菜单图标")
        private String icon;

        @Schema(description = "本地图标")
        private String localIcon;

        @Schema(description = "排序值")
        private Integer order;

        @Schema(description = "缓存路由")
        private boolean keepAlive;

        @Schema(description = "是否隐藏")
        private boolean hideInMenu;

        @Schema(description = "支持多标签")
        private boolean multiTab;

        @Schema(description = "固定在页签中的序号")
        private Integer fixedIndexInTab;

        @Schema(description = "路由查询参数")
        private transient List<QueryKVParis> query;

        @Schema(description = "路由权限按钮")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private List<String> permissions;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "QueryParis", description = "路由查询参数")
    public static class QueryKVParis implements Serializable {

        @Serial
        private static final long serialVersionUID = -8961492109335677992L;

        @Schema(description = "键")
        private String key;

        @Schema(description = "值")
        private String value;
    }
}


