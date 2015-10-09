package com.meuspedidostest;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MeusPedidosApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);
  }

  @Override public void onTerminate() {
    Fresco.shutDown();
    super.onTerminate();
  }

}
