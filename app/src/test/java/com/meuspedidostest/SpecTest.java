package com.meuspedidostest;

import com.meuspedidostest.domain.model.Spec;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpecTest {

  @Test public void testSpec() {

    Spec spec = new Spec();

    Assert.assertNotNull(spec);
    Assert.assertEquals(spec.getId(), 0);
    Assert.assertNull(spec.getName());
    Assert.assertEquals(spec.getRate(), 0);

    spec.setId(1);
    spec.setName("Mobile");
    spec.setRate(8);

    Assert.assertNotNull(spec);
    Assert.assertNotEquals(spec.getId(), 0);
    Assert.assertNotNull(spec.getName());
    Assert.assertNotEquals(spec.getRate(), 0);

    Assert.assertEquals(spec.getId(), 1);
    Assert.assertEquals(spec.getName(), "Mobile");
    Assert.assertEquals(spec.getRate(), 8);

  }

}
