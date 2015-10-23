package com.meuspedidostest.di.components;

import android.app.Activity;
import com.meuspedidostest.di.ActivityModule;
import com.meuspedidostest.di.scopes.ActivityScope;
import dagger.Component;

/**
 * Componente que permite injetar, nos interactors,
 * a referência da activity, utilizando o seu módulo.
 */
@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface AbstractActivityComponent {
  Activity activityContext();
}