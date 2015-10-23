package com.meuspedidostest;

import android.os.Handler;
import android.os.Looper;
import com.meuspedidostest.executor.MainThread;
import org.junit.Before;
import org.mockito.Mockito;

public class MainThreadImplTest implements MainThread {

  Handler handler;
  Looper looper;

  @Before public void setUp() throws Exception {
    handler = Mockito.spy(new Handler());
    looper = Mockito.spy(Looper.getMainLooper());
  }

  @Override public void post(Runnable runnable) {
    handler.post(runnable);
  }

}
