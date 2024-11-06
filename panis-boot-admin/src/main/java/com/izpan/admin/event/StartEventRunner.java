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

package com.izpan.admin.event;

import com.izpan.admin.annotation.AnnotationExtractor;
import com.izpan.common.constants.SystemCacheConstant;
import com.izpan.common.pool.StringPools;
import com.izpan.infrastructure.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 启动事件监听
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.event.StartEventRunner
 * @CreateTime 2024/11/6 - 11:44
 */

@Slf4j
@Component
public class StartEventRunner implements CommandLineRunner {

    private final Environment environment;

    public StartEventRunner(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        // 获取当前的启动环境
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        if (StringPools.DEV.equalsIgnoreCase(profile)) {
            long currentTimeMillis = System.currentTimeMillis();
            log.info("当前启动环境为开发环境，开始提取 Controller 层权限注解信息");
            Map<String, String> allControllerAnnotations = AnnotationExtractor.extractAllControllerAnnotations();
            String permissionKey = SystemCacheConstant.controllerAnnotationPermissionKey();
            RedisUtil.set(permissionKey, allControllerAnnotations);
            log.info("Controller 层权限注解信息提取完成，共计耗时：{}ms", System.currentTimeMillis() - currentTimeMillis);
        }
    }
}
