package com.izpan.modules.monitor.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.logs.operation.MonLogsOperationAddDTO;
import com.izpan.modules.monitor.domain.dto.logs.operation.MonLogsOperationDeleteDTO;
import com.izpan.modules.monitor.domain.dto.logs.operation.MonLogsOperationSearchDTO;
import com.izpan.modules.monitor.domain.dto.logs.operation.MonLogsOperationUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonLogsOperationVO;

/**
 * 操作日志 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.IMonLogsOperationFacade
 * @CreateTime 2024-05-07
 */

public interface IMonLogsOperationFacade {

    /**
     * 操作日志 - 分页查询
     *
     * @param pageQuery                 分页对象
     * @param monLogsOperationSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    RPage<MonLogsOperationVO> listMonLogsOperationPage(PageQuery pageQuery, MonLogsOperationSearchDTO monLogsOperationSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 用户ID
     * @return {@link MonLogsOperationVO} 用户 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    MonLogsOperationVO get(Long id);

    /**
     * 新增用户
     *
     * @param monLogsOperationAddDTO 新增用户 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    boolean add(MonLogsOperationAddDTO monLogsOperationAddDTO);

    /**
     * 编辑更新用户信息
     *
     * @param monLogsOperationUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    boolean update(MonLogsOperationUpdateDTO monLogsOperationUpdateDTO);

    /**
     * 批量删除用户信息
     *
     * @param monLogsOperationDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    boolean batchDelete(MonLogsOperationDeleteDTO monLogsOperationDeleteDTO);

}