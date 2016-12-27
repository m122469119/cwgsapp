package com.isoftstone.finance.cwgsapp.external;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

public class CircularImage extends MaskedImage {
    public CircularImage(Context paramContext) {
        super(paramContext);
    }

    public CircularImage(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public CircularImage(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public Bitmap createMask() {
        Bitmap localBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        Paint localPaint = new Paint(1);
        localPaint.setColor(getResources().getColor(2131558425));
        localCanvas.drawOval(new RectF(0.0F, 0.0F, getWidth(), getHeight()), localPaint);
        return localBitmap;
    }
}