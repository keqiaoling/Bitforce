package com.example.myapplication.wakuang;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */

//挖矿——待上架
public class DaiSjFragment extends Fragment {


    public DaiSjFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dai_sj, container, false);
    }

}
