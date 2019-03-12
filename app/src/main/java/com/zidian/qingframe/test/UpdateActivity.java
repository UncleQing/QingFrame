package com.zidian.qingframe.test;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.zidian.qingframe.R;
import com.zidian.qingframe.library_common.AppConfig;
import com.zidian.qingframe.library_common.base.BaseActivity;
import com.zidian.qingframe.library_common.utils.AppUtils;
import com.zidian.qingframe.library_common.utils.CommonUtil;
import com.zidian.qingframe.library_common.utils.UIUtils;
import com.zidian.qingframe.library_common.utils.dialog.DialogFragmentHelper;

import java.io.File;

import butterknife.OnClick;

public class UpdateActivity extends BaseActivity implements UpdateView {
    //TODO 测试下载apk url
    private String url = "http://wap.apk.anzhi.com/data5/apk/201812/12/com.evernote_04286100.apk";

    private UpdatePresenter mPresenter;

    private BroadcastReceiver mReceiver;
    private boolean isCreat;
    private int pro;
    private Notification.Builder builder;
    private NotificationManager notificationManager;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_update;
    }

    @Override
    protected void afterOnCreate() {
        mPresenter = new UpdatePresenter(this);

        //注册广播用于显示下载进度条
        notificationManager = (NotificationManager) AppUtils.getApp().getSystemService(Context.NOTIFICATION_SERVICE);
        registerReceiver();

    }

    @OnClick({R.id.update_btn, R.id.forced_update_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_btn:
                //TODO 访问服务器更新api，获取更新信息
                //更新提示弹框应在访问服务器更新api后的回调中，在此测试直接放这了
                //更新询问对话框
                DialogFragmentHelper.showUpdateDialog(getSupportFragmentManager(), "1.修改了bug\n2.追加了新功能"
                        , true, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //下载apk
                                mPresenter.downLoadApk(url);
                            }
                        });

                break;
            case R.id.forced_update_btn:
                //TODO 访问服务器更新api，获取更新信息
                //同上，区别是这个更新对话框不可关闭,一般在进入首页后请求更新api显示，若显示则必须更新，否则不可关闭
                DialogFragmentHelper.showUpdateDialog(getSupportFragmentManager(), "1.修改了bug\n2.追加了新功能"
                        , false, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //下载apk
                                mPresenter.downLoadApk(url);
                            }
                        });
                break;
        }
    }

    private void registerReceiver() {
        //下载进度条
        if (null == mReceiver) {
            mReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (AppConfig.DOWNLOAD_PROGRESS.equals(action)) {
                        double total = (double) intent.getLongExtra("total", 0);
                        double current = (double) intent.getLongExtra("totalBytesRead", 0);

                        if (!isCreat) {
                            builder = UIUtils.creatNotification(notificationManager);
                            isCreat = true;
                            pro = 0;
                        }
                        pro = (int) (current / total * 100);
                        if (pro < 100) {
                            builder.setContentText(pro + "%");
                            builder.setProgress(100, pro, false);
                            notificationManager.notify(UIUtils.notifyID, builder.build());
                        } else {
                            UIUtils.cancleNotification(notificationManager);
                            isCreat = false;
                        }
                    }
                }
            };
            IntentFilter filter = new IntentFilter();
            filter.addAction(AppConfig.DOWNLOAD_PROGRESS);
            AppUtils.getApp().registerReceiver(mReceiver, filter);
        }
    }

    @Override
    public void onDownloadSucceed(File file) {
        if (file.exists()) {
            CommonUtil.installApk(getApplicationContext(), file);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mReceiver) {
            AppUtils.getApp().unregisterReceiver(mReceiver);
        }
    }
}
