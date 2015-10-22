package com.meuspedidostest.ui.presenter;

import android.content.Context;
import com.meuspedidostest.domain.model.Email;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;

public interface UserSpecsPresenter extends Presenter {
  void setView(View view);
  void setUser(User user);
  void loadSpecs(Context context);
  User getUser();
  interface View {
    boolean isReady();
    void onSendEmailSuccess(ArrayList<Email> emails);
    void onSendEmailFail();
    void onSpecsLoaded(ArrayList<SpecView> specViews);
  }
}
