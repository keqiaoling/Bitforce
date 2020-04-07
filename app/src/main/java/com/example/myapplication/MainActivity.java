package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.CompatStatusBarActivity;
import com.example.myapplication.chanpin.ChanPinFragment;
import com.example.myapplication.shouye.fragment.ShouYeFragment;
import com.example.myapplication.utils.StatusBarUtil;
import com.example.myapplication.wakuang.WaKuangFragment;
import com.example.myapplication.wode.WoDeFragment;
import com.example.myapplication.yinyong.YinYongFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab)
    TabLayout tab;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ChangColor(R.color.white);
        tab();
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

    private void tab() {
        List<Fragment> fragment = new ArrayList<>();
        fragment.add(new ShouYeFragment());
        fragment.add(new ChanPinFragment());
        fragment.add(new WaKuangFragment());
        fragment.add(new YinYongFragment());
        fragment.add(new WoDeFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragment.get(position);
            }

            @Override
            public int getCount() {
                return fragment.size();
            }
        });

        tab.setupWithViewPager(viewPager);
        tab.getTabAt(0).setCustomView(getCurrentFocus(R.string.tab_sy, R.drawable.tab_shouye));
        tab.getTabAt(1).setCustomView(getCurrentFocus(R.string.tab_cp, R.drawable.tab_chanpin));
        tab.getTabAt(2).setCustomView(getCurrentFocus(R.string.tab_wk, R.drawable.tab_wakuang));
        tab.getTabAt(3).setCustomView(getCurrentFocus(R.string.tab_yy, R.drawable.tab_yinyong));
        tab.getTabAt(4).setCustomView(getCurrentFocus(R.string.tab_wd, R.drawable.tab_wode));
        tab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("--------------" ,"onTabSelected: +"+tab.getPosition());
                if(tab.getPosition()==0){
                    ChangColor(R.color.white);
                }else if(tab.getPosition()==1){
                    ChangColor(R.color.white);
                }
                else if(tab.getPosition()==2){
                    //沉浸式状态栏
                    StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.wk_bg), 0);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        WebView.enableSlowWholeDocumentDraw();
                    }
                    View decor = getWindow().getDecorView();
                    boolean dark = true;
                    if (dark) {
                        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    } else {
                        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    }
                }
                else if(tab.getPosition()==3){
                    ChangColor(R.color.white);
                }
                else if(tab.getPosition()==4){
                    ChangColor(R.color.white);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public View getCurrentFocus(int a, int drawable) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.tab_main, null);
        TextView text = inflate.findViewById(R.id.text);
        text.setText(a);
        ImageView image = inflate.findViewById(R.id.tab_image);
        image.setImageResource(drawable);
        return inflate;
    }
}
