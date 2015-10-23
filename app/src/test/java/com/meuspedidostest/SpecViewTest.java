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

  @Mock SpecView specView;
  @Mock Spec spec;

  @Test public void testSpecView() {

    when(spec.getId()).thenReturn(55);
    when(spec.getRate()).thenReturn(8);
    when(spec.getName()).thenReturn("Maria");

    when(specView.getSpec()).thenReturn(spec);

    Assert.assertEquals(specView.getSpec(), spec);
  }

  @Test public void testSpecViewArray() {

  }

}
