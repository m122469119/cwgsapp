package com.isoftstone.finance.cwgsapp.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.isoftstone.finance.cwgsapp.external.ViewHolder;

import java.util.List;


/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-29 11:22
*/
public abstract class Adapter<T> extends BaseAdapter
{
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected final int mItemLayoutId;

    public Adapter(Context paramContext, List<T> paramList, int paramInt)
    {
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mDatas = paramList;
        this.mItemLayoutId = paramInt;
    }

    private ViewHolder getViewHolder(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
        return ViewHolder.get(this.mContext, paramView, paramViewGroup, this.mItemLayoutId, paramInt);
    }

    public abstract void convert(ViewHolder paramViewHolder, T paramT, int paramInt);

    public int getCount()
    {
        return this.mDatas.size();
    }

    public T getItem(int paramInt)
    {
        return this.mDatas.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
        ViewHolder localViewHolder = getViewHolder(paramInt, paramView, paramViewGroup);
        convert(localViewHolder, getItem(paramInt), paramInt);
        return localViewHolder.getConvertView();
    }
}