package com.lin.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusSendActivity extends Activity implements View.OnClickListener {
    private Button btnMain;
    private Button btnReceive;
    private TextView tvReceive;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_send);
        initView();
    }

    private void initView() {
        btnMain = (Button) findViewById(R.id.btn_main);
        btnReceive = (Button) findViewById(R.id.btn_receive);
        tvReceive = (TextView) findViewById(R.id.tv_receive);
        btnMain.setOnClickListener(this);
        btnReceive.setOnClickListener(this);
    }

    //2.3.接收粘性事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void StickyReceive(StickyEvent event) {
        tvReceive.setText(event.message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main://发布消息
                EventBus.getDefault().post(new MessageEvent("主线程发送过来的消息"));
                finish();
                break;
            case R.id.btn_receive: //点击接收粘性数据按钮时才注册
                //2.4.注册EventBus
                if (isFirst) { //判断是否是第一次注册
                    EventBus.getDefault().register(this);
                    isFirst = false;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //2.5.解除注册
        EventBus.getDefault().removeAllStickyEvents();//移除所有的粘性事件
        EventBus.getDefault().unregister(this);
    }
}
