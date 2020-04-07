package com.example.myapplication.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import android.util.Log;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/** 动态获取权限类
 * 由于国产rom包屏蔽了原生权限回调，导致权限拒绝之后无法得到相应的结果，故改用RxPermissions来进行权限动态获取
 *
 *
 */

public class MPermissionUtils {
    private static int mRequestCode = -1;

    public static void requestPermissionsResult(Activity activity
            , final OnPermissionListener callback,String... permissions){
        requestPermissions(activity, callback,permissions);
    }

    /**
     * 请求权限处理
     * @param object        activity or fragment
     * @param
     * @param permissions   需要请求的权限
     * @param callback      结果回调
     */
    private static void requestPermissions(Object object,final OnPermissionListener callback, String... permissions){
        Observable<Boolean> request =new RxPermissions((Activity) object).request(permissions);
        request.subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if(aBoolean){
                    callback.onPermissionGranted();
                }else{
                    callback.onPermissionDenied();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 显示提示对话框
     */
    public static void showTipsDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings(context);
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private static void startAppSettings(Context context) {
        Intent intent = new Intent();
        try{

            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri1 = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri1);
            context.startActivity(intent);

        } catch (Exception e) {//抛出异常就直接打开设置页面
            Log.e("lujian", e.getLocalizedMessage());
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }


    public interface OnPermissionListener{
        void onPermissionGranted();
        void onPermissionDenied();
    }

    private static OnPermissionListener mOnPermissionListener;
}
