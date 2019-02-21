package com.zidian.qingframe;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mob.MobSDK;
import com.zidian.qingframe.library_common.utils.LogUtils;

import me.jessyan.autosize.AutoSizeConfig;


public class MyApp extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //LogUtils
        if (BuildConfig.OPEN_LOG) {
            LogUtils.openLog();
        }
        //AndroidAutoSize初始化 设置以宽为基准
        AutoSizeConfig.getInstance().setBaseOnWidth(true);

        //ARouter初始化
        if (com.alibaba.android.arouter.BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);

        //ShareSDK初始化
        MobSDK.init(this);
    }



}
