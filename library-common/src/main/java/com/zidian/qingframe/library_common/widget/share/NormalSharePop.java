package com.zidian.qingframe.library_common.widget.share;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zidian.library_common.R;
import com.zidian.qingframe.library_common.utils.AppUtils;
import com.zidian.qingframe.library_common.utils.CommonUtil;
import com.zidian.qingframe.library_common.utils.ToastUtils;
import com.zidian.qingframe.library_common.utils.UIUtils;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


public class NormalSharePop extends PopupWindow implements OnClickListener{
    private LinearLayout wechatBtn, wechatMomentsBtn, wechatFavoriteBtn, qqBtn, sinaWeiBoBtn;
    private Button btnCancel;
    private View cancelView;
    private Context mContext;
    private Platform plat;

    /**
     * 分享参数详解
     * http://wiki.mob.com/%e4%b8%8d%e5%90%8c%e5%b9%b3%e5%8f%b0%e5%88%86%e4%ba%ab%e5%86%85%e5%ae%b9%e7%9a%84%e8%af%a6%e7%bb%86%e8%af%b4%e6%98%8e/
     */
    private String title;
    private String url;
    private String siteUrl;
    private String site;
    private String text;
    private String imageUrl;
    private String imagePath;
    private String titleUrl;


    public NormalSharePop(Activity context, ShareBean shareBean) {

        super(context);
        mContext = context;
        View mView = View.inflate(mContext, R.layout.pop_normalshare, null);

        wechatBtn = mView.findViewById(R.id.normalshare_wechat);
        wechatMomentsBtn = mView.findViewById(R.id.normalshare_wechat_moments);
        wechatFavoriteBtn = mView.findViewById(R.id.normalshare_wechat_favorite);
        qqBtn = mView.findViewById(R.id.normalshare_qq);
        sinaWeiBoBtn = mView.findViewById(R.id.normalshare_sinaweibo);
        btnCancel = mView.findViewById(R.id.normalshare_cancel);
        cancelView = mView.findViewById(R.id.normalshare_cancel_view);

        // 设置按钮监听
        wechatBtn.setOnClickListener(this);
        wechatMomentsBtn.setOnClickListener(this);
        wechatFavoriteBtn.setOnClickListener(this);
        qqBtn.setOnClickListener(this);
        sinaWeiBoBtn.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        cancelView.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        //分享内容
        title = shareBean.getTitle();
        url = shareBean.getUrl();
        siteUrl = shareBean.getSiteUrl();
        site = shareBean.getSite();
        text = shareBean.getText();
        imageUrl = shareBean.getImageUrl();
        imagePath = shareBean.getImagePath();
        titleUrl = shareBean.getTitleUrl();

        //设置PopupWindow的View
        this.setContentView(mView);

        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationBottomFade);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x60000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //全屏遮罩
        this.setClippingEnabled(false);
        //底部状态栏计算
        if (UIUtils.isNavigationBarShow(context)) {
            int heigth = UIUtils.getNavigationBarHeight(context);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btnCancel.getLayoutParams();
            lp.bottomMargin = heigth;
            btnCancel.setLayoutParams(lp);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.normalshare_wechat) {
            //微信通讯录
            plat = ShareSDK.getPlatform(Wechat.NAME);
            if (!plat.isClientValid() && !CommonUtil.isWeixinAvilible(AppUtils.getApp())) {
                ToastUtils.showToast(AppUtils.getApp(), "检查到您手机没有安装微信，请安装后使用该功能");
                return;
            }
            Wechat.ShareParams wechatSP = new Wechat.ShareParams();
            wechatSP.setShareType(Wechat.SHARE_WEBPAGE);
            wechatSP.setTitle(title);
            wechatSP.setText(text);
            wechatSP.setUrl(url);
            wechatSP.setImagePath(imagePath);
            wechatSP.setImageUrl(imageUrl);
            plat.share(wechatSP);

        } else if (i == R.id.normalshare_wechat_moments) {
            //微信朋友圈
            plat = ShareSDK.getPlatform(WechatMoments.NAME);
            if (!plat.isClientValid() && !CommonUtil.isWeixinAvilible(AppUtils.getApp())) {
                ToastUtils.showToast(AppUtils.getApp(), "检查到您手机没有安装微信，请安装后使用该功能");
                return;
            }
            Wechat.ShareParams wechatSP2 = new Wechat.ShareParams();
            wechatSP2.setShareType(WechatMoments.SHARE_WEBPAGE);
            wechatSP2.setTitle(title);
            wechatSP2.setText(text);
            wechatSP2.setUrl(url);
            wechatSP2.setImagePath(imagePath);
            wechatSP2.setImageUrl(imageUrl);
            plat.share(wechatSP2);

        } else if (i == R.id.normalshare_wechat_favorite) {
            //微信收藏
            plat = ShareSDK.getPlatform(WechatFavorite.NAME);
            if (!plat.isClientValid() && !CommonUtil.isWeixinAvilible(AppUtils.getApp())) {
                ToastUtils.showToast(AppUtils.getApp(), "检查到您手机没有安装微信，请安装后使用该功能");
                return;
            }
            Wechat.ShareParams wechatSP3 = new Wechat.ShareParams();
            wechatSP3.setShareType(WechatFavorite.SHARE_WEBPAGE);
            wechatSP3.setTitle(title);
            wechatSP3.setText(text);
            wechatSP3.setUrl(url);
            wechatSP3.setImagePath(imagePath);
            wechatSP3.setImageUrl(imageUrl);
            plat.share(wechatSP3);

        } else if (i == R.id.normalshare_qq) {
            //qq通讯录
            plat = ShareSDK.getPlatform(QQ.NAME);
            if (!plat.isClientValid() && !CommonUtil.isQQClientAvailable(AppUtils.getApp())) {
                ToastUtils.showToast(AppUtils.getApp(), "检查到您手机没有安装QQ，请安装后使用该功能");
                return;
            }
            QQ.ShareParams qqSP = new QQ.ShareParams();
            qqSP.setShareType(QQ.SHARE_WEBPAGE);
            qqSP.setTitle(title);
            qqSP.setText(text);
            qqSP.setUrl(url);
            qqSP.setTitleUrl(titleUrl);
            qqSP.setSite(site);
            qqSP.setSiteUrl(siteUrl);
            qqSP.setImagePath(imagePath);
            qqSP.setImageUrl(imageUrl);
            plat.share(qqSP);

        } else if (i == R.id.normalshare_sinaweibo) {
             //新浪微博
            SinaWeibo.ShareParams weiboSP = new SinaWeibo.ShareParams();
            weiboSP.setShareType(SinaWeibo.SHARE_WEBPAGE);
            weiboSP.setTitle(title);
            //微博分享链接带入描述，不设置url，否则不能显示图片
            weiboSP.setText(text + "\n" + url);
//                weiboSP.setUrl(url);
            weiboSP.setImagePath(imagePath);
            weiboSP.setImageUrl(imageUrl);
            plat = ShareSDK.getPlatform(SinaWeibo.NAME);
            plat.share(weiboSP);

        }else {
            dismiss();
        }
    }
}
