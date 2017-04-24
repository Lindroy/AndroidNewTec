package com.lin.picasso.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.imageloader.Constants;
import com.lin.mr.androidnewtec.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class PicassoListViewAdapter extends BaseAdapter {
    private Context context;

    public PicassoListViewAdapter(Context context) {
        this.context = context;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_picasso_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //显示名称
        viewHolder.tvPicassoItem.setText("图片" + position + 1);
        //加载图片
        Picasso.with(context)
                .load(Constants.IMAGES[position])
                .placeholder(R.drawable.qq)//设置资源加载过程中的显示的Drawable
                .error(R.drawable.twitter) //设置load失败时显示的Drawable
                .into(viewHolder.ivPicassoItem);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_picasso_item)
        ImageView ivPicassoItem;
        @Bind(R.id.tv_picasso_item)
        TextView tvPicassoItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
