package com.meuspedidostest.ui.fragment;

import android.widget.TextView;
import butterknife.Bind;
import com.meuspedidostest.R;
import com.meuspedidostest.domain.model.User;

public class UserSpecsFragment extends AbstractFragment {

  @Bind(R.id.user_grettings) TextView userGrettings;

  @Override protected int getContentViewId() {
    return R.layout.fragment_user_specs;
  }

  public void notifyUser(User user) {
    userGrettings.setText(user.getName());
  }

}
