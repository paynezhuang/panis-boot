package com.izpan.modules.system.domain.bo;

import com.izpan.modules.system.domain.entity.SysMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * 菜单管理 BO 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.bo.SysMenuBO
 * @CreateTime 2023-08-05
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuBO extends SysMenu {

    @Serial
    private static final long serialVersionUID = 1005843878098609281L;

    /**
     * Ids
     */
    private List<Long> ids;

}