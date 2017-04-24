package com.lin.framework.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lin.afinal.AfinalActivity;
import com.lin.application.MyApplication;
import com.lin.eventbus.EventBusActivity;
import com.lin.framework.base.BaseFragment;
import com.lin.glide.GlideActivity;
import com.lin.imageloader.ImageLoaderActivity;
import com.lin.json.FastJsonActivity;
import com.lin.json.GsonActivity;
import com.lin.json.NativeJsonParseActivity;
import com.lin.mr.androidnewtec.R;
import com.lin.okhttp.OKHttpActivity;
import com.lin.picasso.PicassoActivity;
import com.lin.pulltorefresh.PullToRefreshActivity;
import com.lin.recyclerview.RecyclerViewActivity;
import com.lin.volley.VolleyActivity;
import com.lin.xutils3.annotation.Xutils3Activity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 常用框架Fragment
 */
public class CommonFrameFragment extends BaseFragment {
    public static final String NAME = CommonFrameFragment.class.getSimpleName();
    String[] items = {"Afinal", "NativeJsonParse", "Gson", "FastJson", "xUtils3", "EventBus", "ImageLoader", "Volley", "Picasso",
            "OKHttp", "RecyclerView", "PullToRefresh", "Glide"};
    @Bind(R.id.listView)
    ListView listView;


    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_frame_work, null);
        ButterKnife.bind(this, view);
        listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.list_item_main,
                R.id.tv_main, items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (items[position].toString()) {
                    case "Afinal":
                        startActivity(new Intent(MyApplication.getContext(), AfinalActivity.class));
                        break;
                    case "NativeJsonParse":
                        startActivity(new Intent(MyApplication.getContext(), NativeJsonParseActivity.class));
                        break;
                    case "Gson":
                        startActivity(new Intent(MyApplication.getContext(), GsonActivity.class));
                        break;
                    case "FastJson":
                        startActivity(new Intent(MyApplication.getContext(), FastJsonActivity.class));
                        break;
                    case "EventBus":
                        startActivity(new Intent(MyApplication.getContext(), EventBusActivity.class));
                        break;
                    case "ImageLoader":
                        startActivity(new Intent(MyApplication.getContext(), ImageLoaderActivity.class));
                        break;
                    case "Volley":
                        startActivity(new Intent(MyApplication.getContext(), VolleyActivity.class));
                        break;
                    case "xUtils3":
                        startActivity(new Intent(MyApplication.getContext(), Xutils3Activity.class));
                        break;
                    case "Picasso":
                        startActivity(new Intent(MyApplication.getContext(), PicassoActivity.class));
                        break;
                    case "OKHttp":
                        startActivity(new Intent(MyApplication.getContext(), OKHttpActivity.class));
                        break;
                    case "RecyclerView":
                        startActivity(new Intent(MyApplication.getContext(), RecyclerViewActivity.class));
                        break;
                    case "PullToRefresh":
                        startActivity(new Intent(MyApplication.getContext(), PullToRefreshActivity.class));
                        break;
                    case "Glide":
                        startActivity(new Intent(MyApplication.getContext(), GlideActivity.class));
                        break;
                }
            }
        });
        Log.e("TAG", "常用框架初始化了");
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
