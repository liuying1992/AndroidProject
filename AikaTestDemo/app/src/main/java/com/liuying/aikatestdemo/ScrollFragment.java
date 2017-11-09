package com.liuying.aikatestdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import static com.liuying.aikatestdemo.R.id.nest_scroll;

/**
 * Created by liuying on 2017/6/12 19:48.
 * Email: ly1203575492@163.com
 */

public class ScrollFragment extends Fragment {
  private NestedScrollView mNestedScrollView;
  private int lastX, lastY;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_scroll_view, null);
    mNestedScrollView = (NestedScrollView) view.findViewById(nest_scroll);
    initTouch();
    return view;
  }

  private void initTouch() {
    mNestedScrollView.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View view, MotionEvent event) {
        //if (((MainActivity) getActivity()).getIsTouchAvatar()) {
        //  return false;
        //}
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

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
        return false;
      }
    });
  }

  public static ScrollFragment newInstance() {
    Bundle args = new Bundle();
    ScrollFragment fragment = new ScrollFragment();
    fragment.setArguments(args);
    return fragment;
  }
}
