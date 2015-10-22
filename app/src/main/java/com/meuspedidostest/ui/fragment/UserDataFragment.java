package com.meuspedidostest.ui.fragment;

import android.os.Bundle;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import com.meuspedidostest.MeusPedidosApplication;
import com.meuspedidostest.R;
import com.meuspedidostest.di.UserDataModule;
import com.meuspedidostest.di.components.DaggerUserDataComponent;
import com.meuspedidostest.di.components.UserDataComponent;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.activity.BaseActivity;
import com.meuspedidostest.ui.presenter.UserDataPresenter;
import javax.inject.Inject;

/**
 * Esta classe representa a configureção de nome e email do usuário.
 */
public class UserDataFragment extends AbstractFragment implements UserDataPresenter.View {

  private UserDataComponent userDataComponent;

  @Inject UserDataPresenter userDataPresenter;

  @Bind(R.id.name) EditText name;
  @Bind(R.id.email) EditText email;

  @OnClick(R.id.next) void onNextClick() {
    userDataPresenter.setUserData(name.getText().toString(), email.getText().toString());
    userDataPresenter.initialize();
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_user_data;
  }

  /**
   * Injeta o componente na view e declara o callback da presenter
   */
  @Override public void onCreate(Bundle savedInstanceState) {
    userDataComponent().inject(this);
    super.onCreate(savedInstanceState);
    userDataPresenter.setView(this);
  }

  @Override public void onResume() {
    super.onResume();
    userDataPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    userDataPresenter.pause();
  }

  @Override public boolean isReady() {
    return isAdded();
  }

  @Override public void onUserSuccess(User user) {
    ((BaseActivity) getActivity()).notifyUser(user);
  }

  @Override public void onEmptyName() {
    name.setError(getResources().getString(R.string.empty_name_error));
  }

  @Override public void onEmptyEmail() {
    email.setError(getResources().getString(R.string.empty_email_error));
  }

  @Override public void onInvalidEmail() {
    email.setError(getResources().getString(R.string.invalidate_email_error));
  }

  /**
   * Cria a instância do componente e modulos, utilizado para
   * injetar as instancias do presenter.
   *
   * @return nova instancia do componente se necessário
   */
  public UserDataComponent userDataComponent() {
    if (userDataComponent == null) {
      userDataComponent = DaggerUserDataComponent.builder()
          .applicationComponent(((MeusPedidosApplication) getActivity().getApplication()).component())
          .userDataModule(new UserDataModule())
          .build();
    }
    return userDataComponent;
  }

}
