package com.isoftstone.finance.cwgsapp.external;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isoftstone.finance.cwgsapp.R;

import java.util.ArrayList;
import java.util.List;

public class ImageCycleView extends FrameLayout
{
  private List<ImageInfo> data = new ArrayList();
  private Bitmap focusIndicationStyle;
  private Handler handler = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      if (ImageCycleView.this.mViewPager != null)
      {
        ImageCycleView.this.mViewPager.setCurrentItem(1 + ImageCycleView.this.mViewPager.getCurrentItem());
        ImageCycleView.this.handler.sendEmptyMessageDelayed(0, ImageCycleView.this.mCycleDelayed);
      }
      return false;
    }
  });
  private float indication_self_margin_percent = 0.5F;
  private boolean isAutoCycle = true;
  ArrayList<ImageView> ivarr = new ArrayList();
  private Context mContext;
  private int mCount = 0;
  private long mCycleDelayed = 5000L;
  private LinearLayout mIndicationGroup;
  private LoadImageCallBack mLoadImageCallBack;
  private OnPageClickListener mOnPageClickListener;
  private TextView mText;
  private ImageCycleViewPager mViewPager;
  private Bitmap unFocusIndicationStyle;

  public ImageCycleView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public ImageCycleView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  private Bitmap drawCircle(int paramInt1, int paramInt2)
  {
    Paint localPaint = new Paint(1);
    localPaint.setColor(paramInt2);
    Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt1, Bitmap.Config.ARGB_8888);
    new Canvas(localBitmap).drawCircle(paramInt1 / 2, paramInt1 / 2, paramInt1 / 2, localPaint);
    return localBitmap;
  }

  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    this.unFocusIndicationStyle = drawCircle(50, -1);
    this.focusIndicationStyle = drawCircle(50, -16776961);
    initView();
  }

  private void initIndication()
  {
    this.mIndicationGroup.removeAllViews();
    int i = 0;
    if (i < this.mCount)
    {
      ImageView localImageView = new ImageView(this.mContext);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.mIndicationGroup.getLayoutParams().height, -1);
      localLayoutParams.leftMargin = ((int)(this.mIndicationGroup.getLayoutParams().height * this.indication_self_margin_percent));
      localImageView.setScaleType(ImageView.ScaleType.FIT_XY);
      localImageView.setLayoutParams(localLayoutParams);
//      if (i == 0)
//        localImageView.setImageBitmap(this.focusIndicationStyle);
//      while (true)
//      {
//        this.mIndicationGroup.addView(localImageView);
//        i++;
//        break;
//        localImageView.setImageBitmap(this.unFocusIndicationStyle);
//      }
    }
  }

  private void initView()
  {
    View.inflate(this.mContext, R.layout.external_imagecycleview, this);
    FrameLayout localFrameLayout = (FrameLayout)findViewById(R.id.fl_image_cycle);
    this.mViewPager = new ImageCycleViewPager(this.mContext);
    this.mViewPager.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    localFrameLayout.addView(this.mViewPager);
    this.mViewPager.setOnPageChangeListener(new ImageCyclePageChangeListener());
    this.mIndicationGroup = ((LinearLayout)findViewById(R.id.ll_indication_group));
    this.mText = ((TextView)findViewById(R.id.tv_text));
  }

  private void startImageCycle()
  {
    this.handler.sendEmptyMessageDelayed(0, this.mCycleDelayed);
  }

  private void stopImageCycle()
  {
    this.handler.removeCallbacksAndMessages(null);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
//    if (paramMotionEvent.getAction() == 1)
//      if (this.isAutoCycle)
//        startImageCycle();
//    while (true)
//    {
//      return super.dispatchTouchEvent(paramMotionEvent);
//      if (this.isAutoCycle)
//        stopImageCycle();
//    }
    return false;
  }

  public void loadData(List<ImageInfo> paramList, LoadImageCallBack paramLoadImageCallBack)
  {
    this.ivarr.clear();
    this.data = paramList;
    this.mCount = paramList.size();
    initIndication();
    if (paramLoadImageCallBack == null)
      new IllegalArgumentException("LoadImageCallBack 回调函数不能为空！");
    this.mLoadImageCallBack = paramLoadImageCallBack;
    this.mViewPager.setAdapter(new ImageCycleAdapter());
    this.mViewPager.setCurrentItem(1073741823 - 1073741823 % this.mCount);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (this.isAutoCycle)
      startImageCycle();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    stopImageCycle();
  }

  public void setAutoCycle(Boolean paramBoolean)
  {
    this.isAutoCycle = paramBoolean.booleanValue();
  }

  public void setCycleDelayed(long paramLong)
  {
    this.mCycleDelayed = paramLong;
  }

  public void setIndicationStyle(IndicationStyle paramIndicationStyle, int paramInt1, int paramInt2, float paramFloat)
  {
    if (paramIndicationStyle == IndicationStyle.COLOR)
    {
      this.unFocusIndicationStyle = drawCircle(50, paramInt1);
      this.focusIndicationStyle = drawCircle(50, paramInt2);
    }
    while (true)
    {
      this.indication_self_margin_percent = paramFloat;
      initIndication();
//      return;
      if (paramIndicationStyle == IndicationStyle.IMAGE)
      {
        this.unFocusIndicationStyle = BitmapFactory.decodeResource(this.mContext.getResources(), paramInt1);
        this.focusIndicationStyle = BitmapFactory.decodeResource(this.mContext.getResources(), paramInt2);
      }
    }
  }

  public void setOnPageClickListener(OnPageClickListener paramOnPageClickListener)
  {
    this.mOnPageClickListener = paramOnPageClickListener;
  }

  private class ImageCycleAdapter extends PagerAdapter
  {
    private ImageCycleAdapter()
    {
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup.removeView((View)paramObject);
    }

    public int getCount()
    {
      return 2147483647;
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      final ImageCycleView.ImageInfo localImageInfo = (ImageCycleView.ImageInfo)ImageCycleView.this.data.get(paramInt % ImageCycleView.this.mCount);
      ImageView localImageView = ImageCycleView.this.mLoadImageCallBack.loadAndDisplay(localImageInfo);
      localImageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
      localImageView.setScaleType(ImageView.ScaleType.FIT_XY);
      localImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (ImageCycleView.this.mOnPageClickListener != null)
            ImageCycleView.this.mOnPageClickListener.onClick(paramAnonymousView, localImageInfo);
        }
      });
      paramViewGroup.addView(localImageView);
      return localImageView;
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
  }

  private final class ImageCyclePageChangeListener
    implements ViewPager.OnPageChangeListener
  {
    private int preIndex = 0;

    private ImageCyclePageChangeListener()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      int i = paramInt % ImageCycleView.this.mCount;
      String str = ((ImageCycleView.ImageInfo)ImageCycleView.this.data.get(i)).text;
      TextView localTextView = ImageCycleView.this.mText;
      if (TextUtils.isEmpty(str))
        str = "";
      localTextView.setText(str);
      ((ImageView)ImageCycleView.this.mIndicationGroup.getChildAt(this.preIndex)).setImageBitmap(ImageCycleView.this.unFocusIndicationStyle);
      ((ImageView)ImageCycleView.this.mIndicationGroup.getChildAt(i)).setImageBitmap(ImageCycleView.this.focusIndicationStyle);
      this.preIndex = i;
    }
  }

  public class ImageCycleViewPager extends ViewPager
  {
    public ImageCycleViewPager(Context context)
    {
      super(context);
    }

    public ImageCycleViewPager(Context paramAttributeSet, AttributeSet attributeSet)
    {
      super(paramAttributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
    {
      getParent().requestDisallowInterceptTouchEvent(true);
      return super.dispatchTouchEvent(paramMotionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      return super.onInterceptTouchEvent(paramMotionEvent);
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      return super.onTouchEvent(paramMotionEvent);
    }
  }

  public static class ImageInfo
  {
    public Object image;
    public String text = "";
    public Object value;

    public ImageInfo(Object paramObject1, String paramString, Object paramObject2)
    {
      this.image = paramObject1;
      this.text = paramString;
      this.value = paramObject2;
    }
  }

  public static enum IndicationStyle
  {
    COLOR,IMAGE;
  }

  public static abstract interface LoadImageCallBack
  {
    public abstract ImageView loadAndDisplay(ImageCycleView.ImageInfo paramImageInfo);
  }

  public static abstract interface OnPageClickListener
  {
    public abstract void onClick(View paramView, ImageCycleView.ImageInfo paramImageInfo);
  }
}