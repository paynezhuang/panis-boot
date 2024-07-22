package com.izpan.modules.system.domain.dto.position;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 岗位管理 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.position.SysPositionDeleteDTO
 * @CreateTime 2024-06-26 - 22:14:38
 */

@Getter
@Setter
@Schema(name = "SysPositionDeleteDTO", description = "岗位管理 删除 DTO 对象")
public class SysPositionDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 131792752696282930L;

    @Schema(description = "IDs")
    private List<Long> ids;

}