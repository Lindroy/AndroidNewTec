package com.lin.framework.fragment;


import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lin.framework.base.BaseFragment;

/**
 * 第三方框架
 */
public class ThirdPartyFragment extends BaseFragment {
    private static final String NAME = ThirdPartyFragment.class.getSimpleName();
    private TextView textView;

    @Override
    protected View initView() {
        Log.e("TAG", "第三方框架初始化了");
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
