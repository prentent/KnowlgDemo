package com.example.hp.knowlgdemo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * fragment 父类.
 */

public class BaseFragment extends Fragment {


    /*
    打开指定Activity的方法
     */
    protected void openActivity(Context context, Bundle bundle, Class<?> c) {
        Intent intent = new Intent(context, c);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
