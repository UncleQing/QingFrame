package com.zidian.qingframe.library_common.utils;


import android.app.Application;
import android.os.Environment;

import java.io.File;

public class FileUtils {
    private File cache;

    public static FileUtils get() {
        return FileUtils.Holder.IN;
    }

    private FileUtils() {
    }

    public static void initialize(Application application) {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            get().cache = application.getExternalCacheDir();
        } else {
            get().cache = application.getCacheDir();
        }

    }

    public File getCache() {
        return this.cache;
    }

    private static class Holder {
        private static FileUtils IN = new FileUtils();

        private Holder() {
        }
    }
}
