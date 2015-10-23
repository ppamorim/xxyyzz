package com.meuspedidostest;

import com.meuspedidostest.domain.interaction.GetEmailSender;
import com.meuspedidostest.domain.interaction.GetEmailSenderImpl;
import com.meuspedidostest.executor.InteractorExecutor;
import com.meuspedidostest.executor.MainThread;
import com.meuspedidostest.executor.ThreadExecutor;
import org.junit.Before;
import org.mockito.Mockito;

public class GetEmailSenderTest {

  InteractorExecutor interactorExecutor;
  MainThread mainThread;

  GetEmailSender getEmailSender;

  @Before public void setUp() throws Exception {
    mainThread = Mockito.spy(new MainThreadImplTest());
    interactorExecutor = Mockito.spy(new ThreadExecutor());
    getEmailSender = Mockito.spy(new GetEmailSenderImpl(interactorExecutor, mainThread));
  }

  //@Inject GetEmailSender getEmailSender;
  //
  //@Mock Spec spec;
  //
  //@Test public void testSpecIsValid() {
  //  when(spec.getRate()).thenReturn(5);
  //  Assert.assertEquals(getEmailSender.specIsValid(spec), false);
  //
  //  when(spec.getRate()).thenReturn(9);
  //  Assert.assertEquals(getEmailSender.specIsValid(spec), true);
  //}

}
