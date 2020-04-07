package com.example.myapplication.yinyong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class YinYongFragment extends BaseFragment {

    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.jifen)
    TextView jifen;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.image_back, R.id.jifen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                break;
            case R.id.jifen:
                startActivity(new Intent(getActivity(),GetJiFenActivity.class));
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yin_yong;
    }
}
