package com.meuspedidostest.ui.presenter;

import android.content.Context;
import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;

public interface UserSpecsPresenter extends Presenter {
  void setView(View view);
  void loadSpecs(Context context);
  interface View {
    boolean isReady();
    void onSendEmail();
    void onSpecsLoaded(ArrayList<SpecView> specViews);
  }
}
