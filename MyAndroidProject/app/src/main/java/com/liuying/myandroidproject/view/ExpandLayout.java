package com.liuying.myandroidproject.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 可折叠View
 * Created by liuying on 2018/4/17 15:32.
 * Email: ly1203575492@163.com
 */
public class ExpandLayout extends RelativeLayout {
  private ImageView imgArrow;
  private View layoutView;
  private int viewHeight;
  private boolean isExpand;
  private long animationDuration;
  private boolean lock;

  public ExpandLayout(Context context) {
    this(context, null);
  }

  public ExpandLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ExpandLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initView();
  }

  /**
   * 初始化View
   */
  private void initView() {
    layoutView = this;
    isExpand = true;
    animationDuration = 300;
    setViewDimensions();
  }

  /**
   * 设置指引标题
   */
  public void setArrowImg(ImageView imgArrow) {
    this.imgArrow = imgArrow;
  }

  /**
   * @param isExpand 初始状态是否折叠
   */
  public void initExpand(boolean isExpand) {
    this.isExpand = isExpand;
    setViewDimensions();
  }

  /**
   * 设置动画时间
   *
   * @param animationDuration 动画时间
   */
  public void setAnimationDuration(long animationDuration) {
    this.animationDuration = animationDuration;
  }

  /**
   * 获取subView的总高度
   * View.post()的runnable对象中的方法会在View的measure、layout等事件后触发
   */
  private void setViewDimensions() {
    layoutView.post(new Runnable() {
      @Override public void run() {
        if (viewHeight <= 0) {
          viewHeight = layoutView.getMeasuredHeight();
        }
        setViewHeight(layoutView, isExpand ? viewHeight : 0);
      }
    });
  }

  public static void setViewHeight(View view, int height) {
    final ViewGroup.LayoutParams params = view.getLayoutParams();
    params.height = height;
    view.requestLayout();
  }

  /**
   * 切换动画实现
   */
  private void animateToggle(long animationDuration) {
    ValueAnimator heightAnimation =
        isExpand ? ValueAnimator.ofFloat(0f, viewHeight) : ValueAnimator.ofFloat(viewHeight, 0f);
    heightAnimation.setDuration(animationDuration / 2);
    heightAnimation.setStartDelay(animationDuration / 2);

    heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) (float) animation.getAnimatedValue();

        if (imgArrow != null) {
          imgArrow.setRotation(180 * value / viewHeight);
        }

        setViewHeight(layoutView, value);
        if (value == viewHeight || value == 0) {
          lock = false;
        }
      }
    });

    heightAnimation.start();
    lock = true;
  }

  public boolean isExpand() {
    return isExpand;
  }

  /**
   * 折叠view
   */
  public void collapse() {
    isExpand = false;
    animateToggle(animationDuration);
  }

  /**
   * 展开view
   */
  public void expand() {
    isExpand = true;
    animateToggle(animationDuration);
  }

  public void toggleExpand() {
    if (lock) {
      return;
    }
    if (isExpand) {
      collapse();
    } else {
      expand();
    }
  }
}
