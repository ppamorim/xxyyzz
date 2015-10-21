package com.meuspedidostest.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Classe customizada do ViewPager que desabilita o slide
 */
public class NonSwipeableViewPager extends ViewPager {

  public NonSwipeableViewPager(Context context) {
    super(context);
  }

  public NonSwipeableViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent event) {
    return false;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    return false;
  }

}