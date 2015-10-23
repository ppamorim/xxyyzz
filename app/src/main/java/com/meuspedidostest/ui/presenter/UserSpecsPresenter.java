package com.meuspedidostest.ui.presenter;

import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;

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
public interface UserSpecsPresenter extends Presenter {
  void setView(View view);
  void setUser(User user);
  void setSpecs(ArrayList<SpecView> specViews);
  void setSubject(String subject);
  void setTypes(ArrayList<String> types);
  void setContentEmail(String contentEmail);
  void lowMemory();
  User getUser();
  interface View {
    boolean isReady();
    void onSendEmailSuccess();
    void onSendEmailFail();
    void onSpecsLoaded(ArrayList<SpecView> specViews);
  }
}
