package com.meuspedidostest.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import butterknife.ButterKnife;

/**
 * Esta classe abstrata infla o layout e aplica o binder do ButterKnife,
 * assim ajudando na criação das Activities da aplicação.
 */
public abstract class AbstractActivity extends AppCompatActivity {

  /**
   * Infla o layout da view e usa o ButterKnife para aplicar o binder.
   * @param savedInstanceState Salvamento de instância
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentViewId());
    ButterKnife.bind(this);
  }

  /**
   * Esconde o teclado caso esteja aberto.
   */
  public void hideKeyboard() {
    View view = getCurrentFocus();
    if (view != null) {
      ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
          hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }

  /**
   * @return Referência do layout da Activity nos resources.
   */
  protected abstract int getContentViewId();

}