package com.zidian.qingframe.library_common.utils;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PhotoUtils {
    public static final String DEFAULT_URI = AppUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_DCIM)
            + File.separator
            + "photo_tmp.jpg";
    public static final String IMAGE_PATH = AppUtils.getApp().getExternalCacheDir()
            + File.separator
            + "images"
            + File.separator;
    //还需再Manifest配置相关provider
    public static final String FILE_PROVIDER = "com.zidian.qingframe.fileprovider";
    //拍照、相册、裁剪请求码
    public static final int TAKE_PHOTO = 5000;
    public static final int PICK_PHOTO = 5001;
    public static final int CLIP_PHOTO = 5002;

    private Activity mActivity;
    private Uri photoUri;
    private String imgPath;
    private OnPhotoListener mListener;

    private PhotoUtils() {

    }

    private static class Holder {
        private static PhotoUtils IN = new PhotoUtils();
    }

    public static PhotoUtils getInstance() {
        return Holder.IN;
    }

    public PhotoUtils with(Activity activity) {
        this.mActivity = activity;
        File file = new File(DEFAULT_URI);
        if (Build.VERSION.SDK_INT >= 24) {
            photoUri = FileProvider.getUriForFile(AppUtils.getApp(), FILE_PROVIDER, file);
        } else {
            photoUri = Uri.fromFile(file);
        }

        File file2 = new File(IMAGE_PATH);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return this;
    }

    /**
     * 拍照
     *
     * @param file 照片存放目录
     */
    public void takePhoto(File file) {
        imgPath = file.getPath();

        mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, photoUri));

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        mActivity.startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * 拍照
     * 照片存放到指定目录
     */
    public void takePhoto(int requsetCode, String imgName) {
        if (!TextUtils.isEmpty(imgName)) {
            imgPath = Environment.getExternalStorageDirectory() + "/" + imgName;


            File file = new File(imgPath);
            if (Build.VERSION.SDK_INT >= 24) {
                photoUri = FileProvider.getUriForFile(mActivity, FILE_PROVIDER, file);
            } else {
                photoUri = Uri.fromFile(file);
            }
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        mActivity.startActivityForResult(intent, requsetCode);
    }

    /**
     * 相册
     */
    public void pickPhoto(int requsetCode) {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (mActivity.getPackageManager().resolveActivity(i, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            mActivity.startActivityForResult(i, requsetCode);
        }
    }

    /**
     * 该方法需要在Activity的onActivityResult中调用
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                if (mListener != null) {
                    mListener.onPhoto(imgPath);
                }
            } else if (requestCode == PICK_PHOTO) {
                if (data != null) {
                    Uri uri = data.getData();
                    imgPath = pickResult(uri);
                    if (mListener != null) {
                        mListener.onPhoto(imgPath);
                    }
                }
            }
        } else {
            Log.e("photoUtils", "拍照或选择相册取消或失败!");
        }
    }

    private String pickResult(Uri uri) {
        String imgPath = "";
        Cursor cursor = mActivity.getContentResolver().query(uri, null,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String imgNo = cursor.getString(0);     // 图片编号
            imgPath = cursor.getString(1);          // 图片文件路径
            String imgSize = cursor.getString(2);   // 图片大小
            String imgName = cursor.getString(3);   // 图片文件名
            cursor.close();
        } else {
            imgPath = uri.toString();
        }

        imgPath = imgPath.startsWith("file:///") ? imgPath.replace("file://", "") : imgPath;
        Log.e("------", "-----pickResult------" + uri + "  " + imgPath);
        return imgPath;
    }

    public void setOnPhotoListener(OnPhotoListener listener) {
        this.mListener = listener;
    }

    /**
     * 返回刚才拍照或选择的照片路径
     *
     * @return
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * 本地获取bitmap
     *
     * @param file
     * @param num
     * @return
     */
    public static Bitmap getBitmapFromBigImagByInputStream(File file, int num) {
        Bitmap result = null;
        InputStream is1 = null;
        InputStream is2 = null;
        try {
            if (file.exists()) {
                is1 = new FileInputStream(file);
                is2 = new FileInputStream(file);
                BitmapFactory.Options opts = new BitmapFactory.Options();

                opts.inJustDecodeBounds = true;
                result = BitmapFactory.decodeStream(is1, null, opts);
                int imageWidth = opts.outWidth;
                int imageHeight = opts.outHeight;
                // 缩放的比例
                opts.inSampleSize = Math.max(imageWidth / num, imageHeight / num);
                // 内存不足时可被回收
                opts.inPurgeable = true;
                // 设置为false,表示不仅Bitmap的属性，也要加载bitmap
                opts.inPreferredConfig = Bitmap.Config.RGB_565;
                opts.inJustDecodeBounds = false;
                result = BitmapFactory.decodeStream(is2, null, opts);
            } else {
                return null;
            }
        } catch (Exception ex) {
        } finally {
            if (is1 != null) {
                try {
                    is1.close();
                } catch (IOException e1) {
                }
            }
            if (is2 != null) {
                try {
                    is2.close();
                } catch (IOException e1) {
                }
            }
        }
        return result;
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image, int number) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while (baos.toByteArray().length / 1048576 > number) { // 循环判断如果压缩后图片是否大于2M,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 存储bitmap到本地
     *
     * @param f
     * @param bitmap
     */
    public static void saveBitmap(File f, Bitmap bitmap) {
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //base64字符串转bitmap
    public static Bitmap base64ToBitmap(String base64Data) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(base64Data, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //bitmap转base64
    public String bitmapToBase64(Bitmap bit) {
        if (bit == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, bos);//第二个入参表示图片压缩率，如果是100就表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public interface OnPhotoListener {
        void onPhoto(String imgPath);
    }
}
