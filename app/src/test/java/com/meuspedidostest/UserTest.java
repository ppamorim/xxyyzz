package com.meuspedidostest;

import com.meuspedidostest.domain.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

  @Mock User user;

  @Test public void test() {
    when(user.getName()).thenReturn("João");
    when(user.getEmail()).thenReturn("joao@joao.com");

    Assert.assertEquals(user.getName(), "João");
    Assert.assertEquals(user.getEmail(), "joao@joao.com");
  }

}
