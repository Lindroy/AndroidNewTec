package com.lin.pulltorefresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PullToRefreshActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ButterKnife.bind(this);
        tvTitle.setText("PullToRefresh");
    }

    @OnClick({R.id.listview, R.id.gridview, R.id.fragment, R.id.viewpager, R.id.viewpager2, R.id.webview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listview:
                startActivity(new Intent(PullToRefreshActivity.this, PullToRefreshListActivity.class));
                break;
            case R.id.gridview:
                break;
            case R.id.fragment:
                break;
            case R.id.viewpager:
                break;
            case R.id.viewpager2:
                break;
            case R.id.webview:
                break;
        }
    }
}
