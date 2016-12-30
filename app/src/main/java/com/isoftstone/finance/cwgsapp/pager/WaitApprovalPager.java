package com.isoftstone.finance.cwgsapp.pager;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.activity.MatchAccessActivity;
import com.isoftstone.finance.cwgsapp.base.BasePager;
import com.isoftstone.finance.cwgsapp.bean.WaitApprovalItem;
import com.isoftstone.finance.cwgsapp.external.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class WaitApprovalPager extends BasePager
{

  @Bind(R.id.lv_daishenpi)
  ListView lv_daishenpi;

  public WaitApprovalPager(Context paramContext)
  {
    super(paramContext);
  }

  public void initData()
  {
    final ArrayList localArrayList = new ArrayList();
    localArrayList.add(new WaitApprovalItem("账银对账日", "1"));
    localArrayList.add(new WaitApprovalItem("通知开立", "2"));
    localArrayList.add(new WaitApprovalItem("定期开立", "3"));
//    Adapter arrayAdapter = new Adapter(this.context, localArrayList,R.layout.item_waitapproval_lv)
//    {
//      public void convert(ViewHolder paramAnonymousViewHolder, WaitApprovalItem paramAnonymousWaitApprovalItem, int paramAnonymousInt)
//      {
//        paramAnonymousViewHolder.setText(R.id.tvleft_operationname, paramAnonymousWaitApprovalItem.getOperationName());
//        paramAnonymousViewHolder.setText(R.id.tvleft_operation_num, paramAnonymousWaitApprovalItem.getOperationNum());
//      }
//    };
//    this.lv_daishenpi.setAdapter(arrayAdapter);
    this.lv_daishenpi.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        WaitApprovalItem localWaitApprovalItem = (WaitApprovalItem)localArrayList.get(paramAnonymousInt);
        Intent localIntent = new Intent();
        localIntent.setClass(WaitApprovalPager.this.context, MatchAccessActivity.class);
        localIntent.putExtra("title", localWaitApprovalItem.getOperationName());
        localIntent.putExtra("number", localWaitApprovalItem.getOperationNum());
        WaitApprovalPager.this.context.startActivity(localIntent);
      }
    });
  }

  public View initView()
  {
    View localView = View.inflate(WaitApprovalPager.this.context, R.layout.pager_waitapproval, null);
    ButterKnife.bind(this, localView);
    initData();
    return localView;
  }
}