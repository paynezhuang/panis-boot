package com.izpan.admin;

import com.izpan.infrastructure.util.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * PanisBoot Application 项目启动
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.PanisBootAdminApplication
 * @CreateTime 2023/7/6 - 11:11
 */

@EnableScheduling
@MapperScan("com.izpan.modules.**.repository.mapper")
@SpringBootApplication(scanBasePackages = "com.izpan.**")
public class PanisBootAdminApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PanisBootAdminApplication.class, args);
        SpringUtil.setApplicationContext(context);
    }

}
