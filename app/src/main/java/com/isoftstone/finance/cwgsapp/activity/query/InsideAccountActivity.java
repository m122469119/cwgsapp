package com.isoftstone.finance.cwgsapp.activity.query;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.Bind;
import com.isoftstone.finance.cwgsapp.activity.QueryDialog;
import com.isoftstone.finance.cwgsapp.base.BaseActivity;
import com.isoftstone.finance.cwgsapp.requestBean.InsideAccountRequest;
import com.isoftstone.finance.cwgsapp.R;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 11:07
*/
public class InsideAccountActivity extends AppCompatActivity
  implements View.OnClickListener
{
  public static InsideAccountRequest iar;
  public static TextView tv_content;

  @Bind(R.id.btn_insideresetting)
  Button btnInsideresetting;

  @Bind(R.id.btn_insidesearch)
  Button btnInsidesearch;

  @Bind(R.id.imgbtn_left)
  ImageView imgbtnLeft;

  @Bind(R.id.ll_insideaccountnume)
  LinearLayout llInsideaccountnume;

  @Bind(R.id.ll_insideaccountnums)
  LinearLayout llInsideaccountnums;

  @Bind(R.id.ll_insideclientnume)
  LinearLayout llInsideclientnume;

  @Bind(R.id.ll_insideclientnums)
  LinearLayout llInsideclientnums;

  @Bind(R.id.ll_insidecurrency)
  LinearLayout llInsidecurrency;

  @Bind(R.id.tv_insideaccountnume)
  TextView tvInsideaccountnume;

  @Bind(R.id.tv_insideaccountnums)
  TextView tvInsideaccountnums;

  @Bind(R.id.tv_insideclientnume)
  TextView tvInsideclientnume;

  @Bind(R.id.tv_insideclientnums)
  TextView tvInsideclientnums;

  @Bind(R.id.tv_insidecurrency)
  TextView tvInsidecurrency;

  @Bind(R.id.txt_title)
  TextView txtTitle;

  public static void setTvContent(String paramString)
  {
    tv_content.setText(paramString);
  }

  protected void init()
  {
    View localView = View.inflate(this, R.layout.activity_insideaccount, null);
    ButterKnife.bind(this, localView);
    setContentView(localView);
    this.txtTitle.setText("内部账户信息查询");
    this.imgbtnLeft.setOnClickListener(this);
    this.llInsidecurrency.setOnClickListener(this);
    this.llInsideclientnums.setOnClickListener(this);
    this.llInsideclientnume.setOnClickListener(this);
    this.llInsideaccountnums.setOnClickListener(this);
    this.llInsideaccountnume.setOnClickListener(this);
    this.btnInsideresetting.setOnClickListener(this);
    this.btnInsidesearch.setOnClickListener(this);
  }

  protected void initView()
  {
    iar = new InsideAccountRequest();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId()) {
      default:
        return;
      case R.id.imgbtn_left:
        iar.setCurrencyId(null);
        iar.setClientStartCode(null);
        iar.setClientEndCode(null);
        iar.setAccountStartNo(null);
        iar.setAccountEndNo(null);
        finish();
        return;
      case R.id.ll_insidecurrency:
        tv_content = this.tvInsidecurrency;
        putintent("insidepcurrency", "insideAccount", "currencyId");
        return;
      case R.id.ll_insideclientnums:
        tv_content = this.tvInsideclientnums;
        putintent("insidepclientnums", "insideAccount", "clientStartCode");
        return;
      case R.id.ll_insideclientnume:
        tv_content = this.tvInsideclientnume;
        putintent("insidepclientnume", "insideAccount", "clientEndCode");
        return;
      case R.id.ll_insideaccountnums:
        tv_content = this.tvInsideaccountnums;
        putintent("insideaccountnums", "insideAccount", "accountStartNo");
        return;
      case R.id.ll_insideaccountnume:
        tv_content = this.tvInsideaccountnume;
        putintent("insideaccountnume", "insideAccount", "accountEndNo");
        return;
      case R.id.btn_insideresetting:
        this.tvInsidecurrency.setText(null);
        this.tvInsideclientnums.setText(null);
        this.tvInsideclientnume.setText(null);
        this.tvInsideaccountnums.setText(null);
        this.tvInsideaccountnume.setText(null);
        iar.setCurrencyId(null);
        iar.setClientStartCode(null);
        iar.setClientEndCode(null);
        iar.setAccountStartNo(null);
        iar.setAccountEndNo(null);
        return;
//      case R.id.btn_insidesearch:
//        Intent localIntent = new Intent(this, QueryListActivity.class);
//        localIntent.putExtra("POINT", "insideaccount");
//        Bundle localBundle = new Bundle();
//        localBundle.putParcelable("insideaccount", iar);
//        localIntent.putExtra("bundle", localBundle);
//        startActivity(localIntent);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
    ButterKnife.bind(this);
  }

  public void putintent(String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent(this, QueryDialog.class);
    localIntent.putExtra("clickName", paramString1);
    localIntent.putExtra("queryName", paramString2);
    localIntent.putExtra("paramName", paramString3);
    startActivity(localIntent);
  }
}