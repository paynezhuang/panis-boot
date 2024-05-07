package com.izpan.modules.monitor.facade;

import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorAddDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorDeleteDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorSearchDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorUpdateDTO;
import com.izpan.modules.monitor.domain.vo.MonLogsErrorVO;

/**
 * 错误异常日志 门面接口层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.domain.entity.IMonLogsErrorFacade
 * @CreateTime 2024-05-07
 */

public interface IMonLogsErrorFacade {

    /**
     * 错误异常日志 - 分页查询
     *
     * @param pageQuery             分页对象
     * @param monLogsErrorSearchDTO 查询对象
     * @return {@link RPage} 查询结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    RPage<MonLogsErrorVO> listMonLogsErrorPage(PageQuery pageQuery, MonLogsErrorSearchDTO monLogsErrorSearchDTO);

    /**
     * 根据 ID 获取详情信息
     *
     * @param id 用户ID
     * @return {@link MonLogsErrorVO} 用户 VO 对象
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    MonLogsErrorVO get(Long id);

    /**
     * 新增用户
     *
     * @param monLogsErrorAddDTO 新增用户 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    boolean add(MonLogsErrorAddDTO monLogsErrorAddDTO);

    /**
     * 编辑更新用户信息
     *
     * @param monLogsErrorUpdateDTO 编辑更新 DTO 对象
     * @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    boolean update(MonLogsErrorUpdateDTO monLogsErrorUpdateDTO);

    /**
     * 批量删除用户信息
     *
     * @param monLogsErrorDeleteDTO 删除 DTO 对象
     * @return @return {@link Boolean} 结果
     * @author payne.zhuang
     * @CreateTime 2024-05-07 15:10
     */
    boolean batchDelete(MonLogsErrorDeleteDTO monLogsErrorDeleteDTO);

}