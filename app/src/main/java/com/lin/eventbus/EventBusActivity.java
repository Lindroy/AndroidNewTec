package com.lin.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class EventBusActivity extends Activity implements View.OnClickListener {
    private Button btnSend;
    private Button btnSticky;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        initView();
        //2.注册EventBus
        EventBus.getDefault().register(this);

    }

    private void initView() {
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSticky = (Button) findViewById(R.id.btn_sticky);
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnSend.setOnClickListener(this);
        btnSticky.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send://跳转到发送页面
                startActivity(new Intent(EventBusActivity.this, EventBusSendActivity.class));
                break;
            case R.id.btn_sticky://2.2.发送粘性事件
                EventBus.getDefault().postSticky(new StickyEvent("我是粘性事件"));
                startActivity(new Intent(EventBusActivity.this, EventBusSendActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //3.注销eventBus
        EventBus.getDefault().unregister(this);
    }

    //6.接收消息
    //使用注解表示在主线程中接收
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageReceive(MessageEvent messageEvent) {
        //显示接收的消息
        tvResult.setText(messageEvent.info.toString());
    }
}
