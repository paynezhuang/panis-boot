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

package com.izpan.common.util;

import com.izpan.common.pool.StringPools;
import lombok.extern.slf4j.Slf4j;

/**
 * 字符串工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.util.StringUtil
 * @CreateTime 2024/7/21 - 19:09
 */

@Slf4j
public class StringUtil {

    private StringUtil() {

    }

    /**
     * 布尔值转字符串
     *
     * @param bool bool
     * @return {@link String } 字符串 true:1 false:0
     * @author payne.zhuang
     * @CreateTime 2024-07-21 - 19:12:35
     */
    public static String boolToString(boolean bool) {
        return bool ? StringPools.ONE : StringPools.ZERO;
    }
}
