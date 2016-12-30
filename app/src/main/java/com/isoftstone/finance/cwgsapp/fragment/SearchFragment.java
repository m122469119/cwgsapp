package com.isoftstone.finance.cwgsapp.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.pager.QuiceAdapter;
import com.isoftstone.finance.cwgsapp.pager.quice.QuiceItem;
import com.isoftstone.finance.cwgsapp.pager.quice.helper.ItemDragHelperCallback;
import com.isoftstone.finance.cwgsapp.pager.test.common.DividerGridItemDecoration;
import com.isoftstone.finance.cwgsapp.responseBean.PowerInfo;
import com.litesuits.orm.LiteOrm;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(11)
public class SearchFragment extends Fragment
  implements View.OnClickListener
{
  public static final int QUICE_MANAGE_RESULTCODE = 4371;
  private final String DB_NAME = "gljr.db";
  QuiceAdapter adapter;

  @Bind(R.id.imgbtn_left)
  ImageView imgbtnLeft;
  private ItemTouchHelper itemTouchHelper;

  @Bind(R.id.ll_accountmanager)
  LinearLayout ll_accountmanager;

  @Bind(R.id.ll_clientmanager)
  LinearLayout ll_clientmanager;

  @Bind(R.id.ll_fcurrencymanager)
  LinearLayout ll_fcurrencymanager;

  @Bind(R.id.ll_loanmanager)
  LinearLayout ll_loanmanager;

  @Bind(R.id.ll_statementmanager)
  LinearLayout ll_statementmanager;
  public LiteOrm mLiteOrm;
  ArrayList<String> powerResult = new ArrayList();

  @Bind(R.id.recycleView)
  RecyclerView recycleView;
  private ArrayList<QuiceItem> results;
  ArrayList<QuiceItem> tempQuiceItems;

  @Bind(R.id.txt_title)
  TextView txtTitle;

  private void processData(ArrayList<QuiceItem> paramArrayList)
  {
    this.results.clear();
    this.results.addAll(paramArrayList);
    this.tempQuiceItems.clear();
    for (int i = 0; i < this.results.size(); i++)
      if (((QuiceItem)this.results.get(i)).isShowHome())
        this.tempQuiceItems.add(this.results.get(i));
    if (this.adapter != null)
      this.adapter.mYnotifyDataSetChanged();
  }

  private void saveQuiceItemData()
  {
    Log.e("TAG", "hahah");
    Iterator localIterator1 = this.tempQuiceItems.iterator();
    while (localIterator1.hasNext())
      if (((QuiceItem)localIterator1.next()).getItemType() == 2)
        localIterator1.remove();
    for (int i = 0; i < this.tempQuiceItems.size(); i++)
    {
      int k = ((QuiceItem)this.tempQuiceItems.get(i)).getQuiceID();
      Iterator localIterator2 = this.results.iterator();
      while (localIterator2.hasNext())
        if (k == ((QuiceItem)localIterator2.next()).getQuiceID())
          localIterator2.remove();
    }
    this.results.addAll(0, this.tempQuiceItems);
    for (int j = 0; j < this.results.size(); j++)
      ((QuiceItem)this.results.get(j)).setId(j + 1);
    getLiteOrm().deleteAll(QuiceItem.class);
    getLiteOrm().insert(this.results);
  }

  public LiteOrm getLiteOrm()
  {
    if (this.mLiteOrm == null)
    {
      this.mLiteOrm = LiteOrm.newSingleInstance(getActivity(), "gljr.db");
      this.mLiteOrm.setDebugged(true);
    }
    return this.mLiteOrm;
  }

  public void init()
  {
    ArrayList localArrayList1 = getLiteOrm().query(QuiceItem.class);
    if (localArrayList1.isEmpty())
    {
      localArrayList1.add(new QuiceItem(1, 2130903067, 1, "授信使用情况"));
      localArrayList1.add(new QuiceItem(2, 2130903075, 1, "贷款合同"));
      localArrayList1.add(new QuiceItem(3, 2130903068, 1, "贴现合同"));
      localArrayList1.add(new QuiceItem(4, 2130903055, 1, "代客结售汇"));
      localArrayList1.add(new QuiceItem(5, 2130903076, 1, "自身结售汇"));
      localArrayList1.add(new QuiceItem(6, 2130903063, 1, "客户信息"));
      localArrayList1.add(new QuiceItem(7, 2130903072, 1, "银行账户信息"));
      localArrayList1.add(new QuiceItem(8, 2130903064, 1, "内部账户信息"));
      localArrayList1.add(new QuiceItem(9, 2130903071, 1, "虚拟资金池"));
      localArrayList1.add(new QuiceItem(11, 2130903062, 1, "交易记录"));
    }
    ArrayList localArrayList2 = new ArrayList();
    localArrayList2.addAll(localArrayList1);
    Iterator localIterator = localArrayList2.iterator();
    while (localIterator.hasNext())
    {
      QuiceItem localQuiceItem = (QuiceItem)localIterator.next();
      if (!this.powerResult.contains(localQuiceItem.getName()))
        localArrayList1.remove(localQuiceItem);
    }
    processData(localArrayList1);
    ItemTouchHelper localItemTouchHelper = new ItemTouchHelper(new ItemDragHelperCallback());
    localItemTouchHelper.attachToRecyclerView(this.recycleView);
    this.adapter = new QuiceAdapter(this, localItemTouchHelper, this.results, this.tempQuiceItems);
    this.adapter.mYnotifyDataSetChanged();
    this.recycleView.setHasFixedSize(true);
    this.recycleView.setAdapter(this.adapter);
    this.recycleView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    this.recycleView.addItemDecoration(new DividerGridItemDecoration());
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 4371)
    {
      if (this.results != null)
        processData((ArrayList)paramIntent.getSerializableExtra("quiceitems"));
      saveQuiceItemData();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
//    switch (paramView.getId())
//    {
//    default:
//      return;
//    case 2131624872:
//      Intent localIntent5 = new Intent(getContext(), LoanManagerActivity.class);
//      Bundle localBundle5 = new Bundle();
//      localBundle5.putStringArrayList("menus", this.powerResult);
//      localIntent5.putExtra("Menus", localBundle5);
//      startActivity(localIntent5);
//      return;
//    case 2131624875:
//      Intent localIntent4 = new Intent(getContext(), FCurrencyActivity.class);
//      Bundle localBundle4 = new Bundle();
//      localBundle4.putStringArrayList("menus", this.powerResult);
//      localIntent4.putExtra("Menus", localBundle4);
//      startActivity(localIntent4);
//      return;
//    case 2131624873:
//      Intent localIntent3 = new Intent(getContext(), StatementActivity.class);
//      Bundle localBundle3 = new Bundle();
//      localBundle3.putStringArrayList("menus", this.powerResult);
//      localIntent3.putExtra("Menus", localBundle3);
//      startActivity(localIntent3);
//      return;
//    case 2131624876:
//      Intent localIntent2 = new Intent(getContext(), ClientActivity.class);
//      Bundle localBundle2 = new Bundle();
//      localBundle2.putStringArrayList("menus", this.powerResult);
//      localIntent2.putExtra("Menus", localBundle2);
//      startActivity(localIntent2);
//      return;
//    case 2131624874:
//    }
//    Intent localIntent1 = new Intent(getContext(), AccountActivity.class);
//    Bundle localBundle1 = new Bundle();
//    localBundle1.putStringArrayList("menus", this.powerResult);
//    localIntent1.putExtra("Menus", localBundle1);
//    startActivity(localIntent1);
  }

  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    View localView = View.inflate(getActivity(), R.layout.pager_testsearch, null);
    ButterKnife.bind(this, localView);
    this.txtTitle.setText("搜  索");
    this.imgbtnLeft.setVisibility(View.GONE);
    this.ll_accountmanager.setOnClickListener(this);
    this.ll_clientmanager.setOnClickListener(this);
    this.ll_fcurrencymanager.setOnClickListener(this);
    this.ll_loanmanager.setOnClickListener(this);
    this.ll_statementmanager.setOnClickListener(this);
    this.results = new ArrayList();
    this.tempQuiceItems = new ArrayList();
    Bundle localBundle = getActivity().getIntent().getBundleExtra("Menus");
    this.powerResult.clear();
    ArrayList localArrayList = localBundle.getParcelableArrayList("menus");
    if (localArrayList != null)
    {
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        PowerInfo localPowerInfo = (PowerInfo)localIterator.next();
        this.powerResult.add(localPowerInfo.menuName);
      }
    }
    return localView;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onPause()
  {
    super.onPause();
    saveQuiceItemData();
  }

  public void onResume()
  {
    super.onResume();
    init();
  }
}