package com.lin.glide;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.lin.mr.androidnewtec.R;
import com.lin.picasso.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * 要使用Glide的图片转换效果，需要导入下面的库
 * compile 'jp.wasabeef:glide-transformations:2.0.1'
 * // If you want to use the GPU Filters
 * compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.3.0'
 */
public class GlideTransformAdapter extends RecyclerView.Adapter<GlideTransformAdapter.ViewHolder> {

    private Context context;
    private List<String> datas = new ArrayList<>();

    public GlideTransformAdapter(Context context) {
        this.context = context;
        for (int i = 1; i <= 21; i++) {
            datas.add(i + "");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_glide_tranformations, null);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_glide_tranfromations)
        ImageView ivGlideTranfromations;
        @Bind(R.id.tv_glide_name)
        TextView tvGlideName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //设置图片的标题
        holder.tvGlideName.setText("这是图片" + (position + 1));
        int integer = Integer.parseInt(datas.get(position));
        switch (integer) {
            case 1: {
                int width = Utils.dip2px(context, 133.33f);
                int height = Utils.dip2px(context, 126.33f);
                Glide.with(context)
                        .load(R.drawable.check)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(context),
                                new MaskTransformation(context, R.drawable.mask_starfish))
                        .into(holder.ivGlideTranfromations);
                break;
            }
            case 2: {
                int width = Utils.dip2px(context, 150.0f);
                int height = Utils.dip2px(context, 100.0f);
                Glide.with(context)
                        .load(R.drawable.check)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(context),
                                new MaskTransformation(context, R.drawable.mask_chat_right))
                        .into(holder.ivGlideTranfromations);
                break;
            }
            case 3:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .bitmapTransform(
                                new CropTransformation(context, 300, 100, CropTransformation.CropType.TOP))
                        .into(holder.ivGlideTranfromations);
                break;
            case 4:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropTransformation(context, 300, 100))
                        .into(holder.ivGlideTranfromations);
                break;
            case 5:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .bitmapTransform(
                                new CropTransformation(context, 300, 100, CropTransformation.CropType.BOTTOM))
                        .into(holder.ivGlideTranfromations);

                break;
            case 6:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropSquareTransformation(context))
                        .into(holder.ivGlideTranfromations);
                break;
            case 7: //圆形头像
                Glide.with(context)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropCircleTransformation(context))
                        .into(holder.ivGlideTranfromations);
                break;
            case 8:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .bitmapTransform(new ColorFilterTransformation(context, Color.argb(80, 255, 0, 0)))
                        .into(holder.ivGlideTranfromations);
                break;
            case 9:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .bitmapTransform(new GrayscaleTransformation(context))
                        .into(holder.ivGlideTranfromations);
                break;
            case 10:
                Glide.with(context)
                        .load(R.drawable.demo)
//                RoundedCornersTransformation.CornerType.BOTTOM
                        .bitmapTransform(new RoundedCornersTransformation(context, Utils.dip2px(context, 6), 0))
                        .into(holder.ivGlideTranfromations);
                break;
            case 11:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new BlurTransformation(context, 25))
                        .into(holder.ivGlideTranfromations);
                break;
            case 12:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .bitmapTransform(new ToonFilterTransformation(context))
                        .into(holder.ivGlideTranfromations);
                break;
            case 13:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new SepiaFilterTransformation(context))
                        .into(holder.ivGlideTranfromations);
                break;
            case 14:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new ContrastFilterTransformation(context, 2.0f))
                        .into(holder.ivGlideTranfromations);
                break;
            case 15:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new InvertFilterTransformation(context))
                        .into(holder.ivGlideTranfromations);
                break;
            case 16:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new PixelationFilterTransformation(context, 20))
                        .into(holder.ivGlideTranfromations);
                break;
            case 17:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new SketchFilterTransformation(context))
                        .into(holder.ivGlideTranfromations);
                break;
            case 18:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(
                                new SwirlFilterTransformation(context, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .into(holder.ivGlideTranfromations);
                break;
            case 19:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new BrightnessFilterTransformation(context, 0.5f))
                        .into(holder.ivGlideTranfromations);
                break;
            case 20:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new KuwaharaFilterTransformation(context, 25))
                        .into(holder.ivGlideTranfromations);
                break;
            case 21:
                Glide.with(context)
                        .load(R.drawable.check)
                        .bitmapTransform(new VignetteFilterTransformation(context, new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
                        .into(holder.ivGlideTranfromations);
                break;
            default:
                break;
        }
    }
}
