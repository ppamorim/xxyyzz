package com.meuspedidostest.domain.interaction;

import android.content.Context;
import com.meuspedidostest.domain.model.Email;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;

/**
 * Esta interface realiza a conexão entre o presenter e o domain(interactor).
 * A interface declara a instancias de views, o usuário
 * e o contexto da aplicação.
 * A interface Callback retorna o sucesso ou erro do envio dos emails.
 */
public interface GetEmailSender {
  void execute(Callback callback);
  void setSpecView(ArrayList<SpecView> specViews);
  void setUser(User user);
  void setContext(Context context);
  interface Callback {
    void onEmailCreated();
    void onEmailError();
  }
}
