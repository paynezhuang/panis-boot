package com.izpan.admin.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.google.common.collect.Lists;
import com.izpan.infrastructure.domain.BaseEntity;

import java.util.Collections;
import java.util.List;

/**
 * Mybatis Plus 代码生成
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.generator.CodeGenerator
 * @CreateTime 2023/7/14 - 17:27
 */
@SuppressWarnings({"java:S1075", "java:S1602", "java:S1874"})
public class CodeGenerator {

    // 生成存储路径
    private static final String EXPORT_FILE_PATH = "/Users/paynezhuang/Desktop/1/";

    // 生成的表名
    private static final String TABLE_NAMES = "sys_role";

    // 模块
    private static final String MODULES = "system";

    // 生成作者
    private static final String AUTHOR = "payne.zhuang <paynezhuang@gmail.com>";

    @SuppressWarnings({"java:S125","java:S6437"})
    public static void main(String[] args) {
        // 数据源配置
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/panis_boot?serverTimezone=GMT%2B8", "root", "root")
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author(AUTHOR)
                            // 开启 Springdoc 模式 默认值:false
                            .enableSpringdoc()
                            // 禁止打开输出目录 默认值:true
                            .disableOpenDir()
                            // 注释日期
                            .commentDate("yyyy-MM-dd")
                            // 定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .dateType(DateType.TIME_PACK)
                            // 指定输出目录
                            .outputDir(EXPORT_FILE_PATH + "src/main/java");
                })

                .packageConfig(builder -> {
                    // 父包模块名
                    builder.parent("com.izpan")
                            // Controller 包名 默认值:controller
                            .controller(String.format("admin.controller.%s", MODULES))
                            // Entity 包名 默认值:modules
                            .entity(String.format("modules.%s.domain.entity", MODULES))
                            // Service 包名 默认值:service
                            .service(String.format("modules.%s.service", MODULES))
                            // ServiceImpl 包名 默认值:service.impl
                            .serviceImpl(String.format("modules.%s.service.impl", MODULES))
                            // Mapper 包名 默认值:mapper
                            .mapper(String.format("modules.%s.repository.mapper", MODULES))
                            // Modules
                            // .moduleName(MODULES)
                            // Mapper xml 输出目录
                            .pathInfo(Collections.singletonMap(OutputFile.xml, String.format(EXPORT_FILE_PATH + "src/main/java/com/izpan/modules/%s/repository/mapper/", MODULES)));
                    // 设置mapperXml生成路径
                })

                .injectionConfig(consumer -> {
                    List<CustomFile> customFiles = Lists.newArrayList();
                    // VO
                    customFiles.add(new CustomFile.Builder().fileName("%sVO.java")
                            .enableFileOverride()
                            .templatePath("/templates/entityVO.java.ftl")
                            .packageName(String.format("modules.%s.domain.vo", MODULES))
                            .build());
                    String dtoPackageName = String.format("modules.%s.domain.dto", MODULES);
                    // DTO 以实际业务自行建立
                    customFiles.add(new CustomFile.Builder().fileName("%sSearchDTO.java")
                            .enableFileOverride()
                            .templatePath("/templates/entitySearchDTO.java.ftl")
                            .packageName(dtoPackageName)
                            .build());
                    // 新增DTO对象
                    customFiles.add(new CustomFile.Builder().fileName("%sAddDTO.java")
                            .enableFileOverride()
                            .templatePath("/templates/entityAddDTO.java.ftl")
                            .packageName(dtoPackageName)
                            .build());
                    // 编辑更新 DTO 对象
                    customFiles.add(new CustomFile.Builder().fileName("%sUpdateDTO.java")
                            .enableFileOverride()
                            .templatePath("/templates/entityUpdateDTO.java.ftl")
                            .packageName(dtoPackageName)
                            .build());
                    // 删除 DTO 对象
                    customFiles.add(new CustomFile.Builder().fileName("%sDeleteDTO.java")
                            .enableFileOverride()
                            .templatePath("/templates/entityDeleteDTO.java.ftl")
                            .packageName(dtoPackageName)
                            .build());
                    // BO
                    customFiles.add(new CustomFile.Builder().fileName("%sBO.java")
                            .enableFileOverride()
                            .templatePath("/templates/entityBO.java.ftl")
                            .packageName(String.format("modules.%s.domain.bo", MODULES))
                            .build());
                    // facade
                    customFiles.add(new CustomFile.Builder().fileName("I%sFacade.java")
                            .enableFileOverride()
                            .templatePath("/templates/facade.java.ftl")
                            .packageName(String.format("modules.%s.facade", MODULES))
                            .build());
                    // facade impl
                    customFiles.add(new CustomFile.Builder().fileName("%sFacadeImpl.java")
                            .enableFileOverride()
                            .templatePath("/templates/facade-impl.java.ftl")
                            .packageName(String.format("modules.%s.facade.impl", MODULES))
                            .build());
                    consumer.customFile(customFiles);
                })

                // 指定表名
                .strategyConfig(builder -> builder.addInclude(TABLE_NAMES)
                        // Service 策略配置
                        .serviceBuilder()
                        // 文件覆盖
                        .enableFileOverride()
                        // 格式化 Service 接口文件名称
                        .formatServiceFileName("I%sService")
                        // 格式化 Service 实现类文件名称
                        .formatServiceImplFileName("%sServiceImpl")


                        // 实体类策略配置
                        .entityBuilder()
                        // 文件覆盖
                        .enableFileOverride()
                        // 主键策略 雪花算法自动生成的id
                        .idType(IdType.ASSIGN_ID)
                        // 自动填充配置
                        // .addTableFills(new Column("create_time", FieldFill.INSERT))
                        // .addTableFills(new Property("update_time", FieldFill.INSERT_UPDATE))
                        // 开启 lombok
                        .enableLombok()
                        // 逻辑删除字段
                        .logicDeleteColumnName("is_deleted")
                        // 属性加上注解说明
                        // .enableTableFieldAnnotation()
                        // 设置父类
                        .superClass(BaseEntity.class)
                        // 添加父类公共字段
                        .addSuperEntityColumns("id", "create_user", "create_user_id", "create_time", "update_user", "update_user_id", "update_time", "is_deleted")

                        // Controller 策略配置
                        .controllerBuilder()
                        // 文件覆盖
                        .enableFileOverride()
                        // 开启 RestController 注解
                        .enableRestStyle()
                        // 格式化 Controller 文件名称
                        .formatFileName("%sController")

                        // Mapper 策略配置
                        .mapperBuilder()
                        // 文件覆盖
                        .enableFileOverride()
                        // 开启 BaseResultMap
                        .enableBaseResultMap()
                        // 开启 BaseColumnList
                        .enableBaseColumnList()
                        // 格式化 Mapper 文件名称
                        .formatMapperFileName("%sMapper")
                        // 格式化 Mapper xml 实现类文件名称
                        .formatXmlFileName("%sMapper"))


                // 使用Freemarker引擎模板
//                .templateEngine(new FreemarkerTemplateEngine())
                .templateEngine(new EnhanceFreemarkerTemplateEngine())
                .execute();

    }
}

