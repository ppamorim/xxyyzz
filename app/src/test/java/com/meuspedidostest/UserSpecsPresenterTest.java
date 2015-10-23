package com.meuspedidostest;

import android.content.Context;
import com.meuspedidostest.domain.interaction.GetEmailSender;
import com.meuspedidostest.domain.interaction.GetEmailSenderImpl;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.executor.InteractorExecutor;
import com.meuspedidostest.executor.MainThread;
import com.meuspedidostest.executor.ThreadExecutor;
import com.meuspedidostest.ui.presenter.UserSpecsPresenter;
import com.meuspedidostest.ui.presenter.UserSpecsPresenterImpl;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserSpecsPresenterTest {

  @Mock ArrayList<String> types;

  @Mock User user;

  @Mock UserSpecsPresenter.View view;

  @Mock GetEmailSender.Callback callback;

  InteractorExecutor interactorExecutor;
  MainThread mainThread;
  GetEmailSender getEmailSender;

  UserSpecsPresenter userSpecsPresenter;

  @Before public void setUp() throws Exception {
    mainThread = Mockito.spy(new MainThreadImplTest());
    interactorExecutor = Mockito.spy(new ThreadExecutor());
    getEmailSender = Mockito.spy(new GetEmailSenderImpl(interactorExecutor, mainThread));
    userSpecsPresenter = new UserSpecsPresenterImpl(getEmailSender);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenSetViewWithNullThenExpectedIllegalArgumentException() {
    userSpecsPresenter.setView(null);
  }

  @Test public void whenSetViewThenVerifyZeroInteractionsOnView() {
    userSpecsPresenter.setView(view);
    Mockito.verifyZeroInteractions(view);
  }

  @Test public void givenNullUserWhenRunThenVerifyInteractions() {
    getEmailSender.setUser(null);
    getEmailSender.execute(callback);
  }

  @Test public void givenNullUserWhenInitializeThenVerifyZeroInteractionsOnView() {
    userSpecsPresenter.setUser(null);
    userSpecsPresenter.initialize();
    Mockito.verifyZeroInteractions(view);
  }

  @Test public void
  givenViewReadyAndUserWithEmailAndNameWhenInitializeThenVerifyViewOnUserSuccessWithUser() {
    Mockito.when(view.isReady()).thenReturn(true);

    Mockito.when(user.getName()).thenReturn("Pedro");
    Mockito.when(user.getEmail()).thenReturn("pp.amorim@hotmail.com");

    Mockito.when(types.get(0)).thenReturn("Mobile");

    userSpecsPresenter.setContentEmail("teste");
    userSpecsPresenter.setSubject("pp.amorim@hotmail.com");
    userSpecsPresenter.setTypes((String[]) types.toArray());
    userSpecsPresenter.setUser(user);
    userSpecsPresenter.setView(view);
    userSpecsPresenter.initialize();

    Mockito.verifyNoMoreInteractions(callback);
    Mockito.verify(view).onSendEmailSuccess();
    Mockito.verify(view).onSendEmailFail();

  }

}
