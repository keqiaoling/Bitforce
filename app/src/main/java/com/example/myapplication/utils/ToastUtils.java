package com.example.myapplication.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.example.myapplication.ZgwApplication;


/**
 * 一个全局可以使用的toast
 * Created by Administrator on 2016/12/5.
 */

public class ToastUtils {
     private static Toast mToast;
     public static Toast mTopToast;
    public static void showToast(String text){
        if(mToast==null){
            mToast=Toast.makeText(ZgwApplication.getContext(),text,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(text);
            //mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showTopToast(String text){
        if(mTopToast==null){
            mTopToast=Toast.makeText(ZgwApplication.getContext(),text,Toast.LENGTH_SHORT);
            mTopToast.setGravity(Gravity.TOP, 0, DensityUtils.dp2px(ZgwApplication.getContext(),60));
        }else{
            mTopToast.setText(text);
            mTopToast.setGravity(Gravity.TOP, 0, DensityUtils.dp2px(ZgwApplication.getContext(),60));
            mTopToast.setDuration(Toast.LENGTH_SHORT);
        }
        mTopToast.show();
    }

}
