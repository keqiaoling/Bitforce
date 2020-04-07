package com.example.myapplication.okhttp.callback;

import android.content.Context;
import android.content.Intent;

import com.example.myapplication.ZgwApplication;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.utils.DialogUtils;
import com.example.myapplication.utils.SharedPreferencesUtils;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by LG on 2017/3/26.
 */

public class ResultModelCallback<T> extends StringCallback {

    private ResponseCallBack callBack;
    private Context mContext;
    private String strCode;
    private boolean isLoadRefresh = true;

    public ResultModelCallback(Context context, ResponseCallBack callBack) {
        this.mContext = context;
        this.callBack = callBack;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        call.cancel();
        callBack.onError("");
        /*if(id==404){
         EventBus.getDefault().
        }else{*/
        //callBack.onError(e.getMessage());
        //}


    }

    @Override
    public void onResponse(String response, int id) {
        try {
            if (response != null) {
                JSONObject dataJson = new JSONObject(response);
                String code = null;
                String message = null;
                //Log.e("测试数据",response);
                if (dataJson.has("code")) {
                    code = dataJson.getString("code");
                }

                if (dataJson.has("message")) {
                    message = dataJson.getString("message");
                }
                //读取服务器的状态码，由于接口返回实体类不一样，只能伪装成code成功的样子。
                if (dataJson.has("ret")) {
                    code = dataJson.getString("ret");
                }

                if (code == null) {
                    Type finalNeedType;
                    //获取到泛型的对象类型
                    final Type[] types = callBack.getClass().getGenericInterfaces();
                    if (MethodHandler(types) == null || MethodHandler(types).size() == 0) {
                        finalNeedType = null;
                    }

                    finalNeedType = MethodHandler(types).get(0);

                    if (new Gson().fromJson(response, finalNeedType) == null) {
                        throw new NullPointerException();
                    }

                    T modelT = new Gson().fromJson(response, finalNeedType);
                    callBack.onResponse(modelT);

                } else {
                    if (code.equals("200")) {
                        Type finalNeedType;
                        //获取到泛型的对象类型
                        final Type[] types = callBack.getClass().getGenericInterfaces();
                        if (MethodHandler(types) == null || MethodHandler(types).size() == 0) {
                            finalNeedType = null;
                        }

                        finalNeedType = MethodHandler(types).get(0);

                        if (new Gson().fromJson(response, finalNeedType) == null) {
                            throw new NullPointerException();
                        }

                        T modelT = new Gson().fromJson(response, finalNeedType);

                        callBack.onResponse(modelT);

                    }/*else if(code.equals("401")){
                     *//*if(isLoadRefresh&&(boolean)SharedPreferencesUtils.getInstance().getData("islogin", true)){
                       requestUserLogin();
                       isLoadRefresh=false;
                   }*//*
               }*/ else {
                        if (message != null && message != "") {
                            callBack.onError(message);
                            return;
                        }
                        getErrorCode(code);
                    }
                }

            } else {

                DialogUtils.dismissDialogLoading();
                //callBack.onError("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            callBack.onError("网络异常,请稍后重试！");
        }
    }

    public void getErrorCode(String strCode) {
        switch (strCode) {
            case "-1":
                callBack.onError("系统异常");
                break;
            case "500":
                callBack.onError("失败");
                break;
            case "1001":
                callBack.onError("用户余额不足");
                break;
            case "1003":
                callBack.onError("订单在交易中,不能被取消");
                break;
            case "1004":
                callBack.onError("订单已经完成,不能再被取消");
                break;
            case "1005":
                callBack.onError("订单已经取消,不能再被取消");
                break;
            case "1006":
                callBack.onError("无权限");
                break;
            case "1007":
                callBack.onError("库存不足");
                break;
            case "1008":
                callBack.onError("确认订单失败");
                break;
            case "1009":
                callBack.onError("未匹配到信息");
                break;
            case "1010":
                callBack.onError("不能对自己进行交易");
                break;
            case "1011":
                callBack.onError("当天取消订单次数达到3次，不能接单");
                break;
            case "1012":
                callBack.onError("不是商家,不能发布信息");
                break;
            case "1013":
                callBack.onError("C2C账户被冻结,无法交易");
                break;
            case "1014":
                callBack.onError("请求参数不正确");
                break;
            case "1015":
                callBack.onError("请求信息不正确");
                break;
            case "1019":
                callBack.onError("剩余数量不足");
                break;
            case "1021":
                callBack.onError("已存在申诉订单,不能重复提交");
                break;
            case "1023":
                callBack.onError("没有符合的收款方式，请绑定更多的收款方式");
                break;
            default:
                callBack.onError("失败");
                break;

        }
    }

    /**
     * 登录请求接口
     */
    public void requestUserLogin() {
        //Log.e("进入等路","----------");
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("loginName", SharedPreferencesUtils.getInstance().getString("loginName", ""));
//        mapParams.put("password", SharedPreferencesUtils.getInstance().getString("password",""));
        mapParams.put("wxBind", "false");
        OkHttpUtils
                .post()
                .url(ZgwApplication.appRequestUrl + "user/login.html")
                //加入请求头
                .addHeader("X-Requested-With", "XMLHttpReques")
                .params(mapParams)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                call.cancel();
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject dataJson;
                    dataJson = new JSONObject(response);

                    String resultCode = dataJson.getString("resultCode");

                    if (dataJson.has("code")) {
                        strCode = dataJson.getString("code");
                    }

                    DialogUtils.dismissDialogLoading();
                    getDealMessage(resultCode);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    /**
     * 处理请求返回的响应码
     *
     * @param resultCode
     */
    public void getDealMessage(String resultCode) {
        switch (resultCode) {
            case "-5":
            case "-6":
            case "2":
            case "-2":
            case "-3":

                break;
            case "-1":

                break;
            //登陆成功
            case "1":
                //此处是为了JSESSIONID过期之后进行重新登录操作。

                break;
        }
    }


    /**
     * 得到泛型类型
     * MethodHandler
     */
    private static List<Type> MethodHandler(Type[] types) {

        List<Type> needtypes = new ArrayList<>();
        Type needParentType = null;
        for (Type paramType : types) {
            System.out.println("  " + paramType);

            if (paramType instanceof ParameterizedType) {
                Type[] parentypes = ((ParameterizedType) paramType).getActualTypeArguments();
                for (Type childtype : parentypes) {
                    needtypes.add(childtype);
                    if (childtype instanceof ParameterizedType) {
                        Type[] childtypes = ((ParameterizedType) childtype).getActualTypeArguments();
                        for (Type type : childtypes) {
                            needtypes.add(type);
                        }
                    }
                }
            }
        }
        return needtypes;
    }

}
