package com.liuying.aikatestdemo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import static com.liuying.aikatestdemo.R.id.recyclerView;

/**
 * @ explain:
 * @ author：xujun on 2016/10/18 17:21
 * @ email：gdutxiaoxu@163.com
 */
public class ListFragment extends BaseFragment {

  RecyclerView mRecyclerView;
  private static final String KEY = "key";
  private String title = "测试";

  List<String> mDatas = new ArrayList<>();
  private ItemAdapter mAdapter;
  //private SwipeRefreshLayout mSwipeRefreshLayout;
  private boolean isFirst = true;
  private int lastX, lastY;

  public static ListFragment newInstance(String title) {
    ListFragment fragment = new ListFragment();
    Bundle bundle = new Bundle();
    bundle.putString(KEY, title);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override protected void initView(View view) {

    Bundle arguments = getArguments();
    if (arguments != null) {
      title = arguments.getString(KEY);
    }
    mRecyclerView = (RecyclerView) view.findViewById(recyclerView);
    //mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
    //mSwipeRefreshLayout.setEnabled(false);

    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
    DividerItemDecoration itemDecoration =
        new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL);
    mRecyclerView.addItemDecoration(itemDecoration);
    mRecyclerView.setLayoutManager(layoutManager);

    for (int i = 0; i < 50; i++) {
      String s = String.format("我是第%d个" + title, i);
      mDatas.add(s);
    }

    mAdapter = new ItemAdapter(mContext, mDatas);
    mRecyclerView.setAdapter(mAdapter);
    //mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    //  @Override public void onRefresh() {
    //    mSwipeRefreshLayout.postDelayed(new Runnable() {
    //      @Override public void run() {
    //        mSwipeRefreshLayout.setRefreshing(false);
    //        Toast.makeText(mContext, "刷新完成", Toast.LENGTH_SHORT).show();
    //      }
    //    }, 1200);
    //  }
    //});

    onTouch();
  }

  private void onTouch() {

    mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View view, MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        if (isFirst) {
          switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
              lastX = x;
              lastY = y;
              break;
            case MotionEvent.ACTION_MOVE:
              if (lastY == 0) {
                lastX = x;
                lastY = y;
              }
              int offsetY = y - lastY;
              lastX = x;
              lastY = y;
              ((MainActivity) getActivity()).setLayoutParams(offsetY);
              return false;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
              ((MainActivity) getActivity()).goBackLayout();
              return false;
          }
        }
        return false;
      }
    });

    //mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    //  //用来标记是否正在向最后一个滑动
    //  boolean isSlidingToLast = false;
    //
    //  @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    //    super.onScrollStateChanged(recyclerView, newState);
    //    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
    //    if (manager.findFirstCompletelyVisibleItemPosition() == 0) {
    //      isFirst = true;
    //    } else {
    //      isFirst = false;
    //    }
    //  }
    //
    //  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    //    super.onScrolled(recyclerView, dx, dy);
    //    //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
    //    if (dx > 0) {
    //      //大于0表示正在向右滚动
    //      isSlidingToLast = true;
    //    } else {
    //      //小于等于0表示停止或向左滚动
    //      isSlidingToLast = false;
    //    }
    //  }
    //});
  }

  public void tooglePager(boolean isOpen) {
    if (isOpen) {
      setRefreshEnable(false);
      scrollToFirst(false);
    } else {
      setRefreshEnable(true);
    }
  }

  public void scrollToFirst(boolean isSmooth) {
    if (mRecyclerView == null) {
      return;
    }
    if (isSmooth) {
      mRecyclerView.smoothScrollToPosition(0);
    } else {
      mRecyclerView.scrollToPosition(0);
    }
  }

  public void setRefreshEnable(boolean enabled) {
    //if (mSwipeRefreshLayout != null) {
    //  mSwipeRefreshLayout.setEnabled(enabled);
    //}
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_list;
  }

  @Override public void fetchData() {

  }
}
