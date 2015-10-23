package com.meuspedidostest.domain.interaction;

import android.content.Context;
import android.content.res.Resources;
import com.meuspedidostest.R;
import com.meuspedidostest.domain.model.Email;
import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.domain.service.SendEmailService;
import com.meuspedidostest.executor.Interactor;
import com.meuspedidostest.executor.InteractorExecutor;
import com.meuspedidostest.executor.MainThread;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Esta é a implementação do envio de email, ela
 * aproveita a instância da pool de interactors e
 * da mainThread (onde realiza o post para a
 * main thread).
 *
 * Aqui é realizado a avaliação de cada spec declarada
 * pelo usuario, assim como a montagem de cada filtro.
 *
 * Após é enviado quantas vezes necessário o email,
 * após, notifica o presenter que o interaction
 * finalizou.
 */
public class GetEmailSenderImpl extends BaseImpl implements Interactor, GetEmailSender {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private ArrayList<Spec> specs;
  private User user;
  private Callback callback;
  private String[] types;
  private String subject;
  private String contentEmail;

  /**
   * Declara as intâncias nas variáveis utilizando
   * interfaces providas pelo Dagger.
   *
   * @param interactorExecutor Referência da pool de interactions.
   * @param mainThread Referencia da mainThread.
   */
  @Inject public GetEmailSenderImpl(InteractorExecutor interactorExecutor, MainThread mainThread) {
    this.interactorExecutor = interactorExecutor;
    this.mainThread = mainThread;
  }

  @Override public void setSpecs(ArrayList<Spec> specs) {
    this.specs = specs;
  }

  @Override public void setUser(User user) {
    this.user = user;
  }

  @Override public void setSubject(String subject) {
    this.subject = subject;
  }

  @Override public void setTypes(String[] types) {
    this.types = types;
  }

  @Override public void setContentEmail(String contentEmail) {
    this.contentEmail = contentEmail;
  }

  @Override public void execute(Callback callback) {
    validateArguments(callback);
    this.callback = callback;
    this.interactorExecutor.run(this);
  }

  /**
   * Monta e executa o envio de email,
   * de forma assincrona.
   */
  @Override public void run() {

    if(types != null && subject != null && contentEmail != null) {

      ArrayList<Email> emails = new ArrayList<>();

      if (specIsValid(specs.get(0)) && specIsValid(specs.get(1)) && specIsValid(specs.get(2))) {
        emails.add(new Email(types[0], subject));
      }

      if (specIsValid(specs.get(3)) && specIsValid(specs.get(4))) {
        emails.add(new Email(types[1], subject));
      }

      if (specIsValid(specs.get(5)) && specIsValid(specs.get(6))) {
        emails.add(new Email(types[2], subject));
      }

      if (emails.size() == 0) {
        emails.add(new Email("", subject));
      }

      try {

        boolean success = false;

        for (Email email : emails) {
          success = SendEmailService.sendEmail(user.getName(), user.getEmail(), email.getSubject(),
              String.format(contentEmail, email.getType()));
        }
        emails.clear();

        if (success) {
          notifyEmailCreated();
        } else {
          notifyEmailError();
        }
      } catch (Exception e) {
        e.printStackTrace();
        notifyEmailError();
      }
    }

  }

  /**
   * Realiza o post na main thread informando
   * sucesso no envio do email.
   */
  private void notifyEmailCreated() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onEmailCreated();
      }
    });
  }

  /**
   * Realiza o post na main thread informando
   * a falha no envio do email.
   */
  private void notifyEmailError() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onEmailError();
      }
    });
  }

  /**
   * Verifica se a qualificação do usuário
   * esta entre 7 e 10.
   * @param spec Spec do usuário.
   * @return Se o valor for maior ou igual a 7
   * e menor ou igual a 10, returna true,
   * caso contrário, retorna false.
   */
  @Override public boolean specIsValid(Spec spec) {
    int rate = spec.getRate();
    return rate >= 7 && rate <= 10;
  }

}

