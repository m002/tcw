package com.hhgs.tcw.Utils;

import android.content.Context;

import com.hhgs.tcw.Model.JPMessage;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * Created by MFH on 2016/10/11 0011.
 */
public class DBHelper {

    private static DbUtils utils;

    /*初始化DBuitls的方法，在Application中初始化*/

    public static void init(Context context) {

        utils = DbUtils.create(context);

//        开启事物，这样可以提高效率，因为每次执行语句都有一个开启事务和关闭事务

        utils.configAllowTransaction(true);

        try {
            utils.createTableIfNotExist(JPMessage.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

//        设为true的时候，可以在LogCat中看到所执行的语句信息

        utils.configDebug(true);

    }

    public static DbUtils getUtils() {
        return utils;
    }

}
