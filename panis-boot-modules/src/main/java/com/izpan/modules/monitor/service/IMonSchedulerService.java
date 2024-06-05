package com.izpan.modules.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.monitor.domain.bo.MonSchedulerBO;
import com.izpan.modules.monitor.domain.entity.MonScheduler;

import java.util.List;

/**
 * 调度任务 Service 服务接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.service.IMonSchedulerService
 * @CreateTime 2024/5/18 - 18:21
 */

public interface IMonSchedulerService extends IService<MonScheduler> {

    /**
     * 调度管理 - 分页查询
     *
     * @param pageQuery      分页对象
     * @param monSchedulerBO BO 查询对象
     * @return {@link IPage} 分页结果
     * @author payne.zhuang
     * @CreateTime 2024-05-21 10:26:37
     */
    IPage<MonSchedulerBO> listMonSchedulerBOPage(PageQuery pageQuery, MonSchedulerBO monSchedulerBO);

    /**
     * 根据ID查询调度任务详情
     *
     * @param id ID
     * @return {@link MonSchedulerBO} 调度任务对象
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 03:10:57
     */
    MonSchedulerBO queryById(Long id);

    /**
     * 新增调度任务
     *
     * @param monSchedulerBO 调度任务对象
     * @return {@link Boolean} 结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 11:56:31
     */
    boolean addMonScheduler(MonSchedulerBO monSchedulerBO);

    /**
     * 更新调度任务
     *
     * @param monSchedulerBO 调度任务对象
     * @return {@link Boolean} 结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 11:57:06
     */
    boolean updateMonScheduler(MonSchedulerBO monSchedulerBO);

    /**
     * 批量删除调度任务
     *
     * @param ids IDs
     * @return {@link Boolean} 结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 11:57:25
     */
    boolean batchDeleteMonScheduler(List<Long> ids);

    /**
     * 立即执行调度任务
     *
     * @param id ID
     * @return {@link Boolean} 执行结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-23 - 10:46:33
     */
    boolean immediateMonScheduler(Long id);

    /**
     * 暂停调度任务
     *
     * @param id ID
     * @return {@link Boolean} 结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 11:57:58
     */
    boolean pauseMonScheduler(Long id);

    /**
     * 按组暂停调度任务
     *
     * @param id ID
     * @return {@link Boolean} 结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 12:25:19
     */
    boolean pauseMonSchedulerGroup(Long id);

    /**
     * 恢复调度任务
     *
     * @param id ID
     * @return {@link Boolean} 结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 11:58:04
     */
    boolean resumeMonScheduler(Long id);

    /**
     * 按组恢复调度任务
     *
     * @param id ID
     * @return {@link Boolean} 结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 12:25:10
     */
    boolean resumeMonSchedulerGroup(Long id);

    /**
     * 获取所有调度任务名称
     *
     * @return {@link List }<{@link MonScheduler }> 调度任务名称集合
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-30 - 10:09:23
     */
    List<MonScheduler> getAllMonSchedulerJobName();
}
