package com.izpan.modules.system.domain.dto.position;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 岗位管理 查询 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.position.SysPositionSearchDTO
 * @CreateTime 2024-06-26 - 22:14:38
 */

@Getter
@Setter
@Schema(name = "SysPositionSearchDTO", description = "岗位管理 查询 DTO 对象")
public class SysPositionSearchDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3218970095033570239L;

    @Schema(description = "岗位名称")
    private String name;

    @Schema(description = "多语言标题")
    private String status;

}