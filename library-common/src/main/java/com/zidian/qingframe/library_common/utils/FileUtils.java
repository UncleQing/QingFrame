package com.zidian.qingframe.library_common.utils;


import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

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

    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param fileDir
     * @param fileName
     */
    public File writeFile(InputStream inputString, String fileDir, String fileName) {
        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(fileDir, fileName);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("Exception: " + e.getMessage());
        }
        return file;
    }
}
