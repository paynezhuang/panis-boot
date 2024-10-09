package com.izpan.modules.system.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.common.util.CglibUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.page.RPage;
import com.izpan.modules.system.domain.bo.SysRolePermissionBO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionAddDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionDeleteDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionSearchDTO;
import com.izpan.modules.system.domain.dto.role.permission.SysRolePermissionUpdateDTO;
import com.izpan.modules.system.domain.entity.SysRolePermission;
import com.izpan.modules.system.domain.vo.SysRolePermissionVO;
import com.izpan.modules.system.facade.ISysRolePermissionFacade;
import com.izpan.modules.system.service.ISysRolePermissionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色权限管理 门面接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.facade.impl.SysRolePermissionFacadeImpl
 * @CreateTime 2023-08-05
 */

@Service
@RequiredArgsConstructor
public class SysRolePermissionFacadeImpl implements ISysRolePermissionFacade {

    @NonNull
    private ISysRolePermissionService sysRolePermissionService;

    @Override
    public RPage<SysRolePermissionVO> listSysRolePermissionPage(PageQuery pageQuery, SysRolePermissionSearchDTO sysRolePermissionSearchDTO) {
        SysRolePermissionBO sysRolePermissionBO = CglibUtil.convertObj(sysRolePermissionSearchDTO, SysRolePermissionBO::new);
        IPage<SysRolePermission> sysRolePermissionIPage = sysRolePermissionService.listSysRolePermissionPage(pageQuery, sysRolePermissionBO);
        return RPage.build(sysRolePermissionIPage, SysRolePermissionVO::new);
    }

    @Override
    public SysRolePermissionVO get(Long id) {
        SysRolePermission byId = sysRolePermissionService.getById(id);
        return CglibUtil.convertObj(byId, SysRolePermissionVO::new);
    }

    @Override
    @Transactional
    public boolean add(SysRolePermissionAddDTO sysRolePermissionAddDTO) {
        SysRolePermissionBO sysRolePermissionBO = CglibUtil.convertObj(sysRolePermissionAddDTO, SysRolePermissionBO::new);
        return sysRolePermissionService.add(sysRolePermissionBO);
    }

    @Override
    @Transactional
    public boolean update(SysRolePermissionUpdateDTO sysRolePermissionUpdateDTO) {
        SysRolePermissionBO sysRolePermissionBO = CglibUtil.convertObj(sysRolePermissionUpdateDTO, SysRolePermissionBO::new);
        return sysRolePermissionService.updateById(sysRolePermissionBO);
    }

    @Override
    @Transactional
    public boolean batchDelete(SysRolePermissionDeleteDTO sysRolePermissionDeleteDTO) {
        SysRolePermissionBO sysRolePermissionBO = CglibUtil.convertObj(sysRolePermissionDeleteDTO, SysRolePermissionBO::new);
        return sysRolePermissionService.removeBatchByIds(sysRolePermissionBO.getIds(), true);
    }

    @Override
    public List<Long> queryPermissionIdsWithRoleId(Long roleId) {
        return sysRolePermissionService.queryPermissionIdsWithRoleId(roleId);
    }

    @Override
    @Transactional
    public boolean addPermissionForRoleId(Long roleId, List<Long> permissionIds) {
        return sysRolePermissionService.addPermissionForRoleId(roleId, permissionIds);
    }
}