package com.isoftstone.finance.cwgsapp.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.pager.quice.QuiceItem;
import com.isoftstone.finance.cwgsapp.pager.quice.helper.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Iterator;

public class QuiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
  implements OnItemMoveListener
{
  public static final int ITEM_TYPE_ADD = 2;
  public static final int ITEM_TYPE_QUICE = 1;
  private static final long SPACE_TIME = 100L;
  public static final int START_QUICE_MANAGE = 4371;
  private Context context;
  private boolean isEditMode;
  Fragment mFragment;
  private ItemTouchHelper mItemTouchHelper;
  private ArrayList<QuiceItem> quiceItems;
  private long startTime;
  private ArrayList<QuiceItem> tempQuiceItems = new ArrayList();

  public QuiceAdapter(Fragment paramFragment, ItemTouchHelper paramItemTouchHelper, ArrayList<QuiceItem> paramArrayList1, ArrayList<QuiceItem> paramArrayList2)
  {
    this.quiceItems = paramArrayList1;
    this.tempQuiceItems = paramArrayList2;
    this.mItemTouchHelper = paramItemTouchHelper;
    this.mFragment = paramFragment;
  }

  private void cancelEditMode(RecyclerView paramRecyclerView)
  {
    this.isEditMode = false;
    int i = paramRecyclerView.getChildCount();
    for (int j = 0; j < i; j++)
    {
      ImageView localImageView = (ImageView)paramRecyclerView.getChildAt(j).findViewById(R.id.img_edit);
      if (localImageView != null)
        localImageView.setVisibility(View.GONE);
    }
  }

  private boolean isshowAddBtn()
  {
    for (int i = 0; i < this.tempQuiceItems.size(); i++)
      if (((QuiceItem)this.tempQuiceItems.get(i)).getItemType() == 2)
        return true;
    return false;
  }

  private void moveMyToOther(RecyclerView.ViewHolder paramViewHolder)
  {
    int i = paramViewHolder.getAdapterPosition();
    if (i > -1 + this.tempQuiceItems.size())
      return;
    QuiceItem localQuiceItem = (QuiceItem)this.tempQuiceItems.get(i);
    this.tempQuiceItems.remove(i);
    for (int j = 0; j < this.quiceItems.size(); j++)
      if (((QuiceItem)this.quiceItems.get(j)).getQuiceID() == localQuiceItem.getQuiceID())
        ((QuiceItem)this.quiceItems.get(j)).setShowHome(false);
    mYnotifyDataSetChanged();
  }

  private ArrayList<QuiceItem> saveQuiceItemData()
  {
    ArrayList localArrayList1 = new ArrayList();
    localArrayList1.addAll(this.quiceItems);
    ArrayList localArrayList2 = new ArrayList();
    localArrayList2.addAll(this.tempQuiceItems);
    Iterator localIterator1 = localArrayList2.iterator();
    while (localIterator1.hasNext())
      if (((QuiceItem)localIterator1.next()).getItemType() == 2)
        localIterator1.remove();
    for (int i = 0; i < localArrayList2.size(); i++)
    {
      int k = ((QuiceItem)localArrayList2.get(i)).getQuiceID();
      Iterator localIterator2 = localArrayList1.iterator();
      while (localIterator2.hasNext())
        if (k == ((QuiceItem)localIterator2.next()).getQuiceID())
          localIterator2.remove();
    }
    localArrayList1.addAll(0, localArrayList2);
    for (int j = 0; j < localArrayList1.size(); j++)
      ((QuiceItem)localArrayList1.get(j)).setId(j + 1);
    return localArrayList1;
  }

  private void startEditMode(int paramInt, RecyclerView paramRecyclerView)
  {
    this.isEditMode = true;
    ImageView localImageView = (ImageView)paramRecyclerView.getChildAt(paramInt).findViewById(R.id.img_edit);
    if (localImageView != null)
      localImageView.setVisibility(View.VISIBLE);
  }

  public int getItemCount()
  {
    return this.tempQuiceItems.size();
  }

  public int getItemViewType(int paramInt)
  {
    return ((QuiceItem)this.tempQuiceItems.get(paramInt)).getItemType();
  }

  public void mYnotifyDataSetChanged()
  {
    if (!isshowAddBtn())
    {
      QuiceItem localQuiceItem = new QuiceItem();
      localQuiceItem.setItemType(2);
      localQuiceItem.setShowHome(true);
      this.tempQuiceItems.add(localQuiceItem);
    }
    notifyDataSetChanged();
  }

  public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt)
  {
    switch (getItemViewType(paramInt))
    {
    default:
      return;
    case 1:
      MyViewHolder localMyViewHolder = (MyViewHolder)paramViewHolder;
      QuiceItem localQuiceItem = (QuiceItem)this.tempQuiceItems.get(paramInt);
      localMyViewHolder.imageView.setImageResource(localQuiceItem.getIconResID());
      localMyViewHolder.textView.setText(((QuiceItem)this.tempQuiceItems.get(paramInt)).getName());
      return;
    case 2:
//      ((AddViewHolder)paramViewHolder).;
      break;
    }

  }

  public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup paramViewGroup, int paramInt)
  {
    this.context = paramViewGroup.getContext();
    switch (paramInt)
    {
    default:
      return null;
    case 1:
      final MyViewHolder localMyViewHolder = new MyViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.test_item_grid, paramViewGroup, false));
      ((MyViewHolder)localMyViewHolder).img_edit.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localMyViewHolder.getAdapterPosition();
          if (QuiceAdapter.this.isEditMode)
          {
            QuiceAdapter.this.moveMyToOther(localMyViewHolder);
            QuiceAdapter.this.cancelEditMode((RecyclerView)paramViewGroup);
//            QuiceAdapter.access$002(QuiceAdapter.this, false);
          }
        }
      });
      ((MyViewHolder)localMyViewHolder).root.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (QuiceAdapter.this.isEditMode)
            QuiceAdapter.this.cancelEditMode((RecyclerView)paramViewGroup);
          QuiceItem localQuiceItem;
