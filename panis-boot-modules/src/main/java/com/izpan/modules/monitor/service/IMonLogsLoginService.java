package com.izpan.modules.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.monitor.domain.bo.MonLogsLoginBO;
import com.izpan.modules.monitor.domain.entity.MonLogsLogin;

/**
 * 登录日志 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsLogin
 * @CreateTime 2024-05-05
 */

public interface IMonLogsLoginService extends IService<MonLogsLogin> {
    /**
     * 登录日志 - 分页查询
     *
     * @param pageQuery      分页对象
     * @param monLogsLoginBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-05-05 15:10
     */
    IPage<MonLogsLogin> listMonLogsLoginPage(PageQuery pageQuery, MonLogsLoginBO monLogsLoginBO);

}
