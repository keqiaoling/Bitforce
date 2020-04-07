package com.example.myapplication.wakuang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.chanpin.SuanLiFragment;
import com.example.myapplication.chanpin.YiJieShuFragment;
import com.example.myapplication.chanpin.ZuPingFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */

//挖矿fragment
public class WaKuangFragment extends BaseFragment {


    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public WaKuangFragment() {
        // Required empty public constructor

    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        tab();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wa_kuang;
    }

    private void tab() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new WkIngFragment());
        fragments.add(new DaiSjFragment());
        fragments.add(new WkJieShuFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tab.setInlineLabel(true);
        tab.setupWithViewPager(viewPager);
        tab.getTabAt(0).setCustomView(getCurrentFocus(R.string.tab_zp, R.drawable.tab_chanpin_bg));
        tab.getTabAt(1).setCustomView(getCurrentFocus(R.string.tab_sl, R.drawable.tab_chanpin_bg));
        tab.getTabAt(2).setCustomView(getCurrentFocus(R.string.tab_yjs, R.drawable.tab_chanpin_bg));
    }

    public View getCurrentFocus(int title, int drawable) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.tab_chanpin, null);
        TextView text = inflate.findViewById(R.id.text);
        text.setText(title);
        TextView text_bg = inflate.findViewById(R.id.text_bg);
        text_bg.setBackgroundResource(drawable);
        return inflate;
    }

}
