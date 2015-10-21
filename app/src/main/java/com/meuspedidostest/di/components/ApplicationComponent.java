package com.meuspedidostest.di.components;

import android.app.Application;
import com.meuspedidostest.MeusPedidosApplication;
import com.meuspedidostest.di.ApplicationModule;
import com.meuspedidostest.executor.InteractorExecutor;
import com.meuspedidostest.executor.MainThread;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(MeusPedidosApplication meusPedidosApplication);
  Application application();
  InteractorExecutor executor();
  MainThread mainThread();
}