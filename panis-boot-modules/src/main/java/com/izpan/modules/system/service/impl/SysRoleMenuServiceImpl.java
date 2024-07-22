package com.izpan.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.google.common.collect.Sets;
import com.izpan.common.constants.SystemCacheConstant;
import com.izpan.common.util.CglibUtil;
import com.izpan.common.util.CollectionUtil;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.infrastructure.util.RedisUtil;
import com.izpan.modules.system.domain.bo.SysMenuBO;
import com.izpan.modules.system.domain.bo.SysRoleMenuBO;
import com.izpan.modules.system.domain.entity.SysRoleMenu;
import com.izpan.modules.system.repository.mapper.SysRoleMenuMapper;
import com.izpan.modules.system.service.ISysMenuService;
import com.izpan.modules.system.service.ISysRoleMenuService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 角色菜单管理 Service 服务接口实现层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.entity.SysRoleMenu
 * @CreateTime 2023-08-05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @NonNull
    private ISysMenuService sysMenuService;

    @Override
    public IPage<SysRoleMenu> listSysRoleMenuPage(PageQuery pageQuery, SysRoleMenuBO sysRoleMenuBO) {
        return baseMapper.selectPage(pageQuery.buildPage(), new LambdaQueryWrapper<>());
    }

    @Override
    @Transactional
    public boolean add(SysRoleMenuBO sysRoleMenuBO) {
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuBO.getMenuIds().stream()
                .map(menuId -> new SysRoleMenu(sysRoleMenuBO.getRoleId(), menuId)).toList();
        return Db.saveBatch(sysRoleMenus);
    }

    @Override
    @Transactional
    public boolean addMenuForRoleId(Long roleId, List<Long> menuIds) {
        LambdaQueryWrapper<SysRoleMenu> inQueryWrapper = new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, roleId);
        // 查找原有权限
        List<SysRoleMenu> originSysRoleMenusList = baseMapper.selectList(inQueryWrapper);
        Set<Long> originMenuIdSet = originSysRoleMenusList.stream()
                .map(SysRoleMenu::getMenuId).collect(Collectors.toSet());
        // 前端传输菜单Ids，转换为 Set
        Set<Long> menuIdSet = Sets.newHashSet(menuIds);
        // 处理结果
        AtomicBoolean saveBath = new AtomicBoolean(true);
        CollectionUtil.handleDifference(
                originMenuIdSet,
                menuIdSet,
                // 处理增加和删除的菜单
                (addMenuIdSet, removeMenuIdSet) -> {
                    // 如有删除，则进行删除数据
                    if (!CollectionUtils.isEmpty(removeMenuIdSet)) {
                        LambdaQueryWrapper<SysRoleMenu> removeQueryWrapper = new LambdaQueryWrapper<SysRoleMenu>()
                                .eq(SysRoleMenu::getRoleId, roleId)
                                .in(SysRoleMenu::getMenuId, removeMenuIdSet);
                        baseMapper.delete(removeQueryWrapper);
                    }
                    // 根据菜单 ID 找出找出是否有目录的ID，进行移除，无需保存。在查找用户路由时，会填充进入给到前端
                    List<SysMenuBO> parentMenuIds = sysMenuService.queryWithDirectoryList(menuIds);
                    parentMenuIds.stream().map(SysMenuBO::getId).toList().forEach(menuIdSet::remove);
                    // 进行新增数据
                    if (!CollectionUtils.isEmpty(addMenuIdSet)) {
                        menuIdSet.remove(0L);
                        // 进行新增数据
                        List<SysRoleMenu> saveUserRoleList = addMenuIdSet.stream()
                                .map(menuId -> new SysRoleMenu(roleId, menuId)).toList();
                        saveBath.set(Db.saveBatch(saveUserRoleList));
                    }
                    sysMenuService.saveRoleMenuToCache(roleId, menuIdSet);
                }
        );
        return saveBath.get();
    }

    @Override
    @Cacheable(value = SystemCacheConstant.SYSTEM_ROLE_MENU_LIST, key = "#roleId")
    public List<SysMenuBO> queryMenuListWithRoleId(Long roleId) {
        return sysMenuService.queryMenuListWithRoleId(roleId);
    }

    @Override
    public List<Long> queryMenuIdsWithRoleId(Long roleId) {
        if (ObjectUtils.isEmpty(roleId)) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<SysRoleMenu> inQueryWrapper = new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> sysRoleMenus = baseMapper.selectList(inQueryWrapper);
        return sysRoleMenus.stream().map(SysRoleMenu::getMenuId).toList();
    }

    @Override
    public List<SysRoleMenuBO> queryMenuListWithRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<SysRoleMenu> inQueryWrapper = new LambdaQueryWrapper<SysRoleMenu>()
                .in(SysRoleMenu::getRoleId, roleIds);
        return CglibUtil.convertList(baseMapper.selectList(inQueryWrapper), SysRoleMenuBO::new);
    }

    @Override
    public void deleteRoleMenuCacheWithRoleId(Long menuId) {
        // 找出所有关于此菜单的角色
        LambdaQueryWrapper<SysRoleMenu> inQueryWrapper = new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getMenuId, menuId);
        List<SysRoleMenu> sysRoleMenus = baseMapper.selectList(inQueryWrapper);
        sysRoleMenus.stream().map(SysRoleMenu::getRoleId).toList()
                .forEach(roleId -> RedisUtil.del(SystemCacheConstant.roleMenuListKey(roleId)));
    }
}
