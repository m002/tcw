package com.hhgs.tcw.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hhgs.tcw.Application.App;


/**
 * Created by MFH on 2016/12/26 0022.
 */
public class SP {


    public static void put(String key, String value) {
        SharedPreferences sp = App.getApp().getSharedPreferences("key", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();

    }

    public static void del(String key) {
        SharedPreferences sp = App.getApp().getSharedPreferences("key", Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    public static String get(String key) {
        SharedPreferences sp = App.getApp().getSharedPreferences("key", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
}
