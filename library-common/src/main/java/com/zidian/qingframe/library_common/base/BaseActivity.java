package com.zidian.qingframe.library_common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zidian.qingframe.library_common.utils.ActivityStackUtils;
import com.zidian.qingframe.library_common.utils.ToastUtils;
import com.zidian.qingframe.library_common.utils.dialog.BaseDialogFragment;
import com.zidian.qingframe.library_common.utils.dialog.DialogFragmentHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.zidian.qingframe.library_common.utils.dialog.DialogFragmentHelper.PROGRESS_TAG;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private BaseDialogFragment loadingDialog;   //加载进度条弹框
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        afterOnCreate();
    }

    protected abstract int getLayoutId();

    protected abstract void afterOnCreate();

    @Override
    protected void onResume() {
        super.onResume();
        ActivityStackUtils.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        ActivityStackUtils.removeActivity(this);
    }

    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = DialogFragmentHelper.showProgress(getSupportFragmentManager(), null, false);
        } else {
            loadingDialog.show(getSupportFragmentManager(), PROGRESS_TAG);
        }
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isVisible()) {
            loadingDialog.dismissAllowingStateLoss();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(this, msg);
    }
}
