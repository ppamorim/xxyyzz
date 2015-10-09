package com.meuspedidostest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * Esta classe abstrata infla o layout e aplica o binder do ButterKnife,
 * assim ajudando na criação das activities da aplicação.
 */
public abstract class AbstractActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentViewId());
    ButterKnife.bind(this);
  }

  protected abstract int getContentViewId();

}