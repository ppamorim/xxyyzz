package com.meuspedidostest.ui.presenter;

import com.meuspedidostest.domain.model.User;

public interface UserDataPresenter extends Presenter {
  void setName(String name);
  void setEmail(String email);
  interface Callback {
    void onUserCreated(User user);
    void onInvalidName();
    void onInvalidEmail();
  }
}
