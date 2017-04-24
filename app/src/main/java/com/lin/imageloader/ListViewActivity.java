package com.lin.imageloader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListViewActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listView)
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
        tvTitle.setText("在ListView中应用ImageLoader");
        listView.setAdapter(new ImageLoaderListViewAdapter(this));
    }


    class ImageLoaderListViewAdapter extends BaseAdapter {
        private Context context;

        //初始化ImageLoader
        private ImageLoader imageLoader;
        //初始化配置
        private DisplayImageOptions options;

        public ImageLoaderListViewAdapter(Context context) {
            this.context = context;
            imageLoader = ImageLoader.getInstance();
            options = new DisplayImageOptions.Builder()
                    .showStubImage(R.mipmap.ic_launcher)          // 设置图片下载期间显示的图片
                    .showImageForEmptyUri(R.mipmap.ic_launcher)  // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.mipmap.ic_launcher)       // 设置图片加载或解码过程中发生错误显示的图片
                    .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true)                          // 设置下载的图片是否缓存在SD卡中
                    .displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                    .build();                                   // 创建配置过得DisplayImageOption对象
        }

        @Override
        public int getCount() {
            return Constants.IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return Constants.IMAGES[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.list_item, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tvListView.setText("图片" + position + 1);

            imageLoader.displayImage(Constants.IMAGES[position], vh.ivListView, options);
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.iv_listView)
            ImageView ivListView;
            @Bind(R.id.tv_listView)
            TextView tvListView;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    static class ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.listView)
        ListView listView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
