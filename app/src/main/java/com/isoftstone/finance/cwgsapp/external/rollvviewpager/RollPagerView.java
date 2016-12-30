package com.isoftstone.finance.cwgsapp.external.rollvviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.hintview.ColorPointHintView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class RollPagerView extends RelativeLayout
        implements ViewPager.OnPageChangeListener {
    private int alpha;
    private int color;
    private int delay;
    private int gravity;
    private PagerAdapter mAdapter;
    private TimeTaskHandler mHandler = new TimeTaskHandler(this);
    private View mHintView;
    private HintViewDelegate mHintViewDelegate = new HintViewDelegate() {
        public void initView(int paramAnonymousInt1, int paramAnonymousInt2, HintView paramAnonymousHintView) {
            if (paramAnonymousHintView != null)
                paramAnonymousHintView.initView(paramAnonymousInt1, paramAnonymousInt2);
        }

        public void setCurrentPosition(int paramAnonymousInt, HintView paramAnonymousHintView) {
            if (paramAnonymousHintView != null)
                paramAnonymousHintView.setCurrent(paramAnonymousInt);
        }
    };
    private long mRecentTouchTime;
    private ViewPager mViewPager;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private Timer timer;

    public RollPagerView(Context paramContext) {
        this(paramContext, null);
    }

    public RollPagerView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public RollPagerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        initView(paramAttributeSet);
    }

    private void dataSetChanged() {
        startPlay();
        if (this.mHintView != null)
            this.mHintViewDelegate.initView(this.mAdapter.getCount(), this.gravity, (HintView) this.mHintView);
    }

    private void initHint(HintView paramHintView) {
        if (this.mHintView != null)
            removeView(this.mHintView);
        if ((paramHintView == null) || (!(paramHintView instanceof HintView)))
            return;
        this.mHintView = ((View) paramHintView);
        loadHintView();
    }

    private void initView(AttributeSet paramAttributeSet) {
        if (this.mViewPager != null)
            removeView(this.mViewPager);
//        TypedArray localTypedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.RollViewPager);
//        this.gravity = localTypedArray.getInteger(R.styleable.RollViewPager_rollviewpager_hint_gravity, 1);
//        this.paddingRight = ((int) localTypedArray.getDimension(R.styleable.RollViewPager_rollviewpager_hint_paddingRight, 0.0F));
//        this.paddingLeft = ((int) localTypedArray.getDimension(R.styleable.RollViewPager_rollviewpager_hint_paddingLeft, 0.0F));
//        this.paddingTop = ((int) localTypedArray.getDimension(R.styleable.RollViewPager_rollviewpager_hint_paddingTop, 0.0F));
//        this.paddingBottom = ((int) localTypedArray.getDimension(R.styleable.RollViewPager_rollviewpager_hint_paddingBottom, Util.dip2px(getContext(), 4.0F)));
//        this.delay = localTypedArray.getInt(R.styleable.RollViewPager_rollviewpager_play_delay, 0);
//        this.color = localTypedArray.getColor(R.styleable.RollViewPager_rollviewpager_hint_color, Color.parseColor("#FFFFFF"));
//        this.alpha = localTypedArray.getInt(R.styleable.RollViewPager_rollviewpager_hint_alpha, 0);

        this.mViewPager = new ViewPager(getContext());
        this.mViewPager.setId(R.id.viewpager_inner);
        this.mViewPager.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.mViewPager);
