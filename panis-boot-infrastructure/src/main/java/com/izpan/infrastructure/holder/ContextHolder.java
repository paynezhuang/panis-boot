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

package com.izpan.infrastructure.holder;

/**
 * 上下文持有者
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.holder.ContextHolder
 * @CreateTime 2024/8/22 - 09:46
 */
public class ContextHolder {

    private ContextHolder() {

    }

    /**
     * 请求语言
     */
    private static final ThreadLocal<String> REQUEST_LANGUAGE = new ThreadLocal<>();

    public static String language() {
        return REQUEST_LANGUAGE.get();
    }

    public static void setLanguage(String value) {
        REQUEST_LANGUAGE.set(value);
    }

    public static void removeLanguage() {
        REQUEST_LANGUAGE.remove();
    }
}
