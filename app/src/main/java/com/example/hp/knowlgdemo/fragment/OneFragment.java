package com.example.hp.knowlgdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.base.BaseFragment;
import com.example.hp.knowlgdemo.ui.BannerActivity;
import com.example.hp.knowlgdemo.ui.BitmapActivity;
import com.example.hp.knowlgdemo.ui.CollapsingToolbarActivity;
import com.example.hp.knowlgdemo.ui.IndexActivity;
import com.example.hp.knowlgdemo.ui.NDKActivity;
import com.example.hp.knowlgdemo.ui.RectActivity;
import com.example.hp.knowlgdemo.ui.RipplesActivity;
import com.example.hp.knowlgdemo.ui.TouchPullActivity;
import com.example.hp.knowlgdemo.ui.TuXiangActivity;

/**
 * Created by HP on 2017/12/22.
 */

public class OneFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.one_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        inflate.findViewById(R.id.btn_on).setOnClickListener(this);
        inflate.findViewById(R.id.btn_two).setOnClickListener(this);
        inflate.findViewById(R.id.btn_ly).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_on:
                openActivity(getContext(), null, BannerActivity.class);
                break;
            case R.id.btn_two:
                openActivity(getContext(), null, BitmapActivity.class);
                break;
            case R.id.btn_ly:
                openActivity(getContext(), null, RipplesActivity.class);
                break;
            default:
                break;
        }
    }


}
