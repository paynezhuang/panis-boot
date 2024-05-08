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
 * 菜单管理列表 VO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysMenuPageVO
 * @CreateTime 2024/4/7 - 22:15
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(name = "SysMenuPageVO", description = "菜单管理列表 VO 对象")
public class SysMenuTreeVO extends SysMenuVO {

    @Serial
    private static final long serialVersionUID = -6337922157556940336L;

    private List<SysMenuTreeVO> children;
}
