package com.zidian.qingframe.library_common;

/**
 * 全局静态字段
 */
public class AppConfig {

    /**
     * 服务器为客服端分配的应用级别的账户
     */
    public static final String APPID = "cfb-android";

    /**
     * 发送的服务器请求数据类别
     * json, urlencoded,  默认json，其他的都是urlencoded
     */
    public static final String SERVER_REQUEST_METHOD = "json";

    /**
     * 接口版本
     */
    public static final String INTERFACE_VERSION = "1.0.0";


    /**
     * 协议版本
     */
    public static final String PROTOCOL_VERSION = "1.1";

    /**
     * 从拦截器当中发送下载进度广播给service
     */
    public static final String DOWNLOAD_PROGRESS = "com.credithc.hengyiying.app.upgrade.downloadprogress";
}
