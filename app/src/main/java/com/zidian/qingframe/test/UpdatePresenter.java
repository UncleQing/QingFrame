package com.zidian.qingframe.test;

import android.os.Environment;
import android.text.TextUtils;

import com.zidian.qingframe.library_common.AppConfig;
import com.zidian.qingframe.library_common.base.IBaseView;
import com.zidian.qingframe.library_common.retrofit.RetrofitApiService;
import com.zidian.qingframe.library_common.utils.AppUtils;
import com.zidian.qingframe.library_common.utils.FileUtils;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class UpdatePresenter {
    private final static String DOWNLOAD_DIR = AppUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            + File.separator;
    private static String APP_FILE = "qingFrame.apk";
    private UpdateActivity mView;

    UpdatePresenter(IBaseView view) {
        mView = (UpdateActivity) view;
    }

    /**
     * 根据url下载apk
     *
     * @param url
     */
    public void downLoadApk(String url) {
        if (AppConfig.isDownLoad) {
            mView.showToast("已有相同任务");
            return;
        }
        RetrofitApiService.getInstance().downloadApk(url).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showToast("已添加到下载任务");
                        AppConfig.isDownLoad = true;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        File file = FileUtils.get().writeFile(responseBody.byteStream(), DOWNLOAD_DIR, APP_FILE);
                        mView.onDownloadSucceed(file);
                        AppConfig.isDownLoad = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!TextUtils.isEmpty(e.getMessage())) {
                            mView.showToast(e.getMessage());
                        }
                        AppConfig.isDownLoad = false;
                    }

                    @Override
                    public void onComplete() {
                        AppConfig.isDownLoad = false;
                    }
                });
    }
}
