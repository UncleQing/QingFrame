package com.zidian.qingframe.library_common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 有关手机号工具类
 */
public class PhoneUtils {
    //拨号
    public static void callPhone(Context ctx, String phone) {
        if (isMobile(phone)) {
            Intent mIntent = new Intent(Intent.ACTION_DIAL);
            mIntent.setData(Uri.parse("tel:" + phone));
            ctx.startActivity(mIntent);
        }
    }

    //发送短信
    public static void sendMessage(Context ctx, String phone) {
        if (isMobile(phone)) {
            Uri uri = Uri.parse("smsto:" + phone);
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
            ctx.startActivity(smsIntent);
        }
    }

    //手机号判断
    public static boolean isMobile(String phone) {
        String num = "[1]\\d{10}";
        return !TextUtils.isEmpty(phone) && phone.matches(num);
    }

    //手机号脱敏处理 139****5178
    public static String dealPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(phone);
        return sb.replace(3, 7, "****").toString();
    }

    //获取手机后四位尾号
    public static String getPhoneTailNumber(String phone) {
        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            return "";
        }
        StringBuilder sb = new StringBuilder(phone);
        return sb.substring(7, 11);
    }


}
