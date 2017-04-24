package com.lin.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.mr.androidnewtec.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> datas;

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, datas.get(getLayoutPosition()));
                    }
                }
            });

            //单独对图片设置点击事件
            ivIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "我是图片==" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public RecyclerViewAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 相当于getView方法中View和ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_recyclerview, null);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View itemView = inflater.inflate(R.layout.item_recyclerview,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);
        return new ViewHolder(itemView);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //根据position得到对应的数据
        String data = datas.get(position);
        holder.tvTitle.setText(data);
    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener {

        /**
         * 当RecyclerView某个item被点击的时候回调
         *
         * @param view 点击item的视图
         * @param data 点击得到的数据
         */
        public void onItemClick(View view, String data);

    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 添加数据的方法
     */
    public void addData(int position, String data) {
        datas.add(position, data);
        //刷新适配器，使用的刷新插入的方法
        notifyItemInserted(position);
    }

    /**
     * 删除数据的方法
     */
    public void deleteData(int position) {
        datas.remove(position);
        //刷新适配器
        notifyItemRemoved(position);
    }

}
