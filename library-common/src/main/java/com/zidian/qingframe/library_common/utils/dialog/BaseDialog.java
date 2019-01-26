package com.zidian.qingframe.library_common.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zidian.qingframe.library_common.utils.glide.GlideApp;

public class BaseDialog extends Dialog {
    private int height, width;
    private View mView;

    public BaseDialog(Builder builder) {
        this(builder, 0);
    }

    public BaseDialog(Builder builder, int resStyle) {
        super(builder.context, builder.resStyle);
        init(builder);
    }


    private void init(Builder builder) {
        this.height = builder.height;
        this.width = builder.width;
        this.mView = builder.view;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);

        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.height = height;
        layoutParams.width = width;
        window.setAttributes(layoutParams);

    }

    public final static class Builder {
        private Context context;
        private int height, width;
        private View view;
        private int resStyle = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder layout(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder height(int val) {
            height = val;
            return this;
        }

        public Builder width(int val) {
            width = val;
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            View btn = view.findViewById(viewRes);
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(listener);
            return this;
        }

        public Builder setText(int viewRes, int msgRes) {
            View view1 = view.findViewById(viewRes);
            if (view1 instanceof Button) {
                Button button = (Button) view1;
                button.setText(msgRes);
            } else if (view1 instanceof TextView) {
                TextView textView = (TextView) view1;
                textView.setText(msgRes);
            } else {
                return this;
            }
            view1.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setImage(int viewRes, String imgUrl) {
            View view1 = view.findViewById(viewRes);
            if (view1 instanceof ImageView) {
                ImageView iv = (ImageView) view1;
                GlideApp.with(context).load(imgUrl)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(iv);
            } else {
                return this;
            }
            view1.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setImageBitmap(int viewRes, Bitmap bitmap) {
            View view1 = view.findViewById(viewRes);
            if (view1 instanceof ImageView) {
                ImageView iv = (ImageView) view1;
                iv.setImageBitmap(bitmap);
            } else {
                return this;
            }
            view1.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setText(int viewRes, String msg) {
            TextView textView = view.findViewById(viewRes);
            textView.setVisibility(View.VISIBLE);
            textView.setText(msg);
            return this;
        }

        public View getView() {
            return view;
        }

        public BaseDialog build() {
            if (resStyle == -1) {
                return new BaseDialog(this);
            } else {
                return new BaseDialog(this, resStyle);
            }
        }
    }
}
