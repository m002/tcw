package com.hhgs.tcw.Fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhgs.tcw.Activity.MessageActivity;
import com.hhgs.tcw.R;
import com.jauker.widget.BadgeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by MFH on 2016/12/26 0026.
 */

public class GuideFragment extends SupportFragment {

    private Context context;


    @Bind(R.id.message_lin)
    LinearLayout msgLin;
    @Bind(R.id.searchedittext)
    TextView searchTV;

    //消息角标数字
    private BadgeView badgeView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        context = getActivity();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化控件
        initView();
    }

    private void initView() {
        //初始化消息数量并显示在图标右上方
        badgeView = new BadgeView(context);
        badgeView.setTargetView(msgLin);
        badgeView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        badgeView.setBackground(10, getResources().getColor(R.color.tv_Red));
        badgeView.setBadgeCount(5);
    }

    public static GuideFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        GuideFragment fragment = new GuideFragment();
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
}