package com.izpan.modules.monitor.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerAddDTO;
import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerDeleteDTO;
import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerSearchDTO;
import com.izpan.modules.monitor.domain.dto.logs.scheduler.MonLogsSchedulerUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonLogsSchedulerVO;

/**
 * 调度日志 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.IMonLogsSchedulerFacade
 * @CreateTime 2024-05-30
 */

public interface IMonLogsSchedulerFacade {

    /**
     * 调度日志 - 分页查询
     *
     * @param pageQuery                 分页对象
     * @param monLogsSchedulerSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-05-30 15:10
     */
    RPage<MonLogsSchedulerVO> listMonLogsSchedulerPage(PageQuery pageQuery, MonLogsSchedulerSearchDTO monLogsSchedulerSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 调度日志ID
     * @return {@link MonLogsSchedulerVO} 调度日志 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-05-30 15:10
     */
    MonLogsSchedulerVO get(Long id);

    /**
     * 新增调度日志
     *
     * @param monLogsSchedulerAddDTO 新增调度日志 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-30 15:10
     */
    boolean add(MonLogsSchedulerAddDTO monLogsSchedulerAddDTO);

    /**
     * 编辑更新调度日志信息
     *
     * @param monLogsSchedulerUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-30 15:10
     */
    boolean update(MonLogsSchedulerUpdateDTO monLogsSchedulerUpdateDTO);

    /**
     * 批量删除调度日志信息
     *
     * @param monLogsSchedulerDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-30 15:10
     */
    boolean batchDelete(MonLogsSchedulerDeleteDTO monLogsSchedulerDeleteDTO);

}