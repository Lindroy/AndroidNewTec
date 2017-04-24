package com.lin.glide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lin.mr.androidnewtec.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class GlideRecyclerViewAdapter extends RecyclerView.Adapter<GlideRecyclerViewAdapter.ViewHolder> {

    private Context context;

    /**
     * 网络数据链接
     *
     * @param context
     */
    String[] datas = new String[]{
            "http://b337.photo.store.qq.com/psb?/V10FcMmY1Ttz2o/7.fo01qLQ*SI59*E2Wq.j82HuPfes*efgiyEi7mrJdk!/b/dLHI5cioAQAA&bo=VQOAAgAAAAABB*Q!&rf=viewer_4",
            "http://b118.photo.store.qq.com/psb?/V10FcMmY2gHuOI/8*6eK6PHCNTx1utXooId*KAWgwPTllj.b6uBg4McCwM!/b/dAt8W0YJJAAA&bo=VQOAAgAAAAABB*Q!&rf=viewer_4",
            "http://img1.imgtn.bdimg.com/it/u=488611129,2377736106&fm=11&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3398443685,2594061265&fm=11&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2271902832,1324672617&fm=21&gp=0.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D200/sign=d20242020e24ab18ff16e63705fae69a/267f9e2f070828389f547b30bf99a9014c08f1bd.jpg",
            "http://img5.duitang.com/uploads/item/201406/28/20140628132554_UNE4n.thumb.700_0.jpeg",
            "http://cdn.duitang.com/uploads/item/201309/22/20130922202150_ntvAB.thumb.600_0.jpeg",
            "http://cdn.duitang.com/uploads/item/201208/04/20120804013554_yRGfe.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=2050390856,2980742959&fm=21&gp=0.jpg",
            "http://img3.duitang.com/uploads/item/201501/23/20150123204322_N8nw5.jpeg",
            "http://img4q.duitang.com/uploads/item/201505/09/20150509204813_nEwxF.jpeg",
            "http://img1.imgtn.bdimg.com/it/u=2432702027,3704029716&fm=21&gp=0.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com1111/Z3QjilA.jpg"
    };

    public GlideRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_glide_recyclerview, null);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //获取当前数据

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, context.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, context.getResources().getDisplayMetrics());

        Glide.with(context)
                .load(datas[position])
                .placeholder(R.mipmap.ic_launcher) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
//                .animate(R.anim.glide_anim)
                .centerCrop()
                .fitCenter()
                .into(holder.ivGlideRecyclerview);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_glide_recyclerview)
        ImageView ivGlideRecyclerview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
