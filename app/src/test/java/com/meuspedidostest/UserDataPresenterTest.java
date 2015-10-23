package com.meuspedidostest;

import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.presenter.UserDataPresenterImpl;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class UserDataPresenterTest {

  @Mock UserDataPresenter.View view;

  @Mock User user;

  @Test public void testUserDataPresenter() {

    UserDataPresenterImpl userDataPresenterImpl = new UserDataPresenterImpl();
    userDataPresenterImpl.setUser();

    when(userDataPresenterImpl).thenReturn(55);

    userDataPresenter.setView(view);
    userDataPresenter.setUser(user);
    userDataPresenter.initialize();
  }

}
