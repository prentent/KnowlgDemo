package com.example.hp.knowlgdemo.entity;

import com.example.hp.knowlgdemo.utils.LogUtils;

/**
 * Created by HP on 2018/1/17.
 */

public class Persion {
    private String name;
    public int age;
    private String sex;

    public Persion(){
        LogUtils.e("=========","啛啛喳喳");
    }

    public Persion(String str,int age){
        LogUtils.e("=========","s水水水水");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
