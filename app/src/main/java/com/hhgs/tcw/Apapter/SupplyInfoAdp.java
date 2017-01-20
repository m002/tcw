package com.hhgs.tcw.Apapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hhgs.tcw.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by MFH on 2016/11/11 0011.
 */
public class SupplyInfoAdp extends BaseAdapter {
    Context context;
    private LayoutInflater inflater;

    public SupplyInfoAdp(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.supply_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }


    public class ViewHolder {
        public View content_v;
        public TextView infoName, infoStandard, infoUnit, infoBrand, infoMonth, infoFeature, infoAddress;

        public ViewHolder(View v) {
            this.content_v = v;
            this.infoName = (TextView) v.findViewById(R.id.info_name);
            this.infoStandard = (TextView) v.findViewById(R.id.info_guige);
            this.infoUnit = (TextView) v.findViewById(R.id.info_unit);
            this.infoBrand = (TextView) v.findViewById(R.id.info_brand);
            this.infoMonth = (TextView) v.findViewById(R.id.info_month);
            this.infoFeature = (TextView) v.findViewById(R.id.info_feature);
            this.infoAddress = (TextView) v.findViewById(R.id.info_address);
        }
    }
}
