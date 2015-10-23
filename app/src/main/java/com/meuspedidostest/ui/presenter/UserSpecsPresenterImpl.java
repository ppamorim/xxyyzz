package com.meuspedidostest.ui.presenter;

import android.content.Context;
import com.meuspedidostest.R;
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

  private Context context;
  private View view;
  private User user;
  private GetEmailSender getEmailSender;
  private ArrayList<SpecView> specViews;

  /**
   * Injeta a instancia do interactor de envio de email.
   * @param getEmailSender Referência do interactor de envio de email.
   */
  @Inject UserSpecsPresenterImpl(GetEmailSender getEmailSender) {
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
   * informando os dados do usuário e os seus conhecimentos.
   */
  @Override public void initialize() {
    getEmailSender.setContext(context);
    getEmailSender.setUser(user);
    getEmailSender.setSpecView(specViews);
    getEmailSender.execute(new GetEmailSender.Callback() {

      @Override public void onEmailCreated() {
        notifySendEmailSuccess();
      }

      @Override public void onEmailError() {
        notifySendEmailFail();
      }
    });
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
   * Gera as views de conhecimento do usuário.
   * @param context Contexto da aplicação
   */
  @Override public void loadSpecs(Context context) {
    this.context = context;
    cleanListIfNeeded();
    String[] items = context.getResources().getStringArray(R.array.specs);
    specViews = new ArrayList<>(items.length);
    for(String item : items) {
      specViews.add(new SpecView(new Spec(item)));
    }
    onSpecsLoadSuccess(specViews);
  }

  /**
   * Informa o sucesso na criação dos conhecimentos
   * do usuário
   * @param specViews Lista de conhecimentos
   */
  private void onSpecsLoadSuccess(ArrayList<SpecView> specViews) {
    if(view.isReady()) {
      view.onSpecsLoaded(specViews);
    }
  }

  /**
   * Notifica para o Fragment o sucesso do envio do email.
   */
  private void notifySendEmailSuccess() {
    if(view.isReady()) {
      view.onSendEmailSuccess();
    }
  }

  /**
   * Notifica ara o Fragment a falha de envio do email.
   */
  private void notifySendEmailFail() {
    if(view.isReady()) {
      view.onSendEmailFail();
    }
  }

  /**
   * Limpa a lista de conhecimentos para liberar memória.
   */
  private void cleanListIfNeeded() {
    if(specViews != null) {
      specViews.clear();
    }
  }

}
