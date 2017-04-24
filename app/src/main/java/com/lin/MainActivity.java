package com.lin;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lin.framework.base.BaseFragment;
import com.lin.framework.fragment.CommonFrameFragment;
import com.lin.framework.fragment.CustomFragment;
import com.lin.framework.fragment.OtherFragment;
import com.lin.framework.fragment.ThirdPartyFragment;
import com.lin.mr.androidnewtec.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 1.每次切换Fragment时都会初始化Fragment，造成资源的浪费。解决的方法是使用add或者show方法而不是replace，这样切换掉的Fragment就可以
 * 暂时隐藏起来，需要显示时再显示，不必再次初始化；
 * 2.横竖屏切换会重新调用Activity的生命周期，使Fragment出现重影，为了避免这个问题可以在清单文件中进行配置，使Activity在某些情况下不调用生命周期。
 */
public class MainActivity extends FragmentActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rb_common_frame)
    RadioButton rbCommonFrame;
    @Bind(R.id.rb_thirdParty)
    RadioButton rbThirdParty;
    @Bind(R.id.rb_custom)
    RadioButton rbCustom;
    @Bind(R.id.rb_other)
    RadioButton rbOther;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    //Fragment的集合
    private List<BaseFragment> fragmentList;
    //被选中的fragment的position
    int position;

    //上次切换的Fragment
    private BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化Fragment
        initFragment();
        //默认显示第一个Fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_content, fragmentList.get(0)).commit();
        tvTitle.setText("常用框架");
        currentFragment = fragmentList.get(0);//将第一个Fragment记录为当前的Fragment
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new CommonFrameFragment());//常用框架
        fragmentList.add(new ThirdPartyFragment());//第三方
        fragmentList.add(new CustomFragment());//自定义
        fragmentList.add(new OtherFragment());//其他
    }


    @OnClick({R.id.rb_thirdParty, R.id.rb_custom, R.id.rb_other, R.id.rb_common_frame})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_common_frame:
                position = 0;
                tvTitle.setText("常用框架");
                break;
            case R.id.rb_thirdParty:
                position = 1;
                tvTitle.setText("第三方");
                break;
            case R.id.rb_custom:
                position = 2;
                tvTitle.setText("自定义");
                break;
            case R.id.rb_other:
                position = 3;
                tvTitle.setText("其他");
                break;
        }

        //获取position对应的Fragment
        BaseFragment nextFragment = getFragment();
        //切换Fragment
        switchFragment(currentFragment, nextFragment);
    }

    /**
     * @param from:当前显示的Fragment
     * @param to:要跳转到的Fragment
     */
    private void switchFragment(BaseFragment from, BaseFragment to) {
        if (from != to) { //如需要跳转的Fragment跟当前的Fragment相同，则无需切换
            currentFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { //没有添加
                //隐藏from
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl_content, to).commit();
                }

            } else {//to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    //to已经添加过了，只需show即可
                    ft.show(to).commit();
                }
            }
        }
    }

//    private void switchFragment(BaseFragment fragment) {
//        //1.得到FragmentManager
//        FragmentManager fm = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction ft = fm.beginTransaction();
//        //3.替换
//        ft.replace(R.id.fl_content,fragment);
//        //4.提交事务
//        ft.commit();
//    }

    private BaseFragment getFragment() {
        BaseFragment fragment = fragmentList.get(position);
        return fragment;
    }
}
