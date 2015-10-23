package com.meuspedidostest.di.components;

import com.meuspedidostest.di.UserDataModule;
import com.meuspedidostest.di.scopes.ActivityScope;
import com.meuspedidostest.ui.fragment.UserDataFragment;
import com.meuspedidostest.ui.presenter.UserDataPresenter;
import dagger.Component;

/**
 * Componente que permite injetar na View UserDataFragment,
 * a referência dos presenters utilizando o seu módulo.
 */
@ActivityScope @Component(dependencies = ApplicationComponent.class,
    modules = { UserDataModule.class })
public interface UserDataComponent {
  void inject(UserDataFragment userDataFragment);
  UserDataPresenter getPresenter();
}