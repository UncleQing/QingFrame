package com.zidian.qingframe.library_common.utils;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zidian.library_common.R;

/**
 * 自定义toast
 */
public class ToastUtils {
    /** 之前显示的内容 */
    private static String oldMsg ;
    /** Toast对象 */
    private static Toast toast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;

    public static void showToast(Context context, String message){
        View mView = LayoutInflater.from(context).inflate(R.layout.my_toast, null);
        TextView textView = mView.findViewById(R.id.tv_myToast);

        if(toast == null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            textView.setText(message);
            toast.setView(mView);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    textView.setText(message);
                    toast.setView(mView);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show() ;
                }
            }else{
                toast.setView(mView);
                toast.setGravity(Gravity.CENTER, 0, 0);
                oldMsg = message ;
                textView.setText(message);
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }
}
