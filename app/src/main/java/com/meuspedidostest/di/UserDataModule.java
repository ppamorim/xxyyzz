package com.meuspedidostest.di;

import com.meuspedidostest.di.scopes.ActivityScope;
import com.meuspedidostest.ui.presenter.UserDataPresenter;
import com.meuspedidostest.ui.presenter.UserDataPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class UserDataModule {
  @Provides @ActivityScope UserDataPresenter provideUserDataPresenter(
      UserDataPresenterImpl presenter) {
    return presenter;
  }
}
