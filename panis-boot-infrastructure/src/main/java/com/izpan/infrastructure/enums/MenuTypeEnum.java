package com.izpan.infrastructure.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * 菜单类型枚举类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.enums.MenuTypeEnum
 * @CreateTime 2024/4/17 - 14:01
 */

@Getter
public enum MenuTypeEnum implements Serializable {

    DIRECTORY("1", "目录"),
    MENU("2", "菜单");

    private final String value;

    private final String name;

    MenuTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
