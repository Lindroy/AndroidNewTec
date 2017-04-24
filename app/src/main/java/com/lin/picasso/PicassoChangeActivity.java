package com.lin.picasso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;
import com.lin.picasso.adapter.PicassoChangeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicassoChangeActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_picasso)
    ListView lvPicasso;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_change);
        ButterKnife.bind(this);
        initData();
        lvPicasso.setAdapter(new PicassoChangeAdapter(this, data));
    }

    private void initData() {
        tvTitle.setText("Picasso的转换操作");
        data = new ArrayList<>();
        for (int i = 1; i <= 36; i++) {
            data.add(i + "");
        }
    }
}
