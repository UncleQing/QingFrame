package com.zidian.qingframe.library_common.utils.permission;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.zidian.library_common.R;


/**
 * android 6.0 以后的权限申请，使用一个fragment代理的方式请求权限
 */
public class PermissionWrapper {

    public static final String PACKAGE = "package:";
    private static final String FRAGMENT_PERMISSION = "fragment_permission_request";

    private IPermissionRequestProxy iPermissionRequestProxy;
    private IRequestResult iRequestResult;
    private String[] permissions;
    private int requestCode;
    private int error_msg = R.string.permission_tip;

    private PermissionWrapper(Activity mActivity) {
        iPermissionRequestProxy = getPermissionsFragment((AppCompatActivity) mActivity);
    }

    private PermissionFragmentRequestProxy getPermissionsFragment(AppCompatActivity activity) {
        PermissionFragmentRequestProxy permissionFragmentRequestProxy = findPermissionsFragment(activity);

        if (null == permissionFragmentRequestProxy) {
            permissionFragmentRequestProxy = new PermissionFragmentRequestProxy();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(permissionFragmentRequestProxy, FRAGMENT_PERMISSION)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return permissionFragmentRequestProxy;
    }

    private PermissionFragmentRequestProxy findPermissionsFragment(AppCompatActivity activity) {
        return (PermissionFragmentRequestProxy) activity.getSupportFragmentManager().findFragmentByTag(FRAGMENT_PERMISSION);
    }


    /**
     * 检查权限是否已申请
     *
     * @return true 有权限，false 无权限
     */
    private boolean checkSelfPermission() {
        return iPermissionRequestProxy.checkSelfPermission(permissions);
    }

    /**
     * 获取权限dialog
     *
     * @return
     */
    private void permissionsCheck() {
        // 注意这里要使用shouldShowRequestPermissionRationale而不要使用requestPermission方法
        // 因为requestPermissions方法会显示不在询问按钮
        iPermissionRequestProxy.permissionsCheck(permissions, requestCode, error_msg);
    }


    /**
     * 请求权限
     */
    public void requestPermission() {
        if (checkSelfPermission()) {
            iRequestResult.onRequestResultSuccess();
        } else {
            iPermissionRequestProxy.setResultListener(iRequestResult);
            permissionsCheck();
        }
    }


    public void clear() {
        permissions = null;
        requestCode = -1;
    }


    public static class Builder {

        PermissionWrapper permissionWrapper;

        public Builder with(Activity mActivity) {
            permissionWrapper = new PermissionWrapper(mActivity);
            return this;
        }

        public Builder with(Fragment fragment) {
            permissionWrapper = new PermissionWrapper(fragment.getActivity());
            return this;
        }

        public Builder requePermissionString(String... permissions) {
            permissionWrapper.permissions = permissions;
            return this;
        }

        public Builder requestCode(int requestCode) {
            if (requestCode <= 0) {
                throw new RuntimeException("The request code cannot be less than or equal to 0");
            }
            permissionWrapper.requestCode = requestCode;
            return this;
        }

        public Builder setErrorMsg(int errorMsg) {
            permissionWrapper.error_msg = errorMsg;
            return this;
        }

        public Builder onResultListener(IRequestResult iRequestResult) {
            permissionWrapper.iRequestResult = iRequestResult;
            return this;
        }


        public PermissionWrapper create() {
            return permissionWrapper;
        }
    }
}
