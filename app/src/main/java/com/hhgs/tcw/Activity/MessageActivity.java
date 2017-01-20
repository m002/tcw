package com.hhgs.tcw.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hhgs.tcw.Apapter.JPMessageAdp;
import com.hhgs.tcw.Model.JPMessage;
import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.DBHelper;
import com.hhgs.tcw.Utils.T;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity {

    @Bind(R.id.logAct_back)
    ImageView logActBack;
    @Bind(R.id.empty_tv)
    TextView emptyTv;
    @Bind(R.id.message_lv)
    ListView lv;


    private List<JPMessage> msglist;
    private DbUtils db;
    private ProgressDialog progressDialog;
    private JPMessageAdp adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        init();
        getData();
    }

    private void init() {
        db = DBHelper.getUtils();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载");
        progressDialog.show();
    }

    private void getData() {
        msglist = new ArrayList<>();
        try {
            msglist = db.findAll(JPMessage.class);//通过类型查找
            if (msglist.size() == 0) {
                emptyTv.setVisibility(View.INVISIBLE);
            }
            Collections.reverse(msglist);
            progressDialog.dismiss();
            Log.e("msglist", msglist.size() + "");
            adp = new JPMessageAdp(this, msglist);
            lv.setAdapter(adp);
        } catch (DbException e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }
    }

    @OnClick({R.id.logAct_back, R.id.empty_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logAct_back:
                this.finish();
                break;
            case R.id.empty_tv:
                dialogshow();
                break;
        }
    }

    private void dialogshow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要清空所有消息吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    msglist.removeAll(msglist);
                    db.deleteAll(JPMessage.class);
                    adp.notifyDataSetChanged();
                    T.Show("消息已被清空");
                } catch (DbException e) {
                    e.printStackTrace();
                }

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

}
