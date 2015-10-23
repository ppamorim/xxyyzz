package com.meuspedidostest.di;

import com.meuspedidostest.di.scopes.ActivityScope;
import com.meuspedidostest.domain.interaction.GetEmailSender;
import com.meuspedidostest.domain.interaction.GetEmailSenderImpl;
import com.meuspedidostest.ui.presenter.UserSpecsPresenter;
import com.meuspedidostest.ui.presenter.UserSpecsPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Modulo que cria a referência do UserDataPresenter
 * e o interador GetEmailSender.
 */
@Module public class UserSpecsModule {

  @Provides @ActivityScope UserSpecsPresenter provideUserSpecsPresenter(
      UserSpecsPresenterImpl presenter) {
    return presenter;
  }

  @Provides @ActivityScope GetEmailSender provideGetEmailSender(
      GetEmailSenderImpl presenter) {
    return presenter;
  }

}
