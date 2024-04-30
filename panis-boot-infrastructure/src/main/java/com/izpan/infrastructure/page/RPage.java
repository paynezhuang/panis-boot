package com.izpan.infrastructure.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.izpan.common.util.CglibUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.function.Supplier;

/**
 * 分页返回对象
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.page.RPage
 * @CreateTime 2023/7/13 - 13:37
 */
@Data
@AllArgsConstructor
public class RPage<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4865115289132710247L;

    @Schema(description = "当前页码")
    private long page;

    @Schema(description = "每页显示数量")
    private long pageSize;

    @Schema(description = "数据源")
    private transient List<T> records;

    @Schema(description = "总页数")
    private long pages;

    @Schema(description = "总数")
    private long total;

    /**
     * 组装分页返回对象 RPage.build()
     *
     * @param page Mybatis Plus 分页对象
     * @return {@link RPage} 分页返回对象
     * @author payne.zhuang
     * @CreateTime 2023-07-13 13:52
     */
    public static <T> RPage<T> build(IPage<T> page) {
        return new RPage<>(page.getCurrent(), page.getSize(), page.getRecords(), page.getPages(), page.getTotal());
    }

    /**
     * 组装分页返回对象 RPage.build()
     *
     * @param page     Mybatis Plus 分页对象
     * @param supplier 目标对象的供应商，用于创建目标对象实例
     * @param <T>      目标类型参数
     * @return {@link RPage} 构建的 RPage 分页对象，包含转换后的数据
     * @author payne.zhuang
     * @CreateTime 2023-07-13 13:52
     */
    public static <T, R> RPage<R> build(IPage<T> page, Supplier<R> supplier) {
        return new RPage<>(page.getCurrent(), page.getSize(), CglibUtil.convertList(page.getRecords(), supplier), page.getPages(), page.getTotal());
    }
}
