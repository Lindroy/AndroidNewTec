package com.lin.okhttp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.mr.androidnewtec.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 原生OKHttp的使用
 * 必须在子线程中请求数据
 */
public class OKHttpActivity extends Activity {
    private static final int GET = 1;
    private static final int POST = 2;
    private static final String TAG = "TAG";


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    @Bind(R.id.ivOKHttp)
    ImageView ivOKHttp;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET:
                    tvResult.setText(msg.obj.toString());
                    break;
                case POST:
                    tvResult.setText(msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
        tvTitle.setText("OKHttp");
    }


    @OnClick({R.id.btn_get, R.id.btn_post, R.id.btn_get_okHttpUtils, R.id.btn_post_okHttpUtils,
            R.id.btn_download, R.id.btn_upload, R.id.btn_image, R.id.btn_image_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                tvResult.setText("");//清除之前的数据
                getDataFromGet();
                break;
            case R.id.btn_post:
                tvResult.setText("");
                getDataFromPost();
                break;
            case R.id.btn_get_okHttpUtils:
                tvResult.setText("");
                getDataByOKHttpUtils();
                break;
            case R.id.btn_post_okHttpUtils:
                tvResult.setText("");
                postDataByOKHttpUtils();
                break;
            case R.id.btn_download:
                tvResult.setText("");
                downloadFile();
                break;
            case R.id.btn_upload:
                tvResult.setText("");
                multiFileUpload();
                break;
            case R.id.btn_image:
                tvResult.setText("");
                getImage();
                break;
            case R.id.btn_image_list:
                startActivity(new Intent(OKHttpActivity.this,
                        OKHttpListActivity.class));
                break;
        }
    }


    //创建okHttpClient对象
    OkHttpClient client = new OkHttpClient();

    //使用Get请求
    private void getDataFromGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = get("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                    Log.e("TAG", result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * Get请求
     *
     * @param url：网络连接
     * @return
     * @throws IOException
     */
    private String get(String url) throws IOException {
        //创建一个Request
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private void getDataFromPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = post("http://api.m.mtime.cn/PageSubArea/TrailerList.api", "");
                    Log.e("TAG", result);
                    Message msg = Message.obtain();
                    msg.what = POST;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * Post请求
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 使用OKHttpUtils中的Get方式请求网络数据
     */
    public void getDataByOKHttpUtils() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url = "http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    /**
     * 使用OKHttpUtils中的Post方式请求网络数据
     */
    private void postDataByOKHttpUtils() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url = "http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .post()
                .url(url)
                .id(101) //将id也改掉
                .build()
                .execute(new MyStringCallback());
    }

    //回调
    public class MyStringCallback extends StringCallback {
        //执行前
        @Override
        public void onBefore(Request request, int id) {
            setTitle("loading...");
            switch (id) {
                case 102:
                    Toast.makeText(OKHttpActivity.this, "开始上传文件", Toast.LENGTH_SHORT).show();
                    break;
                case 103:
                    Toast.makeText(OKHttpActivity.this, "开始请求图片", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        //执行后
        @Override
        public void onAfter(int id) {
            setTitle("Sample-okHttp");
        }

        //执行后得到的结果
        @Override
        public void onResponse(String response, int id) {
//            Log.e(TAG, "onResponse：complete");
//            tvResult.setText("onResponse:" + response);

            switch (id) {
                case 100:
                    tvResult.setText("Get请求:" + response);
//                    Toast.makeText(OKHttpActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    tvResult.setText("Post请求:" + response);
//                    Toast.makeText(OKHttpActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
                case 102:
                    Toast.makeText(OKHttpActivity.this, "上传文件完成", Toast.LENGTH_SHORT).show();
                    break;
                case 103:
                    Toast.makeText(OKHttpActivity.this, "请求图片完成", Toast.LENGTH_SHORT).show();
                    break;

            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
//            Log.e(TAG, "inProgress:" + progress);
            mProgressBar.setProgress((int) (100 * progress));
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            tvResult.setText("onError:" + e.getMessage());
        }
    }

    /**
     * 使用OKHttpUtils下载大文件
     */
    public void downloadFile() {
        //下载的是《奇异博士》的预告片
        String url = "http://vfx.mtime.cn/Video/2016/07/24/mp4/160724154733643806.mp4";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "OKHttp-test.mp4")//
                {

                    @Override
                    public void onBefore(Request request, int id) {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        mProgressBar.setProgress((int) (100 * progress));
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                        Toast.makeText(OKHttpActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 使用OKHttpUtils上传文件
     * 可以同时上传多个文件
     */
    public void multiFileUpload() {

        String mBaseUrl = "http://192.168.1.104:8080/FileUpload/FileUploadServlet";
        File file = new File(Environment.getExternalStorageDirectory(), "OKHttp-test.mp4");
        File file2 = new File(Environment.getExternalStorageDirectory(), "a.txt");
        if (!file.exists()) {
            Toast.makeText(OKHttpActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "Lindroid");
        params.put("password", "123");

        String url = mBaseUrl;
        OkHttpUtils.post()//
                .id(102)//根据此Id执行回调中的一些方法
                .addFile("file", "OKHttp-upload.mp4", file)//上传至服务端后的名字
                .addFile("file", "b.txt", file2)//上传至服务端后的名字
                .url(url)
                .params(params)//
                .build()//
                .execute(new MyStringCallback());
    }

    /**
     * 使用OKHttpUtils请求图片
     */
    public void getImage() {
//        mTv.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        Log.e("TAG", "onResponse：complete");
                        ivOKHttp.setImageBitmap(bitmap);
                    }
                });
    }
}
