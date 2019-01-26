package com.zidian.qingframe.library_common.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zidian.qingframe.library_common.utils.ToastUtils;
import com.zidian.qingframe.library_common.utils.dialog.BaseDialogFragment;
import com.zidian.qingframe.library_common.utils.dialog.DialogFragmentHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.zidian.qingframe.library_common.utils.dialog.DialogFragmentHelper.PROGRESS_TAG;

public abstract class BaseFragment extends Fragment implements IBaseView {
    private BaseDialogFragment loadingDialog;   //加载进度条弹框
    private Unbinder mUnbinder;

    protected abstract int getLayoutId();

    protected abstract void afterOnCreateView();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        afterOnCreateView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = DialogFragmentHelper.showProgress(getFragmentManager(), null, false);
        } else {
            loadingDialog.show(getFragmentManager(), PROGRESS_TAG);
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
        ToastUtils.showToast(getContext(), msg);
    }
}
