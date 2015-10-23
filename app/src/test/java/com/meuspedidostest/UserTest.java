package com.meuspedidostest;

import com.meuspedidostest.domain.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class UserTest {

  @Mock User user;

  @Test public void test() {
    when(user.getName()).thenReturn("João");
    when(user.getEmail()).thenReturn("joao@joao.com");

    Assert.assertEquals(user.getName(), "João");
    Assert.assertEquals(user.getEmail(), "joao@joao.com");
  }

}
