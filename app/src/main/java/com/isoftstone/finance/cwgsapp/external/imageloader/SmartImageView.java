package com.isoftstone.finance.cwgsapp.external.imageloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.isoftstone.finance.cwgsapp.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartImageView extends ImageView
{
  private static final int LOADING_THREADS = 4;
  private static ExecutorService threadPool = Executors.newFixedThreadPool(4);
  private SmartImageTask currentTask;
  private Drawable mFailDrawable;
  private Drawable mLoadingDrawable;

  public SmartImageView(Context paramContext)
  {
    this(paramContext, null);
  }

  public SmartImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public SmartImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
//    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SmartImageView, paramInt, 0);
//    this.mLoadingDrawable = localTypedArray.getDrawable(R.styleable.SmartImageView_onLoading);
//    this.mFailDrawable = localTypedArray.getDrawable(R.styleable.SmartImageView_onFail);
//    localTypedArray.recycle();
  }

  public static void cancelAllTasks()
  {
    threadPool.shutdownNow();
    threadPool = Executors.newFixedThreadPool(4);
  }

  public void setImage(SmartImage paramSmartImage, final SmartImageTask.OnCompleteListener paramOnCompleteListener)
  {
    if (this.mLoadingDrawable != null)
      setImageDrawable(this.mLoadingDrawable);
    if (this.currentTask != null)
    {
      this.currentTask.cancel();
      this.currentTask = null;
    }
    this.currentTask = new SmartImageTask(getContext(), paramSmartImage);
    this.currentTask.setOnCompleteHandler(new SmartImageTask.OnCompleteHandler()
    {
      public void onComplete(Bitmap paramAnonymousBitmap)
      {
        if (paramAnonymousBitmap != null)
        {
          SmartImageView.this.setImageBitmap(paramAnonymousBitmap);
          if (paramOnCompleteListener != null)
            paramOnCompleteListener.onSuccess(paramAnonymousBitmap);
        }
        do
        {

          if (SmartImageView.this.mFailDrawable != null){
            SmartImageView.this.setImageDrawable(SmartImageView.this.mFailDrawable);
          }
        }
        while (paramOnCompleteListener == null);
        paramOnCompleteListener.onFail();
      }
    });
    threadPool.execute(this.currentTask);
  }

  public void setImageUrl(String paramString)
  {
    setImage(new WebImage(paramString), null);
  }

  public void setImageUrl(String paramString, SmartImageTask.OnCompleteListener paramOnCompleteListener)
  {
    setImage(new WebImage(paramString), paramOnCompleteListener);
  }
}