package com.zidian.qingframe.library_common.base;


import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.just.agentweb.AgentWeb;
import com.zidian.library_common.R;
import com.zidian.qingframe.library_common.AppConfig;
import com.zidian.qingframe.library_common.utils.ActivityStackUtils;
import com.zidian.qingframe.library_common.widget.MyToolBar;

/**
 * html展示页面
 * 跳转时intent传入title和url即可
 */
public class BaseWebActivity extends BaseActivity {
    public static final String TOOL_BAR_TITLE = "toolbar_title";
    public static final String WEBVIEW_URL = "webview_url";

    private MyToolBar mToolBar;
    private FrameLayout mFrameLayout;

    private AgentWeb mAgentWeb;
    private String url;
    private String title;

    private WebChromeClient mWebChromeClient;
    private WebViewClient mWebViewClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void afterOnCreate() {
        initView();
        initListener();

        //初始化webview
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mFrameLayout, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getResources().getColor(R.color.colorPrimary)) //加载条颜色
                .additionalHttpHeader("x-appid", AppConfig.APPID)   //添加头信息，根据实际需求，可以不设置
                .setWebChromeClient(mWebChromeClient)   //根据实际需求，可以不设置
                .setWebViewClient(mWebViewClient)       //根据实际需求，可以不设置
                .createAgentWeb()
                .ready()
                .go(url);
    }

    private void initView() {
        title = getIntent().getStringExtra(TOOL_BAR_TITLE);
        url = getIntent().getStringExtra(WEBVIEW_URL);

        mToolBar = findViewById(R.id.mtb_web);
        mFrameLayout = findViewById(R.id.fl_webview);

        if (TextUtils.isEmpty(url)) {
            url = "http://www.baidu.com";
        }
        //初始化title
        mToolBar.setTitle(title);
    }

    private void initListener() {
        //初始化WebChromeClient，根据实际需求设置，可以不设置
        mWebChromeClient = new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //拦截title
                super.onReceivedTitle(view, title);
            }
        };

        //初始化WebViewClient，根据实际需求设置，可以不设置
        mWebViewClient = new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //页面加载完事件
                super.onPageFinished(view, url);
            }
        };

        //back回退
        mToolBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //webview自回退
                if (!mAgentWeb.back()) {
                    ActivityStackUtils.finishActivity();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
