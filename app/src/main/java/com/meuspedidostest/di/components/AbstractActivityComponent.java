package com.meuspedidostest.di.components;

import android.app.Activity;
import com.meuspedidostest.di.ActivityModule;
import com.meuspedidostest.di.scopes.ActivityScope;
import dagger.Component;

@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface AbstractActivityComponent {
  Activity activityContext();
}