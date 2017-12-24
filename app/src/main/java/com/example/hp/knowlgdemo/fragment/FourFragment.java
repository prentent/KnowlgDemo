package com.example.hp.knowlgdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.base.BaseFragment;
import com.example.hp.knowlgdemo.ui.NDKActivity;

/**
 * Created by HP on 2017/12/22.
 */

public class FourFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.four_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        inflate.findViewById(R.id.button).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                openActivity(getContext(), null, NDKActivity.class);
                break;
            default:
                break;
        }
    }
}
