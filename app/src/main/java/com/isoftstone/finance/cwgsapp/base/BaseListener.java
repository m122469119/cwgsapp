package com.isoftstone.finance.cwgsapp.base;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.activity.LoginActivity;
import com.isoftstone.finance.cwgsapp.responseBean.BaseResult;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:06
*/
public abstract class BaseListener<T extends BaseResult>
  implements Response.Listener<T>
{
  BaseActivity eactivity;

  public BaseListener(BaseActivity paramBaseActivity)
  {
    this.eactivity = paramBaseActivity;
  }

  public void onResponse(T paramT)
  {
    if (paramT.code.contains("T18002"))
    {
      this.eactivity.getSharedPreferences("login", 0).edit().putBoolean("Autologin", false).commit();
      LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this.eactivity).inflate(R.layout.dialog_effectiveness, null);
      TextView localTextView = (TextView)localLinearLayout.findViewById(R.id.tv_title);
      Button localButton = (Button)localLinearLayout.findViewById(R.id.btn_sure);
      localTextView.setText("登录失效，请重新登录");
      Builder localBuilder = new Builder(this.eactivity);
      localBuilder.setView(localLinearLayout);
      final AlertDialog localAlertDialog = localBuilder.create();
      localButton.setOnClickListener(new OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(BaseListener.this.eactivity, LoginActivity.class);
          BaseListener.this.eactivity.startActivity(localIntent);
          BaseListener.this.eactivity.killAll();
          localAlertDialog.dismiss();
        }
      });
      localAlertDialog.show();
      return;
    }
    successResponse(paramT);
  }

  public abstract void successResponse(T paramT);
}