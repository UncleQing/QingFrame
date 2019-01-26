package com.zidian.qingframe.library_common.widget.picker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zidian.library_common.R;
import com.zidian.qingframe.library_common.utils.UIUtils;


public class TakePhotoPopWindow extends PopupWindow implements OnClickListener {
    private View mView;
    public Button btnSaveProject, btnAbandonProject, btnCancelProject;
    Context mcontext;

    private PickPhotoListener mListener;

    public TakePhotoPopWindow(Activity context) {

        super(context);
        mcontext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.populwindow_takephoto, null);

        btnSaveProject = mView.findViewById(R.id.popupwindow_Button_saveProject);
        btnAbandonProject = mView.findViewById(R.id.popupwindow_Button_abandonProject);
        btnCancelProject = mView.findViewById(R.id.popupwindow_cancelButton);


        // 设置按钮监听
        btnCancelProject.setOnClickListener(this);
        btnSaveProject.setOnClickListener(this);
        btnAbandonProject.setOnClickListener(this);


        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationBottomFade);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x60000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //全屏遮罩
        this.setClippingEnabled(false);
        //底部状态栏计算
        if (UIUtils.isNavigationBarShow(context)) {
            int heigth = UIUtils.getNavigationBarHeight(context);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btnCancelProject.getLayoutParams();
            lp.bottomMargin = heigth;
            btnCancelProject.setLayoutParams(lp);
        }

    }

    public void setTitle(String... t1) {
        if (t1.length > 0) {
            btnSaveProject.setText(t1[0]);
        }

        if (t1.length > 1) {
            btnAbandonProject.setText(t1[1]);
        } else {
            btnAbandonProject.setVisibility(View.GONE);
        }
    }

    public void setCancel(String cancel) {
        btnCancelProject.setText(cancel);
    }

    public View getmView() {
        return mView;
    }

    @Override
    public void onClick(View v) {
        if (null != mListener) {
            int id = v.getId();
            if (id == R.id.popupwindow_Button_saveProject) {
                mListener.takePhoto();
            } else if (id == R.id.popupwindow_Button_abandonProject) {
                mListener.pickPhoto();
            } else if (id == R.id.popupwindow_cancelButton) {

            }
        }
        dismiss();
    }

    /**
     * 设置拍照回调监听
     *
     * @param listener
     */
    public void setPickPhotoListener(PickPhotoListener listener) {
        mListener = listener;
    }

    public interface PickPhotoListener {
        void takePhoto();

        void pickPhoto();
    }


    public static class Builder {

        private TakePhotoPopWindow photoPopWindow;

        public Builder(Activity mActivity) {
            photoPopWindow = new TakePhotoPopWindow(mActivity);
        }

        public Builder setTitle(String... t1) {
            photoPopWindow.setTitle(t1);
            return this;
        }


        public Builder setCancel(String t1) {
            photoPopWindow.setCancel(t1);
            return this;
        }

        public Builder setListener(PickPhotoListener listener) {
            photoPopWindow.setPickPhotoListener(listener);
            return this;
        }

        public void show(int resId) {
            View view = LayoutInflater.from(photoPopWindow.mcontext).inflate(resId, null, false);
            photoPopWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        }
    }

}
