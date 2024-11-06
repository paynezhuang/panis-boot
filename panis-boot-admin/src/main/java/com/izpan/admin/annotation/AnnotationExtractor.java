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

package com.izpan.admin.annotation;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.google.common.collect.Maps;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 注解提取器
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.annotation.AnnotationExtractor
 * @CreateTime 2024/11/5 - 22:05
 */
@Slf4j
public class AnnotationExtractor {

    /**
     * 提取指定包装下所有Controller类的注解信息
     *
     * @param basePackage 需要扫描的基础包名
     * @return {@link Map }<{@link String }, {@link Map }<{@link String }, {@link Map }<{@link List }<{@link String }>, {@link String }>>> 注解信息
     * @author payne.zhuang
     * @CreateTime 2024-11-05 - 22:17:15
     */
    public static Map<String, LinkedHashMap<String, LinkedHashMap<List<String>, String>>> extractAllControllerMethodAnnotations(String basePackage) {
        // Controller , Method , Annotation , Value
        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<List<String>, String>>> result = Maps.newLinkedHashMap();

        try (ScanResult scanResult = new ClassGraph()
                .acceptPackages(basePackage)
                .enableClassInfo()
                .enableAnnotationInfo()
                .scan()) {

            for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(RestController.class.getName())) {
                if (classInfo.getSimpleName().endsWith("Controller")) {
                    Class<?> controllerClass = classInfo.loadClass();
                    LinkedHashMap<String, LinkedHashMap<List<String>, String>> controllerAnnotations =
                            extractControllerMethodMap(controllerClass);
                    result.put(controllerClass.getSimpleName(), controllerAnnotations);
                }
            }
        }

        return result;
    }

    /**
     * 提取Controller类的方法注解信息
     *
     * @param controllerClass Controller类
     * @return {@link Map }<{@link String }, {@link Map }<{@link List }<{@link String }>, {@link String }>> 注解信息
     * @author payne.zhuang
     * @CreateTime 2024-11-05 - 22:17:28
     */
    private static LinkedHashMap<String, LinkedHashMap<List<String>, String>> extractControllerMethodMap(Class<?> controllerClass) {
        LinkedHashMap<String, LinkedHashMap<List<String>, String>> result = Maps.newLinkedHashMap();

        // 获取Controller类上的@Tag注解
        if (controllerClass.isAnnotationPresent(Tag.class)) {
            Tag tag = controllerClass.getAnnotation(Tag.class);
            String tagName = tag.name();

            // 遍历Controller类中的所有方法
            Method[] methods = controllerClass.getDeclaredMethods();
            for (Method method : methods) {
                LinkedHashMap<List<String>, String> methodAnnotations = extractControllerAnnotationMap(method);

                // 如果方法上有注解信息，则添加到结果中
                if (!methodAnnotations.isEmpty()) {
                    result.put(tagName + ":" + method.getName(), methodAnnotations);
                }
            }
        }

        return result;
    }

    /**
     * 提取SaCheckPermission 和 Operation方法的注解信息
     *
     * @param method 方法
     * @return {@link Map }< {@link List }<{@link String }>,{@link String }> 注解信息
     * @author payne.zhuang
     * @CreateTime 2024-11-05 - 22:17:36
     */
    private static @NotNull LinkedHashMap<List<String>, String> extractControllerAnnotationMap(Method method) {

        LinkedHashMap<List<String>, String> methodAnnotations = Maps.newLinkedHashMap();

        // 获取方法上的@SaCheckPermission 和 @Operation 注解
        if (method.isAnnotationPresent(SaCheckPermission.class) && method.isAnnotationPresent(Operation.class)) {
            Operation operation = method.getAnnotation(Operation.class);
            SaCheckPermission saCheckPermission = method.getAnnotation(SaCheckPermission.class);
            methodAnnotations.put(List.of(saCheckPermission.value()), operation.summary());
        }

        return methodAnnotations;
    }

    /**
     * 提取所有Controller类的注解信息
     *
     * @return {@link Map }<{@link String }, {@link String }>
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 11:42:57
     */
    public static Map<String, String> extractAllControllerAnnotations() {
        return extractAllControllerAnnotations("com.izpan.admin.controller");
    }

    /**
     * 提取指定包装下所有Controller类的注解信息
     *
     * @param basePackage 包路径
     * @return {@link Map }<{@link String }, {@link String }>
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 11:41:13
     */
    @SuppressWarnings("java:S1602")
    public static Map<String, String> extractAllControllerAnnotations(String basePackage) {
        LinkedHashMap<String, String> result = Maps.newLinkedHashMap();
        Map<String, LinkedHashMap<String, LinkedHashMap<List<String>, String>>> annotations =
                extractAllControllerMethodAnnotations(basePackage);
        annotations.forEach((controller, methods) -> {
            // 所有 Controller
            methods.forEach((method, annotationValues) -> {
                // 所有的注解
                annotationValues.forEach((annotation, value) -> {
                    // 注解里的集合权限
                    annotation.forEach(a -> result.put(a, value));
                });
            });
        });
        return result;
    }

    public static void main(String[] args) {
        Map<String, LinkedHashMap<String, LinkedHashMap<List<String>, String>>> annotations =
                extractAllControllerMethodAnnotations("com.izpan.admin.controller");
        annotations.forEach((controller, methods) -> {
            log.info("Controller: {}", controller);
            methods.forEach((method, annotationValues) -> {
                log.info("  Method: {}", method);
                annotationValues.forEach((annotation, value) -> log.info("    {}: {}", annotation, value));
            });
            log.info("========================================");
        });
    }
}
