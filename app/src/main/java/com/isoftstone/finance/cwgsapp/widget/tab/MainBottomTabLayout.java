package com.isoftstone.finance.cwgsapp.widget.tab;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.isoftstone.finance.cwgsapp.R;

public class MainBottomTabLayout extends LinearLayout {
    private ArgbEvaluator mColorEvaluator;
    private View[] mIconLayouts;
    private int[][] mIconRes = {{R.mipmap.homeun, R.mipmap.home}, {R.mipmap.searchun, R.mipmap.search}, {R.mipmap.personun, R.mipmap.person}};
    private int mLastPosition;
    private int mSelectedPosition;
    private float mSelectionOffset;
    int mTextNormalColor;
    int mTextSelectedColor;
    private String[] mTitles;
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    public MainBottomTabLayout(Context paramContext) {
        this(paramContext, null);
    }

    public MainBottomTabLayout(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public MainBottomTabLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init(paramContext, paramAttributeSet, paramInt);
    }

    private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        this.mTitles = getResources().getStringArray(R.array.tabTxt);
        this.mColorEvaluator = new ArgbEvaluator();
        this.mTextNormalColor = ContextCompat.getColor(getContext(), R.color.main_bottom_tab_textcolor_normal);
        this.mTextSelectedColor = ContextCompat.getColor(getContext(), R.color.main_bottom_tab_textcolor_selected);
    }

    private void onViewPagerPageChanged(int paramInt, float paramFloat) {
        this.mSelectedPosition = paramInt;
        this.mSelectionOffset = paramFloat;
        if ((paramFloat == 0.0F) && (this.mLastPosition != this.mSelectedPosition)) {
            this.mLastPosition = this.mSelectedPosition;
        }
        invalidate();
    }

    private void populateTabLayout() {
        PagerAdapter pagerAdapter = this.mViewPager.getAdapter();
        TabClickListener tabClickListener = new TabClickListener();
        this.mIconLayouts = new View[pagerAdapter.getCount()];
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, this, false);
            this.mIconLayouts[i] = view;
            TabIconView tabIconView = (TabIconView) view.findViewById(R.id.main_bottom_tab_icon);
            tabIconView.init(this.mIconRes[i][0], this.mIconRes[i][1]);
            TextView textView = (TextView) view.findViewById(R.id.main_bottom_tab_text);
            textView.setText(this.mTitles[i]);
            if (view == null) {
                throw new IllegalStateException("tabView is null.");
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = 0;
            layoutParams.weight = 1;
            view.setOnClickListener(tabClickListener);
            addView(view);
            if (i == this.mViewPager.getCurrentItem()) {
                tabIconView.transformPage(0);
                view.setSelected(true);
                textView.setTextColor(this.mTextSelectedColor);
            }
        }
    }

    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        if ((getChildCount() > 0) && (this.mSelectionOffset > 0.0F) && (this.mSelectedPosition < -1 + getChildCount())) {
            View view1 = getChildAt(this.mSelectedPosition);
            View view2 = getChildAt(1 + this.mSelectedPosition);
            View view3 = ((LinearLayout) view1).getChildAt(0);
            View view4 = ((LinearLayout) view2).getChildAt(0);
            View view5 = ((LinearLayout) view1).getChildAt(1);
            View view6 = ((LinearLayout) view2).getChildAt(1);
            if (((view3 instanceof TabIconView)) && ((view4 instanceof TabIconView))) {
                ((TabIconView) view3).transformPage(this.mSelectionOffset);
                ((TabIconView) view4).transformPage(1.0F - this.mSelectionOffset);
            }
            Integer integer1 = (Integer) this.mColorEvaluator.evaluate(this.mSelectionOffset, Integer.valueOf(this.mTextSelectedColor), Integer.valueOf(this.mTextNormalColor));
            Integer integer2 = (Integer) this.mColorEvaluator.evaluate(1 - this.mSelectionOffset, Integer.valueOf(this.mTextSelectedColor), Integer.valueOf(this.mTextNormalColor));
            if (((view5 instanceof TextView)) && ((view6 instanceof TextView))) {
                ((TextView) view5).setTextColor(integer1.intValue());
                ((TextView) view6).setTextColor(integer2.intValue());
            }
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener) {
        this.mViewPagerPageChangeListener = paramOnPageChangeListener;
    }

    public void setViewPager(ViewPager paramViewPager) {
        removeAllViews();
        this.mViewPager = paramViewPager;
        if ((paramViewPager != null) && (paramViewPager.getAdapter() != null)) {
            paramViewPager.addOnPageChangeListener(new InternalViewPagerListener());

            populateTabLayout();
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        private InternalViewPagerListener() {
        }

        public void onPageScrollStateChanged(int paramInt) {
            this.mScrollState = paramInt;
            if (MainBottomTabLayout.this.mViewPagerPageChangeListener != null)
                MainBottomTabLayout.this.mViewPagerPageChangeListener.onPageScrollStateChanged(paramInt);
        }

        public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
            MainBottomTabLayout.this.onViewPagerPageChanged(paramInt1, paramFloat);
            if (MainBottomTabLayout.this.mViewPagerPageChangeListener != null)
                MainBottomTabLayout.this.mViewPagerPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
        }

        public void onPageSelected(int paramInt) {
            TextView textView = null;
            int j = 0;
            for (int i = 0; i < MainBottomTabLayout.this.getChildCount(); i++) {
                TabIconView tabIconView = (TabIconView) MainBottomTabLayout.this.mIconLayouts[i].findViewById(R.id.main_bottom_tab_icon);
                if(paramInt == i){
                    tabIconView.transformPage(j);
                    textView.setTextColor(MainBottomTabLayout.this.mTextSelectedColor);
                    textView = (TextView) MainBottomTabLayout.this.mIconLayouts[i].findViewById(R.id.main_bottom_tab_text);
                }else{
                    textView.setTextColor(MainBottomTabLayout.this.mTextNormalColor);
                    j = 1;
                }
            }
            

            if (this.mScrollState == 0){
                MainBottomTabLayout.this.onViewPagerPageChanged(paramInt, 0);
            }

            for (int m = 0; m < MainBottomTabLayout.this.getChildCount(); m++) {
                View view = MainBottomTabLayout.this.getChildAt(m);
                if(paramInt == m){
                    view.setSelected(true);
                }else{
                    view.setSelected(false);
                }
            }
            if (MainBottomTabLayout.this.mViewPagerPageChangeListener != null){
                MainBottomTabLayout.this.mViewPagerPageChangeListener.onPageSelected(paramInt);
            }
        }
    }

    private class TabClickListener implements View.OnClickListener {
        private TabClickListener() {
        }

        public void onClick(View paramView) {
            for (int i = 0; ; i++)
                if (i < MainBottomTabLayout.this.getChildCount()) {
                    if (paramView == MainBottomTabLayout.this.getChildAt(i))
                        MainBottomTabLayout.this.mViewPager.setCurrentItem(i, false);
                } else
                    return;
        }
    }
}