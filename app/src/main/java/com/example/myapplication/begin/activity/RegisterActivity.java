package com.example.myapplication.begin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.begin.bean.LoginBean;
import com.example.myapplication.begin.bean.RegisterBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


//注册
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.lin_yuYan)
    LinearLayout linYuYan;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.lin_quHao)
    LinearLayout linQuHao;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_pCode)
    EditText edPCode;
    @BindView(R.id.text_code)
    TextView textCode;
    @BindView(R.id.ed_Pass)
    EditText edPass;
    @BindView(R.id.ed_cPass)
    EditText edCPass;
    @BindView(R.id.ed_yCode)
    EditText edYCode;
    @BindView(R.id.image_select)
    ImageView imageSelect;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.text_login)
    TextView textLogin;

    boolean read = false;//是否阅读协议
    @BindView(R.id.text_yuYan)
    TextView textYuYan;
    private PopupWindow mYuYanPop;//语言弹出框
    private RelativeLayout mRela_fanTi;
    private TimeCount time;
    private String areaCode="86";

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            textCode.setText(R.string.text_cxhq);
            textCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            textCode.setClickable(false);//防止重复点击
            textCode.setText(millisUntilFinished / 1000 + "s");
        }
    }

    @Override
    protected void initView() {
        yuYanPop();//语言弹出框
    }

    private void yuYanPop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_yuyan, null);
        mRela_fanTi = inflate.findViewById(R.id.rela_fanTi);
        RelativeLayout rela_zhongWen = inflate.findViewById(R.id.rela_zhongWen);
        TextView text_yingWen = inflate.findViewById(R.id.text_yingWen);
        ImageView image_quXiao = inflate.findViewById(R.id.image_quXiao);
        mYuYanPop = new PopupWindow(inflate, ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT);

        image_quXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYuYanPop.dismiss();
            }
        });

        mRela_fanTi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYuYanPop.dismiss();
                textYuYan.setText(R.string.ftzw);
            }
        });

        rela_zhongWen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYuYanPop.dismiss();
                textYuYan.setText(R.string.jtzw);
            }
        });

        text_yingWen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYuYanPop.dismiss();
                textYuYan.setText("English");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.lin_yuYan, R.id.lin_quHao, R.id.text_code, R.id.image_select, R.id.text_register, R.id.text_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_yuYan://选择语言
                mYuYanPop.showAsDropDown(mRela_fanTi,0,0);
                break;
            case R.id.lin_quHao://选择区号
                break;
            case R.id.text_code://获取手机验证码
                if(edPhone.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.tishi_qtxsjhm, Toast.LENGTH_SHORT).show();
                }else{
                    yanZhengMa();
                }
                break;
            case R.id.image_select://阅读协议
                if (read) {
                    read = false;
                    imageSelect.setImageResource(R.mipmap.zc_unselect);
                } else {
                    read = true;
                    imageSelect.setImageResource(R.mipmap.zc_select);
                }
                break;
            case R.id.text_register://注册
                String name = edName.getText().toString();
                String phone = edPhone.getText().toString();
                String pCode = edPCode.getText().toString();
                String pass = edPass.getText().toString();
                String cPass = edCPass.getText().toString();
                String yCode = edYCode.getText().toString();

                if(name.equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.qsryhmc, Toast.LENGTH_SHORT).show();
                }else if(pCode.equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.qsrsjyzm, Toast.LENGTH_SHORT).show();
                }else if(pass.equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.qsrdlmm, Toast.LENGTH_SHORT).show();
                }else if(cPass.equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.zcsrdlmm, Toast.LENGTH_SHORT).show();
                }else if(!pass.equals(cPass)){
                    Toast.makeText(RegisterActivity.this, R.string.lcsrdmmbyz, Toast.LENGTH_SHORT).show();
                }else if(yCode.equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.qsryqmbt, Toast.LENGTH_SHORT).show();
                }else if(read==false){
                    Toast.makeText(RegisterActivity.this, R.string.qgxyydxy, Toast.LENGTH_SHORT).show();
                }else{
                    register(name,phone,pCode,pass,yCode);
                }
                break;
            case R.id.text_login://登录
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }

    private void register(String name ,String phone,String pCode,String pass,String yCode) {
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("areacode", areaCode);
        jsonObject.put("code", pCode);
        jsonObject.put("invitation", yCode);
        jsonObject.put("password", pass);
        jsonObject.put("phone", phone);
        jsonObject.put("username", name);
        OkHttpUtils.postString()
                .url(ZgwApplication.appRequestUrl+"wallet/v1/user/register")
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(RegisterActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        Toast.makeText(RegisterActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }
                }));
    }

    private void yanZhengMa() {
        OkHttpUtils.post()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/send_code")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addParams("to", areaCode+edPhone.getText().toString())
                .addParams("func", "register")
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(RegisterActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        time = new TimeCount(60000, 1000);
                        time.start();
                        textCode.setClickable(false);
                        Toast.makeText(RegisterActivity.this, R.string.fscg, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
