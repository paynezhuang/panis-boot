package com.izpan.modules.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izpan.modules.system.domain.entity.SysUserRole;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 用户角色管理 Mapper 接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.repository.mapper.SysUserRoleMapper
 * @CreateTime 2023-07-24
 */

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据用户ID查询用户角色
     *
     * @param userId 用户ID
     * @return {@link List<SysUserRole>} 用户角色列表
     * @author payne.zhuang
     * @CreateTime 2023-08-07 17:52
     */
    List<SysUserRole> listUserRoleByUserId(@Param("userId") Long userId);

}
