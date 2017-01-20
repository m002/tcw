package com.hhgs.tcw.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by MFH on 2016/4/7 0007.
 */
public class VersionManager {
    /**
     * 获取软件版本号
     *
     * @param
     * @return
     */

    public int getVersionName(Context context) throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionCode;
    }
}
