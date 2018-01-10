package com.example.hp.knowlgdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.ui.AnmaitorActivity;
import com.example.hp.knowlgdemo.ui.BootSheetActivity;
import com.example.hp.knowlgdemo.ui.ChaJianActivity;

/**
 * Created by HP on 2017/12/22.
 */

public class ThreeFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_three, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        inflate.findViewById(R.id.btn_jj).setOnClickListener(this);
        inflate.findViewById(R.id.btn_sx).setOnClickListener(this);
        inflate.findViewById(R.id.btn_db).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jj:
                startActivity(new Intent(getContext(), ChaJianActivity.class));
                break;
            case R.id.btn_sx:
                startActivity(new Intent(getContext(), AnmaitorActivity.class));
                break;
            case R.id.btn_db:
                startActivity(new Intent(getContext(), BootSheetActivity.class));
                break;
        }
    }
}
