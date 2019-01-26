package com.zidian.qingframe;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.zidian.qingframe.library_common.base.BaseActivity;
import com.zidian.qingframe.library_common.utils.PhotoUtils;
import com.zidian.qingframe.library_common.utils.ToastUtils;
import com.zidian.qingframe.library_common.utils.permission.IRequestResult;
import com.zidian.qingframe.library_common.utils.permission.PermissionRequestCode;
import com.zidian.qingframe.library_common.utils.permission.PermissionWrapper;
import com.zidian.qingframe.library_common.widget.picker.CutPictureActivity;
import com.zidian.qingframe.library_common.widget.picker.TakePhotoPopWindow;
import com.zidian.qingframe.library_common.widget.share.NormalSharePop;
import com.zidian.qingframe.library_common.widget.share.ShareBean;
import com.zidian.qingframe.test.MyToolBarActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_main_mtb)
    Button mBtnMainMtb;
    @BindView(R.id.btn_main_picker)
    Button mBtnMainPicker;
    @BindView(R.id.btn_main_share)
    Button mBtnMainShare;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterOnCreate() {
        //拍照或选择图片回调接口
        PhotoUtils.getInstance().with(this).setOnPhotoListener(new PhotoUtils.OnPhotoListener() {
            @Override
            public void onPhoto(String imgPath) {
                ToastUtils.showToast(MainActivity.this, "图片path:\n" +imgPath);
                //根据需求跳转裁剪界面
                Intent intent = new Intent(MainActivity.this, CutPictureActivity.class);
                intent.putExtra("picPath", imgPath);
                intent.putExtra("number", 720);
                startActivityForResult(intent, PhotoUtils.CLIP_PHOTO);
            }
        });
    }

    @OnClick({R.id.btn_main_mtb, R.id.btn_main_picker, R.id.btn_main_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_mtb:
                //mytoolbar展示
                Intent intent = new Intent(this, MyToolBarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_main_picker:
                //图片选择器演示
                //权限请求
                new PermissionWrapper.Builder().with(this).requePermissionString(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .requestCode(PermissionRequestCode.CAMERA)
                        .setErrorMsg(R.string.permission_save_sd_tip)
                        .onResultListener(new IRequestResult() {
                            @Override
                            public void onRequestResultSuccess() {
                                TakePhotoPopWindow popWindow = new TakePhotoPopWindow(MainActivity.this);
                                popWindow.setTitle("拍照", "从手机相册选择");
                                popWindow.setPickPhotoListener(new TakePhotoPopWindow.PickPhotoListener() {
                                    @Override
                                    public void takePhoto() {
                                        String photoName = "user_photo" + System.currentTimeMillis() + ".jpg";
                                        PhotoUtils.getInstance().with(MainActivity.this).takePhoto(new File(PhotoUtils.IMAGE_PATH + photoName));
                                    }

                                    @Override
                                    public void pickPhoto() {
                                        PhotoUtils.getInstance().with(MainActivity.this).pickPhoto(PhotoUtils.PICK_PHOTO);
                                    }
                                });
                                View contentView = LayoutInflater.from(MainActivity.this).inflate(getLayoutId(), null);
                                popWindow.showAtLocation(contentView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                            }

                            @Override
                            public void onRequestResultFailed() {
                                ToastUtils.showToast(getApplicationContext(), "权限请求失败");
                            }
                        })
                        .create()
                        .requestPermission();

                break;
            case R.id.btn_main_share:
                //分享演示
                NormalSharePop mSharePop = new NormalSharePop(MainActivity.this,
                        ShareBean.builder().
                                setTitle("分享标题").
                                setText("分享内容").
                                setUrl("http://www.baidu.com"). //分享链接
                                setTitleUrl("http://www.baidu.com"). //同上
                                //缩略图url,qq分享链接不能是ip+端口形式，否则不能显示
                                setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2412307550,2368771409&fm=26&gp=0.jpg"));
                mSharePop.showAtLocation(View.inflate(MainActivity.this, getLayoutId(), null),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PhotoUtils.CLIP_PHOTO){
                //裁剪后的回跳
                ToastUtils.showToast(getApplicationContext(), "裁剪后path:" + data.getStringExtra("user_result"));
            }else {
                PhotoUtils.getInstance().with(this).onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
