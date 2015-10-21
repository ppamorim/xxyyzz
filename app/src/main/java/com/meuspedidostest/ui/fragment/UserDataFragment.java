package com.meuspedidostest.ui.fragment;

import butterknife.OnClick;
import com.meuspedidostest.R;
import com.meuspedidostest.ui.activity.BaseActivity;

public class UserDataFragment extends AbstractFragment {

  @OnClick(R.id.next) void onNextClick() {
    ((BaseActivity) getActivity()).slideToNextPage();
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_user_data;
  }

}
