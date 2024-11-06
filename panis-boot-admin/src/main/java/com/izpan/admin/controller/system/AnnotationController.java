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

package com.izpan.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.izpan.common.api.Result;
import com.izpan.common.constants.SystemCacheConstant;
import com.izpan.common.pool.StringPools;
import com.izpan.infrastructure.util.JacksonUtil;
import com.izpan.infrastructure.util.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 注解管理 Controller 控制层
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.admin.controller.system.AnnotationController
 * @CreateTime 2024/11/6 - 11:32
 */

@Slf4j
@RestController
@Tag(name = "注解管理")
@RequiredArgsConstructor
@RequestMapping("/annotation")
public class AnnotationController {

    @Resource
    Environment environment;

    @GetMapping("/permission")
    @SaCheckPermission("annotation:permission")
    @Operation(operationId = "1", summary = "获取 Controller 层所有的权限注解信息")
    public Result<Map<String, String>> getAnnotationData() {
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        String permissionKey = SystemCacheConstant.controllerAnnotationPermissionKey();
        if (StringPools.DEV.equalsIgnoreCase(profile) && RedisUtil.exists(permissionKey)) {
            Object object = RedisUtil.get(permissionKey);
            return Result.data(JacksonUtil.objectToMap(object, String.class, String.class));
        }
        return Result.success();
    }
}
