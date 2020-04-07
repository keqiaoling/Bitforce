package com.example.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ZgwApplication;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.StringCallback;
import com.example.myapplication.utils.DialogUtils;
import com.example.myapplication.utils.SharedPreferencesUtils;
import com.example.myapplication.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import okhttp3.Call;

/** Fragment的基础类
 * Created by LG on 2017/3/7.
 */

public abstract class BaseNoBarColorFragment extends Fragment {
    protected static Activity mActivity;
    /**
     * 初始化视图
     */
    protected  abstract void initView(View view, Bundle savedInstanceState);
    /**
     * 初始化数据
     */
    protected  abstract void initData();

    /**
     * 加载布局文件
     * @return
     */
    protected  abstract int getLayoutId();

    /**
     * 判断是否刷新
     */
    public boolean isRefreshInfo;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, inflate);
        initView(inflate,savedInstanceState);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getActivity().getCurrentFocus();
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
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getActivity().getCurrentFocus();
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


}
