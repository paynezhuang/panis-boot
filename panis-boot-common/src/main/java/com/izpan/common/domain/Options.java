package com.izpan.common.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 前端 Options 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.domain.Options
 * @CreateTime 2024/4/7 - 11:41
 */

@Data
@Builder
@AllArgsConstructor
public class Options<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -9064055350852836125L;

    @Schema(description = "显示的值")
    private String label;

    @Schema(description = "实际值")
    private transient T value;
}
