package com.zidian.qingframe.library_common.utils.glide;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;

public class GlideOptions extends RequestOptions implements Cloneable {
    private static GlideOptions fitCenterTransform0;
    private static GlideOptions centerInsideTransform1;
    private static GlideOptions centerCropTransform2;
    private static GlideOptions circleCropTransform3;
    private static GlideOptions noTransformation4;
    private static GlideOptions noAnimation5;

    public GlideOptions() {
    }

    @CheckResult
    public static GlideOptions sizeMultiplierOf(@FloatRange(from = 0.0D, to = 1.0D) float arg0) {
        return (new GlideOptions()).sizeMultiplier(arg0);
    }

    @CheckResult
    public static GlideOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy arg0) {
        return (new GlideOptions()).diskCacheStrategy(arg0);
    }

    @CheckResult
    public static GlideOptions priorityOf(@NonNull Priority arg0) {
        return (new GlideOptions()).priority(arg0);
    }

    @CheckResult
    public static GlideOptions placeholderOf(@Nullable Drawable arg0) {
        return (new GlideOptions()).placeholder(arg0);
    }

    @CheckResult
    public static GlideOptions placeholderOf(@DrawableRes int arg0) {
        return (new GlideOptions()).placeholder(arg0);
    }

    @CheckResult
    public static GlideOptions errorOf(@Nullable Drawable arg0) {
        return (new GlideOptions()).error(arg0);
    }

    @CheckResult
    public static GlideOptions errorOf(@DrawableRes int arg0) {
        return (new GlideOptions()).error(arg0);
    }

    @CheckResult
    public static GlideOptions skipMemoryCacheOf(boolean skipMemoryCache) {
        return (new GlideOptions()).skipMemoryCache(skipMemoryCache);
    }

    @CheckResult
    public static GlideOptions overrideOf(@IntRange(from = 0L) int arg0, @IntRange(from = 0L) int arg1) {
        return (new GlideOptions()).override(arg0, arg1);
    }

    @CheckResult
    public static GlideOptions overrideOf(@IntRange(from = 0L) int arg0) {
        return (new GlideOptions()).override(arg0);
    }

    @CheckResult
    public static GlideOptions signatureOf(@NonNull Key arg0) {
        return (new GlideOptions()).signature(arg0);
    }

    @CheckResult
    public static GlideOptions fitCenterTransform() {
        if (fitCenterTransform0 == null) {
            fitCenterTransform0 = (new GlideOptions()).fitCenter().autoClone();
        }

        return fitCenterTransform0;
    }

    @CheckResult
    public static GlideOptions centerInsideTransform() {
        if (centerInsideTransform1 == null) {
            centerInsideTransform1 = (new GlideOptions()).centerInside().autoClone();
        }

        return centerInsideTransform1;
    }

    @CheckResult
    public static GlideOptions centerCropTransform() {
        if (centerCropTransform2 == null) {
            centerCropTransform2 = (new GlideOptions()).centerCrop().autoClone();
        }

        return centerCropTransform2;
    }

    @CheckResult
    public static GlideOptions circleCropTransform() {
        if (circleCropTransform3 == null) {
            circleCropTransform3 = (new GlideOptions()).circleCrop().autoClone();
        }

        return circleCropTransform3;
    }

    @CheckResult
    public static GlideOptions bitmapTransform(@NonNull Transformation<Bitmap> arg0) {
        return (new GlideOptions()).transform(arg0);
    }

    @CheckResult
    public static GlideOptions noTransformation() {
        if (noTransformation4 == null) {
            noTransformation4 = (new GlideOptions()).dontTransform().autoClone();
        }

        return noTransformation4;
    }

    @CheckResult
    public static <T> GlideOptions option(@NonNull Option<T> arg0, @NonNull T arg1) {
        return (new GlideOptions()).set(arg0, arg1);
    }

    @CheckResult
    public static GlideOptions decodeTypeOf(@NonNull Class<?> arg0) {
        return (new GlideOptions()).decode(arg0);
    }

    @CheckResult
    public static GlideOptions formatOf(@NonNull DecodeFormat arg0) {
        return (new GlideOptions()).format(arg0);
    }

    @CheckResult
    public static GlideOptions frameOf(@IntRange(from = 0L) long arg0) {
        return (new GlideOptions()).frame(arg0);
    }

    @CheckResult
    public static GlideOptions downsampleOf(@NonNull DownsampleStrategy arg0) {
        return (new GlideOptions()).downsample(arg0);
    }

    @CheckResult
    public static GlideOptions timeoutOf(@IntRange(from = 0L) int arg0) {
        return (new GlideOptions()).timeout(arg0);
    }

    @CheckResult
    public static GlideOptions encodeQualityOf(@IntRange(from = 0L, to = 100L) int arg0) {
        return (new GlideOptions()).encodeQuality(arg0);
    }

    @CheckResult
    public static GlideOptions encodeFormatOf(@NonNull Bitmap.CompressFormat arg0) {
        return (new GlideOptions()).encodeFormat(arg0);
    }

    @CheckResult
    public static GlideOptions noAnimation() {
        if (noAnimation5 == null) {
            noAnimation5 = (new GlideOptions()).dontAnimate().autoClone();
        }

        return noAnimation5;
    }

    @CheckResult
    public final GlideOptions sizeMultiplier(@FloatRange(from = 0.0D, to = 1.0D) float arg0) {
        return (GlideOptions) super.sizeMultiplier(arg0);
    }

    @CheckResult
    public final GlideOptions useUnlimitedSourceGeneratorsPool(boolean flag) {
        return (GlideOptions) super.useUnlimitedSourceGeneratorsPool(flag);
    }

    @CheckResult
    public final GlideOptions useAnimationPool(boolean flag) {
        return (GlideOptions) super.useAnimationPool(flag);
    }

    @CheckResult
    public final GlideOptions onlyRetrieveFromCache(boolean flag) {
        return (GlideOptions) super.onlyRetrieveFromCache(flag);
    }

    @CheckResult
    public final GlideOptions diskCacheStrategy(@NonNull DiskCacheStrategy arg0) {
        return (GlideOptions) super.diskCacheStrategy(arg0);
    }

    @CheckResult
    public final GlideOptions priority(@NonNull Priority arg0) {
        return (GlideOptions) super.priority(arg0);
    }

    @CheckResult
    public final GlideOptions placeholder(@Nullable Drawable arg0) {
        return (GlideOptions) super.placeholder(arg0);
    }

    @CheckResult
    public final GlideOptions placeholder(@DrawableRes int arg0) {
        return (GlideOptions) super.placeholder(arg0);
    }

    @CheckResult
    public final GlideOptions fallback(@Nullable Drawable arg0) {
        return (GlideOptions) super.fallback(arg0);
    }

    @CheckResult
    public final GlideOptions fallback(@DrawableRes int arg0) {
        return (GlideOptions) super.fallback(arg0);
    }

    @CheckResult
    public final GlideOptions error(@Nullable Drawable arg0) {
        return (GlideOptions) super.error(arg0);
    }

    @CheckResult
    public final GlideOptions error(@DrawableRes int arg0) {
        return (GlideOptions) super.error(arg0);
    }

    @CheckResult
    public final GlideOptions theme(@Nullable Resources.Theme arg0) {
        return (GlideOptions) super.theme(arg0);
    }

    @CheckResult
    public final GlideOptions skipMemoryCache(boolean skip) {
        return (GlideOptions) super.skipMemoryCache(skip);
    }

    @CheckResult
    public final GlideOptions override(int width, int height) {
        return (GlideOptions) super.override(width, height);
    }

    @CheckResult
    public final GlideOptions override(int size) {
        return (GlideOptions) super.override(size);
    }

    @CheckResult
    public final GlideOptions signature(@NonNull Key arg0) {
        return (GlideOptions) super.signature(arg0);
    }

    @CheckResult
    public final GlideOptions clone() {
        return (GlideOptions) super.clone();
    }

    @CheckResult
    public final <T> GlideOptions set(@NonNull Option<T> arg0, @NonNull T arg1) {
        return (GlideOptions) super.set(arg0, arg1);
    }

    @CheckResult
    public final GlideOptions decode(@NonNull Class<?> arg0) {
        return (GlideOptions) super.decode(arg0);
    }

    @CheckResult
    public final GlideOptions encodeFormat(@NonNull Bitmap.CompressFormat arg0) {
        return (GlideOptions) super.encodeFormat(arg0);
    }

    @CheckResult
    public final GlideOptions encodeQuality(@IntRange(from = 0L, to = 100L) int arg0) {
        return (GlideOptions) super.encodeQuality(arg0);
    }

    @CheckResult
    public final GlideOptions frame(@IntRange(from = 0L) long arg0) {
        return (GlideOptions) super.frame(arg0);
    }

    @CheckResult
    public final GlideOptions format(@NonNull DecodeFormat arg0) {
        return (GlideOptions) super.format(arg0);
    }

    @CheckResult
    public final GlideOptions disallowHardwareConfig() {
        return (GlideOptions) super.disallowHardwareConfig();
    }

    @CheckResult
    public final GlideOptions downsample(@NonNull DownsampleStrategy arg0) {
        return (GlideOptions) super.downsample(arg0);
    }

    @CheckResult
    public final GlideOptions timeout(@IntRange(from = 0L) int arg0) {
        return (GlideOptions) super.timeout(arg0);
    }

    @CheckResult
    public final GlideOptions optionalCenterCrop() {
        return (GlideOptions) super.optionalCenterCrop();
    }

    @CheckResult
    public final GlideOptions centerCrop() {
        return (GlideOptions) super.centerCrop();
    }

    @CheckResult
    public final GlideOptions optionalFitCenter() {
        return (GlideOptions) super.optionalFitCenter();
    }

    @CheckResult
    public final GlideOptions fitCenter() {
        return (GlideOptions) super.fitCenter();
    }

    @CheckResult
    public final GlideOptions optionalCenterInside() {
        return (GlideOptions) super.optionalCenterInside();
    }

    @CheckResult
    public final GlideOptions centerInside() {
        return (GlideOptions) super.centerInside();
    }

    @CheckResult
    public final GlideOptions optionalCircleCrop() {
        return (GlideOptions) super.optionalCircleCrop();
    }

    @CheckResult
    public final GlideOptions circleCrop() {
        return (GlideOptions) super.circleCrop();
    }

    @CheckResult
    public final GlideOptions transform(@NonNull Transformation<Bitmap> arg0) {
        return (GlideOptions) super.transform(arg0);
    }

    @SafeVarargs
    @CheckResult
    public final GlideOptions transforms(@NonNull Transformation... arg0) {
        return (GlideOptions) super.transforms(arg0);
    }

    @CheckResult
    public final GlideOptions optionalTransform(@NonNull Transformation<Bitmap> arg0) {
        return (GlideOptions) super.optionalTransform(arg0);
    }

    @CheckResult
    public final <T> GlideOptions optionalTransform(@NonNull Class<T> arg0, @NonNull Transformation<T> arg1) {
        return (GlideOptions) super.optionalTransform(arg0, arg1);
    }

    @CheckResult
    public final <T> GlideOptions transform(@NonNull Class<T> arg0, @NonNull Transformation<T> arg1) {
        return (GlideOptions) super.transform(arg0, arg1);
    }

    @CheckResult
    public final GlideOptions dontTransform() {
        return (GlideOptions) super.dontTransform();
    }

    @CheckResult
    public final GlideOptions dontAnimate() {
        return (GlideOptions) super.dontAnimate();
    }

    @CheckResult
    public final GlideOptions apply(@NonNull RequestOptions arg0) {
        return (GlideOptions) super.apply(arg0);
    }

    public final GlideOptions lock() {
        return (GlideOptions) super.lock();
    }

    public final GlideOptions autoClone() {
        return (GlideOptions) super.autoClone();
    }
}
