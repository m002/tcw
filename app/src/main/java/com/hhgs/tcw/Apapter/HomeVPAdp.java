package com.hhgs.tcw.Apapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.FrescoIMG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MFH on 2016/12/30 0030.
 */

public class HomeVPAdp extends PagerAdapter {

    Context context;
    private SimpleDraweeView img;
    private LayoutInflater inflater;
    private List<View> views;
    private List<String> urls, desps;
    private TextView tv;

    public HomeVPAdp(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        //根据datas的长度确定viewpager页数
        views = new ArrayList<View>();
        for (int i = 0; i < 4; i++) {
            View v = inflater.inflate(R.layout.home_vp_content, null);
            views.add(v);
        }

        urls = new ArrayList<>();
        desps = new ArrayList<>();
        urls.add(0, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484735971406&di=ad658b66b647518e822e94ada435c8a1&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F26%2F50%2F21b58PICUYv.jpg");
        urls.add(1, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484736161585&di=a656690a009427dd757e981c63e92af2&imgtype=0&src=http%3A%2F%2Fwww.cscec4b.com.cn%2Fnz_web%2FUploadFile%2F2012827131732985.jpg");
        urls.add(2, "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=162020392,3913430866&fm=23&gp=0.jpg");
        urls.add(3, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484736367310&di=92df89bb531e01b71c4c6959965271a9&imgtype=0&src=http%3A%2F%2Fpic1.cxtuku.com%2F00%2F13%2F74%2Fb838090da752.jpg");

        desps.add(0, "西宁海湖新区xx项目近日招标采购建材");
        desps.add(1, "西宁xx安置项目近日招标采购建材");
        desps.add(2, "格尔木xx项目近日招标采购建材");
        desps.add(3, "西宁xx项目近日招标采购建材");

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        img = (SimpleDraweeView) views.get(position).findViewById(R.id.home_web_img);
        tv = (TextView) views.get(position).findViewById(R.id.home_top_adv_tv);
        FrescoIMG.showIMG(urls.get(position), img);
        tv.setText(desps.get(position));
        return views.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
