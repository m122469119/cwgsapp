package com.isoftstone.finance.cwgsapp.external;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-29 11:22
*/
public class ViewHolder
{
    private View mConvertView;
    private final SparseArray<View> mViews = new SparseArray();

    private ViewHolder(Context paramContext, ViewGroup paramViewGroup, int paramInt1, int paramInt2)
    {
        this.mConvertView = LayoutInflater.from(paramContext).inflate(paramInt1, paramViewGroup, false);
        this.mConvertView.setTag(this);
    }

    public static ViewHolder get(Context paramContext, View paramView, ViewGroup paramViewGroup, int paramInt1, int paramInt2)
    {
        if (paramView == null)
            return new ViewHolder(paramContext, paramViewGroup, paramInt1, paramInt2);
        return (ViewHolder)paramView.getTag();
    }

    public View getConvertView()
    {
        return this.mConvertView;
    }

    public <T extends View> T getView(int paramInt)
    {
        View localView = (View)this.mViews.get(paramInt);
        if (localView == null)
        {
            localView = this.mConvertView.findViewById(paramInt);
            this.mViews.put(paramInt, localView);
        }
//        return localView;
        return null;
    }

    public ViewHolder setImageResource(int paramInt1, int paramInt2)
    {
        ((ImageView)getView(paramInt1)).setImageResource(paramInt2);
        return this;
    }

    public ViewHolder setText(int paramInt, String paramString)
    {
        ((TextView)getView(paramInt)).setText(paramString);
        return this;
    }
}