package com.meuspedidostest;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.meuspedidostest.di.ApplicationModule;
import com.meuspedidostest.di.components.ApplicationComponent;
import com.meuspedidostest.di.components.DaggerApplicationComponent;

public class MeusPedidosApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeDependencyInjector().inject(this);
    Fresco.initialize(this);
  }

  @Override public void onTerminate() {
    Fresco.shutDown();
    super.onTerminate();
  }

  private ApplicationComponent initializeDependencyInjector() {
    if (applicationComponent == null) {
      applicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
    return applicationComponent;
  }

  public ApplicationComponent component() {
    return applicationComponent;
  }

}
