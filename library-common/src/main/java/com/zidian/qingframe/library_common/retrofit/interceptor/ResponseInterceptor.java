package com.zidian.qingframe.library_common.retrofit.interceptor;


import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Response预处理 拦截
 * 根据实际业务需求创建
 */
public class ResponseInterceptor implements Interceptor {

    public ResponseInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        //TODO 根据实际业务需求创建
        return response;
    }
}
