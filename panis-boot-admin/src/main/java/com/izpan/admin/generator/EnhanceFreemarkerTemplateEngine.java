package com.izpan.admin.generator;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.izpan.common.pool.StringPools;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
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
    protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull @NonNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String parentPath = this.getPathInfo(OutputFile.parent);
        // 配置文件
        ConfigBuilder config = (ConfigBuilder) objectMap.get("config");
        InjectionConfig injectionConfig = config.getInjectionConfig();
        assert injectionConfig != null;
        // 自定义参数
        Map<String, Object> customMap = injectionConfig.getCustomMap();
        // 遍历文件
        for (CustomFile file : customFiles) {
            String filePath = StringUtils.isNotBlank(file.getFilePath()) ? file.getFilePath() : parentPath;
            if (StringUtils.isNotBlank(file.getPackageName())) {
                String packageName = getString(tableInfo, file, customMap);
                log.info("packageName:{}", packageName);
                filePath = filePath + File.separator + packageName;
                filePath = filePath.replace(StringPools.DOT, File.separator);
            }
            String fileName = filePath + File.separator + String.format(file.getFileName(), entityName);
            log.info("fileName:{}", fileName);
            injectionConfig.getCustomMap().putAll(customMap);
            this.outputFile(new File(fileName), objectMap, file.getTemplatePath(), file.isFileOverride());
        }

    }

    /**
     * 获取包名
     *
     * @param tableInfo 表信息
     * @param file      文件信息
     * @param customMap 自定义参数
     * @return 包名
     */
    private static String getString(TableInfo tableInfo, CustomFile file, Map<String, Object> customMap) {
        String packageName = file.getPackageName();
        String parent = (String) customMap.get("parent");
        // 如果是dto，把包名中的下划线替换成点，形成多级包名，以及再加上子包名
        if (file.getPackageName().contains(".dto")) {
            String subPackageName = tableInfo.getName().substring(tableInfo.getName().indexOf(StringPools.UNDERSCORE) + 1);
            subPackageName = subPackageName.replace(StringPools.UNDERSCORE, StringPools.DOT);
            packageName = packageName + StringPools.DOT + subPackageName;
            customMap.put("dtoPackageName", parent + StringPools.DOT + packageName);
        } else if (file.getPackageName().contains(".facade")) {
            customMap.put("facadePackageName", parent + StringPools.DOT + packageName);
        } else if (file.getPackageName().contains(".vo")) {
            customMap.put("voPackageName", parent + StringPools.DOT + packageName);
        }
        return packageName;
    }
}
