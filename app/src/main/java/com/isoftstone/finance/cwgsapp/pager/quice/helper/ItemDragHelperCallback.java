package com.isoftstone.finance.cwgsapp.pager.quice.helper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ItemDragHelperCallback extends ItemTouchHelper.Callback {
    public void clearView(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder) {
        if ((paramViewHolder instanceof OnDragVHListener))
            ((OnDragVHListener) paramViewHolder).onItemFinish();
        super.clearView(paramRecyclerView, paramViewHolder);
    }

    public int getMovementFlags(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder) {
        RecyclerView.LayoutManager localLayoutManager = paramRecyclerView.getLayoutManager();
        if (((localLayoutManager instanceof GridLayoutManager)) || ((localLayoutManager instanceof StaggeredGridLayoutManager)))
            ;
        for (int i = 15; ; i = 3)
            return makeMovementFlags(i, 0);
    }

    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public boolean onMove(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder1, RecyclerView.ViewHolder paramViewHolder2) {
        if (paramViewHolder1.getItemViewType() != paramViewHolder2.getItemViewType())
            return false;
        if ((paramRecyclerView.getAdapter() instanceof OnItemMoveListener))
            ((OnItemMoveListener) paramRecyclerView.getAdapter()).onItemMove(paramViewHolder1.getAdapterPosition(), paramViewHolder2.getAdapterPosition());
        return true;
    }

    public void onSelectedChanged(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        if ((paramInt != 0) && ((paramViewHolder instanceof OnDragVHListener)))
            ((OnDragVHListener) paramViewHolder).onItemSelected();
        super.onSelectedChanged(paramViewHolder, paramInt);
    }

    public void onSwiped(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    }
}