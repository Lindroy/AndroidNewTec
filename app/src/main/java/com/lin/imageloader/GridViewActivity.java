package com.lin.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GridViewActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.gridView)
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        ButterKnife.bind(this);
        tvTitle.setText("在GridView中应用ImageLoader");
        gridView.setAdapter(new ImageLoaderGridViewAdapter(this));

    }

    class ImageLoaderGridViewAdapter extends BaseAdapter {
        private Context context;
        private ImageLoader imageLoader;
        private DisplayImageOptions options;

        public ImageLoaderGridViewAdapter(Context context) {
            this.context = context;
            //初始化ImageLoader
            imageLoader = ImageLoader.getInstance();
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.mipmap.ic_launcher)  // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.mipmap.ic_launcher)       // 设置图片加载或解码过程中发生错误显示的图片
                    .resetViewBeforeLoading(true)               // 设置图片在下载前是否重置，复位
                    .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
                    .imageScaleType(ImageScaleType.EXACTLY)     // 设置图片以如何的编码方式显示
                    .bitmapConfig(Bitmap.Config.RGB_565)        // 设置图片的解码类型
                    .displayer(new FadeInBitmapDisplayer(300))  // 设置图片渐变显示
                    .build();
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
                convertView = View.inflate(context, R.layout.gridview_item, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            imageLoader.displayImage(Constants.IMAGES[position], vh.ivGridView, options);
            vh.tvGridView.setText("图片" + position + 1);
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.iv_gridView)
            ImageView ivGridView;
            @Bind(R.id.tv_gridView)
            TextView tvGridView;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
