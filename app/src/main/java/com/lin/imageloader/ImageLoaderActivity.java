package com.lin.imageloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageLoaderActivity extends Activity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_lv)
    Button btnLv;
    @Bind(R.id.btn_gv)
    Button btnGv;
    @Bind(R.id.btn_vp)
    Button btnVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_lv, R.id.btn_gv, R.id.btn_vp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lv:
                startActivity(new Intent(ImageLoaderActivity.this, ListViewActivity.class));
                break;
            case R.id.btn_gv:
                startActivity(new Intent(ImageLoaderActivity.this, GridViewActivity.class));
                break;
            case R.id.btn_vp:
                startActivity(new Intent(ImageLoaderActivity.this, ViewPagerActivity.class));
                break;
        }
    }
}
