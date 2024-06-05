package com.izpan.modules.system.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.bo.SysUserRoleBO;
import com.izpan.modules.system.domain.dto.user.role.SysUserRoleAddDTO;
import com.izpan.modules.system.domain.dto.user.role.SysUserRoleDeleteDTO;
import com.izpan.modules.system.domain.dto.user.role.SysUserRoleSearchDTO;
import com.izpan.modules.system.domain.dto.user.role.SysUserRoleUpdateDTO;
import com.izpan.modules.system.domain.entity.SysUserRole;
import com.izpan.modules.system.domain.vo.SysUserRoleVO;
import com.izpan.modules.system.facade.ISysUserRoleFacade;
import com.izpan.modules.system.service.ISysUserRoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户角色管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysUserRoleFacadeImpl
 * @CreateTime 2023-07-24
 */

@Service
@RequiredArgsConstructor
public class SysUserRoleFacadeImpl implements ISysUserRoleFacade {

    @NonNull
    private ISysUserRoleService sysUserRoleService;

    @Override
    public RPage<SysUserRoleVO> listSysUserRolePage(PageQuery pageQuery, SysUserRoleSearchDTO sysUserRoleSearchDTO) {
        SysUserRoleBO sysUserRoleBO = CglibUtil.convertObj(sysUserRoleSearchDTO, SysUserRoleBO::new);
        IPage<SysUserRole> sysUserRoleIPage = sysUserRoleService.listSysUserRolePage(pageQuery, sysUserRoleBO);
        return RPage.build(sysUserRoleIPage, SysUserRoleVO::new);
    }

    @Override
    public SysUserRoleVO get(Long id) {
        SysUserRole byId = sysUserRoleService.getById(id);
        return CglibUtil.convertObj(byId, SysUserRoleVO::new);
    }

    @Override
    @Transactional
    public boolean add(SysUserRoleAddDTO sysUserRoleAddDTO) {
        SysUserRoleBO sysUserRoleBO = CglibUtil.convertObj(sysUserRoleAddDTO, SysUserRoleBO::new);
        return sysUserRoleService.save(sysUserRoleBO);
    }

    @Override
    @Transactional
    public boolean update(SysUserRoleUpdateDTO sysUserRoleUpdateDTO) {
        SysUserRoleBO sysUserRoleBO = CglibUtil.convertObj(sysUserRoleUpdateDTO, SysUserRoleBO::new);
        return sysUserRoleService.updateById(sysUserRoleBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysUserRoleDeleteDTO sysUserRoleDeleteDTO) {
        SysUserRoleBO sysUserRoleBO = CglibUtil.convertObj(sysUserRoleDeleteDTO, SysUserRoleBO::new);
        return sysUserRoleService.removeBatchByIds(sysUserRoleBO.getIds(), true);
    }

}