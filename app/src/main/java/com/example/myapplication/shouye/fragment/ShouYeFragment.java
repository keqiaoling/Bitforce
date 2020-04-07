package com.example.myapplication.shouye.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.shouye.activity.GongGaoActivity;
import com.example.myapplication.shouye.activity.YiJianActivity;
import com.example.myapplication.shouye.bean.bannerBean;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//首页fragment
public class ShouYeFragment extends BaseFragment {

    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.fankui)
    ImageView fankui;
    @BindView(R.id.gonggao)
    ImageView gonggao;
    @BindView(R.id.banner)
    Banner banner;

    private List<String> mBannerList;//轮播图集合
    private List<bannerBean.DataBean.ListBean> mImageList;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        banner();
    }

    private void banner() {
        mBannerList = new ArrayList<>();

        banner.setImageLoader(new GlideImageLoader());//加载图片
        banner.isAutoPlay(true);//是否自动轮播
        banner.setDelayTime(4000);//轮播间隔时间
        banner.setBannerAnimation(Transformer.DepthPage);//轮播动画
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//轮播指示器位置
        //banner.setOnBannerListener(ShouYeFragment.this);//轮播点击事件

        OkHttpUtils
                .get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/banner/list")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .build()
                .execute(new ResultModelCallback(getActivity(), new ResponseCallBack<bannerBean>() {
                    @Override
                    public void onError(String e) {
                        Log.e("轮播图", "onError: "+ e );
                    }

                    @Override
                    public void onResponse(final bannerBean response) throws JSONException {
                        if (response.getData() != null) {
                            if (response.getData().getList().size() != 0) {
                                mImageList = response.getData().getList();
                                for (int i = 0; i < mImageList.size(); i++) {
                                    Log.e("轮播图", "onResponse: "+mImageList.get(i).getImg() );
                                    mBannerList.add(ZgwApplication.imageUrl+mImageList.get(i).getImg());
                                }

                                if (mBannerList.size() != 0) {
                                    banner.setImages(mBannerList);
                                    banner.start();
                                }
                            }
                        }
                    }
                }));
    }
    //轮播图加载图片
    private class GlideImageLoader implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            RequestOptions options = new RequestOptions().transforms(new CenterCrop());
            Glide.with(context).load((String) path).apply(options).into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }


    @OnClick({R.id.fankui, R.id.gonggao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fankui:
                startActivity(new Intent(getActivity(), YiJianActivity.class));
                break;
            case R.id.gonggao:
                startActivity(new Intent(getActivity(), GongGaoActivity.class));
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shou_ye;
    }
}
