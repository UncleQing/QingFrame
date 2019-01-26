package com.zidian.qingframe.library_common.retrofit;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * 所有网络请求接口管理类
 * 根据不同请求配置接口名、参数
 */
public interface IApiService {
    @POST("/test/ping")
    Observable<HttpResult> testPing();
}
