package com.zidian.qingframe.library_common.utils.permission;

/**
 * 权限请求码，避免不同的地方重复
 */
public class PermissionRequestCode {

    //联系人
    public static final int WRITE_CONTACTS = 10000;
    public static final int GET_ACCOUNTS = 10001;
    public static final int READ_CONTACTS = 10002;

    //拨号、读取手机状态等
    public static final int READ_CALL_LOG = 10003;
    public static final int READ_PHONE_STATE = 10004;
    public static final int CALL_PHONE = 10005;
    public static final int USE_SIP = 10006;
    public static final int PROCESS_OUTGOING_CALLS = 10007;
    public static final int ADD_VOICEMAIL = 10008;

    //相机
    public static final int CAMERA = 10009;

    //日历
    public static final int READ_CALENDAR = 10010;
    public static final int WRITE_CALENDAR = 10011;

    //传感器
    public static final int BODY_SENSORS = 10012;


    //定位
    public static final int ACCESS_FINE_LOCATION = 10013;
    public static final int ACCESS_COARSE_LOCATION = 10014;

    //sd卡存储
    public static final int READ_EXTERNAL_STORAGE = 10015;
    public static final int WRITE_EXTERNAL_STORAGE = 10016;

    //麦克风
    public static final int RECORD_AUDIO = 10017;

    //短信
    public static final int READ_SMS = 10018;
    public static final int RECEIVE_WAP_PUSH = 10019;
    public static final int RECEIVE_MMS = 10020;
    public static final int RECEIVE_SMS = 10021;
    public static final int SEND_SMS = 10022;

}
