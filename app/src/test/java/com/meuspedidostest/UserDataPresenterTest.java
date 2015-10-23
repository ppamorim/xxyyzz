package com.meuspedidostest;

import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.presenter.UserDataPresenter;
import com.meuspedidostest.ui.presenter.UserDataPresenterImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class UserDataPresenterTest {

  @Mock User user;

  @Test public void testUserDataPresenter() {

    UserDataPresenterImpl userDataPresenter = new UserDataPresenterImpl();

    Assert.assertNotNull(userDataPresenter);

    userDataPresenter.setView(new UserDataPresenter.View() {
      @Override public boolean isReady() {
        return true;
      }

      @Override public void onUserSuccess(User user) {
        Assert.assertNotNull(user);
      }

      @Override public void onEmptyName() {
      }

      @Override public void onEmptyEmail() {
      }

      @Override public void onInvalidEmail() {
      }
    });

    when(user.getName()).thenReturn("Pedro");
    when(user.getEmail()).thenReturn("pp.amorim@hotmail.com");

    userDataPresenter.setUser(user);

    Assert.assertNotNull(userDataPresenter);
    Assert.assertEquals(userDataPresenter.getUser(), user);
    Assert.assertEquals(userDataPresenter.getUser().getName(), user.getName());
    Assert.assertEquals(userDataPresenter.getUser().getEmail(), user.getEmail());

    userDataPresenter.initialize();


  }

}
