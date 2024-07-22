package com.izpan.modules.system.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 用户部门管理 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysUserDepartVO
 * @CreateTime 2024-06-26 - 22:14:38
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysUserDepartVO", description = "用户部门管理 VO 对象")
public class SysUserDepartVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 1902152553141613901L;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "部门ID")
    private Long departId;

    @Schema(description = "部门负责人(0:否,1:是)")
    private String principal;
}