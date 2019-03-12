package com.zidian.qingframe.library_common.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 所有网络请求接口管理类
 * 根据不同请求配置接口名、参数
 */
public interface IApiService {
    @POST("/test/ping")
    Observable<HttpResult> testPing();

    /**
     * 下载apk， 不使用缓存
     * @param url
     * @param head
     * @return
     */
    @Streaming
    @GET
    @Headers({"Accept-Encoding:identity","Cache-Control:no-store"})
    Observable<ResponseBody> download(@Url String url, @Header("Upgrade") String head);
}
