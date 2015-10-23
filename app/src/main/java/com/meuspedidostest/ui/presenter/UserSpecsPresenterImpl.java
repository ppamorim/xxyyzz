package com.meuspedidostest.ui.presenter;

import com.meuspedidostest.domain.interaction.GetEmailSender;
import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Este presenter tem como função de controlar o Fragment
 * de conhecimentos do usuário (UserSpecsFragment).
 * Ela também armazena a instância do usuário e a lista
 * de conhecimentos do usuário, assim como sua interação
 * (envio de email).
 */
public class UserSpecsPresenterImpl implements UserSpecsPresenter {

  private View view;
  private User user;
  private GetEmailSender getEmailSender;
  private ArrayList<SpecView> specViews;
  private ArrayList<String> types;
  private String subject;
  private String contentEmail;

  /**
   * Injeta a instancia do interactor de envio de email.
   * @param getEmailSender Referência do interactor de envio de email.
   */
  @Inject public UserSpecsPresenterImpl(GetEmailSender getEmailSender) {
    this.getEmailSender = getEmailSender;
  }

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
   * Configura e inicializa a thread de envio de email,
   * adquire o valor de cada conhecimento do usuário,
   * informando os dados do usuário e os seus conhecimentos.
   */
  @Override public void initialize() {
    if(user != null
        && specViews != null
        && contentEmail != null
        && types != null
        && subject != null) {
      ArrayList<Spec> specs = new ArrayList<>();

      for (SpecView specView : specViews) {
        Spec spec = specView.getSpec();
        spec.setRate(specView.getRate());
        specs.add(spec);
      }

      getEmailSender.setContentEmail(contentEmail);
      getEmailSender.setTypes(types);
      getEmailSender.setSubject(subject);
      getEmailSender.setUser(user);
      getEmailSender.setSpecs(specs);
      getEmailSender.execute(new GetEmailSender.Callback() {

        @Override public void onEmailCreated() {
          notifySendEmailSuccess();
        }

        @Override public void onEmailError() {
          notifySendEmailFail();
        }
      });
    }
  }

  @Override public void resume() { }

  @Override public void pause() { }

  /**
   * Limpa a memória caso necessário.
   */
  @Override public void lowMemory() {
    cleanListIfNeeded();
  }

  /**
   * Declara instância do usuário, utilizado no
   * salvamento de instância.
   * @param user
   */
  @Override public void setUser(User user) {
    this.user = user;
  }

  /**
   * @return Instância do usuário.
   */
  @Override public User getUser() {
    return this.user;
  }

  /**
   * Declara o titulo do email.
   * @param subject titulo do email.
   */
  @Override public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * Declara os tipos de email para o interactor.
   * @param types tipos de email.
   */
  @Override public void setTypes(ArrayList<String> types) {
    this.types = types;
  }

  /**
   * Declara o conteúdo do email para o interactor.
   * @param contentEmail Conteúdo do email.
   */
  @Override public void setContentEmail(String contentEmail) {
    this.contentEmail = contentEmail;
  }

  @Override public void setSpecs(ArrayList<SpecView> specViews) {
    this.specViews = specViews;
  }

  /**
   * Notifica para o Fragment o sucesso do envio do email.
   */
  private void notifySendEmailSuccess() {
    if (view.isReady()) {
      view.onSendEmailSuccess();
    }
  }

  /**
   * Notifica ara o Fragment a falha de envio do email.
   */
  private void notifySendEmailFail() {
    if (view.isReady()) {
      view.onSendEmailFail();
    }
  }

  /**
   * Limpa a lista de conhecimentos para liberar memória.
   */
  private void cleanListIfNeeded() {
    if (specViews != null) {
      specViews.clear();
    }
  }

}
