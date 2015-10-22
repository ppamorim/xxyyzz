package com.meuspedidostest.di.components;

import com.meuspedidostest.di.UserSpecsModule;
import com.meuspedidostest.di.scopes.ActivityScope;
import com.meuspedidostest.domain.interaction.GetEmailSender;
import com.meuspedidostest.ui.fragment.UserSpecsFragment;
import com.meuspedidostest.ui.presenter.UserSpecsPresenter;
import dagger.Component;

@ActivityScope @Component(dependencies = ApplicationComponent.class,
    modules = { UserSpecsModule.class })
public interface UserSpecsComponent {
  void inject(UserSpecsFragment userSpecsFragment);
  UserSpecsPresenter getPresenter();
  GetEmailSender getEmailSender();
}
