package com.izpan.modules.monitor.facade;

import com.izpan.common.domain.Options;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerAddDTO;
import com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerDeleteDTO;
import com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerSearchDTO;
import com.izpan.modules.monitor.domain.dto.scheduler.MonSchedulerUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonSchedulerVO;

import java.util.List;

/**
 * 调度任务 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.facade.IMonSchedulerFacade
 * @CreateTime 2024/5/18 - 16:02
 */

public interface IMonSchedulerFacade {

    /**
     * 调度管理 - 分页查询
     *
     * @param pageQuery             分页对象
     * @param monSchedulerSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-05-21 10:41:36
     */
    RPage<MonSchedulerVO> listMonSchedulerPage(PageQuery pageQuery, MonSchedulerSearchDTO monSchedulerSearchDTO);

    /**
     * 根据ID获取调度任务
     *
     * @param id ID
     * @return {@link MonSchedulerVO} 调度任务对象
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 03:02:37
     */
    MonSchedulerVO get(Long id);

    /**
     * 新增调度任务
     *
     * @param monSchedulerAddDTO 调度任务对象
     * @return {@link Boolean} 新增结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 14:50:10
     */
    boolean addMonScheduler(MonSchedulerAddDTO monSchedulerAddDTO);

    /**
     * 更新调度任务
     *
     * @param monSchedulerUpdateDTO 调度任务对象
     * @return {@link Boolean} 更新结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 14:50:47
     */
    boolean updateMonScheduler(MonSchedulerUpdateDTO monSchedulerUpdateDTO);

    /**
     * 批量删除调度任务
     *
     * @param ids IDs
     * @return {@link Boolean} 结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 11:57:25
     */
    boolean batchDeleteMonScheduler(MonSchedulerDeleteDTO monSchedulerDeleteDTO);

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
     * @return {@link Boolean} 暂停结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 11:57:58
     */
    boolean pauseMonScheduler(Long id);

    /**
     * 按组暂停调度任务
     *
     * @param id ID
     * @return {@link Boolean} 暂停结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 12:25:19
     */
    boolean pauseMonSchedulerGroup(Long id);

    /**
     * 恢复调度任务
     *
     * @param id ID
     * @return {@link Boolean} 恢复结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 11:58:04
     */
    boolean resumeMonScheduler(Long id);

    /**
     * 按组恢复调度任务
     *
     * @param id ID
     * @return {@link Boolean} 恢复结果 {@code true} 成功 {@code false} 失败
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 12:25:10
     */
    boolean resumeMonSchedulerGroup(Long id);

    /**
     * 获取所有调度任务名称
     *
     * @return {@link List }<{@link Options }<{@link String }>> 调度任务名称集合
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-30 - 10:08:18
     */
    List<Options<String>> getAllJobNames();
}
