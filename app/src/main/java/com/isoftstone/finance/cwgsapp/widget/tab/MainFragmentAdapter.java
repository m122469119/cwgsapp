package com.isoftstone.finance.cwgsapp.widget.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainFragmentAdapter extends FragmentPagerAdapter
{
  private final List<Fragment> mFragmentList = new ArrayList();

  public MainFragmentAdapter(FragmentManager paramFragmentManager)
  {
    super(paramFragmentManager);
  }

  public void addFrag(Fragment paramFragment)
  {
    this.mFragmentList.add(paramFragment);
  }

  public int getCount()
  {
    return this.mFragmentList.size();
  }

  public Fragment getItem(int paramInt)
  {
    return (Fragment)this.mFragmentList.get(paramInt);
  }
}