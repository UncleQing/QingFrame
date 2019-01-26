package com.zidian.qingframe.test;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.zidian.qingframe.R;
import com.zidian.qingframe.library_common.base.BaseActivity;
import com.zidian.qingframe.library_common.utils.ActivityStackUtils;
import com.zidian.qingframe.library_common.utils.ToastUtils;
import com.zidian.qingframe.library_common.widget.EditText_Clear;
import com.zidian.qingframe.library_common.widget.MyToolBar;

import butterknife.BindView;

public class MyToolBarActivity extends BaseActivity {

    @BindView(R.id.mtb_1)
    MyToolBar mMtb1;
    @BindView(R.id.mtb_2)
    MyToolBar mMtb2;
    @BindView(R.id.mtb_3)
    MyToolBar mMtb3;
    @BindView(R.id.mtb_4)
    MyToolBar mMtb4;
    @BindView(R.id.mtb_5)
    MyToolBar mMtb5;
    @BindView(R.id.mtb_6)
    MyToolBar mMtb6;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_tool_bar;
    }

    @Override
    protected void afterOnCreate() {
        //back键事件自定义,若不设置只有回退功能，若设置需自行实现回退功能
        mMtb1.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(getApplicationContext(), "back!");
                ActivityStackUtils.finishActivity();
            }
        });

        //标题设置
        mMtb2.setTitle("标题2");

        //右功能键设置
        mMtb4.setRightTitle("右边");
        mMtb4.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(getApplicationContext(), "右边!");
            }
        });

        //搜索框搜索事件设置(键盘搜索键触发）
        mMtb5.setSearchListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                ToastUtils.showToast(getApplicationContext(), "搜索中....");
                return false;
            }
        });
        //搜索框清除事件设置(x或者手动删除最后一个字符后触发)
        mMtb5.setSearchClearListener(new EditText_Clear.SetClearImageListener() {
            @Override
            public void clear() {
                ToastUtils.showToast(getApplicationContext(), "清空!");
            }
        });

        //菜单设置
        mMtb6.getPopWindowBuiler(this)
                .layout(R.layout.layout_toolbar_menu)
                .setAnimationStyle(R.style.AnimationFade)
                .setText(R.id.tv_menu_collect, "还是收藏")
                .setOutSideCancel(R.id.rl_bg)   //设置点击背景关闭window
                .addViewOnclick(R.id.tv_menu_collect, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.showToast(getApplicationContext(), "收藏!");
                    }
                })
                .addViewOnclick(R.id.tv_menu_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.showToast(getApplicationContext(), "分享!");
                    }
                })
                .build();
        mMtb6.setMenuShow(true);

    }


}
