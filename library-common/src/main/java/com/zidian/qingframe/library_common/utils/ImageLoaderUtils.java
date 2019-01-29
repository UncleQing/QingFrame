package com.zidian.qingframe.library_common.utils;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zidian.qingframe.library_common.utils.glide.CircleImageTransform;
import com.zidian.qingframe.library_common.utils.glide.GlideApp;

public class ImageLoaderUtils {
    private ImageLoaderUtils(){}

    /**
     * 普通加载一张图片到ImageView
     * @param imageView
     * @param imageUrl
     */
    public static void loadImage(ImageView imageView, String imageUrl) {
        if(imageView != null) {
            Context context = imageView.getContext();
            if(!(context instanceof Activity) || !((Activity)context).isFinishing()) {
                loadImage(context, imageView, imageUrl);
            }
        }
    }

    public static void loadImage(Context context, ImageView imageView, String imageUrl) {
        try {
            GlideApp.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    /**
     * 加载带占位图的图片到ImageView
     * @param imageView
     * @param imageUrl
     * @param placeholderRes
     * @param errorRes
     */
    public static void loadImageWithPlaceHolder(ImageView imageView, String imageUrl, @DrawableRes int placeholderRes, @DrawableRes int errorRes) {
        if(imageView != null) {
            Context context = imageView.getContext();
            if(!(context instanceof Activity) || !((Activity)context).isFinishing()) {
                loadImageWithPlaceHolder(context, imageView, imageUrl, placeholderRes, errorRes);
            }
        }
    }

    public static void loadImageWithPlaceHolder(Context context, ImageView imageView, String imageUrl, @DrawableRes int placeholderRes, @DrawableRes int errorRes) {
        try {
            GlideApp.with(context).load(imageUrl).placeholder(placeholderRes).error(errorRes == 0?placeholderRes:errorRes).dontAnimate().diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }


    /**
     * 加载指定宽高的图片
     * @param context
     * @param imageView
     * @param imageUrl
     * @param width
     * @param height
     */
    public static void loadImageWithSize(Context context, ImageView imageView, String imageUrl, int width, int height) {
        try {
            GlideApp.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(width, height).into(imageView);
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }

    /**
     * 加载圆形图片
     * @param imageView
     * @param imageUrl
     */
    public static void loadCircleImage(ImageView imageView, String imageUrl) {
        if (imageView != null) {
            Context context = imageView.getContext();
            if (!(context instanceof Activity) || !((Activity)context).isFinishing()) {
                loadCircleImage(context, imageView, imageUrl);
            }
        }
    }

    public static void loadCircleImage(Context context, ImageView imageView, String imageUrl) {
        try {
            GlideApp.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate().transform(new CircleImageTransform()).into(imageView);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }




}
