package com.zidian.qingframe.library_common.utils;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 其他工具类
 */
public class CommonUtil {
    private static long lastClickTime;

    //连击判定
    public synchronized static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();
        boolean isFastClick;
        isFastClick = currentTime - lastClickTime <= 200;
        lastClickTime = currentTime;
        return isFastClick;
    }


    //是否安装QQ
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if ("com.tencent.mobileqq".equals(pn)) {
                    return true;
                }
            }
        }
        return false;
    }

    //是否安装微信
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if ("com.tencent.mm".equals(pn)) {
                    return true;
                }
            }
        }
        return false;
    }

    //自动安装apk
    public static void installApk(Context context, File apkPath) {
        //提升文件读写权限
        String command = "chmod " + "777" + " " + apkPath.getPath();
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //安装跳转
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //这块不要写错了，一定要是你自己Manifest注册的fileprovider
            Uri apkUri = FileProvider.getUriForFile(context, "com.zidian.qingframe.fileprovider", apkPath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkPath),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

}
