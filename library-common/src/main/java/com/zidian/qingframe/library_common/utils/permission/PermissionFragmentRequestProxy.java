package com.zidian.qingframe.library_common.utils.permission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;


/**
 * 代理执行权限的申请和回调权限申请结果
 */
public class PermissionFragmentRequestProxy extends Fragment implements IPermissionRequestProxy {

    private IRequestResult requestResult;
    private int requestCode = -1;

    public PermissionFragmentRequestProxy() {
    }

    @Override
    public boolean checkSelfPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_DENIED) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void permissionsCheck(String[] permissions, int requestCode, int error_msg) {
        this.requestCode = requestCode;
        //进行权限请求
        requestPermissions(permissions, requestCode);
    }


    @Override
    public void setResultListener(IRequestResult listener) {
        this.requestResult = listener;
    }


    // 启动应用的设置
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PermissionWrapper.PACKAGE + getContext().getPackageName()));
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (null != getActivity() && !getActivity().isFinishing()) {
            if (this.requestCode != -1 && this.requestCode == requestCode && null != requestResult) {
                if (grantResults[0] != PackageManager.PERMISSION_DENIED) {
                    requestResult.onRequestResultSuccess();
                } else {
                    requestResult.onRequestResultFailed();
                }
            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
}
