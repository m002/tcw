package com.hhgs.tcw.Apapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hhgs.tcw.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

/**
 * Created by MFH on 2017/1/4 0004.
 */

public class ShopAdp extends BaseAdapter {

    Context context;
    private LayoutInflater inflater;

    public ShopAdp(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.shop_list_item, parent, false);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        //关注店铺
        final ViewHolder finalViewholder = viewholder;
        viewholder.concernLBT.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                finalViewholder.concernTV.setText("已关注");
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                finalViewholder.concernTV.setText("关注");
            }
        });

        return convertView;
    }

    public class ViewHolder {
        public View content_v;
        public SimpleDraweeView shopImg;
        public ImageView aptitudeImg;
        public LikeButton concernLBT;
        public TextView shopNameTV, manageWayTV, noaptitudeTV, addressTV, mainProductTV, concernTV;

        public ViewHolder(View v) {
            this.content_v = v;
            this.shopImg = (SimpleDraweeView) v.findViewById(R.id.shop_img);
            this.aptitudeImg = (ImageView) v.findViewById(R.id.aptitude_img);
            this.concernLBT = (LikeButton) v.findViewById(R.id.concern_button);
            this.shopNameTV = (TextView) v.findViewById(R.id.shop_name);
            this.manageWayTV = (TextView) v.findViewById(R.id.manageWay_tv);
            this.noaptitudeTV = (TextView) v.findViewById(R.id.noaptitude_tv);
            this.addressTV = (TextView) v.findViewById(R.id.address_tv);
            this.mainProductTV = (TextView) v.findViewById(R.id.main_product_tv);
            this.concernTV = (TextView) v.findViewById(R.id.concern_tv);
        }
    }

}
