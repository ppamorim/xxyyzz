package com.meuspedidostest;

import android.app.Application;
import com.meuspedidostest.di.ApplicationModule;
import com.meuspedidostest.di.components.ApplicationComponent;
import com.meuspedidostest.di.components.DaggerApplicationComponent;
import com.squareup.leakcanary.LeakCanary;

/**
 * Classe que determina as configurações principais da aplicação.
 */
public class MeusPedidosApplication extends Application {

  private ApplicationComponent applicationComponent;

  /**
   * Injeta as dependências principais (Interadores e Contextos).
   */
  @Override public void onCreate() {
    super.onCreate();
    initializeDependencyInjector().inject(this);
    //LeakCanary.install(this); Ferramenta não utilizada durante produção
  }

  /**
   * Inicializa o componente da aplicação para permitir a
   * injeção de dependência.
   * @return O componente da aplicação.
   */
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
