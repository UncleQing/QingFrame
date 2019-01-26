package com.zidian.qingframe.library_common.retrofit.interceptor;


import android.text.TextUtils;


import com.zidian.qingframe.library_common.retrofit.ProgressResponseBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 下载拦截器，用于进度条显示
 */
public class DownloadInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String value = request.header("Upgrade");
        if (!TextUtils.isEmpty(value) || "upgrade".equals(value)) {
            //只有是下载的时候会包含这个Upgrade头信息
                return response.newBuilder()
                        .body(new ProgressResponseBody(response.body()))
                        .build();
        }
        return response;
    }

}
