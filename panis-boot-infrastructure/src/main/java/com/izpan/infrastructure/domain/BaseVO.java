package com.izpan.infrastructure.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用的 VO 类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.domain.BaseVO
 * @CreateTime 2023/7/10 - 14:47
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7743104535838008617L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "创建用户名称")
    private String createUser;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
