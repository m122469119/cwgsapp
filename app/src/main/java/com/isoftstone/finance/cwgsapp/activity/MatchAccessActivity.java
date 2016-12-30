package com.isoftstone.finance.cwgsapp.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.base.BaseActivity;
import com.isoftstone.finance.cwgsapp.bean.MatchAccessItem;
import com.isoftstone.finance.cwgsapp.external.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchAccessActivity extends BaseActivity
        implements View.OnClickListener {

    @Bind(R.id.imgbtn_left)
    ImageView leave;

    @Bind(R.id.ls_matchaccess)
    ListView ls_matchaccess;

    @Bind(R.id.txt_title)
    TextView txt_title;

    String number;
    int numbers;

    protected void init() {
        View localView = View.inflate(this, R.layout.acticity_matchaccess, null);
        ButterKnife.bind(this, localView);
        setContentView(localView);
        Intent localIntent = getIntent();
        String str = localIntent.getStringExtra("title");
        this.number = localIntent.getStringExtra("number");
        if (this.number.equals("1")){
            this.numbers = Integer.valueOf(this.number).intValue();
        }
        if (this.number.equals("2")){
            this.numbers = Integer.valueOf(this.number).intValue();
        }
        if (this.number.equals("3")){
            this.numbers = Integer.valueOf(this.number).intValue();
        }
        this.txt_title.setText(str);
        this.leave.setOnClickListener(this);
        initData();
    }

    public void initData() {
        ArrayList localArrayList = new ArrayList();
        String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
//        for (int i = 0; i < this.numbers; i++)
//            localArrayList.add(new MatchAccessItem("中海集团财务有限责任公司", "待审批", "赵子龙", str));
//        Adapter local1 = new Adapter(this, localArrayList, R.layout.item_matchaccese) {
//            public void convert(ViewHolder paramAnonymousViewHolder, MatchAccessItem paramAnonymousMatchAccessItem, int paramAnonymousInt) {
//                paramAnonymousViewHolder.setText(R.id.tv_companyname, paramAnonymousMatchAccessItem.getCommpanyname());
//                paramAnonymousViewHolder.setText(R.id.tv_matchstatus, paramAnonymousMatchAccessItem.getStatus());
//                paramAnonymousViewHolder.setText(R.id.tv_creationname, paramAnonymousMatchAccessItem.getCreator());
//                paramAnonymousViewHolder.setText(R.id.tv_creationtime, paramAnonymousMatchAccessItem.getCreationtime());
//            }
//        };
//        this.ls_matchaccess.setAdapter(local1);
    }

    protected void initView() {
    }

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.imgbtn_left:
                finish();
                break;
            default:
                break;
        }
    }
}