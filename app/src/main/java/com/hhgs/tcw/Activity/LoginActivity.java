package com.hhgs.tcw.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hhgs.tcw.Application.App;
import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.T;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MFH on 16/09/21.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_hole_lin)
    LinearLayout holeLin;
    @Bind(R.id.logAct_back)
    ImageView logActBack;
    @Bind(R.id.loginphone)
    EditText loginphone;
    @Bind(R.id.loginpwd)
    EditText loginpwd;
    @Bind(R.id.btnlogin)
    AppCompatButton btnlogin;
    @Bind(R.id.forgetpwd)
    TextView forgetPwd;
    @Bind(R.id.gotoregister)
    TextView gotoregister;

    private String userId, user_tel;
    private ProgressDialog progressDialog;
    private App app;

    private static final String TAG = "JPush";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        ButterKnife.bind(this);
    }

    private void init() {
        app = App.getApp();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载");
    }

    @OnClick({R.id.logAct_back, R.id.btnlogin, R.id.forgetpwd, R.id.gotoregister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logAct_back:
                finish();
                break;
            case R.id.btnlogin:
                //实现登录
                login();
                break;
            case R.id.forgetpwd:
                Intent fgt = new Intent(LoginActivity.this, ForgetPwdAct.class);
                startActivity(fgt);
                break;
            case R.id.gotoregister:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }

    //实现登录
    private void login() {
        if (loginphone.getText().toString().length() != 11) {
            T.Show("请输入正确的手机号");
        } else if (loginpwd.getText().toString().equals("")) {
            T.Show("请输入密码");
        } else {
            progressDialog.show();
        }
    }

    private void judge(String s) {


    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

}
