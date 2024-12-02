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

package com.izpan.infrastructure.gson.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.izpan.common.pool.StringPools;

import java.io.IOException;

/**
 * String 类型适配器
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.gson.adapter.StringAdapter
 * @CreateTime 2024/12/2 - 15:21
 */
public class StringAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter out, String value) throws IOException {
        if (value != null) {
            // 去除转义字符
            out.value(value.replace(StringPools.BACK_SLASH, StringPools.EMPTY));
        } else {
            out.nullValue();
        }
    }

    @Override
    public String read(JsonReader in) throws IOException {
        return in.nextString();
    }
}
