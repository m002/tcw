package com.hhgs.tcw.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhgs.tcw.Apapter.DemandInfoAdp;
import com.hhgs.tcw.Apapter.SupplyInfoAdp;
import com.hhgs.tcw.R;
import com.hhgs.tcw.View.XListView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class SupplyF extends SupportFragment {

    @Bind(R.id.info_lv)
    XListView Lv;

    private SupplyInfoAdp adp;


    public static SupplyF newInstance() {

        Bundle args = new Bundle();

        SupplyF fragment = new SupplyF();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_list, container, false);
//        EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);

        loadData();

        return view;
    }

    private void loadData() {
        adp=new SupplyInfoAdp(getActivity());
        Lv.setAdapter(adp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
