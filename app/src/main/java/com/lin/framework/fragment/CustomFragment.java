package com.lin.framework.fragment;


import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lin.framework.base.BaseFragment;

/**
 * 自定义
 */
public class CustomFragment extends BaseFragment {
    private static final String NAME = CustomFragment.class.getSimpleName();
    private TextView textView;

    @Override
    protected View initView() {
        Log.e("TAG", "自定义初始化了");
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(20);
        return textView;
    }

    @Override
    protected void initData() {
        textView.setText(NAME);
    }
}
