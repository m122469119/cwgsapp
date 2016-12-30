package com.isoftstone.finance.cwgsapp.external.rollvviewpager;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Util
{
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5 + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(0.5 + paramFloat / paramContext.getResources().getDisplayMetrics().density);
  }
}