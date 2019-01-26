package com.zidian.qingframe.library_common.utils.permission;

/**
 * 请求前线代理的方式的接口
 */
public interface IPermissionRequestProxy {
    boolean checkSelfPermission(String[] permissions);
    void permissionsCheck(String[] permissions, int requestCode, int error_msg);
    void setResultListener(IRequestResult listener);
}
