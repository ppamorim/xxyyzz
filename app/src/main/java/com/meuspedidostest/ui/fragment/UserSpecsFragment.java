package com.meuspedidostest.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.meuspedidostest.MeusPedidosApplication;
import com.meuspedidostest.R;
import com.meuspedidostest.di.UserSpecsModule;
import com.meuspedidostest.di.components.DaggerUserSpecsComponent;
import com.meuspedidostest.di.components.UserSpecsComponent;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.presenter.UserSpecsPresenter;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Esta classe representa a avaliação dos
 * conhecimentos do usuário.
 */
public class UserSpecsFragment extends AbstractFragment implements UserSpecsPresenter.View {

  //Componente utilizado para injetar os modulos.
  private UserSpecsComponent userSpecsComponent;

  //Injeta a referência do presenter.
  @Inject UserSpecsPresenter userSpecsPresenter;

  @Bind(R.id.user_grettings) TextView userGrettings;
  @Bind(R.id.container_specs) LinearLayout containerSpecs;

  //Click listener para quando o usuário clica no botão de finalizar.
  @OnClick(R.id.finish) void onFinishClick() {
    userSpecsPresenter.initialize();
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_user_specs;
  }

  /**
   * Injeta o componente no Fragment e declara o callback e o context para o presenter.
   * @param savedInstanceState Instancia do estado salvo do Fragment.
   */
  @Override public void onCreate(Bundle savedInstanceState) {
    userSpecsComponent().inject(this);
    super.onCreate(savedInstanceState);
    userSpecsPresenter.setView(this);
  }

  /**
   * Carrega os conhecimentos disponíveis na aplicação.
   * @param savedInstanceState Instancia do estado salvo do Fragment
   */
  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    userSpecsPresenter.loadSpecs(getContext());
  }

  @Override public void onResume() {
    super.onResume();
    userSpecsPresenter.resume();
  }

  @Override public void onPause() {
    userSpecsPresenter.pause();
    super.onPause();
  }

  @Override public void onLowMemory() {
    userSpecsPresenter.lowMemory();
    super.onLowMemory();
  }

  /**
   * @return Fragment está visivel e válido
   */
  @Override public boolean isReady() {
    return isAdded();
  }

  /**
   * Informa para o usuário que o email foi enviado com sucesso.
   */
  @Override public void onSendEmailSuccess() {

  }

  /**
   * Informa para o usuário que o envio do email falhou.
   */
  @Override public void onSendEmailFail() {

  }

  /**
   * Monta a view de conhecimentos do usuário.
   * @param specViews Lista de View de conhecimentos.
   */
  @Override public void onSpecsLoaded(ArrayList<SpecView> specViews) {
    containerSpecs.removeAllViews();
    for(SpecView specView : specViews) {
      containerSpecs.addView(specView.createView(LayoutInflater.from(getContext())));
    }
  }

  /**
   * Notifica o usuário para o presenter e configura
   * o nome do usuário para o texto.
   * @param user Instancia do usuário.
   */
  public void notifyUser(User user) {
    userSpecsPresenter.setUser(user);
    userGrettings.setText(
        String.format(getResources().getString(R.string.user_specs_message), user.getName()));
  }

  /**
   * Cria a instância do componente e modulos, utilizado para
   * injetar as instancias do presenter.
   *
   * @return nova instancia do componente se necessário
   */
  public UserSpecsComponent userSpecsComponent() {
    if (userSpecsComponent == null) {
      userSpecsComponent = DaggerUserSpecsComponent.builder()
          .applicationComponent(
              ((MeusPedidosApplication) getActivity().getApplication()).component())
          .userSpecsModule(new UserSpecsModule())
          .build();
    }
    return userSpecsComponent;
  }

}
