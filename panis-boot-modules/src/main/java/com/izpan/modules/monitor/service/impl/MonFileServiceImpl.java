/*
 * All Rights Reserved: Copyright [2024] [Zhuang Pan (paynezhuang@gmail.com)]
 * Open Source Agreement: Apache License, Version 2.0
 * For educational purposes only, commercial use shall comply with the author's copyright information.
 * The author does not guarantee or assume any responsibility for the risks of using software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izpan.modules.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izpan.common.exception.BizException;
import com.izpan.common.util.LongUtil;
import com.izpan.infrastructure.enums.FileCategoryEnum;
import com.izpan.infrastructure.page.PageQuery;
import com.izpan.modules.monitor.domain.bo.MonFileBO;
import com.izpan.modules.monitor.domain.entity.MonFile;
import com.izpan.modules.monitor.repository.mapper.MonFileMapper;
import com.izpan.modules.monitor.service.IMonFileService;
import com.izpan.starter.common.util.FileUtil;
import com.izpan.starter.oss.config.OssProperties;
import com.izpan.starter.oss.domain.OssFile;
import com.izpan.starter.oss.manage.OssManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * 文件管理 Service 服务接口实现层
 *
 * @Author monitor
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.monitor.service.impl.MonFileServiceImpl
 * @CreateTime 2024-11-20 - 17:16:20
 */

@Service
@RequiredArgsConstructor
public class MonFileServiceImpl extends ServiceImpl<MonFileMapper, MonFile> implements IMonFileService {

    @NonNull
    private OssManager ossManager;

    @NonNull
    private OssProperties properties;

    @Override
    public IPage<MonFile> listMonFilePage(PageQuery pageQuery, MonFileBO monFileBO) {
        LambdaQueryWrapper<MonFile> queryWrapper = new LambdaQueryWrapper<MonFile>()
                .like(ObjectUtils.isNotEmpty(monFileBO.getOrderNo()), MonFile::getOrderNo, monFileBO.getOrderNo())
                .eq(ObjectUtils.isNotEmpty(monFileBO.getCategory()), MonFile::getCategory, monFileBO.getCategory())
                .eq(ObjectUtils.isNotEmpty(monFileBO.getLocation()), MonFile::getLocation, monFileBO.getLocation())
                .like(ObjectUtils.isNotEmpty(monFileBO.getName()), MonFile::getName, monFileBO.getName())
                .orderByDesc(MonFile::getCreateTime);
        return baseMapper.selectPage(pageQuery.buildPage(), queryWrapper);
    }

    @Override
    @SneakyThrows
    public boolean putFile(MultipartFile file) {
        if (ObjectUtils.isNotEmpty(file)) throw new BizException("文件不能为空");
        // 上传文件
        OssFile ossFile = ossManager.service().putFile(file.getOriginalFilename(), file.getInputStream());
        // 保存文件信息
        MonFile monFile = of(file, ossFile);
        return super.save(monFile);
    }

    @Override
    public String preview(Long id) {
        MonFile monFile = super.getById(id);
        return ossManager.service().preview(monFile.getPath());
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        syncDeleteWithOSS(LongUtil.toLongList(list));
        return super.removeBatchByIds(list, true);
    }

    @Override
    public void syncDeleteWithOSS(List<Long> ids) {
        if (Boolean.FALSE.equals(properties.getSyncDelete())) return;
        // 如果 oss 需要同步删除，则进行查询文件路径，再进行删除动作
        LambdaQueryWrapper<MonFile> queryWrapper = new LambdaQueryWrapper<MonFile>().in(MonFile::getId, ids);
        List<MonFile> monFiles = baseMapper.selectList(queryWrapper);
        List<String> filePaths = monFiles.stream().map(MonFile::getPath).toList();
        ossManager.service().removeFiles(filePaths);
    }

    /**
     * 转换文件信息
     *
     * @param file    文件
     * @param ossFile OSS 文件
     * @return {@link MonFile } 文件信息
     * @author payne.zhuang
     * @CreateTime 2024-11-26 - 11:55:47
     */
    private MonFile of(MultipartFile file, OssFile ossFile) {
        return MonFile.builder()
                .length(file.getSize())
                .name(ossFile.getName())
                .path(ossFile.getPath())
                .category(FileCategoryEnum.UPLOAD.getCode())
                .suffix(FileUtil.extension(ossFile.getName()))
                .location(ossFile.getLocation())
                .contentType(ossFile.getContentType())
                .uuid(ossFile.getUuid())
                .size(FileUtil.readableFileSize(file.getSize()))
                .build();
    }
}

