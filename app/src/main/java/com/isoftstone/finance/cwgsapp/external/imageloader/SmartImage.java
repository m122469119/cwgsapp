package com.isoftstone.finance.cwgsapp.external.imageloader;

import android.content.Context;
import android.graphics.Bitmap;

public abstract interface SmartImage
{
  public abstract Bitmap getBitmap(Context paramContext);
}