package com.lin.picasso.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.mr.androidnewtec.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class PicassoChangeAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;

    public PicassoChangeAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();//data可能为空，
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_picass_change, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tvPicasso.setText("图片" + position + 1);

        int integer = Integer.parseInt(data.get(position));

//        switch (integer) {
//
//            case 1: {
//                int width = Utils.dip2px(context, 133.33f);
//                int height = Utils.dip2px(context, 126.33f);
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .resize(width, height)
//                        .centerCrop()
//                        .transform((new MaskTransformation(context, R.drawable.mask_starfish)))
//                        .into(vh.ivPicasso);
//                break;
//            }
//            case 2: {
//                int width = Utils.dip2px(context, 150.0f);
//                int height = Utils.dip2px(context, 100.0f);
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .resize(width, height)
//                        .centerCrop()
//                        .transform(new MaskTransformation(context, R.drawable.qq))
//                        .into(vh.ivPicasso);
//                break;
//            }
//            case 3:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.LEFT,
//                                CropTransformation.GravityVertical.TOP))
//                        .into(vh.ivPicasso);
//                break;
//            case 4:
//                Picasso.with(context).load(R.drawable.demo)
//                        // 300, 100, CropTransformation.GravityHorizontal.LEFT, CropTransformation.GravityVertical.CENTER))
//                        .transform(new CropTransformation(300, 100)).into(vh.ivPicasso);
//                break;
//            case 5:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.LEFT,
//                                CropTransformation.GravityVertical.BOTTOM))
//                        .into(vh.ivPicasso);
//                break;
//            case 6:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.TOP))
//                        .into(vh.ivPicasso);
//                break;
//            case 7:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(300, 100))
//                        .into(vh.ivPicasso);
//                break;
//            case 8:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.BOTTOM))
//                        .into(vh.ivPicasso);
//                break;
//            case 9:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
//                                CropTransformation.GravityVertical.TOP))
//                        .into(vh.ivPicasso);
//                break;
//            case 10:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
//                                CropTransformation.GravityVertical.CENTER))
//                        .into(vh.ivPicasso);
//                break;
//            case 11:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
//                                CropTransformation.GravityVertical.BOTTOM))
//                        .into(vh.ivPicasso);
//                break;
//            case 12:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation((float) 16 / (float) 9,
//                                CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.CENTER))
//                        .into(vh.ivPicasso);
//                break;
//            case 13:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation((float) 4 / (float) 3,
//                                CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.CENTER))
//                        .into(vh.ivPicasso);
//                break;
//            case 14:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(3, CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.CENTER))
//                        .into(vh.ivPicasso);
//                break;
//            case 15:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(3, CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.TOP))
//                        .into(vh.ivPicasso);
//                break;
//            case 16:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation(1, CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.CENTER))
//                        .into(vh.ivPicasso);
//                break;
//            case 17:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
//                                CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.CENTER))
//                        .into(vh.ivPicasso);
//                break;
//            case 18:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
//                                CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.TOP))
//                        .into(vh.ivPicasso);
//                break;
//            case 19:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
//                                CropTransformation.GravityHorizontal.RIGHT,
//                                CropTransformation.GravityVertical.BOTTOM))
//                        .into(vh.ivPicasso);
//                break;
//            case 20:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropTransformation((float) 0.5, 0, (float) 4 / (float) 3,
//                                CropTransformation.GravityHorizontal.CENTER,
//                                CropTransformation.GravityVertical.CENTER))
//                        .into(vh.ivPicasso);
//                break;
//            case 21:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropSquareTransformation())
//                        .into(vh.ivPicasso);
//                break;
//            case 22:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new CropCircleTransformation())
//                        .into(vh.ivPicasso);
//                break;
//            case 23:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0)))
//                        .into(vh.ivPicasso);
//                break;
//            case 24:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new GrayscaleTransformation())
//                        .into(vh.ivPicasso);
//                break;
//            case 25:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new RoundedCornersTransformation(30, 0,
//                                RoundedCornersTransformation.CornerType.BOTTOM_LEFT))
//                        .into(vh.ivPicasso);
//                break;
//            case 26:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new BlurTransformation(context, 25, 1))
//                        .into(vh.ivPicasso);
//                break;
//            case 27:
//                Picasso.with(context)
//                        .load(R.drawable.demo)
//                        .transform(new ToonFilterTransformation(context))
//                        .into(vh.ivPicasso);
//                break;
//            case 28:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new SepiaFilterTransformation(context))
//                        .into(vh.ivPicasso);
//                break;
//            case 29:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new ContrastFilterTransformation(context, 2.0f))
//                        .into(vh.ivPicasso);
//                break;
//            case 30:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new InvertFilterTransformation(context))
//                        .into(vh.ivPicasso);
//                break;
//            case 31:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new PixelationFilterTransformation(context, 20))
//                        .into(vh.ivPicasso);
//                break;
//            case 32:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new SketchFilterTransformation(context))
//                        .into(vh.ivPicasso);
//                break;
//            case 33:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new SwirlFilterTransformation(context, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
//                        .into(vh.ivPicasso);
//
//                break;
//            case 34:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new BrightnessFilterTransformation(context, 0.5f))
//                        .into(vh.ivPicasso);
//                break;
//            case 35:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new KuwaharaFilterTransformation(context, 25))
//                        .into(vh.ivPicasso);
//                break;
//            case 36:
//                Picasso.with(context)
//                        .load(R.drawable.check)
//                        .transform(new VignetteFilterTransformation(context, new PointF(0.5f, 0.5f),
//                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
//                        .into(vh.ivPicasso);
//                break;
//        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_picasso)
        ImageView ivPicasso;
        @Bind(R.id.tv_picasso)
        TextView tvPicasso;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
