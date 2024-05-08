package com.izpan.modules.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izpan.modules.system.domain.entity.SysRole;

import java.util.List;

/**
 * 角色管理 Mapper 接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.repository.mapper.SysRoleMapper
 * @CreateTime 2023-07-15
 */

public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户ID查询角色列表
     *
     * @param userId 用户ID
     * @return {@link List<SysRole>} 角色列表集合
     * @author payne.zhuang
     * @CreateTime 2024-04-19 12:24
     */
    List<SysRole> queryRoleListWithUserId(Long userId);
}
