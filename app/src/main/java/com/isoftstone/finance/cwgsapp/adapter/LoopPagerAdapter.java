package com.isoftstone.finance.cwgsapp.adapter;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.HintView;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class LoopPagerAdapter extends PagerAdapter
{
  private ArrayList<View> mViewList = new ArrayList();
  private RollPagerView mViewPager;

  public LoopPagerAdapter(RollPagerView paramRollPagerView)
  {
    this.mViewPager = paramRollPagerView;
    paramRollPagerView.setHintViewDelegate(new LoopHintViewDelegate());
  }

  private View findViewByPosition(ViewGroup paramViewGroup, int paramInt)
  {
    Iterator localIterator = this.mViewList.iterator();
    while (localIterator.hasNext())
    {
      View localView2 = (View)localIterator.next();
      if ((((Integer)localView2.getTag()).intValue() == paramInt) && (localView2.getParent() == null))
        return localView2;
    }
    View localView1 = getView(paramViewGroup, paramInt);
    localView1.setTag(Integer.valueOf(paramInt));
    this.mViewList.add(localView1);
    return localView1;
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramViewGroup.removeView((View)paramObject);
  }

  @Deprecated
  public int getCount()
  {
    if (getRealCount() <= 0)
      return 0;
    return 2147483647;
  }

  protected abstract int getRealCount();

  public abstract View getView(ViewGroup paramViewGroup, int paramInt);

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    View localView = findViewByPosition(paramViewGroup, paramInt % getRealCount());
    paramViewGroup.addView(localView);
    return localView;
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    super.registerDataSetObserver(paramDataSetObserver);
    this.mViewPager.getViewPager().setCurrentItem(getCount() / 2, false);
  }

  private class LoopHintViewDelegate
    implements RollPagerView.HintViewDelegate
  {
    private LoopHintViewDelegate()
    {
    }

    public void initView(int paramInt1, int paramInt2, HintView paramHintView)
    {
      if (paramHintView != null)
        paramHintView.initView(LoopPagerAdapter.this.getRealCount(), paramInt2);
    }

    public void setCurrentPosition(int paramInt, HintView paramHintView)
    {
      if (paramHintView != null)
        paramHintView.setCurrent(paramInt % LoopPagerAdapter.this.getRealCount());
    }
  }
}