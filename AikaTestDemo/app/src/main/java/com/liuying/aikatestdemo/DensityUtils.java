package com.liuying.aikatestdemo;

import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by liuying on 2017/6/12 13:58.
 * Email: ly1203575492@163.com
 */

public class DensityUtils {
  private static int screenWidth = 0;
  private static int screenHeight = 0;

  private DensityUtils() {
    throw new UnsupportedOperationException("cannot be instantiated");
  }

  public static int dp2px(Context context, float dpVal) {
    return (int) TypedValue.applyDimension(1, dpVal, context.getResources().getDisplayMetrics());
  }

  public static int sp2px(Context context, float spVal) {
    return (int) TypedValue.applyDimension(2, spVal, context.getResources().getDisplayMetrics());
  }

  public static float px2dp(Context context, float pxVal) {
    float scale = context.getResources().getDisplayMetrics().density;
    return pxVal / scale;
  }

  public static float px2sp(Context context, float pxVal) {
    return pxVal / context.getResources().getDisplayMetrics().scaledDensity;
  }

  public static int getScreenHeight(Context c) {
    if (screenHeight == 0) {
      WindowManager wm = (WindowManager) c.getSystemService("window");
      Display display = wm.getDefaultDisplay();
      Point size = new Point();
      display.getSize(size);
      screenHeight = size.y;
    }

    return screenHeight;
  }

  public static int getScreenWidth(Context c) {
    if (screenWidth == 0) {
      WindowManager wm = (WindowManager) c.getSystemService("window");
      Display display = wm.getDefaultDisplay();
      Point size = new Point();
      display.getSize(size);
      screenWidth = size.x;
    }

    return screenWidth;
  }
}
