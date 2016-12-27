package com.isoftstone.finance.cwgsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.isoftstone.finance.cwgsapp.external.CircularImage;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PowerActivity extends AppCompatActivity {

    @Bind(R.id.username)
    EditText et_Username;
    @Bind(R.id.login_et_password)
    EditText et_Password;
    @Bind(R.id.iv_deletepass)
    ImageView iv_Deletepass;
    @Bind(R.id.iv_password_eye)
    ImageView iv_PasswordEye;
    @Bind(R.id.btn_login)
    Button btn_Login;
    @Bind(R.id.login_headimage)
    CircularImage img_Headimage;
    @Bind(R.id.activity_login)
    LinearLayout layout_Login;

    ProgressDialog progressDialog;
    SharedPreferences sp_Login;
    SharedPreferences sp_LoginName;
    SharedPreferences sp_SessionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        init();
    }

    protected void init()
    {
        sp_Login = getSharedPreferences("login", 0);
        sp_SessionID = getSharedPreferences("sessionID", 0);
        sp_LoginName = getSharedPreferences("loginName", 0);
        if (sp_Login.getBoolean("Autologin", false))
        {
            startActivity(new Intent(this, PowerActivity.class));
            finish();
        }
        View localView = View.inflate(this, 2130968632, null);
        ButterKnife.inject(this, localView);
        setContentView(localView);
    }
}
