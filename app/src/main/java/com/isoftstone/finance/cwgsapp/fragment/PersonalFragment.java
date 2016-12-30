package com.isoftstone.finance.cwgsapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.activity.LoginActivity;
import com.isoftstone.finance.cwgsapp.activity.MainActivity;
import com.isoftstone.finance.cwgsapp.base.BaseActivity;
import com.isoftstone.finance.cwgsapp.base.BaseListener;
import com.isoftstone.finance.cwgsapp.external.CircularImage;
import com.isoftstone.finance.cwgsapp.manage.Constant;
import com.isoftstone.finance.cwgsapp.manage.DataCleanManager;
import com.isoftstone.finance.cwgsapp.manage.ServerManager;
import com.isoftstone.finance.cwgsapp.responseBean.AppVersionInfo;
import com.isoftstone.finance.cwgsapp.responseBean.AppVersionResult;
import com.isoftstone.finance.cwgsapp.responseBean.LoginResult;
import com.isoftstone.finance.cwgsapp.util.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class PersonalFragment extends Fragment
        implements View.OnClickListener {
    public static int loading_process;
    public static BaseActivity mactivity;
    private static TrustManager myX509TrustManager = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
    public static ImageView temapIv;
    private Handler BroadcastHandler = new Handler() {
        public void handleMessage(Message paramAnonymousMessage) {
            PersonalFragment.this.Beginning();
        }
    };
    AlertDialog adialog;
    String apkUrl;

    @Bind(R.id.bg)
    LinearLayout bg;
    android.app.AlertDialog.Builder builder;
    Context context;

    @Bind(R.id.tv_devicename)
    TextView devicenmae;
    private Handler handler = new Handler() {
        public void handleMessage(Message paramAnonymousMessage) {
            if (!Thread.currentThread().isInterrupted()) {
                if (paramAnonymousMessage.what != 1)
                    PersonalFragment.this.pb.setProgress(paramAnonymousMessage.arg1);
                PersonalFragment.loading_process = paramAnonymousMessage.arg1;
                PersonalFragment.this.tv.setText("已为您加载了：" + PersonalFragment.loading_process + "%");
            }
            while (true) {
                super.handleMessage(paramAnonymousMessage);
//        return;
                if (paramAnonymousMessage.what == 2) {
                    File localFile = new File(Environment.getExternalStorageDirectory(), "Zhapp.apk");
                    PersonalFragment.this.installApk(localFile);
                    PersonalFragment.this.adialog.dismiss();
                } else if (paramAnonymousMessage.what == -1) {
                    ToastUtils.toastShort(paramAnonymousMessage.getData().getString("error"));
                    PersonalFragment.this.adialog.dismiss();
                }
            }
        }
    };
    CircularImage headimage;

    @Bind(R.id.ll_update)
    LinearLayout llUpdate;

    @Bind(R.id.ll_aboutus)
    LinearLayout ll_aboutus;

    @Bind(R.id.ll_bindequip)
    LinearLayout ll_bindequip;

    @Bind(R.id.ll_settingsafe)
    LinearLayout ll_settingsafe;

    @Bind(R.id.ll_suggestion)
    LinearLayout ll_suggestion;

    @Bind(R.id.ll_tohistory)
    LinearLayout ll_tohistory;

    @Bind(R.id.tv_loginname)
    TextView loginname;

    @Bind(R.id.tv_logintime)
    TextView logintime;
    private ProgressBar pb;
    SharedPreferences sp;
    private TextView tv;

    @Bind(R.id.tv_versioncode)
    TextView tvVersioncode;

    @Bind(R.id.tv_quit)
    TextView tv_quit;

    private Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            public void onErrorResponse(VolleyError paramAnonymousVolleyError) {
                Toast.makeText(PersonalFragment.this.getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private Response.Listener<LoginResult> exitSuccessListener() {
        return new Response.Listener<LoginResult>() {
            public void onResponse(LoginResult paramAnonymousLoginResult) {
                if ((paramAnonymousLoginResult.code.equals("T18000000001")) || (paramAnonymousLoginResult.code.contains("T18002"))) {
                    PersonalFragment.this.doExit(PersonalFragment.this.context);
                    return;
                }
                Toast.makeText(PersonalFragment.this.context, "请求失败,请重试", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void installApk(File paramFile) {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.VIEW");
        localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
        this.context.startActivity(localIntent);
    }

    public static PersonalFragment newInstance(BaseActivity paramBaseActivity) {
        PersonalFragment localPersonalFragment = new PersonalFragment();
        mactivity = paramBaseActivity;
        return localPersonalFragment;
    }

    private void sendMsg(int paramInt1, int paramInt2) {
        Message localMessage = new Message();
        localMessage.what = paramInt1;
        localMessage.arg1 = paramInt2;
        this.handler.sendMessage(localMessage);
    }

    public static void setHeadView(Bitmap paramBitmap) {
        temapIv.setImageBitmap(paramBitmap);
    }

    public void Beginning() {
        Thread local7 = new Thread() {
            public void run() {
                PersonalFragment.this.loadFile(PersonalFragment.this.apkUrl);
            }
        };
        LinearLayout localLinearLayout = (LinearLayout) LayoutInflater.from(this.context).inflate(R.layout.layout_loadapk, null);
        this.pb = ((ProgressBar) localLinearLayout.findViewById(R.id.down_pb));
        this.tv = ((TextView) localLinearLayout.findViewById(R.id.tv_loadapk));
        ((TextView) localLinearLayout.findViewById(R.id.tv_loadapk)).setText("版本更新进度提示");
        this.builder = new android.app.AlertDialog.Builder(this.context);
        this.builder.setCancelable(false);
        this.builder.setView(localLinearLayout);
        this.adialog = this.builder.show();
        local7.start();
    }

    public BaseListener<AppVersionResult> checkVersionSuccess() {
        return new BaseListener<AppVersionResult>(mactivity) {
            public void successResponse(AppVersionResult paramAnonymousAppVersionResult) {
                if (paramAnonymousAppVersionResult.code.equals("T18000000001")) {
                    AppVersionInfo localAppVersionInfo = (AppVersionInfo) paramAnonymousAppVersionResult.result.get(0);
                    if (!PersonalFragment.this.sp.getString("versionCode", "").equals(localAppVersionInfo.version)) {
                        PersonalFragment.this.sp.edit().putString("versionCode", localAppVersionInfo.version).commit();
                        PersonalFragment.this.apkUrl = localAppVersionInfo.uri;
                        Message localMessage = PersonalFragment.this.BroadcastHandler.obtainMessage();
                        PersonalFragment.this.BroadcastHandler.sendMessage(localMessage);
                        return;
                    }
                    ToastUtils.toastShort("当前已是最新版本");
                    return;
                }
                ToastUtils.toastShort(paramAnonymousAppVersionResult.message);
            }
        };
    }

    public void doExit(Context paramContext) {
        paramContext.getSharedPreferences("login", 0).edit().putBoolean("Autologin", false).commit();
        DataCleanManager.Exit(paramContext);
        paramContext.startActivity(new Intent(paramContext, LoginActivity.class));
//        ((MainActivity) paramContext).killAll();
    }

    public void exit(Context paramContext) {
        new android.support.v7.app.AlertDialog.Builder(paramContext).setTitle("退出").setMessage("退出将删除本地数据，是否退出？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                ServerManager.exitRequest(PersonalFragment.this.exitSuccessListener(), PersonalFragment.this.errorListener());
                paramAnonymousDialogInterface.dismiss();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.dismiss();
            }
        }).create().show();
    }

    public void initData() {
    }

    public View initView() {
        View localView = View.inflate(this.context, R.layout.pager_personal, null);
        ButterKnife.bind(this, localView);
        this.loginname.setText(Constant.LOGINNAME);
        this.devicenmae.setText(Build.MODEL);
        String str1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
        this.logintime.setText(str1);
        this.headimage = ((CircularImage) localView.findViewById(R.id.head_image));
        this.sp = this.context.getSharedPreferences("AppVersion", 0);
        String str2 = this.sp.getString("versionCode", "1.1.0");
        this.tvVersioncode.setText("当前版本" + str2);
        String str3 = this.context.getCacheDir().getPath();
        File localFile = new File(str3 + "/" + "userhead.png");
        if (localFile.exists())
            this.headimage.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
        while (true) {
            this.headimage.setOnClickListener(this);
            this.ll_bindequip.setOnClickListener(this);
            this.ll_aboutus.setOnClickListener(this);
            this.ll_settingsafe.setOnClickListener(this);
            this.ll_suggestion.setOnClickListener(this);
            this.ll_tohistory.setOnClickListener(this);
            this.tv_quit.setOnClickListener(this);
            this.llUpdate.setOnClickListener(this);
            this.headimage.setImageResource(R.mipmap.head);
            return localView;
        }
    }

    public void loadFile(String paramString) {
        FileOutputStream localFileOutputStream;
        do {
            try {
                SSLContext localSSLContext = SSLContext.getInstance("TLS");
                TrustManager[] arrayOfTrustManager = new TrustManager[1];
                arrayOfTrustManager[0] = myX509TrustManager;
                localSSLContext.init(null, arrayOfTrustManager, null);
                HttpsURLConnection localHttpsURLConnection = (HttpsURLConnection) new URL(this.apkUrl).openConnection();
                localHttpsURLConnection.setSSLSocketFactory(localSSLContext.getSocketFactory());
                localHttpsURLConnection.getHeaderFields().isEmpty();
                float f1 = localHttpsURLConnection.getContentLength();
                InputStream localInputStream = localHttpsURLConnection.getInputStream();
                localFileOutputStream = null;
                if (localInputStream != null) {
                    localFileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "cwgsapp.apk"));
                    byte[] arrayOfByte = new byte[1024];
                    float f2 = 0.0F;
                    while (true) {
                        int i = localInputStream.read(arrayOfByte);
                        if (i == -1)
                            break;
                        localFileOutputStream.write(arrayOfByte, 0, i);
                        f2 += i;
                        sendMsg(1, (int) (100.0F * f2 / f1));
                    }
                }
            } catch (Exception localException) {
                sendMsg(-1, 0);
                return;
            }
            sendMsg(2, 0);
            try{
                localFileOutputStream.flush();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        while (localFileOutputStream == null);
        try {
            localFileOutputStream.close();
        }catch (Exception e){
                e.printStackTrace();
            }
    }

    public void onClick(View paramView) {
//        switch (paramView.getId()) {
//            case 2131624857:
//            case 2131624858:
//            case 2131624859:
//            case 2131624862:
//            case 2131624864:
//            case 2131624866:
//            case 2131624868:
//            default:
//                return;
//            case 2131624856:
//                Intent localIntent6 = new Intent(this.context, QueryListActivity.class);
//                localIntent6.putExtra("POINT", "loadhistory");
//                this.context.startActivity(localIntent6);
//                return;
//            case 2131624860:
//                Intent localIntent5 = new Intent(this.context, BindEquipActivity.class);
//                this.context.startActivity(localIntent5);
//                return;
//            case 2131624861:
//                Intent localIntent4 = new Intent(this.context, SettingSafeActivity.class);
//                this.context.startActivity(localIntent4);
//                return;
//            case 2131624863:
//                Intent localIntent3 = new Intent(this.context, AboutUsActivity.class);
//                this.context.startActivity(localIntent3);
//                return;
//            case 2131624865:
//                Intent localIntent2 = new Intent(this.context, SuggestionActivity.class);
//                this.context.startActivity(localIntent2);
//                return;
//            case 2131624869:
//                exit(this.context);
//                return;
//            case 2131624855:
//                Intent localIntent1 = new Intent(this.context, SetHeadViewActivity.class);
//                localIntent1.putExtra("POINT", "setHeadView");
//                this.context.startActivity(localIntent1);
//                temapIv = this.headimage;
//                return;
//            case 2131624867:
//        }
//        ServerManager.appVersion(checkVersionSuccess(), errorListener());
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        loading_process = 0;
        this.context = getActivity();
        View localView = initView();
        initData();
        ButterKnife.bind(this, localView);
        return localView;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}