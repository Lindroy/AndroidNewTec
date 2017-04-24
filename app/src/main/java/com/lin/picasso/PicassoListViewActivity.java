package com.lin.picasso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;
import com.lin.picasso.adapter.PicassoListViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicassoListViewActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_picasso)
    ListView lvPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_list_view);
        ButterKnife.bind(this);
        tvTitle.setText("使用Picasso在ListView中加载图片");
        lvPicasso.setAdapter(new PicassoListViewAdapter(this));
    }
}
