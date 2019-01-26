package com.zidian.qingframe.library_common.retrofit;

import android.content.Intent;

import com.zidian.qingframe.library_common.AppConfig;
import com.zidian.qingframe.library_common.utils.AppUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 下载response
 */
public class ProgressResponseBody extends ResponseBody {
    private final ResponseBody responseBody;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }


    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                Intent mIntent = new Intent(AppConfig.DOWNLOAD_PROGRESS);
                mIntent.putExtra("totalBytesRead", totalBytesRead);
                mIntent.putExtra("total", responseBody.contentLength());
                AppUtils.getApp().sendBroadcast(mIntent);
                return bytesRead;
            }
        };
    }

}
