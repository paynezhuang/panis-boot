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

package com.izpan.infrastructure.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

/**
 * Druid 数据源配置
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.config.DruidConfiguration
 * @CreateTime 2024/11/29 - 14:14
 */

@Configuration
public class DruidConfiguration {

    /**
     * 配置 Druid 数据源
     *
     * @return {@link DataSource } 数据源
     * @author payne.zhuang
     * @CreateTime 2024-11-29 - 14:16:06
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidPrimary() {
        try (DruidDataSource druidDataSource = new DruidDataSource()) {
            // 设置代理过滤器
            druidDataSource.setProxyFilters(getProxyFilters());
            return druidDataSource;
        }
    }

    /**
     * 获取代理过滤器
     *
     * @return {@link List<Filter> } 过滤器集合
     * @author payne.zhuang
     * @CreateTime 2024-11-29 - 14:16:55
     */
    private List<Filter> getProxyFilters() {
        List<Filter> filterList = Lists.newArrayList();
        filterList.add(wallFilter());
        return filterList;
    }

    /**
     * 配置 WallFilter
     *
     * @return {@link WallFilter } WallFilter 过滤器实例
     * @author payne.zhuang
     * @CreateTime 2024-11-29 - 14:16:28
     */
    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    /**
     * 配置 WallConfig
     *
     * @return {@link WallConfig } WallConfig 配置信息实例
     * @author payne.zhuang
     * @CreateTime 2024-11-29 - 14:17:22
     */
    @Bean
    public WallConfig wallConfig() {
        WallConfig config = new WallConfig();
        // 允许一次执行多条语句
        config.setMultiStatementAllow(true);
        config.setNoneBaseStatementAllow(true);
        return config;
    }
}
