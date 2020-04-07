package com.example.myapplication.utils;

import android.app.Activity;
import android.os.Build;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.view.ViewCompat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by LG on 2017/6/2.
 */

public class StatusTextColourUtils {

    public static void setStatusTextColour(Activity activity,boolean darkmode){

        if (MIUISetStatusBarLightMode(activity, darkmode) || FlymeSetStatusBarLightMode(activity, darkmode)) {

        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //如果是6.0以上将状态栏文字改为黑色，并设置状态栏颜色
                //activity.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                //颜色传入黑色时，状态栏字体改成白色，故反之
                if(darkmode){
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }else{
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

                }
                ViewGroup mContentView = (ViewGroup) activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
                View mChildView = mContentView.getChildAt(0);
                if (mChildView != null) {
                    ViewCompat.setFitsSystemWindows(mChildView, true);
                    ViewCompat.requestApplyInsets(mChildView);
                }
                }

        }
    }


    /**
     * 修改小米系统的状态颜色
     * @param activity
     * @param darkmode
     * @return
     */
    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean darkmode) {
        //StatusBarUtil.setColor(activity, activity.getResources().getColor(R.color.colorWhite), 80);
        boolean result = false;
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);

            result = true;
            //Log.e("------",result+"");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }


    public static boolean FlymeSetStatusBarLightMode(Activity activity, boolean darkmode) {
        boolean result = false;
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class
                    .getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (darkmode) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
