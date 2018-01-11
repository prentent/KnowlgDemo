package com.example.hp.knowlgdemo.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.base.BaseFragment;
import com.example.hp.knowlgdemo.ui.QxActivity;
import com.example.hp.knowlgdemo.ui.ThreadPoolActivity;

/**
 * Created by HP on 2017/12/22.
 */

public class TwoFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.two_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        inflate.findViewById(R.id.btn_qx).setOnClickListener(this);
        inflate.findViewById(R.id.btn_xc).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_qx:
//                preMission();
                openActivity(getContext(), null, QxActivity.class);
                break;
            case R.id.btn_xc:
                openActivity(getContext(), null, ThreadPoolActivity.class);
                break;
            default:
                break;
        }
    }

    /*
    fragment开启权限的方法
     */
    private void preMission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        } else {
            Toast.makeText(getContext(), "已有权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 3) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                preMission();
            } else {
                Toast.makeText(getContext(), "开启失败", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
