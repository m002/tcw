package com.hhgs.tcw.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhgs.tcw.Apapter.SupplyInfoAdp;
import com.hhgs.tcw.R;
import com.hhgs.tcw.View.XListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by MFH on 2017/1/17 0017.
 */

public class SupplyInfoFragment extends SupportFragment {

    @Bind(R.id.info_lv)
    XListView Lv;

    private volatile View self;

    private static SupplyInfoFragment instance;
    private SupplyInfoAdp adp;

    @SuppressLint("ValidFragment")
    private SupplyInfoFragment() {
    }

    public static SupplyInfoFragment getInstance() {
        if (instance == null) {
            synchronized (DemandInfoFragment.class) {
                if (instance == null) instance = new SupplyInfoFragment();
            }
        }
        return instance;
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(R.layout.fragment_info_list, null);
        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }
        ButterKnife.bind(this, this.self);
        loadData();
        return this.self;
    }

    private void loadData() {
        adp = new SupplyInfoAdp(getActivity());
        Lv.setAdapter(adp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
