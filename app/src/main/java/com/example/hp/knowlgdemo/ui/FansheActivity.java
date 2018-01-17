package com.example.hp.knowlgdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.entity.Persion;
import com.example.hp.knowlgdemo.utils.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FansheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanshe);
//        getClass1();
//        getClass2();
//        getClass3();
        getClass4();
    }

    private void getClass4() {
        try {
            Class<?> aClass = Class.forName("com.example.hp.knowlgdemo.entity.Persion");
            Constructor[] constructors = aClass.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                LogUtils.e("========5555", constructor+"");
                try {
                    LogUtils.e("========6666", constructor.newInstance("",12)+"");
                } catch (Exception e) {
                    LogUtils.e("========5555", e.toString());
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            LogUtils.e("========7777", e.toString());
            e.printStackTrace();
        }

    }

    private void getClass3() {
        Persion persion = new Persion();
        Class<? extends Persion> aClass = persion.getClass();
        LogUtils.e("====333", aClass.getName());
        Constructor[] constructors = aClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            LogUtils.e("========5555", constructor+"");
            try {
                LogUtils.e("========6666", constructor.newInstance("",12)+"");
            } catch (Exception e) {
                LogUtils.e("========5555", e.toString());
                e.printStackTrace();
            }
        }
        try {
            Constructor<? extends Persion> constructor = aClass.getConstructor();
            LogUtils.e("========7777", constructor.newInstance(null)+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field[] fields = aClass.getDeclaredFields();
        for (Field f :fields) {
            LogUtils.e("========", f.getName());
        }

        try {
            Field age = aClass.getField("age");
            Object obj=aClass.newInstance();
            age.set(obj,100);
            LogUtils.e("====222223",age.get(obj)+"");

            Field name = aClass.getDeclaredField("name");
            name.setAccessible(true);
            Object obj1=aClass.newInstance();
            name.set(obj1,"xiix");
            LogUtils.e("====222223",name.get(obj1)+"");
        } catch (Exception e) {
            LogUtils.e("====222223",e.toString());
            e.printStackTrace();
        }
    }

    private void getClass2() {
        try {
            Class<?> aClass = Class.forName("com.example.hp.knowlgdemo.entity.Persion");
            LogUtils.e("====222", aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getClass1() {
        Class<Persion> aClass = Persion.class;
        LogUtils.e("====111", aClass.getName());
    }

}
