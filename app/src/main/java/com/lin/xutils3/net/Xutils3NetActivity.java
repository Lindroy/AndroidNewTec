package com.lin.xutils3.net;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.mr.androidnewtec.R;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_xutils3_net)
public class Xutils3NetActivity extends Activity {
    @ViewInject(R.id.tv_result)
    private TextView textView;

    @ViewInject(R.id.progressBar)
    private ProgressBar progressBar;

    @ViewInject(R.id.tv_title)
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        title.setText("xUtils3中的网络模块");
    }

    @Event(value = {R.id.btn_get_post, R.id.btn_download, R.id.btn_upload})
    private void eventClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_post:
                getAndPostNet();
                break;
            case R.id.btn_download:
                Toast.makeText(this, "开始下载", Toast.LENGTH_SHORT).show();
                downloadFile();
                break;
            case R.id.btn_upload:
                uploadFile();
                break;
        }
    }

    private void getAndPostNet() {
        //1.Get请求
        //2.Post请求(只需将下面的方法名称改为post即可)
        RequestParams params =
                new RequestParams("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "xUtils联网请求成功" + result);
//                textView.setText("Get请求结果——"+result.toString());
                textView.setText("PosT请求结果——" + result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "xUtils联网请求失败" + ex.getMessage());
                textView.setText(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "xUtils联网请求取消" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "xUtils联网请求完成");
            }
        });
    }

    /**
     * 下载文件
     */
    private void downloadFile() {
        RequestParams params = new RequestParams("http://vfx.mtime.cn/Video/2016/09/15/mp4/160915092608935956_480.mp4");
        //设置文件保存路径，如果不存在则会创建一个
        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/lin/abc.mp4");
        //设置是否可以立即取消下载
        params.setCancelFast(true);
        //设置是否自动根据头部信息命名
        params.setAutoRename(false);
        //设置断点续传
        params.setAutoResume(true);
        //自定义线程池,有效的值范围[1, 3]，设置为3时，可能阻塞图片加载
        params.setExecutor(new PriorityExecutor(2, true));

        x.http().get(params, new Callback.ProgressCallback<File>() { //对下载进度进行回调

            //下载成功时回调，并把下载的文件路径回传过来
            @Override
            public void onSuccess(File file) {
                Log.e("TAG", "文件路径：" + file.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "下载失败" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Toast.makeText(Xutils3NetActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressBar.setMax((int) total);
                progressBar.setProgress((int) current);
                Log.e("TAG", "onLoading=" + current + "/" + total + ",isDownloading" + isDownloading);
            }
        });
    }

    /**
     * 上传文件
     */
    public void uploadFile() {
        RequestParams params = new RequestParams("http://192.168.0.145:8080/FileUpload/FileUploadServlet");//用的JavaScript的知识
        //以表单形式上传
        params.setMultipart(true);
        //设置上传文件的路径
        params.addBodyParameter("File", new File(Environment.getExternalStorageDirectory() + "/lin/abc.mp4"));
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File file) {
                Log.e("TAG", "文件路径：" + file.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Toast.makeText(Xutils3NetActivity.this, "上传完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                Toast.makeText(Xutils3NetActivity.this, "开始上传", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }
        });
    }

}
