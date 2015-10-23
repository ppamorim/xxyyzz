package com.meuspedidostest.ui.presenter;

import android.util.Patterns;
import com.meuspedidostest.domain.model.User;
import javax.inject.Inject;

public class UserDataPresenterImpl implements UserDataPresenter {

  private View view;
  private User user;

  @Inject UserDataPresenterImpl() { }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void initialize() {
    if(this.user != null) {
      boolean validName = validateName(this.user.getName());
      boolean validEmail = validateEmail(this.user.getEmail());
      if (!validName) {
        notifyInvalidName();
      }
      if (!validEmail) {
        notifyInvalidEmail();
      }
      if(validName && validEmail) {
        notifyUserSuccess(this.user);
      }
    }
  }

  @Override public void setUserData(String name, String email) {
    setUser(new User(name, email));
  }

  @Override public void setUser(User user) {
    this.user = user;
  }

  @Override public void resume() { }

  @Override public void pause() {
    this.user = null;
  }

  private void notifyUserSuccess(User user) {
    if(view.isReady()) {
      view.onUserSuccess(user);
    }
  }

  private void notifyInvalidName() {
    if(view.isReady()) {
      view.onEmptyName();
    }
  }

  private void notifyInvalidEmail() {
    if(view.isReady()) {
      view.onInvalidEmail();
    }
  }

  private boolean validateName(String name) {
    return !name.isEmpty();
  }

  private boolean validateEmail(String email) {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

}
