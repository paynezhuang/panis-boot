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

import java.util.Collection;
import java.util.List;

/**
 * Long 工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.util.LongUtil
 * @CreateTime 2024/11/5 - 14:33
 */
public class LongUtil {

    private LongUtil() {

    }

    /**
     * 转换为 Long 类型
     *
     * @param collection 集合
     * @return {@link List }<{@link Long }> 集合
     * @author payne.zhuang
     * @CreateTime 2024-11-26 - 19:01:24
     */
    public static List<Long> toLongList(Collection<?> collection) {
        return collection.stream()
                .filter(Long.class::isInstance)
                .map(Long.class::cast)
                .toList();
    }

}
