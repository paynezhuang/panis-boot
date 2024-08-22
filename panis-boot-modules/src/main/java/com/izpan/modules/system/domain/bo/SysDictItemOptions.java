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

package com.izpan.modules.system.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据字典子项 Options 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.bo.SysDictItemOptions
 * @CreateTime 2024/8/15 - 22:03
 */

@Data
@Builder
public class SysDictItemOptions implements Serializable {

    @Serial
    private static final long serialVersionUID = -8807424818908992565L;

    /**
     * 显示的值
     */
    private String label;

    /**
     * 值
     */
    private String value;

    /**
     * 类型(前端渲染类型)
     */
    private String type;

    /**
     * 排序
     */
    private Integer sort;

}
