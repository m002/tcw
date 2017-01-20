package com.hhgs.tcw.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;


import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeetingPwdAct extends AppCompatActivity {

    @Bind(R.id.Act_back)
    ImageView ActBack;
    @Bind(R.id.etphone)
    EditText etphone;
    @Bind(R.id.btnsendcode)
    Button btnsendcode;
    @Bind(R.id.etcode)
    EditText etcode;
    @Bind(R.id.etpwd)
    EditText etpwd;
    @Bind(R.id.etpwd2)
    EditText etpwd2;
    @Bind(R.id.action_enter)
    Button actionEnter;
    @Bind(R.id.agreement)
    LinearLayout agreement;
    @Bind(R.id.code_tr)
    LinearLayout codeTR;
    @Bind(R.id.passworld_tl)
    TableLayout passworldTL;


    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetped);
        ButterKnife.bind(this);
        time = new TimeCount(60000, 1000);
    }

    @OnClick({R.id.Act_back, R.id.btnsendcode, R.id.action_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Act_back:
                this.finish();
                break;
            case R.id.btnsendcode:
                sendcode();
                break;
            case R.id.action_enter:
                enter();
                break;
        }
    }

    private void sendcode() {
        if (etphone.getText().toString().length() != 11) {
            Toast.makeText(SeetingPwdAct.this, "请输入正确的手机号", Toast.LENGTH_LONG).show();
        } else {
        }
    }

    private void judge(String s) {

        time.start();
        T.Show("发送成功,请注意查收");
        actionEnter.setVisibility(View.VISIBLE);
        codeTR.setVisibility(View.VISIBLE);
        passworldTL.setVisibility(View.VISIBLE);

    }

    private void enter() {
        if (etcode.getText().toString().length() != 4) {
            Toast.makeText(SeetingPwdAct.this, "验证码输入有误", Toast.LENGTH_LONG).show();
        } else if (!etpwd.getText().toString().equals(etpwd2.getText().toString())) {
            Toast.makeText(SeetingPwdAct.this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
        } else if (etpwd.getText().toString().length() < 6) {
            Toast.makeText(SeetingPwdAct.this, "密码输入长度不得小于6", Toast.LENGTH_LONG).show();
        } else {
        }
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            btnsendcode.setText("获取验证码");
            btnsendcode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            btnsendcode.setClickable(false);//防止重复点击
            btnsendcode.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }
    }


}
