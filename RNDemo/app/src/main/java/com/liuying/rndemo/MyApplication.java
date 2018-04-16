package com.liuying.rndemo;

import android.app.Application;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.liuying.rndemo.module.MyReactPackage;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

/**
 * Created by liuying on 2018/3/12 16:37.
 * Email: ly1203575492@163.com
 */

public class MyApplication extends Application implements ReactApplication {
  @Override public void onCreate() {
    super.onCreate();
  }

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(new MainReactPackage(), new MyReactPackage());
    }

    @Nullable @Override protected String getJSBundleFile() {
      return "index";
    }
  };

  @Override public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }
}
