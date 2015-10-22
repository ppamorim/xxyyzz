package com.meuspedidostest.ui.presenter;

import android.content.Context;
import com.meuspedidostest.R;
import com.meuspedidostest.domain.interaction.GetEmailSender;
import com.meuspedidostest.domain.model.Email;
import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;
import javax.inject.Inject;

public class UserSpecsPresenterImpl implements UserSpecsPresenter {

  private View view;
  private User user;
  private GetEmailSender getEmailSender;
  private ArrayList<SpecView> specViews;

  @Inject UserSpecsPresenterImpl(GetEmailSender getEmailSender) {
    this.getEmailSender = getEmailSender;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void initialize() {
    getEmailSender.setSpecView(specViews);
    getEmailSender.execute(new GetEmailSender.Callback() {

      @Override public void onEmailCreated(ArrayList<Email> emails) {
        notifySendEmailSuccess(emails);
      }

      @Override public void onEmailError() {
        notifySendEmailFail();
      }
    });
  }

  @Override public void resume() { }

  @Override public void pause() { }

  @Override public void setUser(User user) {
    this.user = user;
  }

  @Override public User getUser() {
    return this.user;
  }

  @Override public void loadSpecs(Context context) {
    cleanListIfNeeded();
    String[] items = context.getResources().getStringArray(R.array.specs);
    specViews = new ArrayList<>(items.length);
    for(String item : items) {
      specViews.add(new SpecView(new Spec(item)));
    }
    onSpecsLoadSuccess(specViews);
  }

  private void onSpecsLoadSuccess(ArrayList<SpecView> specViews) {
    if(view.isReady()) {
      view.onSpecsLoaded(specViews);
    }
  }

  private void notifySendEmailSuccess(ArrayList<Email> emails) {
    if(view.isReady()) {
      view.onSendEmailSuccess(emails);
    }
  }

  private void notifySendEmailFail() {
    if(view.isReady()) {
      view.onSendEmailFail();
    }
  }

  private void cleanListIfNeeded() {
    if(specViews != null) {
      specViews.clear();
    }
  }

}
