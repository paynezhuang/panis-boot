package com.izpan.infrastructure.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用的实体类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.domain.BaseEntity
 * @CreateTime 2023/7/6 - 15:45
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1182725599996696966L;

    /**
     * ID
     */
    @Schema(description = "ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建用户名称
     */
    @Schema(description = "创建用户名称")
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建用户ID
     */
    @Schema(description = "创建用户ID")
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新用户名称
     */
    @Schema(description = "更新用户名称")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    /**
     * 更新用户ID
     */
    @Schema(description = "更新用户ID")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUserId;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除(0:否,1:是)
     */
    @TableLogic
    @JsonIgnore
    @Schema(description = "是否删除(0:否,1:是)")
    private Integer isDeleted;

}
