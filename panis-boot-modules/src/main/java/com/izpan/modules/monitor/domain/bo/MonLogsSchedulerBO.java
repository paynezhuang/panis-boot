package com.izpan.modules.monitor.domain.bo;

import com.izpan.modules.monitor.domain.entity.MonLogsScheduler;
import lombok.Data;

import java.io.Serial;
import java.util.List;

/**
 * 调度日志 BO 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsScheduler
 * @CreateTime 2024-05-30
 */

@Data
public class MonLogsSchedulerBO extends MonLogsScheduler {

    @Serial
    private static final long serialVersionUID = -7464199160527154428L;

    /**
     * Ids
     */
    private List<Long> ids;

}