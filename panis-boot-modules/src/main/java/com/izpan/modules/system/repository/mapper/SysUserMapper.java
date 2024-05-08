package com.izpan.modules.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izpan.modules.system.domain.entity.SysUser;

import java.util.List;

/**
 * 用户管理 Mapper 接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.repository.mapper.SysUserMapper
 * @CreateTime 2023/7/6 - 16:04
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据账号获取用户信息
     *
     * @param userName 登录用户名
     * @return {@link SysUser} 用户对象
     * @author payne.zhuang
     * @CreateTime 2023-07-18 19:18
     */
    SysUser getUserByUserName(String userName);

    /**
     * 查询是否包含管理员
     *
     * @param userIds 用户ID集合
     * @return {@link Boolean} 是否包含管理员
     * @author payne.zhuang
     * @CreateTime 2024-04-23 12:14
     */
    boolean queryIsContainAdmin(List<Long> userIds);
}
