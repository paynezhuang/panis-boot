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

package com.izpan.infrastructure.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 下载工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.util.DownloadUtil
 * @CreateTime 2024/9/5 - 11:58
 */

@Slf4j
public class DownloadUtil {

    private DownloadUtil() {

    }

    /**
     * 下载文件
     *
     * @param response 响应
     * @param data     字节数据
     * @param fileName 文件名
     * @author payne.zhuang
     * @CreateTime 2024-09-05 - 11:59:49
     */
    public static void binary(HttpServletResponse response, byte[] data, String fileName) {
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream; charset=UTF-8");
            response.addHeader("Content-Length", String.valueOf(data.length));
            response.getOutputStream().write(data);
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("下载文件失败：", e);
        }
    }
}
