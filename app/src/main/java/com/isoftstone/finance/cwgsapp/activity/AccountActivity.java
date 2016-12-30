package com.isoftstone.finance.cwgsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.activity.query.BankAccountQuery;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-29 10:21
*/
public class AccountActivity extends AppCompatActivity
        implements View.OnClickListener
{

    @Bind(R.id.imgbtn_left)
    ImageView imgbtnLeft;

    @Bind(R.id.iv1)
    ImageView iv1;

    @Bind(R.id.iv2)
    ImageView iv2;

    @Bind(R.id.ll_account1)
    LinearLayout llAccount1;

    @Bind(R.id.ll_account2)
    LinearLayout llAccount2;
    ArrayList<String> powerResult = new ArrayList();

    @Bind(R.id.txt_title)
    TextView txtTitle;

    protected void init()
    {
        View localView = View.inflate(this, R.layout.activity_accountmanager, null);
        ButterKnife.bind(this, localView);
        setContentView(localView);
        this.txtTitle.setText("账户管理");
        this.imgbtnLeft.setOnClickListener(this);
        this.llAccount1.setOnClickListener(this);
        this.llAccount2.setOnClickListener(this);
    }

    protected void initView()
    {

        Bundle localBundle = getIntent().getBundleExtra("Menus");
        this.powerResult.clear();
        ArrayList localArrayList = localBundle.getStringArrayList("menus");

        if (localArrayList != null)
        {
            Iterator localIterator = localArrayList.iterator();
            while (localIterator.hasNext())
            {
                String str = (String)localIterator.next();
                this.powerResult.add(str);
            }
        }
        if (!this.powerResult.contains("银行账户信息"))
        {
            this.llAccount1.setVisibility(View.GONE);
            this.iv1.setVisibility(View.GONE);
        }
        if (!this.powerResult.contains("内部账户信息"))
        {
            this.llAccount2.setVisibility(View.GONE);
            this.iv2.setVisibility(View.GONE);
        }
    }

    public void onClick(View paramView)
    {
        switch (paramView.getId())
        {
            default:
                return;
            case R.id.imgbtn_left:
                finish();
                return;
            case R.id.ll_account1:
                startActivity(new Intent(this, BankAccountQuery.class));
                return;
            case R.id.ll_account2:
                System.out.println("点击了内部账户信息");
//                startActivity(new Intent(this, InsideAccountActivity.class));
                return;
        }

    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_accountmanager);
        ButterKnife.bind(this);
        init();
//        initView();
    }
}