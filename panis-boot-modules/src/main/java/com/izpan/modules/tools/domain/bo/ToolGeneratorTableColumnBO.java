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

package com.izpan.modules.tools.domain.bo;

import com.izpan.modules.tools.domain.entity.ToolGeneratorTableColumn;
import lombok.Data;

import java.io.Serial;
import java.util.List;

/**
 * 代码生成表字段列管理 BO 业务处理对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.domain.bo.ToolGeneratorTableColumnBO
 * @CreateTime 2024-08-26 - 22:13:50
 */

@Data
public class ToolGeneratorTableColumnBO extends ToolGeneratorTableColumn {

    @Serial
    private static final long serialVersionUID = 2678450102210865725L;

    /**
     * Ids
     */
    private List<Long> ids;

}