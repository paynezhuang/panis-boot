package com.izpan.modules.system.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.bo.SysUserBO;
import com.izpan.modules.system.domain.bo.SysUserResponsibilitiesBO;
import com.izpan.modules.system.domain.dto.user.*;
import com.izpan.modules.system.domain.entity.SysUser;
import com.izpan.modules.system.domain.vo.SysUserResponsibilitiesVO;
import com.izpan.modules.system.domain.vo.SysUserVO;
import com.izpan.modules.system.facade.ISysUserFacade;
import com.izpan.modules.system.service.ISysUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysUserFacadeImpl
 * @CreateTime 2023/7/6 - 16:06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserFacadeImpl implements ISysUserFacade {

    @NonNull
    private ISysUserService sysUserService;

    @Override
    public RPage<SysUserVO> listSysUserPage(PageQuery pageQuery, SysUserSearchDTO sysUserSearchDTO) {
        SysUserBO sysUserBO = CglibUtil.convertObj(sysUserSearchDTO, SysUserBO::new);
        IPage<SysUser> sysUserIPage = sysUserService.listSysUserPage(pageQuery, sysUserBO);
        return RPage.build(sysUserIPage, SysUserVO::new);
    }

    @Override
    public SysUserVO get(Long id) {
        SysUser byId = sysUserService.getById(id);
        return CglibUtil.convertObj(byId, SysUserVO::new);
    }

    @Override
    @Transactional
    public boolean addUser(SysUserAddDTO sysUserAddDTO) {
        SysUserBO sysUserBO = CglibUtil.convertObj(sysUserAddDTO, SysUserBO::new);
        return sysUserService.addUser(sysUserBO);
    }

    @Override
    @Transactional
    public boolean updateUser(SysUserUpdateDTO sysUserUpdateDTO) {
        SysUserBO sysUserBO = CglibUtil.convertObj(sysUserUpdateDTO, SysUserBO::new);
        return sysUserService.updateUser(sysUserBO);
    }

    @Override
    @Transactional
    public boolean batchDeleteUser(SysUserDeleteDTO sysUserDeleteDTO) {
        SysUserBO sysUserBO = CglibUtil.convertObj(sysUserDeleteDTO, SysUserBO::new);
        return sysUserService.removeBatchByIds(sysUserBO.getIds());
    }

    @Override
    @Transactional
    public String resetPassword(Long userId) {
        return sysUserService.resetPassword(userId);
    }

    @Override
    public SysUserResponsibilitiesVO queryUserResponsibilitiesWithUserId(Long userId) {
        SysUserResponsibilitiesBO responsibilitiesBO = sysUserService.queryUserResponsibilitiesWithUserId(userId);
        return CglibUtil.convertObj(responsibilitiesBO, SysUserResponsibilitiesVO::new);
    }

    @Override
    @Transactional
    public boolean updateUserResponsibilities(SysUserResponsibilitiesUpdateDTO updateDTO) {
        SysUserResponsibilitiesBO responsibilitiesBO = CglibUtil.convertObj(updateDTO, SysUserResponsibilitiesBO::new);
        return sysUserService.updateUserResponsibilities(responsibilitiesBO);
    }
}
