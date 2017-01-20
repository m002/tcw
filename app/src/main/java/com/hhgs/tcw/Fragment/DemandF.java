package com.hhgs.tcw.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhgs.tcw.Apapter.DemandInfoAdp;
import com.hhgs.tcw.R;
import com.hhgs.tcw.View.XListView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class DemandF extends SupportFragment{


    @Bind(R.id.info_lv)
    XListView Lv;

    private DemandInfoAdp adp;

    public static DemandF newInstance() {

        Bundle args = new Bundle();

        DemandF fragment = new DemandF();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_list, container, false);
        ButterKnife.bind(this, view);
//        EventBus.getDefault().register(this);
        loadData();

        return view;
    }

    private void loadData() {
        adp=new DemandInfoAdp(getActivity());
        Lv.setAdapter(adp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
