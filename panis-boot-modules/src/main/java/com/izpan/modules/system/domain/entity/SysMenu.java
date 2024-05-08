package com.izpan.modules.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 菜单管理 Entity 实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysMenu
 * @CreateTime 2023-12-29
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -8300691108258375248L;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单类型 1:目录 2:菜单
     */
    private String type;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 多语言标题
     */
    private String i18nKey;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 菜单路径
     */
    private String routePath;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 图标类型 1:iconify icon 2:local icon
     */
    private String iconType;

    /**
     * 路由组件；详情可查阅：<a href="https://github.com/soybeanjs/elegant-router/blob/main/README.zh_CN.md">elegant-router</a>
     */
    private String component;

    /**
     * 缓存路由(Y:是,N:否)
     */
    private String keepAlive;

    /**
     * 是否隐藏(Y:是,N:否)
     */
    private String hide;

    /**
     * 外部链接
     */
    private String href;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 支持多标签(Y:是,N:否)
     */
    private String multiTab;

    /**
     * 固定在页签中的序号
     */
    private Integer fixedIndexInTab;

    /**
     * 路由查询参数
     */
    private String query;

    /**
     * 是否启用(0:禁用,1:启用)
     */
    private String status;
}