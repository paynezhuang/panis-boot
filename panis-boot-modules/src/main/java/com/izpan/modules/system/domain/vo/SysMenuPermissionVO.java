package com.izpan.modules.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单类型枚举类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysMenuPermissionVO
 * @CreateTime 2024/4/18 - 10:52
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysMenuPermissionVO", description = "菜单管理 按钮权限 VO 对象")
public class SysMenuPermissionVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4926000759340362039L;

    @Schema(description = "菜单ID")
    private Long menuId;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "多语言标题")
    private String i18nKey;

    @Schema(description = "权限按钮")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Button> buttons;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "Button", description = "权限按钮对象")
    public static class Button implements Serializable {

        @Serial
        private static final long serialVersionUID = 890980738928747062L;

        @Schema(description = "权限ID")
        private Long id;

        @Schema(description = "权限名称")
        private String name;

        @Schema(description = "权限标识")
        private String resource;

        @Schema(description = "权限描述")
        private String description;

    }

}
