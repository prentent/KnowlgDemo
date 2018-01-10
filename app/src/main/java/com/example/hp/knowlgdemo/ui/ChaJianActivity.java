package com.example.hp.knowlgdemo.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ImageFormat;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.knowlgdemo.R;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ChaJianActivity extends AppCompatActivity {

    private ImageView img;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//获取所有控件所有属性
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflaterCompat.setFactory(inflater, new LayoutInflaterFactory() {
//            @Override
//            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//                Log.e("===========",name);
//                for (int i = 0; i < attrs.getAttributeCount(); i++) {
//                    Log.e("===========Name",attrs.getAttributeName(i)+"==="+attrs.getAttributeValue(i));
//                }
//
//                if (name.equals("TextView")) {
//                    return new EditText(context);
//                }
//
//                return null;
//            }
//        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_jian);
        img = (ImageView) findViewById(R.id.img);
        text = (TextView) findViewById(R.id.text);
    }

    public void onClick_cj(View view) {
        switch (view.getId()) {
            case R.id.getdrawable:
                loadPlgin(mSkinPath, mPakegeName, 0);
                break;
            case R.id.gettext:
                loadPlgin(mSkinPath, mPakegeName, 1);
                break;
        }
    }

    private void loadPlgin(String mSkinPath, String mPakegeName, int i) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = assetManager.getClass().getMethod("addAssetPath", String.class);
            method.invoke(assetManager, mSkinPath);

            Resources resources = getResources();
            Resources resources1 = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());

            ResourceManger manger = new ResourceManger(resources1, mPakegeName);
            if (i == 0) {
                Drawable lll = manger.getMipmapByNames("lll");
                if (lll != null) {
                    img.setImageDrawable(lll);
                }
            } else {
                String lll = manger.getStringByName("lll");
                if (lll != null) {
                    text.setText(lll);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private String mSkinPath = Environment.getExternalStorageDirectory() + File.separator + "crash" + File.separator
            + "chajiandemo-debug.apk";
    private String mPakegeName = "com.example.chajiandemo";

    private class ResourceManger {
        private Resources resources;
        private String mPkgNmae;

        public ResourceManger(Resources resources, String mPkgNmae) {
            this.mPkgNmae = mPkgNmae;
            this.resources = resources;
        }

        public Drawable getDrawableByName(String name) {
            try {
                return resources.getDrawable(resources.getIdentifier(name, "drawable", mPkgNmae));
            } catch (Exception e) {
                return null;
            }

        }

        public Drawable getMipmapByNames(String name) {
            try {
                return resources.getDrawable(resources.getIdentifier(name, "mipmap", mPkgNmae));
            } catch (Exception e) {
                return null;
            }

        }

        public ColorStateList getColorByName(String name) {
            try {
                return resources.getColorStateList(resources.getIdentifier(name, "color", mPkgNmae));
            } catch (Exception e) {
                return null;
            }
        }

        public String getStringByName(String name) {
            try {
                return resources.getString(resources.getIdentifier(name, "string", mPkgNmae));
            } catch (Exception e) {
                return null;
            }
        }


    }

}
