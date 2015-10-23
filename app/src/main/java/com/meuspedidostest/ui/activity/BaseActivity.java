package com.meuspedidostest.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import butterknife.Bind;
import com.meuspedidostest.R;
import com.meuspedidostest.domain.model.User;
import com.meuspedidostest.ui.fragment.UserDataFragment;
import com.meuspedidostest.ui.fragment.UserSpecsFragment;
import com.meuspedidostest.ui.view.NonSwipeableViewPager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * Principal atividade da aplicação, foi decidido
 * utilizar fragments com ViewPager para facilitar
 * a montagem e separação da estrutura MVP. Assim
 * tratando essa Activity como um container para os
 * elementos da tela.
 *
 */
public class BaseActivity extends AbstractActivity {

  private ProgressDialog progressDialog;

  @Bind(R.id.view_pager) NonSwipeableViewPager viewPager;
  @Bind(R.id.view_pager_tab) SmartTabLayout smartTabLayout;

  @Override protected int getContentViewId() {
    return R.layout.activity_base;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    configViewPager();
  }

  /**
   * Esta alteração evita que o aplicativo
   * feche sem que o usuário volte para a primeira tela.
   */
  @Override public void onBackPressed() {
    if(backToPreviousPage()) {
      super.onBackPressed();
    }
  }

  /**
   * Configura o ViewPager da Activity utilizando
   * a biblioteca SmartTabLayout.
   */
  private void configViewPager() {
    FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
        getSupportFragmentManager(), FragmentPagerItems.with(this)
        .add("", UserDataFragment.class)
        .add("", UserSpecsFragment.class)
        .create());
    viewPager.setAdapter(adapter);
    smartTabLayout.setViewPager(viewPager);
  }

  /**
   * Executa a troca de página do ViewPager
   * para a direita(direita).
   */
  public void slideToNextPage() {
    int currentItem = viewPager.getCurrentItem();
    if(currentItem < viewPager.getAdapter().getCount()) {
      currentItem++;
      viewPager.setCurrentItem(currentItem);
    }
  }

  /**
   * Executa a troca de página do ViewPager
   * para a esquerda(volta).
   */
  public boolean backToPreviousPage() {
    int currentItem = viewPager.getCurrentItem();
    if(currentItem > 0) {
      currentItem--;
      viewPager.setCurrentItem(currentItem);
      return false;
    }
    return true;
  }

  /**
   * Após configurar o usuário, é necessario informar ao segundo fragment
   * as suas informações, este método é utilizado para esconder o teclado,
   * mover a página para a direita e declarar os dados do usuário.
   * @param user
   */
  public void notifyUser(User user) {
    hideKeyboard();
    slideToNextPage();
    Fragment page = ((FragmentPagerItemAdapter) viewPager.getAdapter()).getPage(1);
    if(page != null && page instanceof UserSpecsFragment) {
      ((UserSpecsFragment) page).notifyUser(user);
    }
  }

  /**
   * Mostra o dialogo de carregamento
   */
  public void showLoading() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setTitle(R.string.loading);
    progressDialog.show();
  }

  /**
   * Esconde o dialogo de carregamento
   */
  public void hideLoading() {
    if(progressDialog != null) {
      progressDialog.hide();
    }
    progressDialog = null;
  }

}
