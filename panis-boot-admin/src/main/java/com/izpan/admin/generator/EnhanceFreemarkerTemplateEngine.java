package com.izpan.admin.generator;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.izpan.common.pool.StringPools;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Mybatis Plus 自定义代码生成引擎
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.generator.EnhanceFreemarkerTemplateEngine
 * @CreateTime 2023/7/15 - 11:27
 */

@Slf4j
@Component
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String dtoSubPackageName = tableInfo.getName().substring(tableInfo.getName().indexOf(StringPools.UNDERSCORE) + 1);
        String parentPath = this.getPathInfo(OutputFile.parent);
        customFiles.forEach(file -> {
            String filePath = StringUtils.isNotBlank(file.getFilePath()) ? file.getFilePath() : parentPath;
            if (StringUtils.isNotBlank(file.getPackageName())) {
                // 如果是 Dto 的话，则再加上子包名
                String packageName = file.getPackageName() + (file.getPackageName().contains(".dto") ? StringPools.DOT + dtoSubPackageName : StringPools.EMPTY);
                filePath = filePath + File.separator + packageName;
                filePath = filePath.replace(StringPools.DOT, File.separator);
            }

            String fileName = filePath + File.separator + String.format(file.getFileName(), entityName);
            log.info("fileName:{}", fileName);
            this.outputFile(new File(fileName), objectMap, file.getTemplatePath(), file.isFileOverride());
        });
    }
}
