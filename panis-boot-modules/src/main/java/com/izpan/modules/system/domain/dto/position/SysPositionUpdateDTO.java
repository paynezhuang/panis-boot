package com.izpan.modules.system.domain.dto.position;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 岗位管理 编辑更新 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.position.SysPositionUpdateDTO
 * @CreateTime 2024-06-26 - 22:14:38
 */

@Getter
@Setter
@Schema(name = "SysPositionUpdateDTO", description = "岗位管理 编辑更新 DTO 对象")
public class SysPositionUpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6633820304564340700L;

    @Schema(description = "ID")
    private Long id;

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