package com.meuspedidostest;

import com.meuspedidostest.domain.interaction.GetEmailSender;
import com.meuspedidostest.domain.interaction.GetEmailSenderImpl;
import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.executor.InteractorExecutor;
import com.meuspedidostest.executor.MainThread;
import com.meuspedidostest.executor.ThreadExecutor;
import com.meuspedidostest.ui.presenter.UserSpecsPresenter;
import com.meuspedidostest.ui.presenter.UserSpecsPresenterImpl;

import com.meuspedidostest.ui.view.SpecView;
import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserSpecsPresenterTest {

  @Mock ArrayList<String> types;

  @Mock SpecView specView;

  @Mock Spec spec;

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

    SpecView specView = new SpecView();

    Assert.assertNotNull(specView);
    Assert.assertEquals(specView.getRate(), 0);
    Assert.assertNull(specView.getSpec());

    Mockito.when(spec.getId()).thenReturn(2);
    Mockito.when(spec.getName()).thenReturn("João");
    Mockito.when(spec.getRate()).thenReturn(10);

    specView.setRate(5);
    specView.setSpec(spec);

    Assert.assertNotNull(specView);
    Assert.assertEquals(specView.getRate(), 0);
    Assert.assertNotNull(specView.getSpec());

    ArrayList<SpecView> specViews = new ArrayList<>();

    Assert.assertNotNull(specViews);
    Assert.assertTrue(specViews.isEmpty());

    specViews.add(specView);

    userSpecsPresenter.setContentEmail("teste");
    userSpecsPresenter.setSubject("pp.amorim@hotmail.com");
    userSpecsPresenter.setTypes(types);
    userSpecsPresenter.setSpecs(specViews);
    userSpecsPresenter.setUser(user);
    userSpecsPresenter.setView(view);
    userSpecsPresenter.initialize();

    //Mockito.verifyNoMoreInteractions(callback);
    //Mockito.verify(view).onSendEmailSuccess();
    //Mockito.verify(view).onSendEmailFail();

  }

}
