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

package com.izpan.modules.monitor.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.izpan.infrastructure.domain.BaseEntity;
import java.io.Serializable;
import com.izpan.infrastructure.domain.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
    import java.io.Serial;

/**
* 文件管理 Entity 实体类
*
* @Author monitor
* @ProjectName panis-boot
* @ClassName com.izpan.modules.monitor.domain.entity.MonFile
* @CreateTime 2024-11-20 - 17:16:20
*/

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mon_file")
public class MonFile extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -5397867642544054703L;

    /**
    * 订单ID
    */
    private Long orderId;

    /**
    * 订单号码
    */
    private String orderNo;

    /**
    * 分类1:上传 2:下载
    */
    private String category;

    /**
    * 存储位置 1:本地 2:Minio
    */
    private String location;

    /**
    * 文件名称
    */
    private String name;

    /**
    * 文件尾缀
    */
    private String suffix;

    /**
    * 文件路径
    */
    private String path;

    /**
    * 文件大小
    */
    private Long length;

    /**
    * 文件大小(带单位)
    */
    private String size;

    /**
    * 文件UUID
    */
    private String uuid;

    /**
    * 文件类型
    */
    private String contentType;

    /**
    * 备注
    */
    private String remark;

}