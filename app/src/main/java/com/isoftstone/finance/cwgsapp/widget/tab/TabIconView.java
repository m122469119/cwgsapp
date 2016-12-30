package com.isoftstone.finance.cwgsapp.widget.tab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class TabIconView extends ImageView {
    private Bitmap mNormalIcon;
    private Rect mNormalRect;
    private Paint mPaint;
    private int mSelectedAlpha = 0;
    private Bitmap mSelectedIcon;
    private Rect mSelectedRect;

    public TabIconView(Context paramContext) {
        super(paramContext);
    }

    public TabIconView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public TabIconView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    private Bitmap createBitmap(int paramInt) {
        return BitmapFactory.decodeResource(getResources(), paramInt);
    }

    public final void changeSelectedAlpha(int paramInt) {
        this.mSelectedAlpha = paramInt;
        invalidate();
    }

    public final void init(int paramInt1, int paramInt2) {
        this.mNormalIcon = createBitmap(paramInt1);
        this.mSelectedIcon = createBitmap(paramInt2);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.mNormalIcon.getHeight();
        layoutParams.width = this.mNormalIcon.getWidth();
        setLayoutParams(layoutParams);
        this.mNormalRect = new Rect(0, 0, this.mNormalIcon.getWidth(), this.mNormalIcon.getHeight());
        this.mSelectedRect = new Rect(0, 0, this.mSelectedIcon.getWidth(), this.mSelectedIcon.getHeight());
        this.mPaint = new Paint(1);
    }

    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        if (this.mPaint == null)
            return;
        this.mPaint.setAlpha(255 - this.mSelectedAlpha);
        paramCanvas.drawBitmap(this.mNormalIcon, null, this.mNormalRect, this.mPaint);
        this.mPaint.setAlpha(this.mSelectedAlpha);
        paramCanvas.drawBitmap(this.mSelectedIcon, null, this.mSelectedRect, this.mPaint);
    }

    public final void transformPage(float paramFloat) {
        changeSelectedAlpha((int) (255 * (1 - paramFloat)));
    }
}