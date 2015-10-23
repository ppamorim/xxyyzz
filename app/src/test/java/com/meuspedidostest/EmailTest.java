package com.meuspedidostest;

import com.meuspedidostest.domain.model.Email;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailTest {

  @Mock Email email;

  @Test public void test() {
    when(email.getSubject()).thenReturn("Olá");
    when(email.getType()).thenReturn("Mobile");

    Assert.assertEquals(email.getSubject(), "Olá");
    Assert.assertEquals(email.getType(), "Mobile");
  }

}