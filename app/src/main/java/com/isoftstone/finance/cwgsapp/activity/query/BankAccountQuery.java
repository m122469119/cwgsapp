package com.isoftstone.finance.cwgsapp.activity.query;

import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.activity.QueryDialog;
import com.isoftstone.finance.cwgsapp.requestBean.BankAccountRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

//import com.isoftstone.finance.cwgsapp.activity.QueryDialog;
//import com.isoftstone.finance.cwgsapp.activity.QueryListActivity;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-29 10:22
*/
public class BankAccountQuery extends AppCompatActivity
        implements View.OnClickListener
{
    public static BankAccountRequest br;
    public static TextView tv_content;
    public static TextView tv_content2;

    @Bind(R.id.btn_banksearch)
    Button btn_banksearch;

    @Bind(R.id.btn_resetting)
    Button btn_resetting;
    Date date = new Date();
    Date date1;
    Date date2;

    @Bind(R.id.et_bankname)
    EditText etBankname;

    @Bind(R.id.et_openbank)
    EditText etOpenbank;

    @Bind(R.id.et_accountname)
    EditText et_accountname;


    @Bind(R.id.et_banknumber)
    EditText et_banknumber;

    @Bind(R.id.imgbtn_left)
    ImageView imgbtn_left;

    @Bind(R.id.iv_screenswitch)
    ImageView iv_screenswitch;

    @Bind(R.id.iv_space)
    TextView iv_space;

    @Bind(R.id.ll_isDirectlink)
    LinearLayout llIsDirectlink;

    @Bind(R.id.ll_accountbelong)
    LinearLayout ll_accountbelong;

    @Bind(R.id.ll_accountnature)
    LinearLayout ll_accountnature;

    @Bind(R.id.ll_accountstate)
    LinearLayout ll_accountstate;

    @Bind(R.id.ll_accounttype)
    LinearLayout ll_accounttype;

    @Bind(R.id.ll_bankname)
    LinearLayout ll_bankname;

    @Bind(R.id.ll_clientname)
    LinearLayout ll_clientname;

    @Bind(R.id.ll_currency)
    LinearLayout ll_currency;

    @Bind(R.id.ll_inout)
    LinearLayout ll_inout;

    @Bind(R.id.ll_office)
    LinearLayout ll_office;

    @Bind(R.id.ll_openaccountend)
    LinearLayout ll_openaccountend;

    @Bind(R.id.ll_openaccountstart)
    LinearLayout ll_openaccountstart;

    @Bind(R.id.ll_openbank)
    LinearLayout ll_openbank;

    @Bind(R.id.ll_screencondition)
    LinearLayout ll_screencondition;

    @Bind(R.id.ll_screenswitch)
    LinearLayout ll_screenswitch;
    private boolean sswitch = false;
    long time1 = this.date.getTime();

    @Bind(R.id.tv_accountbelong)
    TextView tvAccountbelong;

    @Bind(R.id.tv_accountnature)
    TextView tvAccountnature;

    @Bind(R.id.tv_accountstate)
    TextView tvAccountstate;

    @Bind(R.id.tv_accounttype)
    TextView tvAccounttype;

    @Bind(R.id.tv_clientname)
    TextView tvClientname;

    @Bind(R.id.tv_currency)
    TextView tvCurrency;

    @Bind(R.id.tv_inout)
    TextView tvInout;

    @Bind(R.id.tv_isDirectlink)
    TextView tvIsDirectlink;

    @Bind(R.id.tv_office)
    TextView tv_office;

    @Bind(R.id.tv_openend)
    TextView tv_openend;

    @Bind(R.id.tv_openstart)
    TextView tv_openstart;

    @Bind(R.id.tv_screenswitch)
    TextView tv_screenswitch;

    @Bind(R.id.txt_title)
    TextView txt_title;

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_bankaccountquery);
        ButterKnife.bind(this);
        init();
    }

    public static void setTvContent(String paramString)
    {
        tv_content.setText(paramString);
    }

    public static void setTvContent2(String paramString)
    {
        tv_content2.setText(paramString);
    }

    public void dialod(String paramString, Context paramContext)
    {
        LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(paramContext).inflate(R.layout.layout_warn_diaolog, null);
        ((TextView)localLinearLayout.findViewById(R.id.diaolog_message)).setText("请将“" + paramString + "”信息填写完整");
        Builder localBuilder = new Builder(paramContext);
        localBuilder.setView(localLinearLayout);
        localBuilder.setPositiveButton("确认", new OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localBuilder.create().show();
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
    {
        if (paramMotionEvent.getAction() == 0)
        {
            View localView = getCurrentFocus();
            if (isShouldHideInput(localView, paramMotionEvent))
            {
                InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if (localInputMethodManager != null)
                    localInputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(paramMotionEvent);
        }
        if (getWindow().superDispatchTouchEvent(paramMotionEvent))
            return true;
        return super.dispatchTouchEvent(paramMotionEvent);
    }

    protected void init()
    {
        View localView = View.inflate(this, R.layout.activity_bankaccountquery, null);
        ButterKnife.bind(this, localView);
        setContentView(localView);
        this.imgbtn_left.setOnClickListener(this);
        this.txt_title.setText("银行账户信息查询");
        this.ll_office.setOnClickListener(this);
        this.ll_currency.setOnClickListener(this);
        this.ll_bankname.setOnClickListener(this);
        this.ll_openbank.setOnClickListener(this);
        this.ll_accountbelong.setOnClickListener(this);
        this.ll_clientname.setOnClickListener(this);
        this.ll_inout.setOnClickListener(this);
        this.ll_accountstate.setOnClickListener(this);
        this.ll_accountnature.setOnClickListener(this);
        this.ll_accounttype.setOnClickListener(this);
        this.ll_openaccountstart.setOnClickListener(this);
        this.ll_openaccountend.setOnClickListener(this);
        this.ll_screenswitch.setOnClickListener(this);
        this.btn_banksearch.setOnClickListener(this);
        this.btn_resetting.setOnClickListener(this);
        this.llIsDirectlink.setOnClickListener(this);
    }

    protected void initView()
    {
        br = new BankAccountRequest();
    }

    public boolean isShouldHideInput(View paramView, MotionEvent paramMotionEvent)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        int m = 0;
        if ((paramView != null) && ((paramView instanceof EditText)))
        {
            int[] arrayOfInt = { 0, 0 };
            paramView.getLocationInWindow(arrayOfInt);
            i = arrayOfInt[0];
            j = arrayOfInt[1];
            k = j + paramView.getHeight();
            m = i + paramView.getWidth();
        }
        return (paramMotionEvent.getX() <= i) || (paramMotionEvent.getX() >= m) || (paramMotionEvent.getY() <= j) || (paramMotionEvent.getY() >= k);
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
            case R.id.ll_office:
                Intent localIntent13 = new Intent(this, QueryDialog.class);
                localIntent13.putExtra("clickName", "office");
                localIntent13.putExtra("queryName", "bankAccount");
                localIntent13.putExtra("paramName", "offceId");
                tv_content = this.tv_office;
                startActivity(localIntent13);
                return;
//            case R.id.ll_currency:
//                Intent localIntent12 = new Intent(this, QueryDialog.class);
//                localIntent12.putExtra("clickName", "currency");
//                localIntent12.putExtra("queryName", "bankAccount");
//                localIntent12.putExtra("paramName", "currencyId");
//                tv_content = this.tvCurrency;
//                startActivity(localIntent12);
//                return;
//            case R.id.ll_bankname:
//                Intent localIntent11 = new Intent(this, QueryDialog.class);
//                localIntent11.putExtra("clickName", "bankname");
//                localIntent11.putExtra("queryName", "bankAccount");
//                localIntent11.putExtra("paramName", "bankTypeId");
//                tv_content = this.etBankname;
//                tv_content2 = this.etOpenbank;
//                startActivity(localIntent11);
//                return;
//            case R.id.ll_openbank:
//                Intent localIntent10 = new Intent(this, QueryDialog.class);
//                localIntent10.putExtra("clickName", "openbank");
//                localIntent10.putExtra("queryName", "bankAccount");
//                localIntent10.putExtra("paramName", "openBankId");
//                tv_content = this.etOpenbank;
//                startActivity(localIntent10);
//                return;
//            case R.id.ll_accountbelong:
//                Intent localIntent9 = new Intent(this, QueryDialog.class);
//                localIntent9.putExtra("clickName", "belong");
//                localIntent9.putExtra("queryName", "bankAccount");
//                localIntent9.putExtra("paramName", "ownerType");
//                tv_content = this.tvAccountbelong;
//                startActivity(localIntent9);
//                return;
//            case R.id.ll_clientname:
//                Intent localIntent8 = new Intent(this, QueryDialog.class);
//                localIntent8.putExtra("clickName", "clientname");
//                localIntent8.putExtra("queryName", "bankAccount");
//                localIntent8.putExtra("paramName", "clientID");
//                tv_content = this.tvClientname;
//                startActivity(localIntent8);
//                return;
//            case R.id.ll_inout:
//                Intent localIntent7 = new Intent(this, QueryDialog.class);
//                localIntent7.putExtra("clickName", "inout");
//                localIntent7.putExtra("queryName", "bankAccount");
//                localIntent7.putExtra("paramName", "isTerritory");
//                tv_content = this.tvInout;
//                startActivity(localIntent7);
//                return;
            case R.id.ll_isDirectlink:
                Intent localIntent6 = new Intent(this, QueryDialog.class);
                localIntent6.putExtra("clickName", "isDirectlink");
                localIntent6.putExtra("queryName", "bankAccount");
                localIntent6.putExtra("paramName", "isDirectlink");
                tv_content = this.tvIsDirectlink;
                startActivity(localIntent6);
                return;
//            case R.id.ll_accountstate:
//                Intent localIntent5 = new Intent(this, QueryDialog.class);
//                localIntent5.putExtra("clickName", "accountstate");
//                localIntent5.putExtra("queryName", "bankAccount");
//                localIntent5.putExtra("paramName", "accountStatus");
//                tv_content = this.tvAccountstate;
//                startActivity(localIntent5);
//                return;
//            case R.id.ll_accountnature:
//                Intent localIntent4 = new Intent(this, QueryDialog.class);
//                localIntent4.putExtra("clickName", "accountnature");
//                localIntent4.putExtra("queryName", "bankAccount");
//                localIntent4.putExtra("paramName", "accountProperty");
//                tv_content = this.tvAccountnature;
//                startActivity(localIntent4);
//                return;
//            case R.id.ll_accounttype:
//                Intent localIntent3 = new Intent(this, QueryDialog.class);
//                localIntent3.putExtra("clickName", "accounttype");
//                localIntent3.putExtra("queryName", "bankAccount");
//                localIntent3.putExtra("paramName", "accountType");
//                tv_content = this.tvAccounttype;
//                startActivity(localIntent3);
//                return;
            case R.id.ll_openaccountstart:
                Time localTime2 = new Time();
                localTime2.setToNow();
                int m = localTime2.year;
                int n = localTime2.month;
                int i1 = localTime2.monthDay;
                DatePickerDialog localDatePickerDialog2 = new DatePickerDialog(this, new OnDateSetListener()
                {
                    public void onDateSet(DatePicker paramAnonymousDatePicker, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
                    {
                        Calendar localCalendar = Calendar.getInstance();
                        localCalendar.set(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
                        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        BankAccountQuery.this.tv_openstart.setText(localSimpleDateFormat.format(localCalendar.getTime()));
                    }
                }
                        , m, n, i1);
                localDatePickerDialog2.getDatePicker().setMaxDate(this.time1);
                localDatePickerDialog2.show();
                return;
            case R.id.ll_openaccountend:
                Time localTime1 = new Time();
                localTime1.setToNow();
                int i = localTime1.year;
                int j = localTime1.month;
                int k = localTime1.monthDay;
                DatePickerDialog localDatePickerDialog1 = new DatePickerDialog(this, new OnDateSetListener()
                {
                    public void onDateSet(DatePicker paramAnonymousDatePicker, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
                    {
                        Calendar localCalendar = Calendar.getInstance();
                        localCalendar.set(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
                        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        BankAccountQuery.this.tv_openend.setText(localSimpleDateFormat.format(localCalendar.getTime()));
                    }
                }
                        , i, j, k);
                localDatePickerDialog1.getDatePicker().setMaxDate(this.time1);
                localDatePickerDialog1.show();
                return;
            case R.id.ll_screenswitch:
                if (!this.sswitch)
                {
                    this.iv_space.setVisibility(View.VISIBLE);
                    this.ll_screencondition.setVisibility(View.VISIBLE);
                    this.iv_screenswitch.setImageResource(R.mipmap.hidemore);
                    this.tv_screenswitch.setText("收起更多过滤条件");
                    this.sswitch = true;
                    return;
                }
                this.iv_space.setVisibility(View.GONE);
                this.ll_screencondition.setVisibility(View.GONE);
                this.iv_screenswitch.setImageResource(R.mipmap.showmore);
                this.tv_screenswitch.setText("更多过滤条件");
                this.sswitch = false;
                return;
            case R.id.btn_resetting:
                this.tv_office.setText(null);
                this.tvCurrency.setText(null);
                this.etBankname.setText(null);
                this.etOpenbank.setText(null);
                this.tvAccountbelong.setText(null);
                this.et_accountname.setText(null);
                this.et_accountname.setText(null);
                this.tvClientname.setText(null);
                this.tvInout.setText(null);
                this.tvAccountnature.setText(null);
                this.tvAccountstate.setText(null);
                this.tvAccounttype.setText(null);
                this.tv_openstart.setText(null);
                this.tv_openend.setText(null);
                return;
//            case R.id.btn_banksearch:
//                SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
//                try {
//                    this.date1 = localSimpleDateFormat.parse(this.tv_openstart.getText().toString());
//                    this.date2 = localSimpleDateFormat.parse(this.tv_openend.getText().toString());
//                    BankAccountMustMessage localBankAccountMustMessage = new BankAccountMustMessage(this.tvAccountbelong.getText().toString());
//                    if ((localBankAccountMustMessage.getOwnerType().equals("")) || (localBankAccountMustMessage.getOwnerType() == null)) {
//                        dialod("账户归属", this);
//                        return;
//                    }
//                } catch (ParseException localParseException) {
//                    while (true)
//                        localParseException.printStackTrace();
//                    if ((this.tv_openstart.length() == 0) && (this.tv_openend.length() == 0)) {
//                        br.setAccountNo(this.et_banknumber.getText().toString());
//                        br.setAccountName(this.et_accountname.getText().toString());
//                        br.setAccountByOpenDateStart(this.tv_openstart.getText().toString());
//                        br.setAccountByOpenDateEnd(this.tv_openend.getText().toString());
//                        Intent localIntent2 = new Intent(this, QueryListActivity.class);
//                        localIntent2.putExtra("POINT", "bankaccount");
//                        Bundle localBundle2 = new Bundle();
//                        localBundle2.putParcelable("bankaccount", br);
//                        localIntent2.putExtra("bundle", localBundle2);
//                        startActivity(localIntent2);
//                        return;
//                    }
//                    if ((this.date1 != null) && (this.date2 != null) && (this.date1.after(this.date2))) {
//                        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
//                        localBuilder.setMessage("时间选择有误");
//                        localBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
//                                paramAnonymousDialogInterface.dismiss();
//                            }
//                        });
//                        localBuilder.create().show();
//                        return;
//                    }
//                    br.setAccountNo(this.et_banknumber.getText().toString());
//                    br.setAccountName(this.et_accountname.getText().toString());
//                    br.setAccountByOpenDateStart(this.tv_openstart.getText().toString());
//                    br.setAccountByOpenDateEnd(this.tv_openend.getText().toString());
//                    Intent localIntent1 = new Intent(this, QueryListActivity.class);
//                    localIntent1.putExtra("POINT", "bankaccount");
//                    Bundle localBundle1 = new Bundle();
//                    localBundle1.putParcelable("bankaccount", br);
//                    localIntent1.putExtra("bundle", localBundle1);
//                    startActivity(localIntent1);
//                }
        }
    }
}