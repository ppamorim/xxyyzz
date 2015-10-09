package com.meuspedidostest.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * Esta classe abstrata permite agilizar o processo de desenvolvimento
 * e mapeando automaticamente as views da aplicação com ajuda do ButterKnife.
 */
public abstract class AbstractFragment extends Fragment {

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(getContentViewId(), container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  protected abstract int getContentViewId();

}
