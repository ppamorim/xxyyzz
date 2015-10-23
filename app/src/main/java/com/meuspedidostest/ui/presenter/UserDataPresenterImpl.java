package com.meuspedidostest.ui.presenter;

import android.util.Patterns;
import com.meuspedidostest.domain.model.User;
import javax.inject.Inject;

/**
 * Este presenter tem como função de controlar o Fragment
 * de dados do usuário (UserDataFragment).
 * Ele armazena a instância do usuário.
 */
public class UserDataPresenterImpl implements UserDataPresenter {

  private View view;
  private User user;

  @Inject UserDataPresenterImpl() { }

  /**
   * Verifica se a view é valida.
   * @param view Instancia da contract view.
   */
  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  /**
   * Verifica os campos e retorna erro para cada
   * caso (nome vazio, email inválido...).
   * Caso obtenha sucesso, informa a view o
   * sucesso da configuração.
   */
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

  /**
   * Cria nova instância da classe User.
   * @param name Nome do usuário.
   * @param email Email do usuário.
   */
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

  /**
   * Notifica sucesso na configuração do usuário.
   */
  private void notifyUserSuccess(User user) {
    if(view.isReady()) {
      view.onUserSuccess(user);
    }
  }

  /**
   * Notifica para a View que o nome é inválido.
   */
  private void notifyInvalidName() {
    if(view.isReady()) {
      view.onEmptyName();
    }
  }

  /**
   * Notifica para a View que o email é inválido.
   */
  private void notifyInvalidEmail() {
    if(view.isReady()) {
      view.onInvalidEmail();
    }
  }

  /**
   * Verifica se o nome é valido.
   * @param name Nome do usuário.
   * @return True caso o nome não seja vazio,
   * caso contrário, false.
   */
  private boolean validateName(String name) {
    return !name.isEmpty();
  }

  /**
   * Verifica se o email é valido.
   * @param email Email do usuário.
   * @return True caso o email seja válido e não seja vazio,
   * caso contrário, false.
   */
  private boolean validateEmail(String email) {
    return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

}
