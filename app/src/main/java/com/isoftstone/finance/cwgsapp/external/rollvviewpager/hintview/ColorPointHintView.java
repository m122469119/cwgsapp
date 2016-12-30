package com.isoftstone.finance.cwgsapp.external.rollvviewpager.hintview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.Util;

public class ColorPointHintView extends ShapeHintView {
    private int focusColor;
    private int normalColor;

    public ColorPointHintView(Context paramContext, int paramInt1, int paramInt2) {
        super(paramContext);
        this.focusColor = paramInt1;
        this.normalColor = paramInt2;
    }

    public Drawable makeFocusDrawable() {
        GradientDrawable localGradientDrawable = new GradientDrawable();
        localGradientDrawable.setColor(this.focusColor);
        localGradientDrawable.setCornerRadius(Util.dip2px(getContext(), 4.0F));
        localGradientDrawable.setSize(Util.dip2px(getContext(), 8.0F), Util.dip2px(getContext(), 8.0F));
        return localGradientDrawable;
    }

    public Drawable makeNormalDrawable() {
        GradientDrawable localGradientDrawable = new GradientDrawable();
        localGradientDrawable.setColor(this.normalColor);
        localGradientDrawable.setCornerRadius(Util.dip2px(getContext(), 4.0F));
        localGradientDrawable.setSize(Util.dip2px(getContext(), 8.0F), Util.dip2px(getContext(), 8.0F));
        return localGradientDrawable;
    }
}