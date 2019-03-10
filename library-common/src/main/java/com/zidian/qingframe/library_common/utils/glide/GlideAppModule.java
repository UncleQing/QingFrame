package com.zidian.qingframe.library_common.utils.glide;


import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.zidian.qingframe.library_common.utils.FileUtils;

import java.io.File;

public class GlideAppModule extends AppGlideModule {
    public GlideAppModule() {
    }

    @SuppressLint({"CheckResult"})
    public void applyOptions(Context context, GlideBuilder builder) {
        RequestOptions defaultOptions = new RequestOptions();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= 19) {
            assert activityManager != null;

            defaultOptions.format(activityManager.isLowRamDevice() ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888);
        } else {
            defaultOptions.format(DecodeFormat.PREFER_RGB_565);
        }

        File cache = new File(FileUtils.get().getCache(), "glide");
        String path = cache.getAbsolutePath();
        builder.setDiskCache(new DiskLruCacheFactory(path, 10485760));
        builder.setDefaultRequestOptions(defaultOptions);
    }

    public boolean isManifestParsingEnabled() {
        return false;
    }
}
