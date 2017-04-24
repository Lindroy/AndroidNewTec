package com.lin.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lin.json.bean.DataInfo;
import com.lin.json.bean.FilmInfo;
import com.lin.json.bean.ShopInfo;
import com.lin.mr.androidnewtec.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手动解析JSon
 * （1）将json格式的字符串{}转换为Java对象
 * （2）将json格式的字符串[]转换为Java对象的集合
 * （3）复杂json数据解析
 * （4）特殊json数据解析
 */
public class NativeJsonParseActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_original)
    TextView tvOriginal;
    @Bind(R.id.tv_last)
    TextView tvLast;
    private ShopInfo shopInfo;
    private ArrayList<ShopInfo> shopInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_json_parse);
        ButterKnife.bind(this);
        tvTitle.setText("手动解析Json数据");
        shopInfos = new ArrayList<>();
    }

    @OnClick({R.id.btn_toJavaObject, R.id.btn_toJavaList, R.id.btn_complex, R.id.btn_special})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toJavaObject://将json格式的字符串{}转换为Java对象
                jsonToJavaObjectByNative();
                break;
            case R.id.btn_toJavaList://将json格式的字符串[]转换为Java对象的集合
                jsonToJavaListByNative();
                break;
            case R.id.btn_complex:
                jsonToJavaComplex();//复杂json数据解析
                break;
            case R.id.btn_special:
                jsonToJavaOfSpecial();//解析特殊的Json数据
                break;
        }
    }

    private void jsonToJavaObjectByNative() {
        //1.获取或创建json数据
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}\n";
        //2.解析json数据
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            /*
            optXxx方法会在对应的key中的值不存在的时候返回
            一个空字符串或者返回你指定的默认值，但是getString方法会出现空指针异常的错误。
             */
            int id = jsonObject.optInt("id");
            String name = jsonObject.optString("name");
            double price = jsonObject.optDouble("price");
            String imagePath = jsonObject.optString("imagePath");
            //封装Java对象
            shopInfo = new ShopInfo(id, name, price, imagePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //显示原始的Json数据
        tvOriginal.setText(json);
        //显示解析后的Json数据
        tvLast.setText(shopInfo.toString());


    }

    //将json格式的字符串[]转换为Java对象的集合
    private void jsonToJavaListByNative() {
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

        //2.解析json数据
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object != null) {
                    int id = object.optInt("id");
                    String name = object.optString("name");
                    double price = object.optDouble("price");
                    String imagePath = object.optString("imagePath");
                    //封装Java对象
                    shopInfo = new ShopInfo(id, name, price, imagePath);
                    //将解析后的数据添加到集合
                    shopInfos.add(shopInfo);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //显示原始的Json数据
        tvOriginal.setText(json);
        //显示解析后的Json数据
        tvLast.setText(shopInfos.toString());

    }

    //复杂json数据解析
    private void jsonToJavaComplex() {
        //1.获取或创建json数据
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"count\": 5,\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"坚果\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 132,\n" +
                "                \"title\": \"炒货\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 166,\n" +
                "                \"title\": \"蜜饯\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 195,\n" +
                "                \"title\": \"果脯\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 196,\n" +
                "                \"title\": \"礼盒\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"rs_code\": \"1000\",\n" +
                "    \"rs_msg\": \"success\"\n" +
                "}";

        //封装Java对象
        DataInfo dataInfo = new DataInfo();

        //2.解析json数据
        try {
            JSONObject jsonObject = new JSONObject(json);
            //第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            String rs_code = jsonObject.optString("rs_code");
            String rs_msg = jsonObject.optString("rs_msg");

            //第一层的封装
            dataInfo.setRs_code(rs_code);
            dataInfo.setRs_msg(rs_msg);
            DataInfo.DataBean dataBean = new DataInfo.DataBean();
            dataInfo.setData(dataBean);

            //第二层解析
            int count = data.optInt("count");
            JSONArray items = data.optJSONArray("items");

            //第二层数据的封装
            dataBean.setCount(count);
            List<DataInfo.DataBean.ItemsBean> itemsBean = new ArrayList<>();
            dataBean.setItems(itemsBean);

            //第三层解析
            for (int i = 0; i < items.length(); i++) {
                JSONObject jsonObject1 = items.optJSONObject(i);
                if (jsonObject1 != null) {
                    int id = jsonObject1.optInt("id");
                    String title = jsonObject1.optString("title");

                    //第三层数据的封装
                    DataInfo.DataBean.ItemsBean bean = new DataInfo.DataBean.ItemsBean();
                    bean.setId(id);
                    bean.setTitle(title);
                    itemsBean.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //显示Json数据
        tvOriginal.setText(json);
        tvLast.setText(dataInfo.toString());
    }

    //解析特殊的Json数据
    private void jsonToJavaOfSpecial() {
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"list\": {\n" +
                "        \"0\": {\n" +
                "            \"aid\": \"6008965\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 170,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:34\"\n" +
                "        },\n" +
                "        \"1\": {\n" +
                "            \"aid\": \"6008938\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 404,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:33\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        //创建封装的Java对象
        FilmInfo filmInfo = new FilmInfo();
        //2.解析json
        try {
            JSONObject jsonObject = new JSONObject(json);
            //第一层解析
            int code = jsonObject.optInt("code");
            JSONObject list = jsonObject.optJSONObject("list");

            //第一层封装
            filmInfo.setCode(code);
            List<FilmInfo.FilmBean> lists = new ArrayList<>();
            filmInfo.setList(lists);

            //第二层解析
            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonObject1 = list.optJSONObject(i + "");
                if (jsonObject1 != null) {
                    String aid = jsonObject1.optString("aid");
                    String author = jsonObject1.optString("author");
                    int coins = jsonObject1.optInt("coins");
                    String copyright = jsonObject1.optString("copyright");
                    String create = jsonObject1.optString("create");

                    //第二层数据封装
                    FilmInfo.FilmBean filmBean = new FilmInfo.FilmBean();
                    filmBean.setAid(aid);
                    filmBean.setAuthor(author);
                    filmBean.setCoins(coins);
                    filmBean.setCopyright(copyright);
                    filmBean.setCreate(create);
                    lists.add(filmBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //显示Json数据
        tvOriginal.setText(json);
        tvLast.setText(filmInfo.toString());
    }
}
