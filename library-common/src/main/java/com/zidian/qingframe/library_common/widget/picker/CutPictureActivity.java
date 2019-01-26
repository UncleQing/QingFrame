package com.zidian.qingframe.library_common.widget.picker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.zidian.library_common.R;
import com.zidian.qingframe.library_common.base.BaseActivity;
import com.zidian.qingframe.library_common.utils.PhotoUtils;

import java.io.File;

/**
 * 用于图片缩放裁剪窗口
 */
public class CutPictureActivity extends BaseActivity implements View.OnClickListener {

    private ClipImageView imageView;
    private ImageView iv_cut_detele;
    private ImageView iv_cut_sure;
    private String picPath;
    File f = new File(PhotoUtils.IMAGE_PATH, "cutUserPhoto" + System.currentTimeMillis() + ".jpg");
    private int number;

    @Override
    protected void afterOnCreate() {
        initView();
        initListener();
        initData();
    }

    private void initData() {
        // 设置需要裁剪的图片
        Bitmap bit = null;
        File file = new File(picPath);
        //加载图片的过程，会进行判断并压缩
        bit = PhotoUtils.getBitmapFromBigImagByInputStream(file, number);
        imageView.setImageBitmap(bit);
    }

    private void initListener() {
        iv_cut_detele.setOnClickListener(this);
        iv_cut_sure.setOnClickListener(this);
    }

    private void initView() {
        imageView = findViewById(R.id.src_pic);
        iv_cut_detele = findViewById(R.id.iv_cut_detele);
        iv_cut_sure = findViewById(R.id.iv_cut_sure);
        picPath = getIntent().getStringExtra("picPath");
        number = getIntent().getIntExtra("number", 600);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cut_picture;
    }


    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.iv_cut_detele) {
            finish();
        } else if (i == R.id.iv_cut_sure) {
            Bitmap bitmap = imageView.clip();
            bitmap = PhotoUtils.compressImage(bitmap, 2);
            PhotoUtils.saveBitmap(f, bitmap);
            CutPictureActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + f.getAbsolutePath())));
            Intent intent = new Intent();
            intent.putExtra("user_result", f.getPath());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
