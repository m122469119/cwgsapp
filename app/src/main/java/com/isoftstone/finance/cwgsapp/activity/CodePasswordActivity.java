package com.isoftstone.finance.cwgsapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.base.BaseActivity;
import com.isoftstone.finance.cwgsapp.util.ToastUtils;

public class CodePasswordActivity extends BaseActivity
  implements View.OnClickListener
{

  ProgressDialog progressDialog;
  SharedPreferences sp_SessionID;

//  private Response.ErrorListener errorListener()
//  {
//    return new Response.ErrorListener()
//    {
//      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
//      {
//        CodePasswordActivity.this.progressDialog.dismiss();
//        ToastUtils.toastShort("网络错误，请稍后重试");
//      }
//    };
//  }
//
//  protected void init()
//  {
//    View view = View.inflate(this, R.layout.activity_login, null);
//    ButterKnife.bind(this, view);
//    setContentView(view);
//    this.sessionid = getSharedPreferences("sessionid", 0);
//    this.progressDialog = new ProgressDialog(this);
//    this.progressDialog.setCancelable(false);
//  }
//
//  protected void initView()
//  {
//    this.btnEopSubmit.setOnClickListener(this);
//  }
//
//  public void onClick(View paramView)
//  {
//    switch (paramView.getId())
//    {
//    default:
//      return;
//    case 2131624249:
//    }
//    startActivity(new Intent(this, PowerActivity.class));
//    finish();
//  }


  @Override
  protected void init() {

  }

  @Override
  protected void initView() {

  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
    initView();
  }

  @Override
  public void onClick(View view) {

  }

//  public BaseListener<DypasswordResult> successListener()
//  {
//    return new BaseListener(this)
//    {
//      public void successResponse(DypasswordResult paramAnonymousDypasswordResult)
//      {
//        if (paramAnonymousDypasswordResult.code.equals("T18000000001"))
//        {
//          CodePasswordActivity.this.progressDialog.dismiss();
//          com.example.dell.zhapp.manager.ZHConsant.SESSIONID = ((DypasswordInfo)paramAnonymousDypasswordResult.result.get(0)).sessionId;
//          CodePasswordActivity.this.sessionid.edit().putString("sessionid", ((DypasswordInfo)paramAnonymousDypasswordResult.result.get(0)).sessionId).commit();
//          Intent localIntent = new Intent(CodePasswordActivity.this, PowerActivity.class);
//          CodePasswordActivity.this.startActivity(localIntent);
//          CodePasswordActivity.this.finish();
//          return;
//        }
//        CodePasswordActivity.this.progressDialog.dismiss();
//        ToastUtils.toastResponse(paramAnonymousDypasswordResult.message);
//      }
//    };
//  }
}