package com.lin.picasso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PicassoActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_download)
    Button btnDownload;
    @Bind(R.id.btn_listView)
    Button btnListView;
    @Bind(R.id.btn_change)
    Button btnChange;
    @Bind(R.id.iv_picasso1)
    ImageView ivPicasso1;
    @Bind(R.id.iv_picasso2)
    ImageView ivPicasso2;
    @Bind(R.id.iv_picasso3)
    ImageView ivPicasso3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ButterKnife.bind(this);
        tvTitle.setText("Picasso的使用");
    }

    @OnClick({R.id.btn_download, R.id.btn_listView, R.id.btn_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
                //普通加载图片
                Picasso.with(PicassoActivity.this)
                        .load("http://n.sinaimg.cn/translate/20160819/9BpA-fxvcsrn8627957.jpg")
                        .into(ivPicasso1);

                //以裁剪的方式加载图片
                Picasso.with(PicassoActivity.this)
                        .load("http://n.sinaimg.cn/translate/20160819/9BpA-fxvcsrn8627957.jpg")
                        .resize(100, 100) //裁剪后的图片宽高
                        .into(ivPicasso2);

                //以旋转一定角度加载图片
                Picasso.with(PicassoActivity.this)
                        .load("http://n.sinaimg.cn/translate/20160819/9BpA-fxvcsrn8627957.jpg")
                        .rotate(-90) //顺时针旋转90度
                        .into(ivPicasso3);
                break;
            case R.id.btn_listView:
                startActivity(new Intent(PicassoActivity.this, PicassoListViewActivity.class));
                break;
            case R.id.btn_change:
                startActivity(new Intent(PicassoActivity.this, PicassoChangeActivity.class));
                break;
        }
    }
}
