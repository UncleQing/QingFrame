package com.zidian.qingframe.library_common.utils;

import android.app.Application;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AppUtils {

    /**
     * 反射获取app对象
     */
    public static Application getApp() {
        Application application = null;

        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field appField = activityThreadClass.getDeclaredField("mInitialApplication");
            Method method = activityThreadClass.getMethod("currentActivityThread");
            Object localObject = method.invoke(null, (Object[]) null);
            appField.setAccessible(true);
            application = (Application) appField.get(localObject);
        } catch (ClassNotFoundException var5) {
            var5.printStackTrace();
        } catch (NoSuchFieldException var6) {
            var6.printStackTrace();
        } catch (IllegalAccessException var7) {
            var7.printStackTrace();
        } catch (IllegalArgumentException var8) {
            var8.printStackTrace();
        } catch (NoSuchMethodException var9) {
            var9.printStackTrace();
        } catch (InvocationTargetException var10) {
            var10.printStackTrace();
        }

        return application;
    }
}
