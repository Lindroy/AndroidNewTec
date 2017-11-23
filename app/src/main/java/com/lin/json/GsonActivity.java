package com.lin.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lin.json.bean.ShopInfo;
import com.lin.mr.androidnewtec.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GsonActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_original)
    TextView tvOriginal;
    @Bind(R.id.tv_last)
    TextView tvLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_toJavaObject, R.id.btn_toJavaList, R.id.btn_javaToJsonObject, R.id.btn_javaToJsonArray})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toJavaObject://将json格式的字符串{}转换为Java对象
                jsonToJavaObjectByGson();
                break;
            case R.id.btn_toJavaList:
                jsonToJavaListByGson(); //将json格式的字符串[]转换为Java对象的List
                break;
            case R.id.btn_javaToJsonObject://将Java对象转换为json字符串{}
                javaToJsonObjectByGson();
                break;
            case R.id.btn_javaToJsonArray:
                javaToJsonArrayByGson(); //将Java对象的List转换为json字符串[]
                break;
        }
    }

    //将json格式的字符串{}转换为Java对象
    private void jsonToJavaObjectByGson() {
        //1.获取或创建Java对象
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}\n";
        //2.使用Gson进行解析
        Gson gson = new Gson();
        ShopInfo shopInfo = gson.fromJson(json, ShopInfo.class);


        //显示原始的Json数据
        tvOriginal.setText(json);
        //显示解析后的Json数据
        tvLast.setText(shopInfo.toString());
    }

    //将json格式的字符串[]转换为Java对象的List
    private void jsonToJavaListByGson() {
        //1.获取或创建json数据
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f1.jpg\",\n" +
                "        \"name\": \"大虾1\",\n" +
                "        \"price\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f2.jpg\",\n" +
                "        \"name\": \"大虾2\",\n" +
                "        \"price\": 12.5\n" +
                "    }\n" +
                "]";

        //2.解析Json数据
        Gson gson = new Gson();
        List<ShopInfo> shops = gson.fromJson(json, new TypeToken<List<ShopInfo>>() {
        }.getType());

        //3.展示数据
        //显示原始的Json数据
        tvOriginal.setText(json);
        //显示解析后的Json数据
        tvLast.setText(shops.toString());
    }

    //将Java对象转换为json字符串{}
    private void javaToJsonObjectByGson() {
        //1.获取或创建Java对象
        ShopInfo shopInfo = new ShopInfo(1, "菠菜", 250.0, "bocai");

        //2.生成json数据
        Gson gson = new Gson();
        String json = gson.toJson(shopInfo);

        //3.展示数据
        //显示原始的数据
        tvOriginal.setText(shopInfo.toString());
        //显示转换后的Json数据
        tvLast.setText(json);
    }

    //将Java对象的List转换为json字符串[]
    private void javaToJsonArrayByGson() {
        //1.获取或创建Java对象
        List<ShopInfo> shops = new ArrayList<>();
        ShopInfo paiGu = new ShopInfo(1, "排骨", 25.0, "paigu");
        ShopInfo bocai = new ShopInfo(1, "菠菜", 5.0, "bocai");
        shops.add(paiGu);
        shops.add(bocai);

        //2.生成JSON数据
        Gson gson = new Gson();
        String json = gson.toJson(shops);

        //3.展示数据
        //显示原始的数据
        tvOriginal.setText(shops.toString());
        //显示转换后的Json数据
        tvLast.setText(json);
    }

}
