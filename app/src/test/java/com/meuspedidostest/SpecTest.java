package com.meuspedidostest;

import com.meuspedidostest.domain.model.Spec;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpecTest {

  @Mock Spec spec;

  @Test public void test() {
    when(spec.getId()).thenReturn(55);
    when(spec.getName()).thenReturn("Mobile");
    when(spec.getRate()).thenReturn(5);

    Assert.assertEquals(spec.getId(), 55);
    Assert.assertEquals(spec.getName(), "Mobile");
    Assert.assertEquals(spec.getRate(), 5);
  }

}
