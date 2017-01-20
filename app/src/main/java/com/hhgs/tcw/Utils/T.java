package com.hhgs.tcw.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hhgs.tcw.Application.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MFH on 2016/12/26 0026.
 */

public class T {


    //封装Toast方法（根据持续时间分为长短）
    public static void Show(String msg) {
        Toast.makeText(App.getApp(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void LongShow(String msg) {
        Toast.makeText(App.getApp(), msg, Toast.LENGTH_LONG).show();
    }


    //从字符串中获取json数组
    public static JSONArray getContentArray(String data) {
        try {
            JSONArray array = new JSONArray(data);
            return array;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new JSONArray();
        }
    }

    //从字符串中获取json对象
    public static JSONObject getContentObject(String data) {
        try {
            JSONObject obj = new JSONObject(data);
            return obj;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new JSONObject();
        }
    }

    //判断请求服务器返回数据是否成功
    private static int  JudgeData(String s) {
        JSONObject object = getContentObject(s);
        if (!object.optString("code").equals("200")) {
            T.Show(object.optString("msg"));
            return 1;
        } else {
            return 0;
        }
    }

    //判断网络是否可用
    public static boolean isNetworkAvailable(Context activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //将时间戳转为正常格式
    public static String convert(long mill) {
        Date date = new Date(mill);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

}
