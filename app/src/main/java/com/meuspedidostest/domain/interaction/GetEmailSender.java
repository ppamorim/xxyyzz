package com.meuspedidostest.domain.interaction;

import com.meuspedidostest.domain.model.Email;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;

public interface GetEmailSender {
  void execute(Callback callback);
  void setSpecView(ArrayList<SpecView> specViews);
  interface Callback {
    void onEmailCreated(ArrayList<Email> emails);
    void onEmailError();
  }
}
