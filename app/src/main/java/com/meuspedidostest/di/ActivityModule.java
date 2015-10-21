package com.meuspedidostest.di;

import android.app.Activity;
import com.meuspedidostest.di.scopes.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {

  private final Activity activityContext;

  public ActivityModule(Activity activityContext) {
    this.activityContext = activityContext;
  }

  @Provides @ActivityScope Activity getActivityContext() {
    return activityContext;
  }

}