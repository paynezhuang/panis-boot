package com.izpan.modules.system.domain.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜单管理 动态查询参数 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.menu.queryKeyValuePairs
 * @CreateTime 2024/4/25 - 16:22
 */
@Data
public class SysMenuQueryKVPairsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8405260450479507855L;

    @Schema(description = "键")
    private String key;

    @Schema(description = "值")
    private String value;
}
