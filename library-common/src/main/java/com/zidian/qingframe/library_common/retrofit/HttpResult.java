package com.zidian.qingframe.library_common.retrofit;


/**
 * 网络请求基础bean
 * 根据实际服务器数据类型配置
 */
public class HttpResult<T> {
    private String code;
    private String msg;
    private T data;

}
