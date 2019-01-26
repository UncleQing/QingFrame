package com.zidian.qingframe.library_common.utils.glide;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.net.URL;

public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> implements Cloneable {
    GlideRequest(Class<TranscodeType> transcodeClass, RequestBuilder<?> other) {
        super(transcodeClass, other);
    }

    GlideRequest(Glide glide, RequestManager requestManager, Class<TranscodeType> transcodeClass, Context context) {
        super(glide, requestManager, transcodeClass, context);
    }

    @CheckResult
    protected GlideRequest<File> getDownloadOnlyRequest() {
        return (new GlideRequest(File.class, this)).apply(DOWNLOAD_ONLY_OPTIONS);
    }

    @CheckResult
    public GlideRequest<TranscodeType> sizeMultiplier(@FloatRange(from = 0.0D,to = 1.0D) float arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).sizeMultiplier(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).sizeMultiplier(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean flag) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).useUnlimitedSourceGeneratorsPool(flag);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).useUnlimitedSourceGeneratorsPool(flag);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> useAnimationPool(boolean flag) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).useAnimationPool(flag);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).useAnimationPool(flag);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean flag) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).onlyRetrieveFromCache(flag);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).onlyRetrieveFromCache(flag);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> diskCacheStrategy(@NonNull DiskCacheStrategy arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).diskCacheStrategy(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).diskCacheStrategy(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> priority(@NonNull Priority arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).priority(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).priority(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@Nullable Drawable arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).placeholder(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).placeholder(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@DrawableRes int arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).placeholder(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).placeholder(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> fallback(@Nullable Drawable arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).fallback(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).fallback(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> fallback(@DrawableRes int arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).fallback(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).fallback(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> error(@Nullable Drawable arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).error(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).error(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> error(@DrawableRes int arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).error(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).error(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> theme(@Nullable Resources.Theme arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).theme(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).theme(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> skipMemoryCache(boolean skip) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).skipMemoryCache(skip);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).skipMemoryCache(skip);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> override(int width, int height) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).override(width, height);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).override(width, height);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> override(int size) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).override(size);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).override(size);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> signature(@NonNull Key arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).signature(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).signature(arg0);
        }

        return this;
    }

    @CheckResult
    public <T> GlideRequest<TranscodeType> set(@NonNull Option<T> arg0, @NonNull T arg1) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).set(arg0, arg1);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).set(arg0, arg1);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> decode(@NonNull Class<?> arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).decode(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).decode(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> encodeFormat(@NonNull Bitmap.CompressFormat arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).encodeFormat(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).encodeFormat(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> encodeQuality(@IntRange(from = 0L,to = 100L) int arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).encodeQuality(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).encodeQuality(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> frame(@IntRange(from = 0L) long arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).frame(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).frame(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> format(@NonNull DecodeFormat arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).format(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).format(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).disallowHardwareConfig();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).disallowHardwareConfig();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> downsample(@NonNull DownsampleStrategy arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).downsample(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).downsample(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> timeout(@IntRange(from = 0L) int arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).timeout(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).timeout(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterCrop() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalCenterCrop();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalCenterCrop();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> centerCrop() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).centerCrop();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).centerCrop();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalFitCenter() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalFitCenter();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalFitCenter();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> fitCenter() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).fitCenter();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).fitCenter();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterInside() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalCenterInside();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalCenterInside();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> centerInside() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).centerInside();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).centerInside();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalCircleCrop() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalCircleCrop();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalCircleCrop();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> circleCrop() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).circleCrop();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).circleCrop();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull Transformation<Bitmap> arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).transform(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).transform(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> transforms(@NonNull Transformation... arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).transforms(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).transforms(arg0);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalTransform(@NonNull Transformation<Bitmap> arg0) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalTransform(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalTransform(arg0);
        }

        return this;
    }

    @CheckResult
    public <T> GlideRequest<TranscodeType> optionalTransform(@NonNull Class<T> arg0, @NonNull Transformation<T> arg1) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalTransform(arg0, arg1);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalTransform(arg0, arg1);
        }

        return this;
    }

    @CheckResult
    public <T> GlideRequest<TranscodeType> transform(@NonNull Class<T> arg0, @NonNull Transformation<T> arg1) {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).transform(arg0, arg1);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).transform(arg0, arg1);
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> dontTransform() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).dontTransform();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).dontTransform();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> dontAnimate() {
        if(this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).dontAnimate();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).dontAnimate();
        }

        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> apply(@NonNull RequestOptions arg0) {
        return (GlideRequest)super.apply(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> transition(@NonNull TransitionOptions<?, ? super TranscodeType> arg0) {
        return (GlideRequest)super.transition(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> listener(@Nullable RequestListener<TranscodeType> arg0) {
        return (GlideRequest)super.listener(arg0);
    }

    public GlideRequest<TranscodeType> error(@Nullable RequestBuilder<TranscodeType> arg0) {
        return (GlideRequest)super.error(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType> arg0) {
        return (GlideRequest)super.thumbnail(arg0);
    }

    @SafeVarargs
    @CheckResult
    public final GlideRequest<TranscodeType> thumbnail(@Nullable RequestBuilder... arg0) {
        return (GlideRequest)super.thumbnail(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(float sizeMultiplier) {
        return (GlideRequest)super.thumbnail(sizeMultiplier);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Object arg0) {
        return (GlideRequest)super.load(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Bitmap arg0) {
        return (GlideRequest)super.load(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Drawable arg0) {
        return (GlideRequest)super.load(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable String arg0) {
        return (GlideRequest)super.load(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Uri arg0) {
        return (GlideRequest)super.load(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable File arg0) {
        return (GlideRequest)super.load(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@RawRes @DrawableRes @Nullable Integer arg0) {
        return (GlideRequest)super.load(arg0);
    }

    /** @deprecated */
    @Deprecated
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable URL arg0) {
        return (GlideRequest)super.load(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable byte[] arg0) {
        return (GlideRequest)super.load(arg0);
    }

    @CheckResult
    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest)super.clone();
    }
}
