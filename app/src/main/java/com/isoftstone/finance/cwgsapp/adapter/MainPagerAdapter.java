package com.isoftstone.finance.cwgsapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.isoftstone.finance.cwgsapp.base.BasePager;

import java.util.ArrayList;

public class MainPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<BasePager> pagerList;

    public MainPagerAdapter(Context paramContext, ArrayList<BasePager> paramArrayList) {
        this.pagerList = paramArrayList;
        this.context = paramContext;
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
        paramViewGroup.removeView((View) paramObject);
    }

    public int getCount() {
        return this.pagerList.size();
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
        paramViewGroup.addView(((BasePager) this.pagerList.get(paramInt)).getRootView());
        return ((BasePager) this.pagerList.get(paramInt)).getRootView();
    }

    public boolean isViewFromObject(View paramView, Object paramObject) {
        return paramView == paramObject;
    }
}