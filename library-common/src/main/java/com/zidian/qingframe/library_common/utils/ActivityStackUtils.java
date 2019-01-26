package com.zidian.qingframe.library_common.utils;


import android.app.Activity;
import android.os.Build;

import java.util.Iterator;
import java.util.Stack;

/**
 * activity stack管理类
 */
public class ActivityStackUtils {
    private static Stack<Activity> sActivityStack = new Stack<Activity>();

    /**
     * 入栈
     * oncreate之后调用
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        sActivityStack.add(activity);
    }

    /**
     * 出栈
     * onDestroy之后调用
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
        }

    }

    /**
     * 返回栈顶activity
     *
     * @return
     */
    public static Activity getTopActivity() {
        return sActivityStack.isEmpty() ? null : sActivityStack.lastElement();
    }

    /**
     * 结束当前activity
     * 代替context.finish
     */
    public static void finishActivity() {
        Activity activity = sActivityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定activity
     *
     * @param activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
            finishAnimtor(activity);
            activity = null;
        }
    }

    /**
     * 结束动画，5.0以后区分
     *
     * @param activity
     */
    private static void finishAnimtor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.finishAfterTransition();
        } else {
            activity.finish();
        }
    }

    /**
     * 结束所有activity
     * exit时调用
     */
    public static void removeAllActivity() {
        if (sActivityStack != null && sActivityStack.size() > 0) {
            Iterator var0 = sActivityStack.iterator();
            while (var0.hasNext()) {
                Activity activity = (Activity) var0.next();
                if (null != activity) {
                    finishAnimtor(activity);
                }
            }
        }

    }

    /**
     * 结束除目标以外的所有activity
     * 一般用于返回到登录页面
     * @param clazz
     */
    public void removeAllActivityWithoutThis(Class<?> clazz) {
        Stack<Activity> tmp = new Stack<>();

        while (!sActivityStack.empty()) {
            Activity current = sActivityStack.pop();//获取到并移除栈顶的对象。
            if (current != null
                    && !current.getClass().getSimpleName()
                    .equals(clazz.getSimpleName())) {
                finishAnimtor(current);
            } else if (null != current &&
                    (current.getClass().getSimpleName().equals(clazz.getSimpleName()))) {
                tmp.push(current);
            }
        }

        if (null != tmp && tmp.size() > 0) {
            sActivityStack.addAll(tmp);
        }
    }
}
