package com.isoftstone.finance.cwgsapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.isoftstone.finance.cwgsapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PowerActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.tv_logout)
    TextView tv_Logout;
    @Bind(R.id.rl_logout)
    RelativeLayout rl_Logout;
    @Bind(R.id.iv_businessCenter)
    ImageView iv_BusinessCenter;
    @Bind(R.id.iv_managerCenter)
    ImageView iv_ManagerCenter;
    ProgressDialog progressDialog;
    SharedPreferences sp_Login;
    SharedPreferences sp_LoginName;
    SharedPreferences sp_SessionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        init();
//        initView();
    }

    protected void init(){
        iv_BusinessCenter.setOnClickListener(this);
        iv_ManagerCenter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //点击业务中心
            case R.id.iv_businessCenter:
                this.progressDialog.dismiss();
                Intent intent = new Intent(PowerActivity.this, MainActivity.class);
                PowerActivity.this.startActivity(intent);
                PowerActivity.this.finish();
                break;
            //点击管理中心
            case R.id.iv_managerCenter:
                break;
            //点击注销
            case R.id.tv_logout:
            default:
                break;
        }
    }


}
