package com.izpan.modules.monitor.domain.bo;

import com.izpan.modules.monitor.domain.entity.MonLogsOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * 操作日志 BO 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.MonLogsOperation
 * @CreateTime 2024-05-07
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MonLogsOperationBO extends MonLogsOperation {

    @Serial
    private static final long serialVersionUID = -262121882432249040L;

    /**
     * Ids
     */
    private List<Long> ids;

}