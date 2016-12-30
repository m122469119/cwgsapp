package com.isoftstone.finance.cwgsapp.base;

import android.content.Context;
import android.view.View;

public abstract class BasePager
{
  public Context context;
  public View view;

  public BasePager(Context paramContext)
  {
    this.context = paramContext;
    this.view = initView();
  }

  public View getRootView()
  {
    return this.view;
  }

  public abstract void initData();

  public void initTitleBar(String paramString)
  {
  }

  public abstract View initView();
}