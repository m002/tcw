package com.hhgs.tcw.Fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhgs.tcw.Activity.MessageActivity;
import com.hhgs.tcw.Activity.SetActivity;
import com.hhgs.tcw.R;
import com.jauker.widget.BadgeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leolin.shortcutbadger.ShortcutBadger;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by MFH on 2016/12/26 0026.
 */

public class MineFragment extends SupportFragment {

    @Bind(R.id.user_msg_lin)
    LinearLayout msgLin;
    @Bind(R.id.setting_lin)
    LinearLayout setLin;


    //消息角标数字
    private BadgeView badgeView;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        context = getActivity();
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {
        //初始化消息数量并显示在图标右上方
        badgeView = new BadgeView(context);
        badgeView.setTargetView(msgLin);
        badgeView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        badgeView.setBackground(10, getResources().getColor(R.color.tv_Red));
        badgeView.setBadgeCount(5);

    }

    public static MineFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.setting_lin, R.id.user_msg_lin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_lin:
                Intent setInt = new Intent(context, SetActivity.class);
                startActivity(setInt);
                break;
            case R.id.user_msg_lin:
                Intent msgInt = new Intent(context, MessageActivity.class);
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