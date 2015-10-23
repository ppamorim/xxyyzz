package com.meuspedidostest;

import com.meuspedidostest.domain.model.Email;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailTest {

  @Test public void testEmail() {

    Email email = new Email();

    Assert.assertNotNull(email);
    Assert.assertNull(email.getSubject());
    Assert.assertNull(email.getType());

    email.setSubject("pp.amorim@hotmail.com");
    email.setType("Mobile");

    Assert.assertNotNull(email);
    Assert.assertNotNull(email.getSubject());
    Assert.assertNotNull(email.getType());

    Assert.assertEquals(email.getSubject(), "pp.amorim@hotmail.com");
    Assert.assertEquals(email.getType(), "Mobile");

  }

}