package com.liuying.myandroidproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by liuying on 2018/4/16 17:04.
 * Email: ly1203575492@163.com
 */
public class MyApplication extends Application {
  private static Context mContext;

  @Override public void onCreate() {
    super.onCreate();
    mContext = this;
  }

  public static Context getInstance() {
    return mContext;
  }
}
