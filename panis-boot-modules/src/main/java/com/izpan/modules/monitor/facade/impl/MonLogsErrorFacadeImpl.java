package com.izpan.modules.monitor.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.bo.MonLogsErrorBO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorAddDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorDeleteDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorSearchDTO;
import com.izpan.modules.monitor.domain.dto.logs.exception.MonLogsErrorUpdateDTO;
import com.izpan.modules.monitor.domain.entity.MonLogsError;
import com.izpan.modules.monitor.domain.vo.MonLogsErrorVO;
import com.izpan.modules.monitor.facade.IMonLogsErrorFacade;
import com.izpan.modules.monitor.service.IMonLogsErrorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 错误异常日志 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsErrorFacadeImpl
 * @CreateTime 2024-05-07
 */

@Service
@RequiredArgsConstructor
public class MonLogsErrorFacadeImpl implements IMonLogsErrorFacade {

    @NonNull
    private IMonLogsErrorService monLogsErrorService;

    @Override
    public RPage<MonLogsErrorVO> listMonLogsErrorPage(PageQuery pageQuery, MonLogsErrorSearchDTO monLogsErrorSearchDTO) {
        MonLogsErrorBO monLogsErrorBO = CglibUtil.convertObj(monLogsErrorSearchDTO, MonLogsErrorBO::new);
        IPage<MonLogsError> monLogsErrorIPage = monLogsErrorService.listMonLogsErrorPage(pageQuery, monLogsErrorBO);
        return RPage.build(monLogsErrorIPage, MonLogsErrorVO::new);
    }

    @Override
    public MonLogsErrorVO get(Long id) {
        MonLogsError byId = monLogsErrorService.getById(id);
        return CglibUtil.convertObj(byId, MonLogsErrorVO::new);
    }

    @Override
    public boolean add(MonLogsErrorAddDTO monLogsErrorAddDTO) {
        MonLogsErrorBO monLogsErrorBO = CglibUtil.convertObj(monLogsErrorAddDTO, MonLogsErrorBO::new);
        return monLogsErrorService.save(monLogsErrorBO);
    }

    @Override
    public boolean update(MonLogsErrorUpdateDTO monLogsErrorUpdateDTO) {
        MonLogsErrorBO monLogsErrorBO = CglibUtil.convertObj(monLogsErrorUpdateDTO, MonLogsErrorBO::new);
        return monLogsErrorService.updateById(monLogsErrorBO);
    }

    @Override
    public boolean batchDelete(MonLogsErrorDeleteDTO monLogsErrorDeleteDTO) {
        MonLogsErrorBO monLogsErrorBO = CglibUtil.convertObj(monLogsErrorDeleteDTO, MonLogsErrorBO::new);
        return monLogsErrorService.removeBatchByIds(monLogsErrorBO.getIds(), true);
    }

}