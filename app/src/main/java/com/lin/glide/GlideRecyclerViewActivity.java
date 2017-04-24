package com.lin.glide;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GlideRecyclerViewActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rv_glide)
    RecyclerView rvGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_recycler_view);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        tvTitle.setText("Glide在RecyclerView中加载图片");
        rvGlide.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,//方向
                false));//数据是否反向
        rvGlide.setAdapter(new GlideRecyclerViewAdapter(this));
    }
}
