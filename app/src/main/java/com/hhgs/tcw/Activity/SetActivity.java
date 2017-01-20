package com.hhgs.tcw.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhgs.tcw.Application.App;
import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.DataManager;
import com.hhgs.tcw.Utils.SP;
import com.hhgs.tcw.Utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class SetActivity extends AppCompatActivity {

    @Bind(R.id.logAct_back)
    ImageView logActBack;
    @Bind(R.id.push_switch)
    SwitchCompat pushSwitch;
    @Bind(R.id.cache_size_tv)
    TextView cacheSize;
    @Bind(R.id.clean_cache_lin)
    LinearLayout cleanCacheLin;
    @Bind(R.id.suggestion_lin)
    LinearLayout suggestionLin;
    @Bind(R.id.changepwd_lin)
    LinearLayout changepwdLin;
    @Bind(R.id.agreement_lin)
    LinearLayout agreementLin;
    @Bind(R.id.exit_login_lin)
    LinearLayout exitLin;


    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        app = App.getApp();
        initView();
    }

    private void initView() {
        //计算本应用的缓存
        try {
            cacheSize.setText(DataManager.getTotalCacheSize(app));
        } catch (Exception e) {
            e.printStackTrace();
        }
        suggestionLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        if (JPushInterface.isPushStopped(app.getApplicationContext())) {
            pushSwitch.setChecked(false);
        } else {
            pushSwitch.setChecked(true);
        }
        pushSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    JPushInterface.resumePush(app.getApplicationContext());// 接收推送

                } else {
                    JPushInterface.stopPush(app.getApplicationContext());//停止推送
                }
            }
        });
    }

    @OnClick({R.id.logAct_back, R.id.clean_cache_lin, R.id.agreement_lin, R.id.changepwd_lin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logAct_back:
                finish();
                break;
            case R.id.clean_cache_lin:
                dialogshow_cache();
                break;
            case R.id.agreement_lin:
                break;
            case R.id.changepwd_lin:
                if (SP.get("user_id") != null && !SP.get("user_id").equals("")) {
                    Intent intent = new Intent(SetActivity.this, SeetingPwdAct.class);
                    startActivity(intent);
                } else {
                    T.Show("请先注册登录");
                }
                break;
            case R.id.exit_login_lin:
                dialogshow_exit();
                break;
        }
    }

    //清理缓存
    private void dialogshow_cache() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要清除本应用的图片缓存吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataManager.clearAllCache(app);
                T.Show("缓存已被清除");
                cacheSize.setText("0MB");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    //退出登录
    private void dialogshow_exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要退出当前帐号吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                T.Show("已退出登录");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
}
