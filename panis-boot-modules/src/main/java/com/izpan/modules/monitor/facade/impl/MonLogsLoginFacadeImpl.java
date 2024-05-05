package com.izpan.modules.monitor.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.monitor.domain.bo.MonLogsLoginBO;
import com.izpan.modules.monitor.domain.dto.logs.login.MonLogsLoginAddDTO;
import com.izpan.modules.monitor.domain.dto.logs.login.MonLogsLoginDeleteDTO;
import com.izpan.modules.monitor.domain.dto.logs.login.MonLogsLoginSearchDTO;
import com.izpan.modules.monitor.domain.dto.logs.login.MonLogsLoginUpdateDTO;
import com.izpan.modules.monitor.domain.entity.MonLogsLogin;
import com.izpan.modules.monitor.domain.vo.MonLogsLoginVO;
import com.izpan.modules.monitor.facade.IMonLogsLoginFacade;
import com.izpan.modules.monitor.service.IMonLogsLoginService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 登录日志 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName MonLogsLoginFacadeImpl
 * @CreateTime 2024-05-05
 */

@Service
@RequiredArgsConstructor
public class MonLogsLoginFacadeImpl implements IMonLogsLoginFacade {

    @NonNull
    private IMonLogsLoginService monLogsLoginService;

    @Override
    public RPage<MonLogsLoginVO> listMonLogsLoginPage(PageQuery pageQuery, MonLogsLoginSearchDTO monLogsLoginSearchDTO) {
        MonLogsLoginBO monLogsLoginBO = CglibUtil.convertObj(monLogsLoginSearchDTO, MonLogsLoginBO::new);
        IPage<MonLogsLogin> monLogsLoginIPage = monLogsLoginService.listMonLogsLoginPage(pageQuery, monLogsLoginBO);
        return RPage.build(monLogsLoginIPage, MonLogsLoginVO::new);
    }

    @Override
    public MonLogsLoginVO get(Long id) {
        MonLogsLogin byId = monLogsLoginService.getById(id);
        return CglibUtil.convertObj(byId, MonLogsLoginVO::new);
    }

    @Override
    public boolean add(MonLogsLoginAddDTO monLogsLoginAddDTO) {
        MonLogsLoginBO monLogsLoginBO = CglibUtil.convertObj(monLogsLoginAddDTO, MonLogsLoginBO::new);
        return monLogsLoginService.save(monLogsLoginBO);
    }

    @Override
    public boolean update(MonLogsLoginUpdateDTO monLogsLoginUpdateDTO) {
        MonLogsLoginBO monLogsLoginBO = CglibUtil.convertObj(monLogsLoginUpdateDTO, MonLogsLoginBO::new);
        return monLogsLoginService.updateById(monLogsLoginBO);
    }

    @Override
    public boolean batchDelete(MonLogsLoginDeleteDTO monLogsLoginDeleteDTO) {
        MonLogsLoginBO monLogsLoginBO = CglibUtil.convertObj(monLogsLoginDeleteDTO, MonLogsLoginBO::new);
        return monLogsLoginService.removeBatchByIds(monLogsLoginBO.getIds(), true);
    }

}