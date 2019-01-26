package com.zidian.qingframe.library_common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

public class DeviceUtils {
    public static final String CHANNEL_KEY = "channel";

    /**
     * 获取设备的唯一id
     *
     * @param mContext
     * @return
     */
    public static String getDeviceId(Context mContext) {

        return Settings.Secure
                .getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID)
                + Build.SERIAL;
    }

    /**
     * 获取手机型号，比如 mi note
     *
     * @return
     */
    public static String getDeviceModel() {
        return Build.BRAND + " " + Build.MODEL;
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static String getSystemCurrentVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取app版本名称versionName
     *
     * @return
     */
    public static String getAppVersion(Context mContext) {
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取app版本名称versionCode
     *
     * @return
     */
    public static int getAppVersionCode(Context mContext) {
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取application中指定的meta-data。本例中，调用方法时key就是UMENG_CHANNEL
     *
     * @return 如果没有获取成功(没有对应值, 或者异常)，则返回值为空
     */
    private static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return "app";
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (TextUtils.isEmpty(resultData)) {
                resultData = "app";
            }
        }
        return resultData;
    }
}
