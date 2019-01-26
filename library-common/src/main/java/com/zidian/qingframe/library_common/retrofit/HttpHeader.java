package com.zidian.qingframe.library_common.retrofit;


import com.zidian.qingframe.library_common.AppConfig;
import com.zidian.qingframe.library_common.utils.AppUtils;
import com.zidian.qingframe.library_common.utils.DeviceUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求头处理
 * 根据服务器需求设定
 */

public class HttpHeader {
    private static Map<String, String> headers;

    public static Map<String, String> getHeaders() {
        if (null == headers) {
            headers = new HashMap<>();
            headers.put("x-v", AppConfig.INTERFACE_VERSION);
            headers.put("x-appid", AppConfig.APPID);
            headers.put("x-ver", AppConfig.PROTOCOL_VERSION);
            headers.put("x-client-info", String.format("%s:%s:%s:%s",
                    DeviceUtils.getDeviceModel(),
                    DeviceUtils.getSystemCurrentVersion(),
                    DeviceUtils.getDeviceId(AppUtils.getApp())
            ));

            headers.put("x-client-version", DeviceUtils.getAppVersion(AppUtils.getApp()));
        }
        return headers;
    }
}
