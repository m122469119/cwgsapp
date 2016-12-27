package com.isoftstone.finance.cwgsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.isoftstone.finance.cwgsapp.external.CircularImage;
import com.isoftstone.finance.cwgsapp.requestBean.LoginRequest;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

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

    String deviceId;
    String deviceName;

    private boolean mbDisplayFlg = false;

    String userName;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        init();
        initView();
    }

    protected void init() {
        sp_Login = getSharedPreferences("login", 0);
        sp_SessionID = getSharedPreferences("sessionID", 0);
        sp_LoginName = getSharedPreferences("loginName", 0);
        if (sp_Login.getBoolean("Autologin", false)) {
            startActivity(new Intent(this, PowerActivity.class));
            finish();
        }
        View view = View.inflate(this, R.layout.activity_login, null);
        ButterKnife.bind(this, view);
        setContentView(view);
    }

    protected void initView() {
        this.iv_Deletepass.setOnClickListener(this);
        this.iv_PasswordEye.setOnClickListener(this);
        this.btn_Login.setOnClickListener(this);
        String str = getCacheDir().getPath();
        File userHeadFile = new File(str + "/" + "userhead.png");
        if (userHeadFile.exists()) {
            this.img_Headimage.setImageBitmap(BitmapFactory.decodeFile(userHeadFile.getAbsolutePath()));
        }
        this.et_Password.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable paramAnonymousEditable) {
                if ((LoginActivity.this.et_Password.getText().equals("")) || (LoginActivity.this.et_Password.getText().equals(null))) {
                    LoginActivity.this.iv_Deletepass.setVisibility(View.GONE);
                }else{
                    LoginActivity.this.iv_Deletepass.setVisibility(View.VISIBLE);
                }
            }

            public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
                if ((LoginActivity.this.et_Password.getText().equals("")) || (LoginActivity.this.et_Password.getText().equals(null))) {
                    LoginActivity.this.iv_Deletepass.setVisibility(View.GONE);
                }else{
                    LoginActivity.this.iv_Deletepass.setVisibility(View.VISIBLE);
                }
            }

            public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
                if ((LoginActivity.this.et_Password.getText().equals("")) || (LoginActivity.this.et_Password.getText().equals(null))) {
                    LoginActivity.this.iv_Deletepass.setVisibility(View.GONE);
                } else {
                    LoginActivity.this.iv_Deletepass.setVisibility(View.VISIBLE);
                }
            }
        });
        this.img_Headimage.setImageResource(R.mipmap.head);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //点击按钮
            case R.id.btn_login:
                login(this.et_Username.getText().toString(), this.et_Password.getText().toString());
                break;
            //点击重置密码的图片
            case R.id.iv_deletepass:
                this.et_Password.setText("");
                this.iv_Deletepass.setVisibility(View.GONE);
                break;
            //点击显示隐藏密码的图片
            case R.id.iv_password_eye:
                if(this.mbDisplayFlg){
                    this.et_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    this.iv_PasswordEye.setImageResource(R.mipmap.hidepass);
                    this.mbDisplayFlg = false;
                }else{
                    this.et_Password.postInvalidate();
                    this.et_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    this.iv_PasswordEye.setImageResource(R.mipmap.showpass);
                    this.mbDisplayFlg = true;
                }

        }
    }

    private void login(String userName, String password)
    {
        if ((!"".equals(userName)) && (!"".equals(password)))
        {
            this.progressDialog.show();
            this.deviceId = ((TelephonyManager)getSystemService("phone")).getDeviceId();
            this.deviceName = Build.MODEL;
            new LoginRequest(userName, password, this.deviceId);
            this.userName = userName;
            this.password = password;
            ServerManager.getHisBind(hisbindsuccessListener(), errorListener());
            return;
        }else{
            Toast.makeText(this, "帐号、密码必须填写", 0).show();
        }
    }
}
