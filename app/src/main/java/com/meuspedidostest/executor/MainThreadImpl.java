package com.meuspedidostest.executor;

import android.os.Handler;
import android.os.Looper;
import javax.inject.Inject;

public class MainThreadImpl implements MainThread {

  private Handler handler;

  @Inject MainThreadImpl() {
    this.handler = new Handler(Looper.getMainLooper());
  }

  public void post(Runnable runnable) {
    handler.post(runnable);
  }

}
