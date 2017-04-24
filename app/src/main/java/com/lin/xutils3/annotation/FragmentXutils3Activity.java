package com.lin.xutils3.annotation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_fragment)
public class FragmentXutils3Activity extends android.support.v4.app.FragmentActivity {

    @ViewInject(R.id.tv_title)
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        tvTitle.setText("在Fragment使用xUtils");

        //1.得到FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //3.替换Fragment
        ft.replace(R.id.fl_content, new XutilsFragment(), null);
        //4.提交事务
        ft.commit();
    }
}
