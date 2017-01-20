package com.hhgs.tcw.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.hhgs.tcw.Activity.MessageActivity;
import com.hhgs.tcw.Apapter.HomeBaseAdp;
import com.hhgs.tcw.Apapter.HomeGVAdp;
import com.hhgs.tcw.Apapter.HomeVPAdp;
import com.hhgs.tcw.CityList.citylist.CityList;
import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.SP;
import com.hhgs.tcw.Utils.T;
import com.hhgs.tcw.View.SwipLayout;
import com.hhgs.tcw.View.XListView;
import com.jauker.widget.BadgeView;
import com.viewpagerindicator.CirclePageIndicator;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by MFH on 2016/12/26 0026.
 */

public class HomeFragment extends SupportFragment {

    private Context context;


    //主布局及其控件
    private View view;
    @Bind(R.id.city_name)
    TextView cityName;
    @Bind(R.id.searchedittext)
    TextView searchedittext;
    @Bind(R.id.message_lin)
    LinearLayout msgLin;
    @Bind(R.id.my_swip)
    SwipLayout mySwip;
    @Bind(R.id.home_lv)
    XListView homeListview;


    //头部布局及其控件
    View headerview;
    GridView homeGV;
    AutoScrollViewPager homeViewpager;
    CirclePageIndicator indicator;
    View vfview;
    LinearLayout homenewslin;
    ViewFlipper homevf;
    LinearLayout vflin;

    //消息角标数字
    private BadgeView badgeView;

    //数据适配
    HomeBaseAdp homeBaseAdp;
    HomeVPAdp homeVPAdp;
    HomeGVAdp homeGVAdp;

    private boolean canload = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        ButterKnife.bind(this, view);

//        if (view == null) {
//            view = inflater.inflate(R.layout.fragment_home, null);
//        }
//        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
//        ViewGroup parent = (ViewGroup) view.getParent();
//        if (parent != null) {
//            parent.removeView(view);
//        }
        //获取数据并将JSon转实体类
        loadData();

        initView();

        //加载listview
        initLV();

        return view;
    }

    private void loadData() {

        homeBaseAdp = new HomeBaseAdp(getActivity());
        //listview的头部与末尾布局
        head();
    }

    private void head() {
        headerview = LayoutInflater.from(context).inflate(R.layout.home_header, null);
        homeViewpager = (AutoScrollViewPager) headerview.findViewById(R.id.home_viewpager);
        indicator = (CirclePageIndicator) headerview.findViewById(R.id.home_indicator);
        homevf = (ViewFlipper) headerview.findViewById(R.id.home_vf);
        homenewslin = (LinearLayout) headerview.findViewById(R.id.home_news_lin);
        vflin = (LinearLayout) headerview.findViewById(R.id.home_vf_lin);
        homeGV = (GridView) headerview.findViewById(R.id.home_shop_gv);


        //加载顶部轮播viewpager
        homeVPAdp = new HomeVPAdp(getActivity());
        homeViewpager.setAdapter(homeVPAdp);
        //处理左右滑动与上下滑动的冲突
        homeViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mySwip.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mySwip.setEnabled(true);
                        break;
                }
                return false;
            }
        });
        homeViewpager.setInterval(2000);
        homeViewpager.setScrollDurationFactor(3);
        homeViewpager.startAutoScroll();
        homeViewpager.setStopScrollWhenTouch(true);
        homeViewpager.setCycle(true);

        //viewpager指示器
        indicator.setViewPager(homeViewpager);
//        indicator.setPageColor(0x880000FF);
        indicator.setFillColor(getResources().getColor(R.color.theme_color));
        indicator.setStrokeColor(getResources().getColor(R.color.theme_color));


        //滚动新闻
        for (int i = 0; i < 3; i++) {
            vfview = LayoutInflater.from(context).inflate(R.layout.viewfliper_custom, null);
            TextView vfTv = (TextView) vfview.findViewById(R.id.home_vf_tv);
            vfTv.setMaxEms(12);
            vfTv.setSingleLine(true);
            vfTv.setEllipsize(TextUtils.TruncateAt.END);
            vfTv.setText("你快乐吗？");
            vfview.setId(i);
            homevf.addView(vfview);
            vfview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        homevf.setInAnimation(context, R.anim.push_up_in);
        homevf.setOutAnimation(context, R.anim.push_up_out);
        homevf.startFlipping();

        //加载中部推荐企业GridView
        homeGVAdp = new HomeGVAdp(context);
        homeGV.setAdapter(homeGVAdp);

        homeListview.addHeaderView(headerview);
    }

    private void initView() {

        Log.e("SP.get(user_city)", SP.get("user_city"));
        cityName.setText(SP.get("user_city"));

        // 设置下拉刷新时的颜色值,颜色值需要定义在xml中
        mySwip.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_light, android.R.color.holo_red_light);
        mySwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });

//        mySwip.setOnLoadListener(new SwipLayout.OnLoadListener() {
//            @Override
//            public void onLoad() {
//                if (canload == true) {
//                } else {
//                    T.Show("没有更多了");
//                    mySwip.setLoading(false);
//                }
//            }
//        });

        //初始化消息数量并显示在图标右上方
        badgeView = new BadgeView(context);
        badgeView.setTargetView(msgLin);
        badgeView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        badgeView.setBackground(10, getResources().getColor(R.color.tv_Red));
        badgeView.setBadgeCount(5);

    }

    private void initLV() {
        homeListview.setAdapter(homeBaseAdp);
        homeListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null)
            switch (resultCode) {
                case 2:
                    SP.put("user_city", data.getStringExtra("city"));
                    String scity = data.getStringExtra("city");
                    cityName.setText(scity);
                    break;
                default:
                    break;
            }
    }

    @OnClick({R.id.city_name, R.id.searchedittext, R.id.message_lin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.city_name:
                //城市列表  选择城市
                Log.e("我被点击了", "yes");
                Intent intent = new Intent(getActivity(), CityList.class);
                startActivityForResult(intent, 1);
                break;
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
