package com.zidian.qingframe.library_common.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zidian.library_common.R;


public class MyToolBar extends RelativeLayout {
    /**
     * 自定义属性
     * mode:6种title样式,默认back
     * title：在带有title样式中显示标题
     * style:默认白底黑字，app样式则是橘底白字
     * right_title：右title显示文本
     * search_hint：搜索框提示文本
     */
    public static final int ONLY_BACK = 1;
    public static final int ONLY_TITLE = 2;
    public static final int BACK_AND_TITLE = 3;
    public static final int BACK_AND_TITLE_AND_RIGHT = 4;
    public static final int BACK_AND_SEARCH = 5;
    public static final int BACK_AND_TITLE_AND_MENU = 6;

    public static final int NORMAL_STYLE = 1;
    public static final int APP_STYLE = 2;

    private Context mContext;
    private int mode;
    private String title;
    private int style;
    private String right_title;
    private String search_hint;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_right;
    private ViewStub vs_search;
    private EditText_Clear ets_search;
    private RelativeLayout rl_bg;
    private ImageView iv_menu;
    private PopupWindow popupWindow;


    public MyToolBar(Context context) {
        this(context, null);
    }

    public MyToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyToolBar);
        mode = array.getInt(R.styleable.MyToolBar_mode, 1);
        style = array.getInt(R.styleable.MyToolBar_style, 1);
        title = array.getString(R.styleable.MyToolBar_title);
        right_title = array.getString(R.styleable.MyToolBar_right_title);
        search_hint = array.getString(R.styleable.MyToolBar_search_hint);
        array.recycle();
        initView();
        initListener();
    }

    private void initListener() {
        //默认返回键
        if (iv_back.getVisibility() == VISIBLE) {
            iv_back.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mContext instanceof Activity) {
                        Activity baseActivity = (Activity) mContext;
                        baseActivity.onBackPressed();
                    }
                }
            });
        }

        if (iv_menu.getVisibility() == VISIBLE) {
            iv_menu.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    showWindow();
                }
            });
        }
    }

    private void initView() {
        View.inflate(mContext, R.layout.layout_my_toolbar, this);
        iv_back = findViewById(R.id.iv_mtl_cancel);
        tv_title = findViewById(R.id.tv_mtl_title);
        tv_right = findViewById(R.id.tv_mtl_ok);
        vs_search = findViewById(R.id.vs_search);
        rl_bg = findViewById(R.id.rl_bg);
        iv_menu = findViewById(R.id.iv_mtl_menu);

        initStyle();
        initVisibility();
    }

    private void initStyle() {
        if (style == NORMAL_STYLE) {
            //使用默认
        } else if (style == APP_STYLE) {
            iv_back.setImageResource(R.mipmap.icon_back);
            tv_title.setTextColor(getResources().getColor(R.color.white));
            tv_right.setTextColor(getResources().getColor(R.color.white));
            rl_bg.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void initVisibility() {
        switch (mode) {
            case ONLY_BACK:
                iv_back.setVisibility(VISIBLE);
                tv_title.setVisibility(GONE);
                tv_right.setVisibility(GONE);
                iv_menu.setVisibility(GONE);
                break;
            case ONLY_TITLE:
                iv_back.setVisibility(GONE);
                tv_title.setVisibility(VISIBLE);
                iv_menu.setVisibility(GONE);
                if (!TextUtils.isEmpty(title)) {
                    tv_title.setText(title);
                }
                tv_right.setVisibility(GONE);
                break;
            case BACK_AND_TITLE:
                iv_back.setVisibility(VISIBLE);
                tv_title.setVisibility(VISIBLE);
                iv_menu.setVisibility(GONE);
                if (!TextUtils.isEmpty(title)) {
                    tv_title.setText(title);
                }
                tv_right.setVisibility(GONE);
                break;
            case BACK_AND_TITLE_AND_RIGHT:
                iv_back.setVisibility(VISIBLE);
                tv_title.setVisibility(VISIBLE);
                iv_menu.setVisibility(GONE);
                if (!TextUtils.isEmpty(title)) {
                    tv_title.setText(title);
                }
                tv_right.setVisibility(VISIBLE);
                if (!TextUtils.isEmpty(right_title)) {
                    tv_right.setText(right_title);
                }
                break;
            case BACK_AND_SEARCH:
                iv_back.setVisibility(VISIBLE);
                tv_title.setVisibility(GONE);
                tv_right.setVisibility(GONE);
                iv_menu.setVisibility(GONE);
                ets_search = (EditText_Clear) vs_search.inflate();
                if (!TextUtils.isEmpty(search_hint)) {
                    ets_search.setHint(search_hint);
                }
                break;
            case BACK_AND_TITLE_AND_MENU:
                iv_back.setVisibility(VISIBLE);
                tv_title.setVisibility(VISIBLE);
                if (!TextUtils.isEmpty(title)) {
                    tv_title.setText(title);
                }
                tv_right.setVisibility(GONE);
                iv_menu.setVisibility(VISIBLE);
                break;

        }
    }

    private void showWindow() {
        if (mode == BACK_AND_TITLE_AND_MENU) {
            if (popupWindow != null) {
                if (!popupWindow.isShowing()) {
                    popupWindow.showAsDropDown(iv_menu);
                } else {
                    closeWindow();
                }
            }
        }
    }

    public void closeWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void setMode(int mode) {
        this.mode = mode;
        initVisibility();
    }

    public void setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
    }

    public void setRightTitle(String title) {
        this.right_title = title;
        tv_right.setText(right_title);
    }

    public void setLeftOnClickListener(OnClickListener leftOnClickListener) {
        if (iv_back.getVisibility() == VISIBLE) {
            iv_back.setOnClickListener(leftOnClickListener);
        }
    }

    public void setRightOnClickListener(OnClickListener rightOnClickListener) {
        if (mode == BACK_AND_TITLE_AND_RIGHT) {
            tv_right.setOnClickListener(rightOnClickListener);
        }
    }

    public void setSearchListener(TextView.OnEditorActionListener listener) {
        if (mode == BACK_AND_SEARCH) {
            ets_search.setOnEditorActionListener(listener);
        }
    }

    public void setSearchClearListener(EditText_Clear.SetClearImageListener listener) {
        if (mode == BACK_AND_SEARCH) {
            ets_search.setListener(listener);
        }
    }

    public void setMenuShow(boolean show) {
        if (mode != BACK_AND_TITLE_AND_MENU) {
            return;
        }
        if (show) {
            iv_menu.setVisibility(VISIBLE);
        } else {
            iv_menu.setVisibility(GONE);
        }
    }

    public PopWindowBuilder getPopWindowBuiler(Context context){
        return new PopWindowBuilder(context);
    }

    public class PopWindowBuilder {
        private Context context;
        private View view;
        private int animationRes;

        public PopWindowBuilder(Context context) {
            this.context = context;
        }

        public PopWindowBuilder layout(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public PopWindowBuilder setAnimationStyle(int animationRes) {
            this.animationRes = animationRes;
            return this;
        }

        public PopWindowBuilder setOutSideCancel(int bgRes) {
            View bg = view.findViewById(bgRes);
            bg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    closeWindow();
                }
            });
            return this;
        }

        public PopWindowBuilder addViewOnclick(int viewRes, final OnClickListener listener) {
            View btn = view.findViewById(viewRes);
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);
                    closeWindow();
                }
            });
            return this;
        }

        public PopWindowBuilder setImage(int viewRes, int imgRes) {
            View view1 = view.findViewById(viewRes);
            if (view1 instanceof ImageView) {
                ImageView iv = (ImageView) view1;
                iv.setImageResource(imgRes);
                view1.setVisibility(View.VISIBLE);
            }
            return this;
        }

        public PopWindowBuilder setText(int viewRes, String msg) {
            TextView textView = view.findViewById(viewRes);
            textView.setVisibility(View.VISIBLE);
            textView.setText(msg);
            return this;
        }

        public void build() {
            popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            ColorDrawable dw = new ColorDrawable(0x60000000);
            popupWindow.setBackgroundDrawable(dw);
            popupWindow.setAnimationStyle(animationRes);
            popupWindow.setOutsideTouchable(true);
        }
    }
}
