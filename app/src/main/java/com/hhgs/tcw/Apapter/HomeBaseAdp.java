package com.hhgs.tcw.Apapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hhgs.tcw.R;


/**
 * Created by MFH on 2016/12/30 0030.
 */

public class HomeBaseAdp extends BaseAdapter {

    Context context;
    private LayoutInflater inflater;

    public HomeBaseAdp(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 5;
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
            convertView = inflater.inflate(R.layout.home_list_item, parent, false);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public class ViewHolder {
        public View content_v;
        public TextView infoName, infoStandard, infoUnit, infoBrand, infoCount, infoFeature, infoAddress;

        public ViewHolder(View v) {
            this.content_v = v;
            this.infoName = (TextView) v.findViewById(R.id.info_name);
            this.infoStandard = (TextView) v.findViewById(R.id.info_guige);
            this.infoUnit = (TextView) v.findViewById(R.id.info_unit);
            this.infoBrand = (TextView) v.findViewById(R.id.info_brand);
            this.infoCount = (TextView) v.findViewById(R.id.info_count);
            this.infoFeature = (TextView) v.findViewById(R.id.info_feature);
            this.infoAddress = (TextView) v.findViewById(R.id.info_address);
        }
    }

//    public class ViewHolder {
//        public View content_v;
//        public SimpleDraweeView productImg;
//        public TextView productName, productStandard, productUnit, productBrand, productNPrice, productOPrice;
//
//        public ViewHolder(View v) {
//            this.content_v = v;
//            this.productImg = (SimpleDraweeView) v.findViewById(R.id.product_img);
//            this.productName = (TextView) v.findViewById(R.id.product_name_tv);
//            this.productStandard = (TextView) v.findViewById(R.id.product_standard_tv);
//            this.productUnit = (TextView) v.findViewById(R.id.product_unit_tv);
//            this.productBrand = (TextView) v.findViewById(R.id.product_brand_tv);
//            this.productNPrice = (TextView) v.findViewById(R.id.product_now_price_tv);
//            this.productOPrice = (TextView) v.findViewById(R.id.product_original_price_tv);
//        }
//    }

}
