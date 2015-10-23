package com.meuspedidostest.domain.interaction;

import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.domain.model.User;
import java.util.ArrayList;

/**
 * Esta interface realiza a conexão entre o presenter e o domain(interactor).
 * A interface declara a instancias de views, o usuário
 * e o contexto da aplicação.
 * A interface Callback retorna o sucesso ou erro do envio dos emails.
 */
public interface GetEmailSender {
  void execute(Callback callback);
  void setSpecs(ArrayList<Spec> specs);
  void setUser(User user);
  void setSubject(String subject);
  void setTypes(String[] types);
  void setContentEmail(String contentEmail);
  boolean specIsValid(Spec spec);
  interface Callback {
    void onEmailCreated();
    void onEmailError();
  }
}
