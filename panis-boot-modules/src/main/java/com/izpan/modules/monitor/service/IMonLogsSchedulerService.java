package com.izpan.modules.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.monitor.domain.bo.MonLogsSchedulerBO;
import com.izpan.modules.monitor.domain.entity.MonLogsScheduler;

/**
 * 调度日志 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsScheduler
 * @CreateTime 2024-05-30
 */

public interface IMonLogsSchedulerService extends IService<MonLogsScheduler> {
    /**
     * 调度日志 - 分页查询
     *
     * @param pageQuery          分页对象
     * @param monLogsSchedulerBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-05-30 15:10
     */
    IPage<MonLogsScheduler> listMonLogsSchedulerPage(PageQuery pageQuery, MonLogsSchedulerBO monLogsSchedulerBO);
}
