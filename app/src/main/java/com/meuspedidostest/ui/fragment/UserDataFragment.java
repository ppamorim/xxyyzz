package com.meuspedidostest.ui.fragment;

import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import com.meuspedidostest.R;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.activity.BaseActivity;

public class UserDataFragment extends AbstractFragment {

  @Bind(R.id.name) EditText name;
  @Bind(R.id.email) EditText email;

  @OnClick(R.id.next) void onNextClick() {
    ((BaseActivity) getActivity()).notifyUser(new User(name.getText().toString(), email.getText().toString()));
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_user_data;
  }

}
