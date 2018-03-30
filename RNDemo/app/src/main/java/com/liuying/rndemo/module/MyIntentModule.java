package com.liuying.rndemo.module;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * 定义JS方法
 *
 * Created by liuying on 2018/3/15 13:33.
 * Email: ly1203575492@163.com
 */

public class MyIntentModule extends ReactContextBaseJavaModule {
  private static final String INTENT_MODULE = "INTENT_MODULE";

  public MyIntentModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override public String getName() {
    return INTENT_MODULE;
  }

  /**
   * 打开界面
   */
  @ReactMethod public void openActivity(String name, String params) {
    try {
      Activity currentActivity = getCurrentActivity();
      if (currentActivity != null) {
        Class clz = Class.forName(name);
        Intent intent = new Intent(currentActivity, clz);
        intent.putExtra("params", params);
        currentActivity.startActivity(intent);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
