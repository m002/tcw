package com.hhgs.tcw.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.camnter.easyslidingtabs.widget.EasySlidingTabs;
import com.hhgs.tcw.Apapter.TabsFragmentAdapter;
import com.hhgs.tcw.Fragment.DemandInfoFragment;
import com.hhgs.tcw.Fragment.SupplyInfoFragment;
import com.hhgs.tcw.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class InfoListActivity extends SupportActivity {

    //主布局及控件
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.info_name_title)
    TextView infoName;
    @Bind(R.id.info_sliding_tabs)
    EasySlidingTabs rankSlidingTabs;
    @Bind(R.id.info_easy_vp)
    ViewPager rankEasyVp;

    //数据适配
    private TabsFragmentAdapter adapter;
    List<Fragment> fragments;

    //数据传递
    private String name;


    public static final String[] titles = {"需求信息", "供应信息"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        name = getIntent().getStringExtra("name");

        this.fragments = new LinkedList<>();
        DemandInfoFragment demandInfoFrag = DemandInfoFragment.getInstance();
        SupplyInfoFragment supplyInfoFrag = SupplyInfoFragment.getInstance();
        this.fragments.add(demandInfoFrag);
        this.fragments.add(supplyInfoFrag);

        this.adapter = new TabsFragmentAdapter(this.getSupportFragmentManager(), titles,
                this.fragments);
        this.rankEasyVp.setAdapter(this.adapter);
        this.rankSlidingTabs.setViewPager(this.rankEasyVp);

        loadView();
    }

    private void loadView() {
        infoName.setText(name);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
