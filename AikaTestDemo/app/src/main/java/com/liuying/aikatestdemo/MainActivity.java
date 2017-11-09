package com.liuying.aikatestdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements AppBarLayout.OnOffsetChangedListener {
  ViewPager mViewPager;
  List<Fragment> mFragments;
  Toolbar mToolbar;
  private AppBarLayout mAppBarLayout;
  private ImageView mBgImgView, mAvatarImg;
  private LinearLayout mCollapsingToolbarLayout;
  String[] mTitles = new String[] {
      "主页", "微博", "相册"
  };
  private boolean isVerticalOffset = true;
  private int lastX, lastY;
  private boolean isTouch = false;
  //XY坐标
  private float downX, downY;

  //X/Y方向速度相关的帮助类
  private VelocityTracker velocityTracker;
  //回弹系数设置
  private float mStrength = SpringForce.STIFFNESS_HIGH;//强度
  private float mDamping = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY;//阻尼系数

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mViewPager = (ViewPager) findViewById(R.id.viewpager);
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mAppBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
    mBgImgView = (ImageView) findViewById(R.id.main_backdrop);
    mCollapsingToolbarLayout = (LinearLayout) findViewById(R.id.layout_parent);
    mAvatarImg = (ImageView) findViewById(R.id.img_avatar);
    mToolbar.setTitle("");
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    mAppBarLayout.addOnOffsetChangedListener(this);
    setupViewPager();
    setImageViewParams();
    initTouch();
  }

  private void setImageViewParams() {

    velocityTracker = VelocityTracker.obtain();

    LinearLayout.LayoutParams layoutParams =
        (LinearLayout.LayoutParams) mBgImgView.getLayoutParams();
    layoutParams.height = DensityUtils.dp2px(this, 210);
    layoutParams.width = DensityUtils.getScreenWidth(this);
    mBgImgView.setLayoutParams(layoutParams);
  }

  private void initTouch() {
    CoordinatorLayout.LayoutParams params =
        (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
    AppBarLayout.Behavior behavior = new AppBarLayout.Behavior() {
      //@Override public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child,
      //    MotionEvent ev) {
      //  return super.onInterceptTouchEvent(parent, child, ev);
      //}

      @Override
      public boolean onTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        doTouch(child, ev);
        return super.onTouchEvent(parent, child, ev);
      }
    };
    params.setBehavior(behavior);

    mAvatarImg.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            downX = event.getX();
            downY = event.getY();
            velocityTracker.addMovement(event);
            break;
          case MotionEvent.ACTION_MOVE:
            isTouch = true;
            mAvatarImg.setTranslationX(event.getX() - downX);
            mAvatarImg.setTranslationY(event.getY() - downY);
            return true;
          case MotionEvent.ACTION_UP:
          case MotionEvent.ACTION_CANCEL:
            velocityTracker.computeCurrentVelocity(1000);
            if (mAvatarImg.getTranslationX() != 0) {
              SpringAnimation animX =
                  new SpringAnimation(mAvatarImg, SpringAnimation.TRANSLATION_X, 0);
              animX.getSpring().setStiffness(mStrength);
              animX.getSpring().setDampingRatio(mDamping);
              animX.setStartVelocity(velocityTracker.getXVelocity());
              animX.start();
            }
            if (mAvatarImg.getTranslationY() != 0) {
              SpringAnimation animY =
                  new SpringAnimation(mAvatarImg, SpringAnimation.TRANSLATION_Y, 0);
              animY.getSpring().setStiffness(mStrength);
              animY.getSpring().setDampingRatio(mDamping);
              animY.setStartVelocity(velocityTracker.getYVelocity());
              animY.start();
            }
            velocityTracker.clear();
            return false;
        }
        return false;
      }
    });
  }

  /**
   * 手势监听
   */
  private boolean doTouch(AppBarLayout child, MotionEvent event) {
    int x = (int) event.getRawX();
    int y = (int) event.getRawY();
    int approHeight = DensityUtils.getScreenWidth(this) / 2;
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        lastX = x;
        lastY = y;
        break;
      case MotionEvent.ACTION_MOVE:

        int increaseMax = approHeight * 2 / 3;
        int angleMaxHeight = increaseMax * 3 / 4;
        int limitHeight = increaseMax + approHeight;

        int offsetX = x - lastX;
        int offsetY = y - lastY;

        lastX = x;
        lastY = y;

        setLayoutParams(offsetY);

        //此处拦截事件避免滑动冲突}
        return true;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        goBackLayout();
        return false;
    }
    return false;
  }

  public void goBackLayout() {
    goBackCircle();
  }

  private void goBackCircle() {
    final ViewGroup.LayoutParams imglp = mBgImgView.getLayoutParams();
    final float imgw = DensityUtils.getScreenWidth(this);// 图片当前宽度
    final float imgh = mBgImgView.getLayoutParams().height;// 图片当前高度
    final float newimgW = DensityUtils.getScreenWidth(this);// 图片原宽度
    final float newimgH = DensityUtils.dp2px(this, 210);// 图片原高度

    final ViewGroup.LayoutParams colllp = mCollapsingToolbarLayout.getLayoutParams();
    final float collw = DensityUtils.getScreenWidth(this);// 当前宽度
    final float collh = mCollapsingToolbarLayout.getLayoutParams().height;// 当前高度
    final float newcollW = DensityUtils.getScreenWidth(this);// 图片原宽度
    final float newcollH = DensityUtils.dp2px(this, 335);// 图片原高度

    // 设置动画
    ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration(200);
    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        float cVal = (Float) animation.getAnimatedValue();
        //背景图
        imglp.width = (int) (imgw - (imgw - newimgW) * cVal);
        imglp.height = (int) (imgh - (imgh - newimgH) * cVal);
        mBgImgView.setLayoutParams(imglp);
        //layout
        colllp.width = (int) (collw - (collw - newcollW) * cVal);
        colllp.height = (int) (collh - (collh - newcollH) * cVal);
        mCollapsingToolbarLayout.setLayoutParams(colllp);
        LUtils.e((int) (collh - (collh - newcollH) * cVal) + "高度");
      }
    });
    anim.start();
  }

  private void setupViewPager() {
    final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    setupViewPager(viewPager);

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupViewPager(ViewPager viewPager) {
    mFragments = new ArrayList<>();
    for (int i = 0; i < mTitles.length; i++) {
      //ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
      ScrollFragment scrollFragment = ScrollFragment.newInstance();
      mFragments.add(scrollFragment);
    }
    BaseFragmentAdapter adapter =
        new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);

    viewPager.setAdapter(adapter);
  }

  @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    LUtils.e(verticalOffset + "------------");
    if (appBarLayout.getTotalScrollRange()==verticalOffset){

    }
    if (0 == verticalOffset) {
      isVerticalOffset = true;
    } else {
      isVerticalOffset = false;
    }
  }

  public void setLayoutParams(int offsetY) {
    LUtils.e("offsetY:==" + offsetY);
    if (offsetY > 0 && isVerticalOffset) {
      int needHeight = mBgImgView.getHeight() + offsetY;

      if (needHeight > DensityUtils.getScreenWidth(this)) {
        return;
      }

      LinearLayout.LayoutParams layoutParams =
          (LinearLayout.LayoutParams) mBgImgView.getLayoutParams();
      layoutParams.height = needHeight;
      mBgImgView.setLayoutParams(layoutParams);

      int collNeedHeight = mCollapsingToolbarLayout.getHeight() + offsetY;
      CollapsingToolbarLayout.LayoutParams cooLayout =
          (CollapsingToolbarLayout.LayoutParams) mCollapsingToolbarLayout.getLayoutParams();
      cooLayout.height = collNeedHeight;
      mCollapsingToolbarLayout.setLayoutParams(cooLayout);
    }
  }

  public boolean getIsOffset() {
    return isVerticalOffset;
  }

  public boolean getIsTouchAvatar() {
    return isTouch;
  }
}
