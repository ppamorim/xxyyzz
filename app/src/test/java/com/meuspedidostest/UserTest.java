package com.meuspedidostest;

import com.meuspedidostest.domain.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

  @Test public void testUser() {

    User user = new User();

    Assert.assertNotNull(user);
    Assert.assertNull(user.getName());
    Assert.assertNull(user.getEmail());

    user.setName("Pedro Paulo de Amorim");
    user.setEmail("pp.amorim@hotmail.com");

    Assert.assertNotNull(user);
    Assert.assertNotNull(user.getName());
    Assert.assertNotNull(user.getEmail());

    Assert.assertEquals(user.getName(), "Pedro Paulo de Amorim");
    Assert.assertEquals(user.getEmail(), "pp.amorim@hotmail.com");

  }

}
