package com.izpan.modules.system.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 刷新 Token 对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.modules.system.domain.dto.RefreshTokenDTO
 * @CreateTime 2023/10/30 - 22:30
 */
@Getter
@Setter
@Schema(description = "刷新 Token 对象")
@Data
public class RefreshTokenDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3363800793578233381L;

    @NotBlank
    @Schema(description = "刷新token")
    private String refreshToken;
}
