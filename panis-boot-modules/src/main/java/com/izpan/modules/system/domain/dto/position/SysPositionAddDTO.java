package com.izpan.modules.system.domain.dto.position;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 岗位管理 新增 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.position.SysPositionAddDTO
 * @CreateTime 2024-06-26 - 22:14:38
 */

@Getter
@Setter
@Schema(name = "SysPositionAddDTO", description = "岗位管理 新增 DTO 对象")
public class SysPositionAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4538631377423411548L;

    @Schema(description = "岗位名称")
    private String name;

    @Schema(description = "岗位编码")
    private String code;

    @Schema(description = "岗位名称简写")
    private String abbr;

    @Schema(description = "岗位描述")
    private String description;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;
}