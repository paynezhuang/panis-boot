package com.izpan.modules.system.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 岗位管理 VO 展示类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysPositionVO
 * @CreateTime 2024-06-26 - 22:14:38
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysPositionVO", description = "岗位管理 VO 对象")
public class SysPositionVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 6940712375244420217L;

    @Schema(description = "岗位名称")
    private String name;

    @Schema(description = "多语言标题")
    private String i18nKey;

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