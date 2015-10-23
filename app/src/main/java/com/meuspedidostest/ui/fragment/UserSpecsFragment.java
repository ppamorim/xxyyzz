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

public class UserSpecsFragment extends AbstractFragment implements UserSpecsPresenter.View {

  private UserSpecsComponent userSpecsComponent;

  @Inject UserSpecsPresenter userSpecsPresenter;

  @Bind(R.id.user_grettings) TextView userGrettings;
  @Bind(R.id.container_specs) LinearLayout containerSpecs;

  @OnClick(R.id.finish) void onFinishClick() {
    userSpecsPresenter.initialize();
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_user_specs;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    userSpecsComponent().inject(this);
    super.onCreate(savedInstanceState);
    userSpecsPresenter.setView(this);
    userSpecsPresenter.setContext(getContext());
  }

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

  @Override public boolean isReady() {
    return isAdded();
  }

  @Override public void onSendEmailSuccess() {

  }

  @Override public void onSendEmailFail() {

  }

  @Override public void onSpecsLoaded(ArrayList<SpecView> specViews) {
    containerSpecs.removeAllViews();
    for(SpecView specView : specViews) {
      containerSpecs.addView(specView.createView(LayoutInflater.from(getContext())));
    }
  }

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
