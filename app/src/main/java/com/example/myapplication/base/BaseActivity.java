package com.example.myapplication.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**  Activity的基础类
 * Created by LG on 2017/3/7.
 */

public abstract class BaseActivity extends AppCompatActivity {


    /**
     * 初始化视图
     */
    protected  abstract void initView();

    /**
     * 初始化数据
     */
    protected  abstract void initData();

    /**
     * 加载布局文件
     * @return
     */
    protected  abstract int getLayoutId();

    private Unbinder unbinder;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(getLayoutId());
        //注解绑定
        unbinder = ButterKnife.bind(this);
      ChangColor(R.color.white);
        initData();
        initView();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ChangColor(int color) {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(color));
        }


        //这里注意下 因为在评论区发现有网友调用setRootViewFitsSystemWindows 里面 winContent.getChildCount()=0 导致代码无法继续
        //是因为你需要在setContentView之后才可以调用 setRootViewFitsSystemWindows

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
    }
    /**
     * 隐藏键盘
     */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    /**
     * 显示键盘
     */
    public void showSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    public interface SessionInfoCallback{
        void onResponse();
    }

    public interface UserinfoCallback{
        void onResponse();
    }



    public interface PersonalInfoCallback{
        void onResponse();
    }



    public interface UserLoginCallback{
        void onResponse(String e);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//    /**
//     * 处理请求返回的响应码
//     *
//     * @param resultCode
//     */
//    public void getDealMessage(String resultCode,UserLoginCallback userLoginCallback) {
//        switch (resultCode) {
//            case "-5":
//            case "-6":
//            case "2":
//            case "-2":
//            case "-3":
//                break;
//            case "-1":
//                SharedPreferencesUtils.getInstance().saveData("JSESSIONID", "");
//                SharedPreferencesUtils.getInstance().saveData("route", "");
//                SharedPreferencesUtils.getInstance().saveData("islogin",false);
//
//                UserinfoBean userinfoBean = UserInfoLiveData.getIns().getValue();
//                userinfoBean.setLogin(false);
//                userinfoBean.setSessionInfoBean(null);
//                userinfoBean.setFrontSessionBean(null);
//                UserInfoLiveData.getIns().setValue(userinfoBean);
//                Intent itLogin = new Intent(BaseActivity.this, LoginActivity.class);
//                itLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                itLogin.putExtra("type", "info");
//                startActivity(itLogin);
//                break;
//            //登陆成功
//            case "1":
//                userLoginCallback.onResponse("8989");
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
