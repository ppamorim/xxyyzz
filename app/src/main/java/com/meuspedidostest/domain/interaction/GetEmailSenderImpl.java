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
  private ArrayList<SpecView> specViews;
  private User user;
  private Callback callback;
  private Context context;

  /**
   * Declara as intâncias nas variáveis utilizando
   * interfaces providas pelo Dagger.
   *
   * @param interactorExecutor Referência da pool de interactions.
   * @param mainThread Referencia da mainThread.
   */
  @Inject GetEmailSenderImpl(InteractorExecutor interactorExecutor, MainThread mainThread) {
    this.interactorExecutor = interactorExecutor;
    this.mainThread = mainThread;
  }

  @Override public void setSpecView(ArrayList<SpecView> specViews) {
    this.specViews = specViews;
  }

  @Override public void setUser(User user) {
    this.user = user;
  }

  @Override public void setContext(Context context) {
    this.context = context;
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

    ArrayList<Email> emails = new ArrayList<>();
    ArrayList<Spec> specs = new ArrayList<>(specViews.size());

    for(SpecView specView : specViews) {
      Spec spec = specView.getSpec();
      spec.setRate(specView.getRate());
      specs.add(spec);
    }

    Resources resources = context.getResources();
    String genericSubject = resources.getString(R.string.feedback_message);

    if(specIsValid(specs.get(0)) && specIsValid(specs.get(1)) && specIsValid(specs.get(2))) {
      emails.add(new Email(resources.getString(R.string.mobile), genericSubject));
    }

    if(specIsValid(specs.get(3)) && specIsValid(specs.get(4))) {
      emails.add(new Email(resources.getString(R.string.back_end), genericSubject));
    }

    if(specIsValid(specs.get(5)) && specIsValid(specs.get(6))) {
      emails.add(new Email(resources.getString(R.string.mobile), genericSubject));
    }

    if(emails.size() == 0) {
      emails.add(new Email("", genericSubject));
    }

    try {

      for(Email email : emails) {
        System.out.println("EMAIL: " + email.getType());
        SendEmailService.sendEmail(user.getName(), user.getEmail(), email.getSubject(),
            String.format(resources.getString(R.string.generic_email), email.getType()));
      }
      emails.clear();
      notifyEmailCreated();
    } catch (Exception e) {
      e.printStackTrace();
      notifyEmailError();
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
  private boolean specIsValid(Spec spec) {
    int rate = spec.getRate();
    return rate >= 7 && rate <= 10;
  }

}

