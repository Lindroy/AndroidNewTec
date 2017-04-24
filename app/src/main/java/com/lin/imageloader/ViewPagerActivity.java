package com.lin.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewPagerActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        tvTitle.setText("在ViewPager中应用ImageLoader");
        viewPager.setAdapter(new ImageLoaderViewPagerAdapter(this));
    }

    class ImageLoaderViewPagerAdapter extends PagerAdapter {
        private Context context;
        //初始化ImageLoader
        private ImageLoader imageLoader;
        //初始化配置
        private DisplayImageOptions options;

        public ImageLoaderViewPagerAdapter(Context context) {
            this.context = context;
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
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(context, R.layout.viewpager_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_viewPager);
            imageLoader.displayImage(Constants.IMAGES[position], imageView, options);
            container.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}

