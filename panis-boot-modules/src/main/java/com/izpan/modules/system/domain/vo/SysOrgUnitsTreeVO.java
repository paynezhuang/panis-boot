package com.izpan.modules.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * 组织/部门/子部门管理树形结构 VO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysOrgUnitsTreeVO
 * @CreateTime 2024-07-16 - 16:35:30
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(name = "SysOrgUnitsTreeVO", description = "组织/部门/子部门管理 VO 对象")
public class SysOrgUnitsTreeVO extends SysOrgUnitsVO {

    @Serial
    private static final long serialVersionUID = 1018090219691602043L;

    @Schema(description = "子部门")
    private List<SysOrgUnitsTreeVO> children;
}
