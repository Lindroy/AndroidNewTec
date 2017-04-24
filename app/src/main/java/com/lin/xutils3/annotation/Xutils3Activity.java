package com.lin.xutils3.annotation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.mr.androidnewtec.R;
import com.lin.xutils3.net.Xutils3NetActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_xutils3) //加载布局的写法
public class Xutils3Activity extends Activity {
    @ViewInject(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xutils3);
        x.view().inject(this);

        //设置标题
        tvTitle.setText("xUtils3的使用");
    }

    /**
     * Button点击事件
     *
     * @param v
     */
    @Event(value = {R.id.btn_annotation, R.id.btn_net, R.id.btn_image, R.id.btn_image_list})
    private void clickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_annotation:
//                Toast.makeText(this, "注解模块被点击", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Xutils3Activity.this, FragmentXutils3Activity.class));
                break;
            case R.id.btn_net:
                Toast.makeText(this, "联网模块被点击", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Xutils3Activity.this, Xutils3NetActivity.class));
                break;
            case R.id.btn_image:
                Toast.makeText(this, "加载图片被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_image_list:
                Toast.makeText(this, "加载图片列表被点击", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
