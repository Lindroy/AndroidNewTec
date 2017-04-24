package com.lin.afinal;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AfinalActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinal);
        ButterKnife.bind(this);
        tvTitle.setText("Afinal的使用");
    }

    @OnClick({R.id.btn_loadImage, R.id.btn_getText, R.id.btn_loadFile, R.id.btn_uploadText})
    public void onClick(View view) {
        FinalHttp finalHttp = new FinalHttp();//用于网络请求
        switch (view.getId()) {
            case R.id.btn_loadImage://使用Afinal加载图片
                FinalBitmap finalBitmap = FinalBitmap.create(this);
                //网络请求图片时默认显示的图片
                finalBitmap.configLoadingImage(R.drawable.qq);
                //开始加载图片
                finalBitmap.display(image,
                        "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg");//不知道为什么设置宽高并没有起作用
                break;
            case R.id.btn_getText://使用Afinal请求文本

                String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
                finalHttp.get(url, new AjaxCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        tvResult.setText("开始加载");
                    }

                    @Override
                    public void onSuccess(String s) {
                        super.onSuccess(s);
                        //显示加载成功后的结果
                        tvResult.setText(s.toString());
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        tvResult.setText("加载失败");
                    }
                });
                break;
            case R.id.btn_loadFile:
                //请求网络资源的地址
                String url2 = "http://vfx.mtime.cn/Video/2016/07/24/mp4/160724154733643806.mp4";
                //存放视频的位置（如果是getFileDir()的话就是下载到应用包下的file文件）
                String target = Environment.getExternalStorageDirectory() + "/afinal.mp4";
                finalHttp.download(url2, target, new AjaxCallBack<File>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        tvResult.setText("开始下载文件");
                    }

                    @Override
                    public void onLoading(long count, long current) {
                        super.onLoading(count, current);
                        tvResult.setText("正在下载文件");
                    }

                    @Override
                    public void onSuccess(File file) {
                        super.onSuccess(file);
                        tvResult.setText("下载文件完成");
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        tvResult.setText("下载文件失败");
                    }
                });
                break;
            case R.id.btn_uploadText:
                String url3 = "http://192.168.1.104:8080/FileUpload/FileUploadServlet";
                AjaxParams params = new AjaxParams();
                try {
                    params.put("File", new File(Environment.getExternalStorageDirectory() + "/afinal.mp4"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //获取要上传的本地资源
                finalHttp.post(url3, params, new AjaxCallBack<Object>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        tvResult.setText("开始上传文件");
                    }

                    @Override
                    public void onLoading(long count, long current) {
                        super.onLoading(count, current);
                    }

                    @Override
                    public void onSuccess(Object o) {
                        super.onSuccess(o);
                        tvResult.setText("上传文件成功");
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        tvResult.setText("上传文件失败");
                    }
                });
                break;
        }
    }
}
