package com.lin.glide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;
import com.lin.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        tvTitle.setText("Glide的使用");
    }

    @OnClick({R.id.btn_normal, R.id.btn_load, R.id.btn_transform})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_normal://基本方法的使用
                ActivityUtils.startActivity(GlideActivity.this, GlideBaseActivity.class);
                break;
            case R.id.btn_load://在RecyclerView中加载图片
                ActivityUtils.startActivity(GlideActivity.this, GlideRecyclerViewActivity.class);
                break;
            case R.id.btn_transform://图片变换
                ActivityUtils.startActivity(GlideActivity.this, GlideTransformActivity.class);
                break;
        }
    }
}
