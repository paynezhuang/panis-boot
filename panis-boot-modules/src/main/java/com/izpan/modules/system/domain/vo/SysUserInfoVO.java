package com.izpan.modules.system.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * 用户个人信息 VO 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.vo.SysUserInfoVO
 * @CreateTime 2024/4/7 - 16:30
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysUserInfoVO", description = "用户个人信息 VO 对象")
public class SysUserInfoVO extends SysUserVO {

    @Serial
    private static final long serialVersionUID = 9099836707458372984L;

    /**
     * 角色IDs
     */
    private List<Long> roleIds;

}
