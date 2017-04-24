package com.lin.xutils3.annotation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.mr.androidnewtec.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.fragment_xutils)
public class XutilsFragment extends Fragment {

    @ViewInject(R.id.btn_fragment)
    private Button button;
    @ViewInject(R.id.tv_text)
    private TextView textView;

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container); //跟在Activity中的注解有所不同
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "我是Fragment中的按钮", Toast.LENGTH_SHORT).show();
            }
        });


        textView.setText("我在Fragment中初始化了");

    }
}
