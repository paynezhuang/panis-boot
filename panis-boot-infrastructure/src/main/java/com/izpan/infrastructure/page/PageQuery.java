package com.izpan.infrastructure.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页查询对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.page.PageQuery
 * @CreateTime 2023/7/12 - 22:19
 */
@Getter
@Setter
public class PageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = -9112559334485771185L;

    @Min(value = 1, message = "当前页码不能小于1")
    @Schema(description = "当前页码", defaultValue = "1")
    private Integer page = 1;

    @Range(min = 1, max = 500, message = "每页显示数量范围为[1,500]")
    @Schema(description = "每页显示数量", defaultValue = "20")
    private Integer pageSize = 20;

    /**
     * 构建分页对象
     *
     * @return {@link IPage} 分页对象
     * @author payne.zhuang
     * @CreateTime 2023-07-13 13:51
     */
    @JsonIgnore
    public <T> IPage<T> buildPage() {
        return new Page<>(this.getPage(), this.getPageSize());
    }

}
