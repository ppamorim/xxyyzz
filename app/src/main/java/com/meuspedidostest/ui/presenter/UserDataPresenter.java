package com.meuspedidostest.ui.presenter;

import com.meuspedidostest.domain.model.User;

public interface UserDataPresenter extends Presenter {
  void setView(View view);
  void setUserData(String name, String email);
  void setUser(User user);
  interface View {
    boolean isReady();
    void onUserSuccess(User user);
    void onEmptyName();
    void onEmptyEmail();
    void onInvalidEmail();
  }
}
