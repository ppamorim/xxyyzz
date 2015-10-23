package com.meuspedidostest.ui.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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
 * Esta classe representa a configuração de nome
 * e email do usuário.
 */
public class UserDataFragment extends AbstractFragment implements UserDataPresenter.View {

  //Componente utilizado para injetar os modulos.
  private UserDataComponent userDataComponent;

  //Injeta a referência do presenter.
  @Inject UserDataPresenter userDataPresenter;

  @Bind(R.id.name) EditText name;
  @Bind(R.id.email) EditText email;

  //Click listener para quando o usuário clica no botão de próximo.
  @OnClick(R.id.next) void onNextClick() {
    userDataPresenter.setUserData(name.getText().toString(), email.getText().toString());
    userDataPresenter.initialize();
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_user_data;
  }

  /**
   * Injeta o componente no Fragment e declara o callback da presenter
   * @param savedInstanceState Instancia do estado salvo do Fragment
   */
  @Override public void onCreate(Bundle savedInstanceState) {
    userDataComponent().inject(this);
    super.onCreate(savedInstanceState);
    userDataPresenter.setView(this);
  }

  /**
   * Configura o botão de send do teclado do Android
   */
  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    configEnterClick();
  }

  @Override public void onResume() {
    super.onResume();
    userDataPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    userDataPresenter.pause();
  }

  /**
   * @return Fragment está visivel e válido
   */
  @Override public boolean isReady() {
    return isAdded();
  }

  @Override public void onUserSuccess(User user) {
    ((BaseActivity) getActivity()).notifyUser(user);
  }

  @Override public void onEmptyName() {
    name.setError(getResources().getString(R.string.empty_name_error));
  }

  @Override public void onInvalidEmail() {
    email.setError(getResources().getString(R.string.invalidate_email_error));
  }

  /**
   * Cria a instância do componente e modulos, utilizado para
   * injetar as instancias do presenter.
   *
   * @return nova instancia do componente se necessário.
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

  /**\
   * Configura o click no botão de enviar do
   * teclado do Android.
   */
  private void configEnterClick() {
    email.setOnKeyListener(new View.OnKeyListener(){
      @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (keyCode) {
          case KeyEvent.KEYCODE_ENTER:
            onNextClick();
            break;
        }
        return false;
      }
    });
  }

}
