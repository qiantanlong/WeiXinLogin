package com.zhengsheng.administrator.childteach.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.List;

/**
 * Created by yuhongzhen on 2016/9/22.
 */

public class StateOfNetWork {
    static Context context;
    private static  StateOfNetWork stateOfNetWork;
    private String typeName;

    private StateOfNetWork(){

    }

    public static StateOfNetWork getInstance(Context context){
        if (stateOfNetWork==null){
            stateOfNetWork=new StateOfNetWork();
        }
        StateOfNetWork.context =context;
        return stateOfNetWork;
    }
    /**
     * 检测网络是否连接
     *
     * @return
     */
    public  boolean isNetConnected() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] infos = cm.getAllNetworkInfo();
            if (infos != null) {
                for (NetworkInfo ni : infos) {
                    if (ni.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检测wifi是否连接
     *
     * @return
     */
    public  boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测3G是否连接
     *
     * @return
     */
    public  boolean is3gConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String typeName = networkInfo.getSubtypeName();
                Log.i("typeName",typeName);

                return true;
            }
        }
        return false;
    }
    public String getMobileNetName(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                typeName = networkInfo.getSubtypeName();
                return typeName;
            }
        }
        return "未知网络";
    }

    /**
     * 检测GPS是否打开
     *
     * @return
     */
    public   boolean isGpsEnabled() {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> accessibleProviders = lm.getProviders(true);
        for (String name : accessibleProviders) {
            if ("gps".equals(name)) {
                return true;
            }
        }
        return false;
    }

}
