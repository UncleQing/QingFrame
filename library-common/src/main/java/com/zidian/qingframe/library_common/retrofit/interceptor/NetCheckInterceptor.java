package com.zidian.qingframe.library_common.retrofit.interceptor;


import com.zidian.qingframe.library_common.utils.AppUtils;
import com.zidian.qingframe.library_common.utils.NetworkUtils;

import java.io.IOException;
import java.net.UnknownHostException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 网络检测 拦截器
 */
public class NetCheckInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isNetworkConnected(AppUtils.getApp())) {
            throw new UnknownHostException("no network is connected");
        }
        return chain.proceed(chain.request().newBuilder().build());
    }
}
