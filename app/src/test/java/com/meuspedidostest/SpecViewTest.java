package com.meuspedidostest;

import com.meuspedidostest.domain.model.Spec;
import com.meuspedidostest.ui.view.SpecView;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpecViewTest {

  @Mock Spec spec;

  @Test public void testSpecView() {

    SpecView specView = new SpecView();

    Assert.assertNotNull(specView);
    Assert.assertEquals(specView.getRate(), 0);
    Assert.assertNull(specView.getSpec());

    when(spec.getId()).thenReturn(2);
    when(spec.getName()).thenReturn("João");
    when(spec.getRate()).thenReturn(10);

    specView.setRate(5);
    specView.setSpec(spec);

    Assert.assertNotNull(specView);
    Assert.assertEquals(specView.getRate(), 0);
    Assert.assertNotNull(specView.getSpec());

    Assert.assertTrue(specView.getSpec() == spec);

    Assert.assertTrue(specView.getSpec().getId() == spec.getId());
    Assert.assertTrue(specView.getSpec().getRate() == spec.getRate());
    Assert.assertTrue(specView.getSpec().getName().equals(spec.getName()));

  }

}
