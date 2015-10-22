package com.meuspedidostest.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.meuspedidostest.R;
import com.meuspedidostest.domain.model.Spec;
import java.util.ArrayList;
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class SpecView {

  private Spec spec;

  @Bind(R.id.language_name) TextView languageName;
  @Bind(R.id.seek_bar) DiscreteSeekBar discreteSeekBar;

  public SpecView(Spec spec) {
    this.spec = spec;
  }

  public View createView(LayoutInflater layoutInflater) {
    View view = layoutInflater.inflate(R.layout.adapter_language, null, false);
    ButterKnife.bind(this, view);
    languageName.setText(spec.getName());
    return view;
  }

  public int getRate() {
    return discreteSeekBar.getProgress();
  }

  public Spec getSpec() {
    return spec;
  }

}
