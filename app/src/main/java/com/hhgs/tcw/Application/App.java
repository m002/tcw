package com.hhgs.tcw.Application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hhgs.tcw.Utils.DBHelper;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by MFH on 2016/12/26 0026.
 */

public class App extends Application {


    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        //数据库初始化
        DBHelper.init(this);
        //极光初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //Fresco初始化
        Fresco.initialize(this);
    }


    public static App getApp() {
        return app;
    }

}
