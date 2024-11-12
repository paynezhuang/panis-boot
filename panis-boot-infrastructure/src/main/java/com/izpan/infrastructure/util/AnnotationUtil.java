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

package com.izpan.infrastructure.util;

import lombok.SneakyThrows;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 注解工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.util.AnnotationUtil
 * @CreateTime 2024/11/12 - 11:33
 */
public class AnnotationUtil {

    private AnnotationUtil() {

    }

    /**
     * 判断是否包含指定注解
     *
     * @param currentClass    类
     * @param fieldName       字段名
     * @param annotationClass 注解类
     * @return 是否包含指定注解
     */
    @SneakyThrows
    public static <A extends Annotation> boolean hasAnnotation(Class<?> currentClass, String fieldName, Class<A> annotationClass) {
        Field field = null;
        while (currentClass != null) {
            try {
                field = currentClass.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                // 取不到字段，则取父类
                currentClass = currentClass.getSuperclass();
            }
        }
        assert field != null;
        return AnnotationUtils.getAnnotation(field, annotationClass) != null;
    }
}
