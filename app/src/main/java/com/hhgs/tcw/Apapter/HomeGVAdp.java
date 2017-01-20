package com.hhgs.tcw.Apapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.FrescoIMG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MFH on 2017/1/3 0003.
 */

public class HomeGVAdp extends BaseAdapter {

    Context context;
    private LayoutInflater inflater;
    private List<String> urls;

    public HomeGVAdp(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        urls = new ArrayList<>();
        urls.add(0, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484737283708&di=6552edbd66a2e206ab4f6c00a06df1c5&imgtype=0&src=http%3A%2F%2Fimg.sg560.com%2Fmember_Data%2Fimg201106%2F179289%2F2011062510512095.jpg");
        urls.add(1, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484737333262&di=e6ba281a87c6d64905e8975f1302c15c&imgtype=0&src=http%3A%2F%2Fm.qqzhi.com%2Fupload%2Fimg_0_2489640838D4229286714_23.jpg");
        urls.add(2, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484736504448&di=13b0213eb158fb7ca522c535a12b08fc&imgtype=0&src=http%3A%2F%2Fimg.atobo.com%2FUserFiles%2FCW_UserImage%2FCN%2F9%2F2%2F1%2F3%2F321%2F9213321%2F2015_7_31_16_54_41_7397.jpg");
        urls.add(3, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484736499084&di=a8246e1ef70cea6f11d6c471dd55d403&imgtype=0&src=http%3A%2F%2Fimg13.weikeimg.com%2Fdata%2Fuploads%2F2015%2F09%2F19%2F153324425455fce4848cb18.png");
        urls.add(4, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484737353768&di=57cdbed0f060e55e6f998764dcfdac22&imgtype=0&src=http%3A%2F%2Fimg11.weikeimg.com%2Fdata%2Fuploads%2F2015%2F03%2F22%2F470831751550ec65ab04fd.jpg");
        urls.add(5, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484736714882&di=e2fc2a335c2a3aa1de856e33b303bb44&imgtype=0&src=http%3A%2F%2Fm.qqzhi.com%2Fupload%2Fimg_0_2489640838D4229286714_23.jpg");


    }

    @Override
    public int getCount() {
        return 6;
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

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_head_gv_content, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FrescoIMG.showIMG(urls.get(i), viewHolder.Img);

        return convertView;
    }


    public class ViewHolder {
        public View contentV;
        public SimpleDraweeView Img;

        public ViewHolder(View v) {
            this.contentV = v;
            this.Img = (SimpleDraweeView) v.findViewById(R.id.home_gv_content_img);
        }
    }

}
