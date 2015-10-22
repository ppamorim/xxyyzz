package com.meuspedidostest.domain.interaction;

import com.meuspedidostest.domain.model.Email;
import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.executor.Interactor;
import com.meuspedidostest.executor.InteractorExecutor;
import com.meuspedidostest.executor.MainThread;
import com.meuspedidostest.ui.view.SpecView;
import com.meuspedidostest.util.EmailType;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Geralmente tento ser o mais genérico nas minhas classes,
 * no entanto neste caso, algo genérico não iria funcionar.
 */
public class GetEmailSenderImpl extends BaseImpl implements Interactor, GetEmailSender {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private ArrayList<SpecView> specViews;
  private Callback callback;

  @Inject GetEmailSenderImpl(InteractorExecutor interactorExecutor, MainThread mainThread) {
    this.interactorExecutor = interactorExecutor;
    this.mainThread = mainThread;
  }

  @Override public void setSpecView(ArrayList<SpecView> specViews) {
    this.specViews = specViews;
  }

  @Override public void execute(Callback callback) {
    validateArguments(callback);
    this.callback = callback;
    this.interactorExecutor.run(this);
  }

  @Override public void run() {

    ArrayList<Email> emails = new ArrayList<>();
    ArrayList<Spec> specs = new ArrayList<>(specViews.size());

    for(SpecView specView : specViews) {
      Spec spec = specView.getSpec();
      spec.setRate(specView.getRate());
      specs.add(spec);
    }

    if(specIsValid(specs.get(0)) && specIsValid(specs.get(1)) && specIsValid(specs.get(2))) {
      emails.add(new Email(EmailType.FRONT_END));
    }

    if(specIsValid(specs.get(3)) && specIsValid(specs.get(4))) {
      emails.add(new Email(EmailType.BACK_END));
    }

    if(specIsValid(specs.get(5)) && specIsValid(specs.get(6))) {
      emails.add(new Email(EmailType.MOBILE));
    }

    if(emails.size() == 0) {
      emails.add(new Email(EmailType.GENERIC));
    }

    if(emails.size() > 0) {
      notifyEmailCreated(emails);
    } else {
      notifyEmailError();
    }

  }

  private void notifyEmailCreated(final ArrayList<Email> emails) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onEmailCreated(emails);
      }
    });
  }

  private void notifyEmailError() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onEmailError();
      }
    });
  }

  private boolean specIsValid(Spec spec) {
    int rate = spec.getRate();
    return rate >= 7 && rate <= 10;
  }

}

