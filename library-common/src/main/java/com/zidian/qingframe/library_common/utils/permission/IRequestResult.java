package com.zidian.qingframe.library_common.utils.permission;


/**
 * 请求权限代理的方式的权限申请结果
 */
public interface IRequestResult {
    void onRequestResultSuccess();
    void onRequestResultFailed();
}
