package com.isoftstone.finance.cwgsapp.external.rollvviewpager.hintview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import com.isoftstone.finance.cwgsapp.external.rollvviewpager.Util;

public class IconHintView extends ShapeHintView {
    private int focusResId;
    private int normalResId;
    private int size;

    public IconHintView(Context paramContext, @DrawableRes int paramInt1, @DrawableRes int paramInt2) {
        this(paramContext, paramInt1, paramInt2, Util.dip2px(paramContext, 22.0F));
    }

    public IconHintView(Context paramContext, @DrawableRes int paramInt1, @DrawableRes int paramInt2, int paramInt3) {
        super(paramContext);
        this.focusResId = paramInt1;
        this.normalResId = paramInt2;
        this.size = paramInt3;
    }

    private Bitmap drawableToBitmap(Drawable paramDrawable) {
        int i = paramDrawable.getIntrinsicWidth();
        int j = paramDrawable.getIntrinsicHeight();
        if (paramDrawable.getOpacity() != PixelFormat.OPAQUE) ;
        for (Bitmap.Config localConfig = Bitmap.Config.ARGB_8888; ; localConfig = Bitmap.Config.RGB_565) {
            Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
            Canvas localCanvas = new Canvas(localBitmap);
            paramDrawable.setBounds(0, 0, i, j);
            paramDrawable.draw(localCanvas);
            return localBitmap;
        }
    }

    private Drawable zoomDrawable(Drawable paramDrawable, int paramInt1, int paramInt2) {
        int i = paramDrawable.getIntrinsicWidth();
        int j = paramDrawable.getIntrinsicHeight();
        Bitmap localBitmap = drawableToBitmap(paramDrawable);
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(paramInt1 / i, paramInt2 / j);
        return new BitmapDrawable(null, Bitmap.createBitmap(localBitmap, 0, 0, i, j, localMatrix, true));
    }

    public Drawable makeFocusDrawable() {
        Drawable localDrawable = getContext().getResources().getDrawable(this.focusResId);
        if (this.size > 0)
            localDrawable = zoomDrawable(localDrawable, this.size, this.size);
        return localDrawable;
    }

    public Drawable makeNormalDrawable() {
        Drawable localDrawable = getContext().getResources().getDrawable(this.normalResId);
        if (this.size > 0)
            localDrawable = zoomDrawable(localDrawable, this.size, this.size);
        return localDrawable;
    }
}