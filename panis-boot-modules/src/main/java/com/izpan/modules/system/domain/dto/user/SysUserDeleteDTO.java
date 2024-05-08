package com.izpan.modules.system.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户管理 - 删除 DTO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.SysUserUpdateDTO
 * @CreateTime 2023/7/17 - 17:19
 */
@Getter
@Setter
public class SysUserDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2193354291912191311L;

    @Schema(description = "IDs")
    private List<Long> ids;

}
