package com.hhgs.tcw.Activity;

import android.graphics.Color;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.hhgs.tcw.Fragment.GuideFragment;
import com.hhgs.tcw.Fragment.HomeFragment;
import com.hhgs.tcw.Fragment.InfoFragment;
import com.hhgs.tcw.Fragment.MineFragment;
import com.hhgs.tcw.Fragment.ShopFragment;
import com.hhgs.tcw.R;
import com.hhgs.tcw.event.TabSelectedEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import me.leolin.shortcutbadger.ShortcutBadger;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends SupportActivity implements BottomNavigationBar.OnTabSelectedListener {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mFragments[FIRST] = HomeFragment.newInstance("首页");
            mFragments[SECOND] = InfoFragment.newInstance("供求");
            mFragments[THIRD] = ShopFragment.newInstance("商铺");
            mFragments[FOURTH] = GuideFragment.newInstance("指导价");
            mFragments[FIFTH] = MineFragment.newInstance("我的");

            loadMultipleRootFragment(R.id.layFrame, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(HomeFragment.class);
            mFragments[SECOND] = findFragment(InfoFragment.class);
            mFragments[THIRD] = findFragment(ShopFragment.class);
            mFragments[FOURTH] = findFragment(GuideFragment.class);
            mFragments[FIFTH] = findFragment(MineFragment.class);
        }

        initView();
    }

    private void initView() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColor(Color.RED)
                .setText("5")
                .setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home, "首页").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.info, "供求").setActiveColorResource(R.color.teall))
                .addItem(new BottomNavigationItem(R.mipmap.shop, "供应商").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.guide, "指导价").setActiveColorResource(R.color.brownn))
                .addItem(new BottomNavigationItem(R.mipmap.mine, "我的").setActiveColorResource(R.color.greyy))
//                .addItem(new BottomNavigationItem(R.mipmap.mine, "我的").setActiveColorResource(R.color.grey).setBadgeItem(numberBadgeItem))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        ShortcutBadger.applyCount(this, 5); //1.1.4版本添加数字“徽章”的方法
    }


    @Override
    public void onTabSelected(int position) {

        showHideFragment(mFragments[position]);
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
        SupportFragment currentFragment = mFragments[position];
        int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

        // 这里推荐使用EventBus来实现 -> 解耦
        if (count == 1) {
            // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
            // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
            EventBus.getDefault().post(new TabSelectedEvent(position));
        }
    }
}