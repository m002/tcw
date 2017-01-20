package com.hhgs.tcw.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhgs.tcw.Activity.MessageActivity;
import com.hhgs.tcw.Apapter.HomeBaseAdp;
import com.hhgs.tcw.Apapter.HomeGVAdp;
import com.hhgs.tcw.Apapter.HomeVPAdp;
import com.hhgs.tcw.Apapter.ShopAdp;
import com.hhgs.tcw.CityList.citylist.CityList;
import com.hhgs.tcw.R;
import com.hhgs.tcw.View.SwipLayout;
import com.hhgs.tcw.View.XListView;
import com.jauker.widget.BadgeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by MFH on 2016/12/26 0026.
 */

public class ShopFragment extends SupportFragment {

    private Context context;

    //主布局及其控件
    @Bind(R.id.searchedittext)
    TextView searchTV;
    @Bind(R.id.message_lin)
    LinearLayout msgLin;
    @Bind(R.id.my_swip)
    SwipLayout mySwip;
    @Bind(R.id.shop_lv)
    XListView shopListview;

    //数据适配
    ShopAdp shopAdp;
    //消息角标数字
    private BadgeView badgeView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        context = getActivity();
        ButterKnife.bind(this, view);

        //获取数据并将JSon转实体类
        loadData();

        initView();

        //加载listview
        initLV();

        return view;
    }


    private void loadData() {
        shopAdp = new ShopAdp(getActivity());
    }

    private void initView() {
        //初始化消息数量并显示在图标右上方
        badgeView = new BadgeView(context);
        badgeView.setTargetView(msgLin);
        badgeView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        badgeView.setBackground(10, getResources().getColor(R.color.tv_Red));
        badgeView.setBadgeCount(5);
    }

    private void initLV() {
        shopListview.setAdapter(shopAdp);
        shopListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    public static ShopFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @OnClick({R.id.searchedittext, R.id.message_lin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchedittext:
                break;
            case R.id.message_lin:
                Intent msgInt = new Intent(getActivity(), MessageActivity.class);
                startActivity(msgInt);
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}