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

  @Mock ArrayList<String> types;

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

  @Test public void validatSpecValidator() {

    Spec spec = new Spec();
    spec.setId(1);
    spec.setRate(10);
    spec.setName("Luana");
    Assert.assertTrue(getEmailSender.specIsValid(spec));

    Spec specSecond = new Spec();
    specSecond.setId(4);
    specSecond.setRate(3);
    specSecond.setName("Carlos");
    Assert.assertFalse(getEmailSender.specIsValid(specSecond));

  }

  @Test(expected = IllegalArgumentException.class)
  public void whenExecuteWithNullThenExpectedIllegalArgumentException() {
    getEmailSender.execute(null);
  }

  @Test public void givenNullUserWhenInitializeThenVerifyZeroInteractionsOnCallback() {
    getEmailSender.setUser(null);
    getEmailSender.execute(callback);
    Mockito.verifyZeroInteractions(callback);
  }

  @Test public void givenNullSubjectWhenInitializeThenVerifyZeroInteractionsOnCallback() {
    getEmailSender.setSubject(null);
    getEmailSender.execute(callback);
    Mockito.verifyZeroInteractions(callback);
  }

  @Test public void givenNullContentEmailWhenInitializeThenVerifyZeroInteractionsOnCallback() {
    getEmailSender.setContentEmail(null);
    getEmailSender.execute(callback);
    Mockito.verifyZeroInteractions(callback);
  }

  @Test public void givenNullTypesWhenInitializeThenVerifyZeroInteractionsOnCallback() {
    getEmailSender.setTypes(null);
    getEmailSender.execute(callback);
    Mockito.verifyZeroInteractions(callback);
  }

  @Test public void whenSetUserThenVerifyZeroInteractionsOnCallback() {

    Mockito.when(user.getName()).thenReturn("Pedro");

    Mockito.when(spec.getId()).thenReturn(2);
    Mockito.when(spec.getName()).thenReturn("João");
    Mockito.when(spec.getRate()).thenReturn(10);

    Mockito.when(specs.get(0)).thenReturn(spec);

    Mockito.when(types.get(0)).thenReturn("Mobile");

    getEmailSender.setUser(user);
    getEmailSender.setSpecs(specs);
    getEmailSender.setSubject("pp.amorim@hotmail.com");
    getEmailSender.setContentEmail("teste");
    getEmailSender.setTypes(types);
    getEmailSender.execute(callback);

    Mockito.verifyNoMoreInteractions(callback);

    //Mockito.verify(callback).onEmailCreated();
    //Mockito.verify(callback).onEmailError();
  }

}