//        localTypedArray.recycle();
        initHint(new ColorPointHintView(getContext(), Color.parseColor("#E3AC42"), Color.parseColor("#88ffffff")));
    }

    private void loadHintView() {
        addView(this.mHintView);
        this.mHintView.setPadding(this.paddingLeft, this.paddingTop, this.paddingRight, this.paddingBottom);
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
        localLayoutParams.addRule(12);
        this.mHintView.setLayoutParams(localLayoutParams);
        GradientDrawable localGradientDrawable = new GradientDrawable();
        localGradientDrawable.setColor(this.color);
        localGradientDrawable.setAlpha(this.alpha);
        this.mHintView.setBackgroundDrawable(localGradientDrawable);
        HintViewDelegate localHintViewDelegate = this.mHintViewDelegate;
        if (this.mAdapter == null) ;
        for (int i = 0; ; i = this.mAdapter.getCount()) {
            localHintViewDelegate.initView(i, this.gravity, (HintView) this.mHintView);
            return;
        }
    }

    private void startPlay() {
        if ((this.delay <= 0) || (this.mAdapter == null) || (this.mAdapter.getCount() <= 1))
            return;
        if (this.timer != null)
            this.timer.cancel();
        this.timer = new Timer();
        this.timer.schedule(new WeakTimerTask(this), this.delay, this.delay);
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
        this.mRecentTouchTime = System.currentTimeMillis();
        return super.dispatchTouchEvent(paramMotionEvent);
    }

    public ViewPager getViewPager() {
        return this.mViewPager;
    }

    public void onPageScrollStateChanged(int paramInt) {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    }

    public void onPageSelected(int paramInt) {
        this.mHintViewDelegate.setCurrentPosition(paramInt, (HintView) this.mHintView);
    }

    public void setAdapter(PagerAdapter paramPagerAdapter) {
        this.mViewPager.setAdapter(paramPagerAdapter);
        this.mViewPager.addOnPageChangeListener(this);
        this.mAdapter = paramPagerAdapter;
        dataSetChanged();
        paramPagerAdapter.registerDataSetObserver(new JPagerObserver());
    }

    public void setAnimationDurtion(final int paramInt) {
        try {
            Field localField = ViewPager.class.getDeclaredField("mScroller");
            localField.setAccessible(true);
            Scroller local3 = new Scroller(getContext(), new Interpolator() {
                public float getInterpolation(float paramAnonymousFloat) {
                    float f = paramAnonymousFloat - 1.0F;
                    return 1.0F + f * (f * (f * (f * f)));
                }
            }) {
                public void startScroll(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4) {
                    super.startScroll(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3, paramAnonymousInt4, paramInt);
                }

                public void startScroll(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5) {
                    if (System.currentTimeMillis() - RollPagerView.this.mRecentTouchTime > RollPagerView.this.delay)
                        ;
                    for (int i = paramInt; ; i = paramAnonymousInt5 / 2) {
                        super.startScroll(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3, paramAnonymousInt4, i);
                        return;
                    }
                }
            };
            localField.set(this.mViewPager, local3);
            return;
        } catch (NoSuchFieldException localNoSuchFieldException) {
            localNoSuchFieldException.printStackTrace();
            return;
        } catch (IllegalArgumentException localIllegalArgumentException) {
            localIllegalArgumentException.printStackTrace();
            return;
        } catch (IllegalAccessException localIllegalAccessException) {
            localIllegalAccessException.printStackTrace();
        }
    }

    public void setHintAlpha(int paramInt) {
        this.alpha = paramInt;
        initHint((HintView) this.mHintView);
    }

    public void setHintPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        this.paddingLeft = paramInt1;
        this.paddingTop = paramInt2;
        this.paddingRight = paramInt3;
        this.paddingBottom = paramInt4;
        this.mHintView.setPadding(this.paddingLeft, this.paddingTop, this.paddingRight, this.paddingBottom);
    }

    public void setHintView(HintView paramHintView) {
        if (this.mHintView != null)
            removeView(this.mHintView);
        this.mHintView = ((View) paramHintView);
        if ((paramHintView != null) && ((paramHintView instanceof View)))
            initHint(paramHintView);
    }

    public void setHintViewDelegate(HintViewDelegate paramHintViewDelegate) {
        this.mHintViewDelegate = paramHintViewDelegate;
    }

    public void setPlayDelay(int paramInt) {
        this.delay = paramInt;
        startPlay();
    }

    public static abstract interface HintViewDelegate {
        public abstract void initView(int paramInt1, int paramInt2, HintView paramHintView);

        public abstract void setCurrentPosition(int paramInt, HintView paramHintView);
    }

    private class JPagerObserver extends DataSetObserver {
        private JPagerObserver() {
        }

        public void onChanged() {
            RollPagerView.this.dataSetChanged();
        }

        public void onInvalidated() {
            RollPagerView.this.dataSetChanged();
        }
    }

    private static final class TimeTaskHandler extends Handler {
        private WeakReference<RollPagerView> mRollPagerViewWeakReference;

        public TimeTaskHandler(RollPagerView paramRollPagerView) {
            this.mRollPagerViewWeakReference = new WeakReference(paramRollPagerView);
        }

        public void handleMessage(Message paramMessage) {
            RollPagerView localRollPagerView = (RollPagerView) this.mRollPagerViewWeakReference.get();
            int i = 1 + localRollPagerView.getViewPager().getCurrentItem();
            if (i >= localRollPagerView.mAdapter.getCount())
                i = 0;
            localRollPagerView.getViewPager().setCurrentItem(i);
            localRollPagerView.mHintViewDelegate.setCurrentPosition(i, (HintView) localRollPagerView.mHintView);
        }
    }

    private static class WeakTimerTask extends TimerTask {
        private WeakReference<RollPagerView> mRollPagerViewWeakReference;

        public WeakTimerTask(RollPagerView paramRollPagerView) {
            this.mRollPagerViewWeakReference = new WeakReference(paramRollPagerView);
        }

        public void run() {
            RollPagerView localRollPagerView = (RollPagerView) this.mRollPagerViewWeakReference.get();
            if (localRollPagerView != null) {
                if ((localRollPagerView.isShown()) && (System.currentTimeMillis() - localRollPagerView.mRecentTouchTime > localRollPagerView.delay))
                    localRollPagerView.mHandler.sendEmptyMessage(0);
                return;
            }
            cancel();
        }
    }
}