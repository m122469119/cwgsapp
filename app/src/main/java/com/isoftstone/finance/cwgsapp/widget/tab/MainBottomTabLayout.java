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
import android.util.Log;
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
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_mainbottom_tab, this, false);
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
//        Log.i("test", "onDraw: 111111111111111111");
//        super.onDraw(paramCanvas);
//        if ((getChildCount() > 0) && (this.mSelectionOffset > 0) && (this.mSelectedPosition < -1 + getChildCount())) {
//            View view1 = getChildAt(this.mSelectedPosition);
//            View view2 = getChildAt(1 + this.mSelectedPosition);
//            View view3 = ((LinearLayout) view1).getChildAt(0);
//            View view4 = ((LinearLayout) view2).getChildAt(0);
//            View view5 = ((LinearLayout) view1).getChildAt(1);
//            View view6 = ((LinearLayout) view2).getChildAt(1);
//            if (((view3 instanceof TabIconView)) && ((view4 instanceof TabIconView))) {
//                ((TabIconView) view3).transformPage(this.mSelectionOffset);
//                ((TabIconView) view4).transformPage(1 - this.mSelectionOffset);
//            }
//            Integer integer1 = (Integer) this.mColorEvaluator.evaluate(this.mSelectionOffset, Integer.valueOf(this.mTextSelectedColor), Integer.valueOf(this.mTextNormalColor));
//            Integer integer2 = (Integer) this.mColorEvaluator.evaluate(1 - this.mSelectionOffset, Integer.valueOf(this.mTextSelectedColor), Integer.valueOf(this.mTextNormalColor));
//            if (((view5 instanceof TextView)) && ((view6 instanceof TextView))) {
//                ((TextView) view5).setTextColor(integer1.intValue());
//                ((TextView) view6).setTextColor(integer2.intValue());
//            }
//        }
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

         /**
          * @方法名称：
          * @方法描述：当
          * @创建人：zwxiao
          * @创建时间：2017/1/4 14:15
          * @参数1(param1)     [参数1说明]
          * @参数2(param2)    [参数2说明]
          * @返回值(return)  [返回类型说明]
          * @异常处理exception/throws [异常类型] [异常说明]
          */
        public void onPageSelected(int paramInt) {
            int j = 0;
            for (int i = 0; i < MainBottomTabLayout.this.getChildCount(); i++) {
                TabIconView tabIconView = (TabIconView) MainBottomTabLayout.this.mIconLayouts[i].findViewById(R.id.main_bottom_tab_icon);
                TextView textView = (TextView) MainBottomTabLayout.this.mIconLayouts[i].findViewById(R.id.main_bottom_tab_text);
                if (paramInt == i) {
                    tabIconView.transformPage(0);
                    textView.setTextColor(MainBottomTabLayout.this.mTextSelectedColor);
                } else {
                    textView.setTextColor(MainBottomTabLayout.this.mTextNormalColor);
                    tabIconView.transformPage(1);
                }
            }


            if (this.mScrollState == 0) {
                MainBottomTabLayout.this.onViewPagerPageChanged(paramInt, 0);
            }

            for (int m = 0; m < MainBottomTabLayout.this.getChildCount(); m++) {
                View view = MainBottomTabLayout.this.getChildAt(m);
                if (paramInt == m) {
                    view.setSelected(true);
                } else {
                    view.setSelected(false);
                }
            }
            if (MainBottomTabLayout.this.mViewPagerPageChangeListener != null) {
                MainBottomTabLayout.this.mViewPagerPageChangeListener.onPageSelected(paramInt);
            }
        }
    }

    private class TabClickListener implements View.OnClickListener {
        private TabClickListener() {
        }

        public void onClick(View paramView) {
            for (int i = 0; i < MainBottomTabLayout.this.getChildCount(); i++){
                if (paramView == MainBottomTabLayout.this.getChildAt(i)) {
                    MainBottomTabLayout.this.mViewPager.setCurrentItem(i, false);
                }
            }
        }
    }
}