package com.meuspedidostest;

import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.presenter.UserDataPresenter;
import com.meuspedidostest.ui.presenter.UserDataPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserDataPresenterTest {

  @Mock User user;

  @Mock UserDataPresenter.View view;

  UserDataPresenter userDataPresenter;

  @Before public void setUp() throws Exception {
    userDataPresenter = new UserDataPresenterImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenSetViewWithNullThenExpectedIllegalArgumentException() {
    userDataPresenter.setView(null);
  }

  @Test public void whenSetViewThenVerifyZeroInteractionsOnView() {
    userDataPresenter.setView(view);
    Mockito.verifyZeroInteractions(view);
  }

  @Test public void givenNullUserWhenInitializeThenVerifyZeroInteractionsOnView() {
    userDataPresenter.setUser(null);
    userDataPresenter.initialize();
    Mockito.verifyZeroInteractions(view);
  }

  @Test public void
  givenViewReadyAndUserWithEmailAndNameWhenInitializeThenVerifyViewOnUserSuccessWithUser() {
    Mockito.when(view.isReady()).thenReturn(true);

    Mockito.when(user.getName()).thenReturn("Pedro");
    Mockito.when(user.getEmail()).thenReturn("pp.amorim@hotmail.com");

    userDataPresenter.setUser(user);
    userDataPresenter.setView(view);
    userDataPresenter.initialize();

    Mockito.verify(view).onUserSuccess(Mockito.eq(user));
  }

}