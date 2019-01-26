package com.zidian.qingframe.library_common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 网络状态以及类型管理类
 */
public class NetworkUtils {
    private NetworkUtils() {
        throw new IllegalStateException(" cannot to new the Object ");
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isAvailable();
    }

    public static String getNetworkType(Context context) {
        String strNetworkType = "";
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == 1) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == 0) {
                String _strSubTypeName = networkInfo.getSubtypeName();
                Log.e("cocos2d-x", "Network getSubtypeName : " + _strSubTypeName);
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        strNetworkType = "2G";
                        break;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        strNetworkType = "3G";
                        break;
                    case 13:
                        strNetworkType = "4G";
                        break;
                    default:
                        if (!_strSubTypeName.equalsIgnoreCase("TD-SCDMA") && !_strSubTypeName.equalsIgnoreCase("WCDMA") && !_strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = _strSubTypeName;
                        } else {
                            strNetworkType = "3G";
                        }
                }
            }
        }

        Log.e("cocos2d-x", "Network Type : " + strNetworkType);
        return strNetworkType;
    }
}
