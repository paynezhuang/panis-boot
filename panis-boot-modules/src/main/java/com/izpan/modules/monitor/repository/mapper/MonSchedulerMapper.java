package com.izpan.modules.monitor.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.modules.monitor.domain.bo.MonSchedulerBO;
import com.izpan.modules.monitor.domain.entity.MonScheduler;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 调度任务 Mapper 接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.repository.mapper.MonSchedulerMapper
 * @CreateTime 2024/5/18 - 17:21
 */

public interface MonSchedulerMapper extends BaseMapper<MonScheduler> {

    /**
     * 自定义分页
     *
     * @param page           分页信息
     * @param monSchedulerBO 查询对象
     * @return 数据结果
     */
    List<MonSchedulerBO> listMonSchedulerPage(IPage<MonSchedulerBO> page, @Param("monSchedulerBO") MonSchedulerBO monSchedulerBO);

    /**
     * 根据ID查询调度任务详情
     *
     * @param id ID
     * @return {@link MonSchedulerBO} 调度任务对象
     * @author payne.zhuang <paynezhuang@gmail.com>
     * @CreateTime 2024-05-21 - 03:09:30
     */
    MonSchedulerBO queryById(Long id);
}
