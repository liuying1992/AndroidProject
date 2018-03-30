package com.liuying.rndemo.module;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by liuying on 2018/3/15 13:42.
 * Email: ly1203575492@163.com
 */

public class MyReactPackage implements ReactPackage {
  @Override public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    return Arrays.<NativeModule>asList(new MyIntentModule(reactContext));
  }

  @Override public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }
}
