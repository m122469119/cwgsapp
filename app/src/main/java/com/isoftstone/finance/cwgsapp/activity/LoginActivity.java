package com.isoftstone.finance.cwgsapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.external.CircularImage;
import com.isoftstone.finance.cwgsapp.manage.Constant;
import com.isoftstone.finance.cwgsapp.manage.ServerManager;
import com.isoftstone.finance.cwgsapp.requestBean.LoginRequest;
import com.isoftstone.finance.cwgsapp.responseBean.HisBindInfo;
import com.isoftstone.finance.cwgsapp.responseBean.HisBindResult;
import com.isoftstone.finance.cwgsapp.responseBean.LoginInfo;
import com.isoftstone.finance.cwgsapp.responseBean.LoginResult;
import com.isoftstone.finance.cwgsapp.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
* @项目名称：财务公司App
* @类描述：登录页面对应的activity
* @创建人：zwxiao
* @创建时间：2016/12/29 10:24
*/
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.login_et_username)
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

//    ProgressDialog progressDialog;
//    SharedPreferences sp_Login;
//    SharedPreferences sp_LoginName;
//    SharedPreferences sp_SessionID;

    String deviceId;
    String deviceName;

    private boolean mbDisplayFlg = false;

    String userName;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
        init();
        initView();
    }

    protected void init() {
        this.iv_Deletepass = ((ImageView) findViewById(R.id.iv_deletepass));
        this.iv_Deletepass.setOnClickListener(this);
        this.iv_PasswordEye = ((ImageView) findViewById(R.id.iv_password_eye));
        this.iv_PasswordEye.setOnClickListener(this);
        this.btn_Login = ((Button) findViewById(R.id.btn_login));
        this.btn_Login.setOnClickListener(this);
        this.et_Username = ((EditText) findViewById(R.id.login_et_username));
        this.et_Password = ((EditText) findViewById(R.id.login_et_password));
//        sp_Login = getSharedPreferences("login", 0);
//        sp_SessionID = getSharedPreferences("sessionID", 0);
//        sp_LoginName = getSharedPreferences("loginName", 0);
//        if (sp_Login.getBoolean("Autologin", false)) {
//            startActivity(new Intent(this, PowerActivity.class));
//            finish();
//        }
    }

    protected void initView() {
//        String str = getCacheDir().getPath();
//        File userHeadFile = new File(str + "/" + "userhead.png");
//        if (userHeadFile.exists()) {
//            this.img_Headimage.setImageBitmap(BitmapFactory.decodeFile(userHeadFile.getAbsolutePath()));
//        }
        this.et_Password.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable paramAnonymousEditable) {
                if ((LoginActivity.this.et_Password.getText().equals("")) || (LoginActivity.this.et_Password.getText().equals(null))) {
                    LoginActivity.this.iv_Deletepass.setVisibility(View.GONE);
                } else {
                    LoginActivity.this.iv_Deletepass.setVisibility(View.VISIBLE);
                }
            }

            public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
                if ((LoginActivity.this.et_Password.getText().equals("")) || (LoginActivity.this.et_Password.getText().equals(null))) {
                    LoginActivity.this.iv_Deletepass.setVisibility(View.GONE);
                } else {
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
        switch (view.getId()) {
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
                if (this.mbDisplayFlg) {
                    this.et_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    this.iv_PasswordEye.setImageResource(R.mipmap.hidepass);
                    this.mbDisplayFlg = false;
                } else {
                    this.et_Password.postInvalidate();
                    this.et_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    this.iv_PasswordEye.setImageResource(R.mipmap.showpass);
                    this.mbDisplayFlg = true;
                }
                break;
            default:
                    break;

        }
    }

    private void login(String userName, String password) {
        if ((!"".equals(userName)) && (!"".equals(password))) {
//            this.progressDialog.show();
//            this.deviceId = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//            this.deviceName = Build.MODEL;
//            new LoginRequest(userName, password, this.deviceId);
//            this.userName = userName;
//            this.password = password;
//            ServerManager.getHisBind(hisbindsuccessListener(), errorListener());
//            Intent intent = new Intent(LoginActivity.this, GuideActivity.class);
//            LoginActivity.this.startActivity(intent);
//            LoginActivity.this.finish();
        } else {
            Toast.makeText(this, "帐号、密码必须填写", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(LoginActivity.this, PowerActivity.class);
        startActivity(intent);
        finish();
    }

//    private Response.Listener<HisBindResult> hisbindsuccessListener() {
//        return new Response.Listener<HisBindResult>() {
//            public void onResponse(HisBindResult paramAnonymousHisBindResult) {
//                if (paramAnonymousHisBindResult.code.equals("T18000000003")) {
//                    LoginRequest localLoginRequest1 = new LoginRequest(LoginActivity.this.userName, LoginActivity.this.password, LoginActivity.this.deviceId);
//                    ServerManager.getLoginQuery(LoginActivity.this.deviceName, localLoginRequest1, LoginActivity.this.loginsuccessListener(), LoginActivity.this.errorListener());
//                }
//                if (paramAnonymousHisBindResult.code.equals("T18000000001")) {
//                    int i = 1;
//                    ArrayList resultList = paramAnonymousHisBindResult.getResult();
//                    Iterator resultIterator = resultList.iterator();
//                    while (resultIterator.hasNext()) {
//                        HisBindInfo bindInfo = ((HisBindInfo) resultIterator.next());
//                    }
//                    if ((resultList.size() >= 2) && (i != 0)) {
//                        ToastUtils.toastShort("该设备需要绑定才能登入");
//                        LoginActivity.this.progressDialog.dismiss();
//                        return;
//                    }
//                    LoginRequest localLoginRequest3 = new LoginRequest(LoginActivity.this.userName, LoginActivity.this.password, LoginActivity.this.deviceId);
//                    ServerManager.getLoginQuery(LoginActivity.this.deviceName, localLoginRequest3, LoginActivity.this.loginsuccessListener(), LoginActivity.this.errorListener());
//                    return;
//                }
//                LoginRequest localLoginRequest2 = new LoginRequest(LoginActivity.this.userName, LoginActivity.this.password, LoginActivity.this.deviceId);
//                ServerManager.getLoginQuery(LoginActivity.this.deviceName, localLoginRequest2, LoginActivity.this.loginsuccessListener(), LoginActivity.this.errorListener());
//            }
//        };
//    }

//    private Response.ErrorListener errorListener() {
//        return new Response.ErrorListener() {
//            public void onErrorResponse(VolleyError paramAnonymousVolleyError) {
//                LoginActivity.this.progressDialog.dismiss();
//                ToastUtils.toastShort("网络错误，请稍后重试");
//            }
//        };
//    }

//    private Response.Listener<LoginResult> loginsuccessListener() {
//        return new Response.Listener() {
//            public void onResponse(LoginResult paramAnonymousLoginResult) {
//                LoginActivity.this.progressDialog.dismiss();
//                String str = paramAnonymousLoginResult.code;
//                int i = -1;
//                switch (str.hashCode()) {
//                    default:
//                    case -50519786:
//                    case -50519785:
//                    case 836983897:
//                    case 836983898:
//                }
//                while (true)
//                    switch (i) {
//                        default:
//                            return;
//                        if (str.equals("T18000000001")) {
//                            i = 0;
//                            continue;
//                            if (str.equals("T18000000002")) {
//                                i = 1;
//                                continue;
//                                if (str.equals("T18001000003")) {
//                                    i = 2;
//                                    continue;
//                                    if (str.equals("T18001000004"))
//                                        i = 3;
//                                }
//                            }
//                        }
//                        break;
//                        case 0:
//                        case 1:
//                        case 2:
//                        case 3:
//                    }
//                LoginActivity.this.sp_LoginName.edit().putString("UserId", ((LoginInfo) paramAnonymousLoginResult.result.get(0)).getId()).commit();
//                Constant.USERID = ((LoginInfo) paramAnonymousLoginResult.result.get(0)).getId();
//                Constant.SESSIONID = ((LoginInfo) paramAnonymousLoginResult.result.get(0)).getSessionId();
//                Constant.USERNAME = ((LoginInfo) paramAnonymousLoginResult.result.get(0)).loginName;
//                LoginActivity.this.sp_SessionID.edit().putString("sessionid", ((LoginInfo) paramAnonymousLoginResult.result.get(0)).getSessionId()).commit();
//                LoginActivity.this.sp_LoginName.edit().putString("loginname", LoginActivity.this.et_Username.getText().toString()).commit();
//                Intent localIntent = new Intent(LoginActivity.this, CodePasswordActivity.class);
//                LoginActivity.this.startActivity(localIntent);
//                LoginActivity.this.finish();
//                Toast.makeText(LoginActivity.this, "系统内部错误", 0).show();
//                Toast.makeText(LoginActivity.this, "登录校验失败", 0).show();
//                ToastUtils.toastShort("该设备需要绑定才能登入");
//            }
//        };
//    }
}
