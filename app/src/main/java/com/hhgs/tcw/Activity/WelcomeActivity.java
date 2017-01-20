package com.hhgs.tcw.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hhgs.tcw.Application.App;
import com.hhgs.tcw.R;
import com.hhgs.tcw.Utils.FrescoIMG;
import com.hhgs.tcw.Utils.SP;
import com.hhgs.tcw.Utils.T;
import com.hhgs.tcw.Utils.VersionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by MFH on 2016/9/21 0021.
 */
public class WelcomeActivity extends AppCompatActivity implements Animation.AnimationListener {


    @Bind(R.id.welcome_img)
    SimpleDraweeView welcomeImg;


    private String updateUrl, imgUrl, initMsg, enable, searchMsg, updateData;
    private double bestVersion, lastVersion, localVersion;

    //HomeGlobal实体对象

    //版本管理工具
    private VersionManager versionManager;

    //欢迎页动画
    private Animation alphaAnimation = null;

    //自定义Toast

    //百度地图定位
    //百度地图定位
    public LocationClient mLocationClient = null;
    public MyLocationListenner myListener = new MyLocationListenner();//定位成功回调监听接口
    public BDLocation bdLocation;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);
        app = App.getApp();

        versionManager = new VersionManager();
        try {
            localVersion = versionManager.getVersionName(this);
            Log.e("localVersion", String.valueOf(localVersion));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //百度地图定位
        loadlocation();

        //判断网路情况
        judgeNet();

    }

    private void loadlocation() {
        mLocationClient = new LocationClient(WelcomeActivity.this.getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        mLocationClient.start();
        if (bdLocation == null) {
            SP.put("user_lon", "101.7842690000");
            SP.put("user_lat", "36.6234770000");
            SP.put("user_city", "西宁市");
            Log.e("没有定位", "yes");
            Log.e("SP.get(user_city)", SP.get("user_city"));
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认bd09ll  gcj02，设置返回的定位结果坐标系
        int span = 10000;
//        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedAddress(true);
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    private void judgeNet() {
        if (!T.isNetworkAvailable(this)) {
            //弹框提示然后退出
            dialogExit("请检查网络");
        } else {
            enter();
        }

    }

    private void enter() {
        FrescoIMG.showImg(welcomeImg, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484736230656&di=3e71316a65156cd7810abadf897b5532&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F140514%2F235025-1405140P14338.jpg");
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);
        alphaAnimation.setFillEnabled(true); //启动Fill保持
        alphaAnimation.setFillAfter(true);  //设置动画的最后一帧是保持在View上面
        welcomeImg.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(this);//为动画设置监听

    }


    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        //动画结束时结束欢迎界面并转到软件的主界面
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.putExtra("action", "0");
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        this.finish();


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    //退出弹框
    private void dialogExit(String Msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(Msg);
        builder.show();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //在这里执行延时任务

                // 关闭进程
                android.os.Process.killProcess(android.os.Process.myPid());//获取PID
                System.exit(0);
            }
        }, 3000);
    }


    private void dialogshow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前版本过低，请更新至最新版本");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(updateUrl);
                intent.setData(content_url);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("退出程序", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //在这里执行延时任务

                        // 关闭进程
                        android.os.Process.killProcess(android.os.Process.myPid());//获取PID
                        System.exit(0);
                    }
                }, 1000);
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

    @Override
    public void onDestroy() {
        mLocationClient.stop();
        super.onDestroy();
    }

    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            bdLocation = location;
            //Receive Location

            //获取用户当前经纬度


            String city = location.getCity();
            Log.e("我被定位了", "yes");
            if(city!=null)
            {
                SP.put("user_city", city);
                SP.put("user_lon", location.getLongitude() + "");
                SP.put("user_lat", location.getLatitude() + "");
            }

            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }
}
