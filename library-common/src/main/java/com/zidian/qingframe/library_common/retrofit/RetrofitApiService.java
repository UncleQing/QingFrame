package com.zidian.qingframe.library_common.retrofit;


import com.zidian.qingframe.library_common.retrofit.interceptor.DownloadInterceptor;
import com.zidian.qingframe.library_common.retrofit.interceptor.NetCheckInterceptor;
import com.zidian.qingframe.library_common.retrofit.interceptor.RequestInterceptor;
import com.zidian.qingframe.library_common.retrofit.interceptor.ResponseInterceptor;
import com.zidian.qingframe.library_common.utils.AppUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiService {
    private Retrofit mRetrofit;
    private IApiService mApiService;

    private static class Holder {
        private static final RetrofitApiService INSTANCE = new RetrofitApiService();
    }

    public static RetrofitApiService getInstance() {
        return Holder.INSTANCE;
    }

    private RetrofitApiService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new NetCheckInterceptor())  //网络连接状态
                .addInterceptor(new RequestInterceptor())   //requset
                .addInterceptor(new ResponseInterceptor())  //response
                .addNetworkInterceptor(new DownloadInterceptor())   //下载进度
                .connectTimeout(RetrofitConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(RetrofitConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(RetrofitConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS) //超时和重连，上同
                .cache(new Cache(AppUtils.getApp().getExternalCacheDir(), RetrofitConfig.CACHE_SIZE))
                .retryOnConnectionFailure(true) //错误重连
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(RetrofitConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mApiService = mRetrofit.create(IApiService.class);
    }

    public IApiService getApiService() {
        return mApiService;
    }

    /**
     * ----------------------------
     * 根据不同业务请求创建
     * ----------------------------
     */

    /**
     * 测试模块-测试接口
     * @return
     */
    Observable<HttpResult> testPing() {
        return mApiService.testPing();
    }

    /**
     * 个人中心-下载apk
     *
     * @return
     */
    public Observable<ResponseBody> downloadApk(String url) {
        return mApiService.download(url, "upgrade");
    }
}
