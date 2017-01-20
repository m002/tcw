package com.hhgs.tcw.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhgs.tcw.Apapter.InfoAdp;
import com.hhgs.tcw.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by MFH on 2016/12/26 0026.
 */

public class InfoFragment extends SupportFragment {

    @Bind(R.id.message_title)
    TextView messageTitle;
    @Bind(R.id.message_add_msg)
    ImageView messageAddMsg;
    @Bind(R.id.title_information)
    RelativeLayout titleInformation;
    @Bind(R.id.searchedittext)
    TextView searchedittext;
    @Bind(R.id.info_expdb_lv)
    ExpandableListView infoExpdbLv;

    private Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        context = getActivity();
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {
        infoExpdbLv.setAdapter(new InfoAdp(getActivity(), infoExpdbLv));
    }


    public static InfoFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.message_add_msg, R.id.searchedittext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_add_msg:
                // 发布需求/供应信息 弹出菜单
                showPopupMenu(messageAddMsg);
                break;
            case R.id.searchedittext:
                break;
        }
    }

    //material design 风格菜单
    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(context, view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
    }

}

