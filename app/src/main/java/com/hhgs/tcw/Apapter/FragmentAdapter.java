package com.hhgs.tcw.Apapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hhgs.tcw.Fragment.DemandF;
import com.hhgs.tcw.Fragment.SupplyF;

/**
 * Created by MFH on 2017/1/20 0020.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private String[] mTab = new String[]{"需求信息", "供应信息"};

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return DemandF.newInstance();
        } else {
            return SupplyF.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}
