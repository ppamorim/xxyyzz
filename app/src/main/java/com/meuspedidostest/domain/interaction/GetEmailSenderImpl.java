package com.meuspedidostest.domain.interaction;

import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.executor.Interactor;
import com.meuspedidostest.executor.InteractorExecutor;
import com.meuspedidostest.executor.MainThread;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;
import javax.inject.Inject;

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
    ArrayList<Spec> specs = new ArrayList<>(specViews.size());
    for(SpecView specView : specViews) {
      Spec spec = specView.getSpec();
      spec.setRate(specView.getRate());
      specs.add(spec);
    }
  }


  //private void notifyEmailCreated(final  genres) {
  //  mainThread.post(new Runnable() {
  //    @Override public void run() {
  //      callback.onEmailCreated(genres);
  //    }
  //  });
  //}

  private void notifyEmailError() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onEmailError();
      }
    });
  }

}

