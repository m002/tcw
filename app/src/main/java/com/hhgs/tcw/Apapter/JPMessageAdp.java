package com.hhgs.tcw.Apapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hhgs.tcw.Model.JPMessage;
import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.T;

import java.util.List;

/**
 * Created by MFH on 2016/11/28 0028.
 */

public class JPMessageAdp extends BaseAdapter {

    private Context context;
    private List<JPMessage> datalist;
    private LayoutInflater inflater;


    public JPMessageAdp(Context context, List<JPMessage> datalist) {
        this.context = context;
        this.datalist = datalist;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (datalist.size() == 0) {
            return 1;
        }
        return datalist.size();
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

        if (datalist.size() == 0) {
            Log.e("datalist长度为0", "yes");
            View v = LayoutInflater.from(context).inflate(R.layout.myorder_nocontent, null);
            TextView tv = (TextView) v.findViewById(R.id.define_tv);
            tv.setText("您还没有任何消息");
            tv.setTextColor(context.getResources().getColor(R.color.hui));
            return v;
        }
        Log.e("datalist长度为0", "no");
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.msg_lv_content, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.msgTitle.setText(datalist.get(position).getTitle());
        viewHolder.msgContent.setText(datalist.get(position).getMsg());
        viewHolder.msgDate.setText(T.convert(datalist.get(position).getDate()));
        return convertView;
    }

    public class ViewHolder {
        public View contentV;
        public TextView msgTitle, msgContent, msgDate;

        public ViewHolder(View v) {
            this.contentV = v;
            this.msgTitle = (TextView) v.findViewById(R.id.msg_title);
            this.msgContent = (TextView) v.findViewById(R.id.msg_content);
            this.msgDate = (TextView) v.findViewById(R.id.msg_date);
        }
    }
}
