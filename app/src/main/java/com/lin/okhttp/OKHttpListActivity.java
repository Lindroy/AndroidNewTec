package com.lin.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.mr.androidnewtec.R;
import com.lin.okhttp.domain.DataBean;
import com.lin.uitls.CacheUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

public class OKHttpListActivity extends AppCompatActivity {

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.tv_noData)
    TextView tvNoData;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_list);
        ButterKnife.bind(this);
        getDataByOKHttpUtils();
    }

    /**
     * 使用OKHttpUtils中的Get方式请求网络数据
     */
    public void getDataByOKHttpUtils() {
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        //优先去获取缓存的数据
        String saveJson = CacheUtils.getString(this, url);
        if (!TextUtils.isEmpty(saveJson)) {
            processedData(saveJson);
        }
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
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
                    Toast.makeText(OKHttpListActivity.this, "开始上传文件", Toast.LENGTH_SHORT).show();
                    break;
                case 103:
                    Toast.makeText(OKHttpListActivity.this, "开始请求图片", Toast.LENGTH_SHORT).show();
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
//                    tvResult.setText("Get请求:" + response);
//                    Toast.makeText(OKHttpActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
//                    tvResult.setText("Post请求:" + response);
//                    Toast.makeText(OKHttpActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }

            if (response != null) {
                //将response缓存起来
                CacheUtils.putString(OKHttpListActivity.this, url, response);//url作为key
                processedData(response);
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
//            Log.e(TAG, "inProgress:" + progress);
            progressBar.setProgress((int) (100 * progress));
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 解析请求得到的Json数据
     *
     * @param json
     */
    private void processedData(String json) {
        //解析数据
        DataBean dataBean = parsedJson(json);
        List<DataBean.ItemData> datas = dataBean.getTrailers();

        if (datas != null && datas.size() > 0) {
            //有数据
            tvNoData.setVisibility(View.GONE);
            //显示适配器
            listView.setAdapter(new OKHttpListAdapter(OKHttpListActivity.this, datas));
        } else {
            //没有数据
            tvNoData.setVisibility(View.VISIBLE);
        }

        progressBar.setVisibility(View.GONE);
    }

    /**
     * 解析json数据
     *
     * @param response
     * @return
     */
    private DataBean parsedJson(String response) {
        DataBean dataBean = new DataBean();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.optJSONArray("trailers");
            if (jsonArray != null && jsonArray.length() > 0) {
                List<DataBean.ItemData> trailers = new ArrayList<>();
                dataBean.setTrailers(trailers);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObjectItem = (JSONObject) jsonArray.get(i);

                    if (jsonObjectItem != null) {

                        DataBean.ItemData mediaItem = new DataBean.ItemData();

                        String movieName = jsonObjectItem.optString("movieName");//name
                        mediaItem.setMovieName(movieName);

                        String videoTitle = jsonObjectItem.optString("videoTitle");//desc
                        mediaItem.setVideoTitle(videoTitle);

                        String imageUrl = jsonObjectItem.optString("coverImg");//imageUrl
                        mediaItem.setCoverImg(imageUrl);

                        String hightUrl = jsonObjectItem.optString("hightUrl");//data
                        mediaItem.setHightUrl(hightUrl);

                        //把数据添加到集合
                        trailers.add(mediaItem);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataBean;
    }
}