//          do
//          {
//            return;
//            int i = localMyViewHolder.getAdapterPosition();
//            localQuiceItem = (QuiceItem)QuiceAdapter.this.tempQuiceItems.get(i);
//            if (localQuiceItem.getName().equals("授信使用情况"))
//            {
//              Intent localIntent1 = new Intent(QuiceAdapter.this.context, CreditUseQueryActivity.class);
//              QuiceAdapter.this.context.startActivity(localIntent1);
//              return;
//            }
//            if (localQuiceItem.getName().equals("贷款合同"))
//            {
//              Intent localIntent2 = new Intent(QuiceAdapter.this.context, LoanContractActivity.class);
//              QuiceAdapter.this.context.startActivity(localIntent2);
//              return;
//            }
//            if (localQuiceItem.getName().equals("贴现合同"))
//            {
//              Intent localIntent3 = new Intent(QuiceAdapter.this.context, DiscountContractActivity.class);
//              QuiceAdapter.this.context.startActivity(localIntent3);
//              return;
//            }
//            if (localQuiceItem.getName().equals("代客结售汇"))
//            {
//              Intent localIntent4 = new Intent(QuiceAdapter.this.context, ReplaceExchangeActivity.class);
//              QuiceAdapter.this.context.startActivity(localIntent4);
//              return;
//            }
//            if (localQuiceItem.getName().equals("自身结售汇"))
//            {
//              Intent localIntent5 = new Intent(QuiceAdapter.this.context, SelfExchangeActivity.class);
//              QuiceAdapter.this.context.startActivity(localIntent5);
//              return;
//            }
//            if (localQuiceItem.getName().equals("客户信息"))
//            {
//              Intent localIntent6 = new Intent(QuiceAdapter.this.context, ClientMessageActivity.class);
//              QuiceAdapter.this.context.startActivity(localIntent6);
//              return;
//            }
//            if (localQuiceItem.getName().equals("银行账户信息"))
//            {
//              Intent localIntent7 = new Intent(QuiceAdapter.this.context, BankAccountQuery.class);
//              QuiceAdapter.this.context.startActivity(localIntent7);
//              return;
//            }
//            if (localQuiceItem.getName().equals("内部账户信息"))
//            {
//              Intent localIntent8 = new Intent(QuiceAdapter.this.context, InsideAccountActivity.class);
//              QuiceAdapter.this.context.startActivity(localIntent8);
//              return;
//            }
//            if (localQuiceItem.getName().equals("虚拟资金池"))
//            {
//              Intent localIntent9 = new Intent(QuiceAdapter.this.context, VirtualPoolActivity.class);
//              QuiceAdapter.this.context.startActivity(localIntent9);
//              return;
//            }
//          }
//          while (!localQuiceItem.getName().equals("交易记录"));
//          Intent localIntent10 = new Intent(QuiceAdapter.this.context, TranRecordActivity.class);
//          QuiceAdapter.this.context.startActivity(localIntent10);
        }
      });
      ((MyViewHolder)localMyViewHolder).root.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          if (!QuiceAdapter.this.isEditMode)
          {
            int j = localMyViewHolder.getAdapterPosition();
            RecyclerView localRecyclerView2 = (RecyclerView)paramViewGroup;
            QuiceAdapter.this.startEditMode(j, localRecyclerView2);
            if (localRecyclerView2.getChildAt(0) != localRecyclerView2.getLayoutManager().findViewByPosition(0));
          }
          while (true)
          {
            QuiceAdapter.this.mItemTouchHelper.startDrag(localMyViewHolder);
            QuiceAdapter.this.cancelEditMode((RecyclerView)paramViewGroup);
            RecyclerView localRecyclerView1 = (RecyclerView)paramViewGroup;
            int i = localMyViewHolder.getAdapterPosition();
            QuiceAdapter.this.startEditMode(i, localRecyclerView1);
            return true;
          }
        }
      });
      ((MyViewHolder)localMyViewHolder).root.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (QuiceAdapter.this.isEditMode)
            switch (MotionEventCompat.getActionMasked(paramAnonymousMotionEvent))
            {
            default:
            case 0:
            case 2:
            case 1:
            case 3:
            }
          while (true)
          {
//            return false;
//            QuiceAdapter.access$702(QuiceAdapter.this, System.currentTimeMillis());
//            continue;
            if (System.currentTimeMillis() - QuiceAdapter.this.startTime > 100L)
            {
              QuiceAdapter.this.cancelEditMode((RecyclerView)paramViewGroup);
              RecyclerView localRecyclerView = (RecyclerView)paramViewGroup;
              int i = localMyViewHolder.getAdapterPosition();
              QuiceAdapter.this.startEditMode(i, localRecyclerView);
              QuiceAdapter.this.mItemTouchHelper.startDrag(localMyViewHolder);
              continue;
//              QuiceAdapter.access$702(QuiceAdapter.this, 0L);
            }
          }
        }
      });
      return localMyViewHolder;
    case 2:
    }
    AddViewHolder localAddViewHolder = new AddViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.layout_home_quice_addbtn, paramViewGroup, false));
