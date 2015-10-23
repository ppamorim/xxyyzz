package com.meuspedidostest;

import com.meuspedidostest.domain.interaction.GetEmailSender;
import com.meuspedidostest.domain.interaction.GetEmailSenderImpl;
import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.executor.InteractorExecutor;
import com.meuspedidostest.executor.MainThread;
import com.meuspedidostest.executor.ThreadExecutor;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetEmailSenderTest {

  @Mock User user;

  @Mock Spec spec;

  @Mock ArrayList<Spec> specs;

  InteractorExecutor interactorExecutor;
  MainThread mainThread;

  GetEmailSender getEmailSender;

  @Mock GetEmailSender.Callback callback;

  @Before public void setUp() throws Exception {
    mainThread = Mockito.spy(new MainThreadImplTest());
    interactorExecutor = Mockito.spy(new ThreadExecutor());
    getEmailSender = Mockito.spy(new GetEmailSenderImpl(interactorExecutor, mainThread));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenExecuteWithNullThenExpectedIllegalArgumentException() {
    getEmailSender.execute(null);
  }

  @Test public void whenSetUserThenVerifyZeroInteractionsOnCallback() {

    Assert.assertNotNull(user);

    Mockito.when(user.getName()).thenReturn("Pedro");
    Mockito.when(user.getEmail()).thenReturn("pp.amorim@hotmail.com");

    Mockito.when(spec.getId()).thenReturn(2);
    Mockito.when(spec.getName()).thenReturn("João");
    Mockito.when(spec.getRate()).thenReturn(10);

    Mockito.when(specs.get(0)).thenReturn(spec);

    getEmailSender.setUser(user);
    getEmailSender.setSpecView(specs);
    getEmailSender.execute(callback);
    Mockito.verifyNoMoreInteractions(callback);
    Mockito.verify(callback).onEmailCreated();
    Mockito.verify(callback).onEmailError();
  }

}
