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

import com.google.common.base.Splitter;
import com.izpan.common.pool.StringPools;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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


    /**
     * 字符串转 List<Long>
     *
     * @param str 字符串
     * @return {@link List }<{@link Long }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-05 - 14:34:46
     */
    public static List<Long> toLongList(String str) {
        return toLongList(str, StringPools.COMMA);
    }

    /**
     * 字符串转 List<Long>
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return {@link List }<{@link Long }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-05 - 14:34:46
     */
    public static List<Long> toLongList(String str, String separator) {
        return Splitter.on(separator)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str)
                .stream()
                .map(Long::valueOf)
                .toList();
    }

    /**
     * 字符串转 List<String> 集合
     *
     * @param str 字符串
     * @return {@link List }<{@link String }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 16:15:01
     */
    public static List<String> toStringList(String str) {
        return toStringList(str, StringPools.COMMA);
    }

    /**
     * 字符串转 List<String> 集合
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return {@link List }<{@link String }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 16:15:23
     */
    public static List<String> toStringList(String str, String separator) {
        return Splitter.on(separator)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str);
    }

    /**
     * List<String> 转字符串
     *
     * @param list 集合
     * @return {@link String } 字符串, 逗号分隔符拼接字符串集合元素
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 16:15:47
     */
    public static String toString(List<String> list) {
        return toString(list, StringPools.COMMA);
    }

    /**
     * List<String> 转字符串
     *
     * @param list      集合
     * @param separator 分隔符
     * @return {@link String }
     * @author payne.zhuang
     * @CreateTime 2024-11-06 - 16:15:58
     */
    public static String toString(List<String> list, String separator) {
        return String.join(separator, list);
    }
}
