package com.isoftstone.finance.cwgsapp.pager.test.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {16843284};
    private int lineWidth = 1;
    private Drawable mDivider;

    public DividerGridItemDecoration() {
        this(Color.parseColor("#dddddd"));
    }

    public DividerGridItemDecoration(int paramInt) {
        this.mDivider = new ColorDrawable(paramInt);
    }

    public DividerGridItemDecoration(Context paramContext) {
        TypedArray localTypedArray = paramContext.obtainStyledAttributes(ATTRS);
        this.mDivider = localTypedArray.getDrawable(0);
        localTypedArray.recycle();
    }

    private int getSpanCount(RecyclerView paramRecyclerView) {
        int i = -1;
        RecyclerView.LayoutManager localLayoutManager = paramRecyclerView.getLayoutManager();
        if ((localLayoutManager instanceof GridLayoutManager))
            i = ((GridLayoutManager) localLayoutManager).getSpanCount();
        while (!(localLayoutManager instanceof StaggeredGridLayoutManager))
            return i;
        return ((StaggeredGridLayoutManager) localLayoutManager).getSpanCount();
    }

    private boolean isLastColum(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3) {
        RecyclerView.LayoutManager localLayoutManager = paramRecyclerView.getLayoutManager();
        if ((localLayoutManager instanceof GridLayoutManager)) {
            if ((paramInt1 + 1) % paramInt2 != 0) ;
        } else
            do {
//                return true;
                if (!(localLayoutManager instanceof StaggeredGridLayoutManager))
//                    break;
                if (((StaggeredGridLayoutManager) localLayoutManager).getOrientation() != 1){

                }
//                    break label54;
            }
            while ((paramInt1 + 1) % paramInt2 == 0);
//        label54:
        while (paramInt1 < paramInt3 - paramInt3 % paramInt2)
            return false;
        return true;
    }

    private boolean isLastRaw(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3) {
        RecyclerView.LayoutManager localLayoutManager = paramRecyclerView.getLayoutManager();
        if ((localLayoutManager instanceof GridLayoutManager)) {
            if (paramInt1 < paramInt3 - paramInt3 % paramInt2) ;
        } else
            do {
//                return true;
                if (!(localLayoutManager instanceof StaggeredGridLayoutManager))
                    break;
                if (((StaggeredGridLayoutManager) localLayoutManager).getOrientation() != 1){

                }
//                    break label60;
            }
            while (paramInt1 >= paramInt3 - paramInt3 % paramInt2);
//        label60:
        while ((paramInt1 + 1) % paramInt2 != 0)
            return false;
        return true;
    }

    public void drawHorizontal(Canvas paramCanvas, RecyclerView paramRecyclerView) {
        int i = paramRecyclerView.getChildCount();
        for (int j = 0; j < i; j++) {
            View localView = paramRecyclerView.getChildAt(j);
            RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams) localView.getLayoutParams();
            int k = localView.getLeft() - localLayoutParams.leftMargin;
            int m = localView.getRight() + localLayoutParams.rightMargin + this.lineWidth;
            int n = localView.getBottom() + localLayoutParams.bottomMargin;
            int i1 = n + this.lineWidth;
            this.mDivider.setBounds(k, n, m, i1);
            this.mDivider.draw(paramCanvas);
        }
    }

    public void drawVertical(Canvas paramCanvas, RecyclerView paramRecyclerView) {
        int i = paramRecyclerView.getChildCount();
        for (int j = 0; j < i; j++) {
            View localView = paramRecyclerView.getChildAt(j);
            RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams) localView.getLayoutParams();
            int k = localView.getTop() - localLayoutParams.topMargin;
            int m = localView.getBottom() + localLayoutParams.bottomMargin;
            int n = localView.getRight() + localLayoutParams.rightMargin;
            int i1 = n + this.lineWidth;
            this.mDivider.setBounds(n, k, i1, m);
            this.mDivider.draw(paramCanvas);
        }
    }

    public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
        paramState.willRunPredictiveAnimations();
        ((RecyclerView.LayoutParams) paramView.getLayoutParams()).getViewLayoutPosition();
        getSpanCount(paramRecyclerView);
        paramRecyclerView.getAdapter().getItemCount();
        paramRect.set(0, 0, this.lineWidth, this.lineWidth);
    }

    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
        drawHorizontal(paramCanvas, paramRecyclerView);
        drawVertical(paramCanvas, paramRecyclerView);
    }
}