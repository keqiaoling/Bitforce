package com.example.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.myapplication.ZgwApplication;


/**
 * Created by LG on 2017/6/14.
 */

public class NetUtils {
    /**
     * 判断网络情况
     *
     * @param context  上下文
     * @return false 表示没有网络 true 表示有网络
     */
    public static boolean isNetworkAvalible(Context context) {
        if(context!=null){
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) ZgwApplication.getContext().getSystemService(ZgwApplication.getContext().CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        }
        return false;
    }

    /**
     * 判断网络是否连接
     **/
    public static boolean netState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取代表联网状态的NetWorkInfo对象
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        // 获取当前的网络连接是否可用
        boolean available = false;
        try {
            available = networkInfo.isAvailable();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (available) {
            Log.i("通知", "当前的网络连接可用");
            return true;
        } else {
            Log.i("通知", "当前的网络连接不可用");
            return false;
        }
    }

    /**
     * 在连接到网络基础之上,判断设备是否是SIM网络连接
     *
     * @param context
     * @return
     */
    public static boolean IsMobileNetConnect(Context context) {
        try {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            if (NetworkInfo.State.CONNECTED == state)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getMac(Context context)
    {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String str = info.getMacAddress();
        if (str == null) str = "";
        return str;
    }
}
