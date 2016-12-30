package com.isoftstone.finance.cwgsapp.pager;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.base.BasePager;
import com.isoftstone.finance.cwgsapp.bean.DoneApprovalItem;
import com.isoftstone.finance.cwgsapp.external.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoneApprovalPager extends BasePager
{

  @Bind(R.id.lv_yishenpi)
  ListView lv_yishenpi;

  public DoneApprovalPager(Context paramContext)
  {
    super(paramContext);
  }

  public void initData()
  {
    ArrayList localArrayList = new ArrayList();
    String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    localArrayList.add(new DoneApprovalItem("未通过", "张三", str));
    localArrayList.add(new DoneApprovalItem("已拒绝", "李四", str));
    localArrayList.add(new DoneApprovalItem("待审批", "王五", str));
//    Adapter local1 = new Adapter(this.context, localArrayList, R.layout.item_doneapproval)
//    {
//      public void convert(ViewHolder paramAnonymousViewHolder, DoneApprovalItem paramAnonymousDoneApprovalItem, int paramAnonymousInt)
//      {
//        TextView localTextView = (TextView)paramAnonymousViewHolder.getView(R.id.tvdraw_status);
//        if (paramAnonymousDoneApprovalItem.getStatus().equals("未通过"))
//          localTextView.setBackgroundResource(R.drawable.shape_orange);
//        while (true)
//        {
//          paramAnonymousViewHolder.setText(R.id.tvdraw_status, paramAnonymousDoneApprovalItem.getStatus());
//          paramAnonymousViewHolder.setText(R.id.tvdraw_username, paramAnonymousDoneApprovalItem.getUsername());
//          paramAnonymousViewHolder.setText(R.id.tvdraw_time, paramAnonymousDoneApprovalItem.getTime());
//          localTextView.setBackgroundResource(R.drawable.shape_8d8d8d);//shape_8d8d8d
//          return;
//        }
//      }
//    };
//    this.lv_yishenpi.setAdapter(local1);
  }

  public View initView()
  {
    View view = View.inflate(DoneApprovalPager.this.context, R.layout.pager_doneapproval, null);
    ButterKnife.bind(this, view);
    initData();
    return view;
  }
}