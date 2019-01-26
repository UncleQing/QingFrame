package com.zidian.qingframe.library_common.retrofit.interceptor;

import com.zidian.qingframe.library_common.retrofit.HttpHeader;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求头填充 拦截
 * 根据实际业务需要建立
 */
public class RequestInterceptor implements Interceptor {
    Map<String, String> headers;


    public RequestInterceptor() {
        headers = HttpHeader.getHeaders();
    }

    public RequestInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        //TODO 根据实际业务需要建立...
        return chain.proceed(requestBuilder.build());
    }
}
