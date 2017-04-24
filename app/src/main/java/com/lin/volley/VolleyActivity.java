package com.lin.volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lin.mr.androidnewtec.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class VolleyActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_volley_result)
    ImageView ivVolleyResult;
    @Bind(R.id.iv_networkImageview)
    NetworkImageView ivNetworkImageview;
    @Bind(R.id.tv_result)
    TextView tvResult;
    private RequestQueue requestQueue;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        ButterKnife.bind(this);
        tvTitle.setText("Volley");
        //1.创建请求队列
        requestQueue = Volley.newRequestQueue(VolleyActivity.this);

        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    }

    @OnClick({R.id.btn_get, R.id.btn_post, R.id.btn_getjson, R.id.btn_imagerequest, R.id.btn_imageloader, R.id.btn_networkimageview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get: //Get请求

                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    //接收成功回调
                    @Override
                    public void onResponse(String s) {
                        tvResult.setText(s);
                    }
                }, new Response.ErrorListener() {
                    //接收异常时回调
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        tvResult.setText("加载失败" + volleyError);
                    }
                });
                requestQueue.add(stringRequest);
                break;
            case R.id.btn_post://Post请求
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST,//post请求需要写明参数
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        tvResult.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        tvResult.setText("加载失败" + volleyError);
                    }
                }) {
                    //Post请求也可以传递参数
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        return map;
                    }
                };
                requestQueue.add(stringRequest1);
                break;
            case R.id.btn_getjson:
                JsonObjectRequest objectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tvResult.setText(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        tvResult.setText("请求失败" + volleyError);
                    }
                });
                requestQueue.add(objectRequest);
                break;
            case R.id.btn_imagerequest://请求图片
                String url1 = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
                ImageRequest imageRequest = new ImageRequest(url1, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivVolleyResult.setVisibility(View.VISIBLE);
                        ivVolleyResult.setImageBitmap(bitmap);
                    }
                }, 0,
                        0, //宽高的最大值设为0，表示不对图片的最大宽高进行限制
                        Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        ivVolleyResult.setImageResource(R.drawable.ic_launcher);
                    }
                });
                requestQueue.add(imageRequest);
                break;
            case R.id.btn_imageloader://通过ImageLoader加载图片
                //创建一个ImageLoader
//                ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
//                    @Override
//                    public Bitmap getBitmap(String s) {
//                        return null;
//                    }
//
//                    @Override
//                    public void putBitmap(String s, Bitmap bitmap) {
//
//                    }
//                });

                ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
                //加载图片
                ivVolleyResult.setVisibility(View.VISIBLE);
                String url2 = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
                ImageLoader.ImageListener imageListener = imageLoader.getImageListener(ivVolleyResult,
                        R.drawable.ic_launcher, R.drawable.ic_tab_audio_press);
                imageLoader.get(url2, imageListener);

                break;
            case R.id.btn_networkimageview:
                ivNetworkImageview.setVisibility(View.VISIBLE);

                //创建一个ImageLoader
                ImageLoader imageLoader1 = new ImageLoader(requestQueue, new BitmapCache());
                String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";

                //默认图片设置
                ivNetworkImageview.setDefaultImageResId(R.drawable.ic_tab_video);
                //加载异常的图片设置
                ivNetworkImageview.setErrorImageResId(R.drawable.ic_launcher);
                //加载图片
                ivNetworkImageview.setImageUrl(url, imageLoader1);
                break;
        }
    }
}
