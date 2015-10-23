package com.meuspedidostest.ui.presenter;

import com.meuspedidostest.domain.model.User;

/**
 * Esta interface tem como trabalho gerar uma conexão
 * entre a View(Fragment) e Presenter.
 * Ela configura os callbacks, os dados do usuário
 * e se caso necessário, o usuário(util para salvamento
 * de instância).
 *
 * A interface View tem como objetivo informar a
 * View das interações realizadas no Presenter.
 */
public interface UserDataPresenter extends Presenter {
  void setView(View view);
  void setUserData(String name, String email);
  void setUser(User user);
  User getUser();
  interface View {
    boolean isReady();
    void onUserSuccess(User user);
    void onEmptyName();
    void onEmptyEmail();
    void onInvalidEmail();
  }
}
