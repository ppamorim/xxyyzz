package com.meuspedidostest.ui.activity;

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

public class BaseActivity extends AbstractActivity {

  @Bind(R.id.view_pager) NonSwipeableViewPager viewPager;
  @Bind(R.id.view_pager_tab) SmartTabLayout smartTabLayout;

  @Override protected int getContentViewId() {
    return R.layout.activity_base;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    configViewPager();
  }

  @Override public void onBackPressed() {
    if(backToPreviousPage()) {
      super.onBackPressed();
    }
  }

  private void configViewPager() {
    FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
        getSupportFragmentManager(), FragmentPagerItems.with(this)
        .add("", UserDataFragment.class)
        .add("", UserSpecsFragment.class)
        .create());
    viewPager.setAdapter(adapter);
    smartTabLayout.setViewPager(viewPager);
  }

  public void slideToNextPage() {
    int currentItem = viewPager.getCurrentItem();
    if(currentItem < viewPager.getAdapter().getCount()) {
      currentItem++;
      viewPager.setCurrentItem(currentItem);
    }
  }

  public boolean backToPreviousPage() {
    int currentItem = viewPager.getCurrentItem();
    if(currentItem > 0) {
      currentItem--;
      viewPager.setCurrentItem(currentItem);
      return false;
    }
    return true;
  }

  public void notifyUser(User user) {
    Fragment page = ((FragmentPagerItemAdapter) viewPager.getAdapter()).getPage(1);
    if(page != null && page instanceof UserSpecsFragment) {
      ((UserSpecsFragment) page).notifyUser(user);
    }
  }

}
