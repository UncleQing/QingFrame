package com.zidian.qingframe.library_common.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zidian.library_common.R;


public class EditText_Clear extends android.support.v7.widget.AppCompatEditText {


    private Drawable clearDrawable, searchDrawable;

    public EditText_Clear(Context context) {
        super(context);
        init();
    }

    public EditText_Clear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditText_Clear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        clearDrawable = getResources().getDrawable(R.mipmap.icon_edit_clear);
        searchDrawable = getResources().getDrawable(R.mipmap.icon_search);

        setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null, null, null);
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(hasFocus() && text.length() > 0);
        //手动删除最后一个字符时触发
        if (TextUtils.isEmpty(text) && lengthBefore == 1) {
            if (listener != null) {
                listener.clear();
            }
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIconVisible(focused && length() > 0);
    }

    private void setClearIconVisible(boolean visible) {
        setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null,
                visible ? clearDrawable : null, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // 当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
            case MotionEvent.ACTION_UP:
                Drawable drawable = clearDrawable;
                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {
                    setText("");
                    if (null != listener) {
                        listener.clear();
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private SetClearImageListener listener;

    public SetClearImageListener getListener() {
        return listener;
    }

    public void setListener(SetClearImageListener listener) {
        this.listener = listener;
    }

    public interface SetClearImageListener {
        void clear();
    }


}