//    ((RecyclerView)paramViewGroup);
    ((AddViewHolder)localAddViewHolder).rootview.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (QuiceAdapter.this.isEditMode)
        {
          RecyclerView localRecyclerView = (RecyclerView)paramViewGroup;
          QuiceAdapter.this.cancelEditMode(localRecyclerView);
          return;
        }
//        Intent localIntent = new Intent(QuiceAdapter.this.context, QuiceMamangeActivity.class);
//        Bundle localBundle = new Bundle();
//        localBundle.putSerializable("quiceItems", QuiceAdapter.this.saveQuiceItemData());
//        localIntent.putExtras(localBundle);
//        QuiceAdapter.this.mFragment.startActivityForResult(localIntent, 4371);
      }
    });
    return localAddViewHolder;
  }

  public void onItemMove(int paramInt1, int paramInt2)
  {
    QuiceItem localQuiceItem = (QuiceItem)this.tempQuiceItems.get(paramInt1);
    this.tempQuiceItems.remove(paramInt1);
    this.tempQuiceItems.add(paramInt2, localQuiceItem);
    notifyItemMoved(paramInt1, paramInt2);
  }

  public class AddViewHolder extends RecyclerView.ViewHolder
  {

    @Bind(R.id.rootview)
    LinearLayout rootview;

    public AddViewHolder(View view)
    {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

  public class MyViewHolder extends RecyclerView.ViewHolder
  {
    public ImageView imageView;
    public ImageView img_edit;
    public RelativeLayout root;
    public TextView textView;

    public MyViewHolder(View view)
    {
      super(view);
      int i = ((WindowManager)QuiceAdapter.this.context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
      ViewGroup.LayoutParams localLayoutParams = view.getLayoutParams();
      localLayoutParams.height = (i / 4);
      view.setLayoutParams(localLayoutParams);
      this.textView = ((TextView)view.findViewById(R.id.item_text));
      this.imageView = ((ImageView)view.findViewById(R.id.item_img));
      this.img_edit = ((ImageView)view.findViewById(R.id.img_edit));
      this.root = ((RelativeLayout)view.findViewById(R.id.root));
    }
  }
}