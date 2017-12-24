//
// Created by HP on 2017/12/23.
//
#include<stdio.h>
#include<stdlib.h>
#include "com_example_hp_knowlgdemo_ui_NDKActivity.h"

JNIEXPORT jstring JNICALL Java_com_example_hp_knowlgdemo_ui_NDKActivity_getStringFromC
  (JNIEnv *env, jclass jclass){

  return (*env)->NewStringUTF(env,"Hello from JNI!!!");
  };

  JNIEXPORT jstring JNICALL Java_com_example_hp_knowlgdemo_ui_NDKActivity_getStringFromB
    (JNIEnv *env, jclass jclass){

    return (*env)->NewStringUTF(env,"bbbbbb!!!");
    };

