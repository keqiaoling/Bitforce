package com.example.myapplication.begin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.begin.bean.LoginBean;
import com.example.myapplication.begin.bean.RegisterBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.shouye.bean.bannerBean;
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


//登录
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.lin_yuYan)
    LinearLayout linYuYan;
    @BindView(R.id.lin_quHao)
    LinearLayout linQuHao;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.rela_login)
    RelativeLayout relaLogin;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.text_forget)
    TextView textForget;
    @BindView(R.id.text_yuYan)
    TextView textYuYan;

    private PopupWindow mYuYanPop;//语言弹出框
    private RelativeLayout mRela_fanTi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ceshi();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        yuYanPop();//语言弹出框
    }

    private void ceshi() {
        OkHttpUtils
                .get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/banner/list")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .build()
                .execute(new ResultModelCallback(LoginActivity.this, new ResponseCallBack<bannerBean>() {
                    @Override
                    public void onError(String e) {

                    }

                    @Override
                    public void onResponse(final bannerBean response) throws JSONException {
                        if(response.getData()!=null){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                    }
                }));
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

    private void login(String pass,String phone) {

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", pass);
        jsonObject.put("phone", phone);
        OkHttpUtils.postString()
                .url(ZgwApplication.appRequestUrl+"wallet/v1/user/login")
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<LoginBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(LoginActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(LoginBean response) throws org.json.JSONException {
                        String data = response.getData();
                        SharedPreferenceUtils.setToken(data);
                        SharedPreferenceUtils.setName(edPhone.getText().toString());

                        Toast.makeText(LoginActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }));
    }

    @OnClick({R.id.lin_yuYan, R.id.lin_quHao, R.id.rela_login, R.id.text_register, R.id.text_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_yuYan:
                mYuYanPop.showAsDropDown(mRela_fanTi,0,0);
                break;
            case R.id.lin_quHao:
                break;
            case R.id.rela_login:
                if(edPhone.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, R.string.qsrdhhm, Toast.LENGTH_SHORT).show();
                }else if(edPass.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, R.string.qsrdlmm, Toast.LENGTH_SHORT).show();
                }else{
                    login(edPass.getText().toString(),edPhone.getText().toString());
                }
                break;
            case R.id.text_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.text_forget:
                break;
        }
    }
}
