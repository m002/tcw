package com.hhgs.tcw.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhgs.tcw.Apapter.FragmentAdapter;
import com.hhgs.tcw.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class InfoActivity extends SupportActivity {

    //主布局及控件
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.info_name_title)
    TextView infoName;
    @Bind(R.id.tab)
    TabLayout mTab;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;


    //数据传递
    private String name;

//    private TabLayout mTab;
//    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        loadData();


    }

    private void loadData() {
        name = getIntent().getStringExtra("name");

        initView();
    }

    private void initView() {
//        mTab = (TabLayout) findViewById(R.id.tab);
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());

        mViewPager.setAdapter(new FragmentAdapter(this.getSupportFragmentManager()));
        mTab.setupWithViewPager(mViewPager);

        infoName.setText(name);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
