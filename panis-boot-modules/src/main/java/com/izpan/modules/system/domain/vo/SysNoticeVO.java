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

package com.izpan.modules.system.domain.vo;

import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.time.LocalDateTime;

/**
* 通知公告 VO 展示类
*
* @Author payne.zhuang <paynezhuang@gmail.com>
* @ProjectName panis-boot
* @ClassName com.izpan.modules.system.domain.vo.SysNoticeVO
* @CreateTime 2024-11-10 - 12:55:52
*/

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SysNoticeVO", description = "通知公告 VO 对象")
public class SysNoticeVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 6360378836188126724L;

    @Schema(description = "分类 1:通知 2:公告")
    private String category;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "发布时间")
    private LocalDateTime releaseTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "是否启用(0:禁用,1:启用)")
    private String status;

}