package com.lin.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.mr.androidnewtec.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<String> datas;
    private RecyclerViewAdapter adapter;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        tvTitle.setText("RecyclerView的使用");
        //创建数据集合
        initData();
        //设置RecyclerView的适配器
        adapter = new RecyclerViewAdapter(this, datas);
        recyclerView.setAdapter(adapter);

        //设置显示的起始位置
        recyclerView.scrollToPosition(datas.size() / 2);//从中间位置开始显示
        //设置分割线
        recyclerView.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST));
        //item的点击事件
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(RecyclerViewActivity.this, "data==" + data, Toast.LENGTH_SHORT).show();
            }
        });
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());//这是默认的动画，所以加不加的效果是一样的

        //下拉刷新控件
        initRefresh();
    }

    //下拉刷新控件
    private void initRefresh() {
        //设置小圆圈的颜色，可以设置多个颜色来回切换
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_red_light,
                android.R.color.holo_blue_light);
        //设置背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.white));
        //设置滑动距离
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置大小模式
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置下拉刷新的监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //主线程
                        refreshData();
                        //刷新数据
                        adapter.notifyItemRangeChanged(0, 50);
                        recyclerView.scrollToPosition(0);
                        //刷新数据后隐藏下拉刷新控件
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        swipeRefreshLayout.refreshDrawableState();
    }

    private void refreshData() {
        for (int i = 0; i < 50; i++) {
            datas.add(0, "这是新数据" + i);
        }
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("这是数据" + i);
        }
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_list, R.id.btn_grid, R.id.btn_flow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add://添加
                //每次都在第一位的地方添加数据
                adapter.addData(0, "这是一条新数据" + i);
                recyclerView.scrollToPosition(0);
                i++;
                break;
            case R.id.btn_delete://删除
                adapter.deleteData(0);
                break;
            case R.id.btn_list://列表
                recyclerView.setLayoutManager(new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,//方向
                        false));//数据是否反向
                break;
            case R.id.btn_grid://Grid
                recyclerView.setLayoutManager(new GridLayoutManager(this,
                        3, //列数
                        LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.btn_flow://Flow(瀑布流)
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
