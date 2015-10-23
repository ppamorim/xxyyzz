package com.meuspedidostest.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.meuspedidostest.R;
import com.meuspedidostest.domain.model.Spec;
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Representa a classe que infla o layout das
 * views para ser adicionada no Fragment de
 * conhecimentos do usuário.
 */
public class SpecView {

  private Spec spec;

  @Bind(R.id.language_name) TextView languageName;
  @Bind(R.id.seek_bar) DiscreteSeekBar discreteSeekBar;

  public SpecView() { }

  public SpecView(Spec spec) {
    this.spec = spec;
  }

  /**
   * Cria a view e utiliza o ButterKnife para dar
   * bind nas views internas.
   * Seta o nome do usuário.
   * @param layoutInflater Instância do LayoutInflater
   * @return View inflada
   */
  public View createView(LayoutInflater layoutInflater) {
    View view = layoutInflater.inflate(R.layout.adapter_language, null, false);
    ButterKnife.bind(this, view);
    languageName.setText(spec.getName());
    return view;
  }

  public int getRate() {
    return discreteSeekBar.getProgress();
  }

  public void setSpec(Spec spec) {
    this.spec = spec;
  }

  public Spec getSpec() {
    return spec;
  }

}
