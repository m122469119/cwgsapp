package com.isoftstone.finance.cwgsapp.external.rollvviewpager.hintview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.HintView;

public abstract class ShapeHintView extends LinearLayout
  implements HintView
{
  private Drawable dot_focus;
  private Drawable dot_normal;
  private int lastPosition = 0;
  private int length = 0;
  private ImageView[] mDots;

  public ShapeHintView(Context paramContext)
  {
    super(paramContext);
  }

  public ShapeHintView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void initView(int paramInt1, int paramInt2)
  {
    removeAllViews();
    setOrientation(HORIZONTAL);
    switch (paramInt2)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      this.length = paramInt1;
      this.mDots = new ImageView[paramInt1];
      this.dot_focus = makeFocusDrawable();
      this.dot_normal = makeNormalDrawable();
      for (int i = 0; i < paramInt1; i++)
      {
        this.mDots[i] = new ImageView(getContext());
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams.setMargins(10, 0, 10, 0);
        this.mDots[i].setLayoutParams(localLayoutParams);
        this.mDots[i].setBackgroundDrawable(this.dot_normal);
        addView(this.mDots[i]);
      }
      setGravity(19);
      continue;
//      setGravity(17);
//      continue;
//      setGravity(21);
    }
//    setCurrent(0);
  }

  public abstract Drawable makeFocusDrawable();

  public abstract Drawable makeNormalDrawable();

  public void setCurrent(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > -1 + this.length))
      return;
    this.mDots[this.lastPosition].setBackgroundDrawable(this.dot_normal);
    this.mDots[paramInt].setBackgroundDrawable(this.dot_focus);
    this.lastPosition = paramInt;
  }
}