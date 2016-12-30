package com.isoftstone.finance.cwgsapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.activity.query.BankAccountQuery;
import com.isoftstone.finance.cwgsapp.base.Adapter;
import com.isoftstone.finance.cwgsapp.base.BaseActivity;
import com.isoftstone.finance.cwgsapp.base.BaseListener;
import com.isoftstone.finance.cwgsapp.bean.DialogText;
import com.isoftstone.finance.cwgsapp.external.ViewHolder;
import com.isoftstone.finance.cwgsapp.manage.ServerManager;
import com.isoftstone.finance.cwgsapp.responseBean.AccountBelong;
import com.isoftstone.finance.cwgsapp.responseBean.AccountMessage;
import com.isoftstone.finance.cwgsapp.responseBean.BankTypeInfo;
import com.isoftstone.finance.cwgsapp.responseBean.BankTypeResult;
import com.isoftstone.finance.cwgsapp.responseBean.BorrowUnitCodeInfo;
import com.isoftstone.finance.cwgsapp.responseBean.BorrowUnitCodeResult;
import com.isoftstone.finance.cwgsapp.responseBean.CMAboveUnitInfo;
import com.isoftstone.finance.cwgsapp.responseBean.CMAboveUnitResult;
import com.isoftstone.finance.cwgsapp.responseBean.CMClientnoInfo;
import com.isoftstone.finance.cwgsapp.responseBean.CMClientnoResult;
import com.isoftstone.finance.cwgsapp.responseBean.CUClientNoInfo;
import com.isoftstone.finance.cwgsapp.responseBean.CUClientNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.ClientMessageInfo;
import com.isoftstone.finance.cwgsapp.responseBean.ClientMessageResult;
import com.isoftstone.finance.cwgsapp.responseBean.CurrencyInfo;
import com.isoftstone.finance.cwgsapp.responseBean.CurrencyResult;
import com.isoftstone.finance.cwgsapp.responseBean.DraftType;
import com.isoftstone.finance.cwgsapp.responseBean.FCurrencyInfo;
import com.isoftstone.finance.cwgsapp.responseBean.FCurrencyResult;
import com.isoftstone.finance.cwgsapp.responseBean.FxCustomEntityForAppInfo;
import com.isoftstone.finance.cwgsapp.responseBean.FxCustomEntityForAppResult;
import com.isoftstone.finance.cwgsapp.responseBean.IAAccountNoInfo;
import com.isoftstone.finance.cwgsapp.responseBean.IAAccountNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.IAClientNoInfo;
import com.isoftstone.finance.cwgsapp.responseBean.IAClientNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.InOut;
import com.isoftstone.finance.cwgsapp.responseBean.NoticeDepositNumInfo;
import com.isoftstone.finance.cwgsapp.responseBean.NoticeDepositNumResult;
import com.isoftstone.finance.cwgsapp.responseBean.OAccountBankInfo;
import com.isoftstone.finance.cwgsapp.responseBean.OAccountBankResult;
import com.isoftstone.finance.cwgsapp.responseBean.OfficeInfo;
import com.isoftstone.finance.cwgsapp.responseBean.OfficeResult;
import com.isoftstone.finance.cwgsapp.responseBean.RDLimitInfo;
import com.isoftstone.finance.cwgsapp.responseBean.RDLimitResult;
import com.isoftstone.finance.cwgsapp.responseBean.RDTicketInfo;
import com.isoftstone.finance.cwgsapp.responseBean.RDTicketResult;
import com.isoftstone.finance.cwgsapp.responseBean.SelfAccountNoInfo;
import com.isoftstone.finance.cwgsapp.responseBean.SelfAccountNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.TranDetailPayment;
import com.isoftstone.finance.cwgsapp.responseBean.TranDetailReStatus;
import com.isoftstone.finance.cwgsapp.responseBean.VPAccountNoInfo;
import com.isoftstone.finance.cwgsapp.responseBean.VPAccountNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.VPClientNoInfo;
import com.isoftstone.finance.cwgsapp.responseBean.VPClientNoResult;
import com.isoftstone.finance.cwgsapp.util.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

//import com.isoftstone.finance.cwgsapp.activity.query.ClientMessageActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.CreditUseQueryActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.DiscountContractActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.InsideAccountActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.LoanContractActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.NotifyDepositActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.RegularDepositActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.ReplaceExchangeActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.SelfExchangeActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.TranRecordActivity;
//import com.isoftstone.finance.cwgsapp.activity.query.VirtualPoolActivity;
//import com.isoftstone.finance.cwgsapp.requestBean.ClientMessageRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.CreditUseRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.DiscountContractRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.InsideAccountRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.LoanContractRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.NoticeDepositListRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.RegularDepositRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.ReplaceGuestRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.SelfExchangeRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.TranDetailRequest;
//import com.isoftstone.finance.cwgsapp.requestBean.VirtualPoolRequest;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:05
*/
public class QueryDialog<T> extends BaseActivity
        implements OnClickListener
{
    String Clickname;
    String ParamName;
    String QueryName;
    Adapter adapter;
    ArrayList<DialogText> arr = new ArrayList();
    String code;

    @Bind(R.id.et_dialogmessage)
    EditText et_dialogmessage;

    @Bind(R.id.iv_closedialog)
    ImageView iv_closedialog;

    @Bind(R.id.iv_dialogsearch)
    ImageView iv_dialogsearch;

    @Bind(R.id.ll_dialog)
    LinearLayout ll_dialog;

    @Bind(R.id.lv_dialoglist)
    ListView lv_dialoglist;
    String name;
    ArrayList<DialogText> newarr;
    ProgressDialog pdg;

    @Bind(R.id.tv_dialogtitle)
    TextView tv_dialogtitle;


    private BaseListener<NoticeDepositNumResult> NDNumQuerySuccessListener()
    {
        return new BaseListener<NoticeDepositNumResult>(this)
        {
            public void successResponse(NoticeDepositNumResult paramAnonymousNoticeDepositNumResult)
            {
                QueryDialog.this.adapter = new Adapter<NoticeDepositNumInfo>(QueryDialog.this, paramAnonymousNoticeDepositNumResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, NoticeDepositNumInfo paramAnonymous2NoticeDepositNumInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2NoticeDepositNumInfo.depositNo);
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((NoticeDepositNumInfo)paramAnonymousNoticeDepositNumResult.getResult().get(i)).getId(), ((NoticeDepositNumInfo)paramAnonymousNoticeDepositNumResult.getResult().get(i)).getDepositNo().toString()));
            }
        };
    }

    private BaseListener<RDLimitResult> RDlimitsuccessListener()
    {
        return new BaseListener<RDLimitResult>(this)
        {
            public void successResponse(RDLimitResult paramAnonymousRDLimitResult)
            {
                QueryDialog.this.adapter = new Adapter<RDLimitInfo>(QueryDialog.this, paramAnonymousRDLimitResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, RDLimitInfo paramAnonymous2RDLimitInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2RDLimitInfo.getName());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((RDLimitInfo)paramAnonymousRDLimitResult.getResult().get(i)).getValue(), ((RDLimitInfo)paramAnonymousRDLimitResult.getResult().get(i)).getName().toString()));
            }
        };
    }

    private BaseListener<RDTicketResult> RDticketsuccessListener()
    {
        return new BaseListener<RDTicketResult>(this)
        {
            public void successResponse(RDTicketResult paramAnonymousRDTicketResult)
            {
                QueryDialog.this.adapter = new Adapter<RDTicketInfo>(QueryDialog.this, paramAnonymousRDTicketResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, RDTicketInfo paramAnonymous2RDTicketInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2RDTicketInfo.getFixedNo());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((RDTicketInfo)paramAnonymousRDTicketResult.getResult().get(i)).getId(), ((RDTicketInfo)paramAnonymousRDTicketResult.getResult().get(i)).getFixedNo().toString()));
            }
        };
    }

    private BaseListener<BankTypeResult> banktypesuccessListener()
    {
        return new BaseListener<BankTypeResult>(this)
        {
            public void successResponse(BankTypeResult paramAnonymousBankTypeResult)
            {
                QueryDialog.this.adapter = new Adapter<BankTypeInfo>(QueryDialog.this, paramAnonymousBankTypeResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, BankTypeInfo paramAnonymous2BankTypeInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2BankTypeInfo.getName());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((BankTypeInfo)paramAnonymousBankTypeResult.getResult().get(i)).getId(), ((BankTypeInfo)paramAnonymousBankTypeResult.getResult().get(i)).getName().toString()));
            }
        };
    }

    private BaseListener<BorrowUnitCodeResult> borrowunitcodesuccessListener()
    {
        return new BaseListener<BorrowUnitCodeResult>(this)
        {
            public void successResponse(BorrowUnitCodeResult paramAnonymousBorrowUnitCodeResult)
            {
                QueryDialog.this.adapter = new Adapter<BorrowUnitCodeInfo>(QueryDialog.this, paramAnonymousBorrowUnitCodeResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, BorrowUnitCodeInfo paramAnonymous2BorrowUnitCodeInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2BorrowUnitCodeInfo.getClientCode());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((BorrowUnitCodeInfo)paramAnonymousBorrowUnitCodeResult.getResult().get(i)).getClientCode(), ((BorrowUnitCodeInfo)paramAnonymousBorrowUnitCodeResult.getResult().get(i)).getClientCode().toString()));
            }
        };
    }

    private BaseListener<ClientMessageResult> clientmessagesuccessListener()
    {
        return new BaseListener<ClientMessageResult>(this)
        {
            public void successResponse(ClientMessageResult paramAnonymousClientMessageResult)
            {
                QueryDialog.this.adapter = new Adapter<ClientMessageInfo>(QueryDialog.this, paramAnonymousClientMessageResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, ClientMessageInfo paramAnonymous2ClientMessageInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2ClientMessageInfo.getClientName());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((ClientMessageInfo)paramAnonymousClientMessageResult.getResult().get(i)).getClientID(), ((ClientMessageInfo)paramAnonymousClientMessageResult.getResult().get(i)).getClientName().toString()));
            }
        };
    }

    private BaseListener<CMAboveUnitResult> cmaboveunitsuccessListener()
    {
        return new BaseListener<CMAboveUnitResult>(this)
        {
            public void successResponse(CMAboveUnitResult paramAnonymousCMAboveUnitResult)
            {
                QueryDialog.this.adapter = new Adapter<CMAboveUnitInfo>(QueryDialog.this, paramAnonymousCMAboveUnitResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, CMAboveUnitInfo paramAnonymous2CMAboveUnitInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2CMAboveUnitInfo.getParentNametrl());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((CMAboveUnitInfo)paramAnonymousCMAboveUnitResult.getResult().get(i)).getParentId(), ((CMAboveUnitInfo)paramAnonymousCMAboveUnitResult.getResult().get(i)).getParentNametrl()));
            }
        };
    }

    private BaseListener<CMClientnoResult> cmclientnosuccessListener()
    {
        return new BaseListener<CMClientnoResult>(this)
        {
            public void successResponse(CMClientnoResult paramAnonymousCMClientnoResult)
            {
                QueryDialog.this.adapter = new Adapter<CMClientnoInfo>(QueryDialog.this, paramAnonymousCMClientnoResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, CMClientnoInfo paramAnonymous2CMClientnoInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2CMClientnoInfo.getClientName());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((CMClientnoInfo)paramAnonymousCMClientnoResult.getResult().get(i)).getClientNo(), ((CMClientnoInfo)paramAnonymousCMClientnoResult.getResult().get(i)).getClientName()));
            }
        };
    }

    private BaseListener<CUClientNoResult> cuclientnosuccessListener()
    {
        return new BaseListener<CUClientNoResult>(this)
        {
            public void successResponse(CUClientNoResult paramAnonymousCUClientNoResult)
            {
                QueryDialog.this.adapter = new Adapter<CUClientNoInfo>(QueryDialog.this, paramAnonymousCUClientNoResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, CUClientNoInfo paramAnonymous2CUClientNoInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2CUClientNoInfo.getClientCode());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((CUClientNoInfo)paramAnonymousCUClientNoResult.getResult().get(i)).getClientCode(), ((CUClientNoInfo)paramAnonymousCUClientNoResult.getResult().get(i)).getClientCode().toString()));
            }
        };
    }

    private BaseListener<CurrencyResult> currencysuccessListener()
    {
        return new BaseListener<CurrencyResult>(this)
        {
            public void successResponse(CurrencyResult paramAnonymousCurrencyResult)
            {
                QueryDialog.this.adapter = new Adapter<CurrencyInfo>(QueryDialog.this, paramAnonymousCurrencyResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, CurrencyInfo paramAnonymous2CurrencyInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2CurrencyInfo.getName());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((CurrencyInfo)paramAnonymousCurrencyResult.getResult().get(i)).getId(), ((CurrencyInfo)paramAnonymousCurrencyResult.getResult().get(i)).getName().toString()));
            }
        };
    }

    private Response.ErrorListener errorListener()
    {
        return new Response.ErrorListener()
        {
            public void onErrorResponse(VolleyError paramAnonymousVolleyError)
            {
                ToastUtils.toastShort("加载失败");
                QueryDialog.this.pdg.dismiss();
            }
        };
    }

    private BaseListener<FCurrencyResult> fcurrencysuccessListener()
    {
        return new BaseListener<FCurrencyResult>(this)
        {
            public void successResponse(FCurrencyResult paramAnonymousFCurrencyResult)
            {
                QueryDialog.this.adapter = new Adapter<FCurrencyInfo>(QueryDialog.this, paramAnonymousFCurrencyResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, FCurrencyInfo paramAnonymous2FCurrencyInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2FCurrencyInfo.getName());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((FCurrencyInfo)paramAnonymousFCurrencyResult.getResult().get(i)).getId(), ((FCurrencyInfo)paramAnonymousFCurrencyResult.getResult().get(i)).getCode().toString()));
            }
        };
    }

    private BaseListener<IAAccountNoResult> iaaccountnumsuccessListener()
    {
        return new BaseListener<IAAccountNoResult>(this)
        {
            public void successResponse(IAAccountNoResult paramAnonymousIAAccountNoResult)
            {
                QueryDialog.this.adapter = new Adapter<IAAccountNoInfo>(QueryDialog.this, paramAnonymousIAAccountNoResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, IAAccountNoInfo paramAnonymous2IAAccountNoInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2IAAccountNoInfo.getAccountNo());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((IAAccountNoInfo)paramAnonymousIAAccountNoResult.getResult().get(i)).getAccountNo(), ((IAAccountNoInfo)paramAnonymousIAAccountNoResult.getResult().get(i)).getAccountNo().toString()));
            }
        };
    }

    private BaseListener<IAClientNoResult> iaclientnumsuccessListener()
    {
        return new BaseListener<IAClientNoResult>(this)
        {
            public void successResponse(IAClientNoResult paramAnonymousIAClientNoResult)
            {
                if (paramAnonymousIAClientNoResult.code.equals("T18000000001"))
                {
                    QueryDialog.this.adapter = new Adapter<IAClientNoInfo>(QueryDialog.this, paramAnonymousIAClientNoResult.getResult(), R.layout.item_clientid)
                    {
                        public void convert(ViewHolder paramAnonymous2ViewHolder, IAClientNoInfo paramAnonymous2IAClientNoInfo, int paramAnonymous2Int)
                        {
                            paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2IAClientNoInfo.getClientNo());
                        }
                    };
                    QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                    QueryDialog.this.pdg.dismiss();
                    for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                        QueryDialog.this.arr.add(new DialogText(((IAClientNoInfo)paramAnonymousIAClientNoResult.getResult().get(i)).getClientNo(), ((IAClientNoInfo)paramAnonymousIAClientNoResult.getResult().get(i)).getClientNo().toString()));
                }
            }
        };
    }

    private BaseListener<OAccountBankResult> oaccountbanksuccessListener()
    {
        return new BaseListener<OAccountBankResult>(this)
        {
            public void successResponse(OAccountBankResult paramAnonymousOAccountBankResult)
            {
                QueryDialog.this.adapter = new Adapter<OAccountBankInfo>(QueryDialog.this, paramAnonymousOAccountBankResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, OAccountBankInfo paramAnonymous2OAccountBankInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2OAccountBankInfo.getName());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((OAccountBankInfo)paramAnonymousOAccountBankResult.getResult().get(i)).getId(), ((OAccountBankInfo)paramAnonymousOAccountBankResult.getResult().get(i)).getName().toString()));
            }
        };
    }

    private BaseListener<OfficeResult> officesuccessListener()
    {
        return new BaseListener<OfficeResult>(this)
        {
            public void successResponse(OfficeResult paramAnonymousOfficeResult)
            {
                System.out.print(paramAnonymousOfficeResult);
                QueryDialog.this.adapter = new Adapter<OfficeInfo>(QueryDialog.this, paramAnonymousOfficeResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, OfficeInfo paramAnonymous2OfficeInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2OfficeInfo.getName());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((OfficeInfo)paramAnonymousOfficeResult.getResult().get(i)).getId(), ((OfficeInfo)paramAnonymousOfficeResult.getResult().get(i)).getName().toString()));
            }
        };
    }

    private BaseListener<FxCustomEntityForAppResult> replacecnSuccessListener()
    {
        return new BaseListener<FxCustomEntityForAppResult>(this)
        {
            public void successResponse(FxCustomEntityForAppResult paramAnonymousFxCustomEntityForAppResult)
            {
                QueryDialog.this.adapter = new Adapter<FxCustomEntityForAppInfo>(QueryDialog.this, paramAnonymousFxCustomEntityForAppResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, FxCustomEntityForAppInfo paramAnonymous2FxCustomEntityForAppInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2FxCustomEntityForAppInfo.getClientCode());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((FxCustomEntityForAppInfo)paramAnonymousFxCustomEntityForAppResult.getResult().get(i)).getClientCode().toString(), ((FxCustomEntityForAppInfo)paramAnonymousFxCustomEntityForAppResult.getResult().get(i)).getClientCode().toString()));
            }
        };
    }

    private BaseListener<SelfAccountNoResult> selfaccountnosuccessListener()
    {
        return new BaseListener<SelfAccountNoResult>(this)
        {
            public void successResponse(SelfAccountNoResult paramAnonymousSelfAccountNoResult)
            {
                QueryDialog.this.adapter = new Adapter<SelfAccountNoInfo>(QueryDialog.this, paramAnonymousSelfAccountNoResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, SelfAccountNoInfo paramAnonymous2SelfAccountNoInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2SelfAccountNoInfo.getAccountNo());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((SelfAccountNoInfo)paramAnonymousSelfAccountNoResult.getResult().get(i)).getAccountNo(), ((SelfAccountNoInfo)paramAnonymousSelfAccountNoResult.getResult().get(i)).getAccountNo().toString()));
            }
        };
    }

    private BaseListener<VPAccountNoResult> vpaccountnosuccessListener()
    {
        return new BaseListener<VPAccountNoResult>(this)
        {
            public void successResponse(VPAccountNoResult paramAnonymousVPAccountNoResult)
            {
                QueryDialog.this.adapter = new Adapter<VPAccountNoInfo>(QueryDialog.this, paramAnonymousVPAccountNoResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, VPAccountNoInfo paramAnonymous2VPAccountNoInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2VPAccountNoInfo.getAccountNo());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((VPAccountNoInfo)paramAnonymousVPAccountNoResult.getResult().get(i)).getId(), ((VPAccountNoInfo)paramAnonymousVPAccountNoResult.getResult().get(i)).getAccountNo().toString()));
            }
        };
    }

    private BaseListener<VPClientNoResult> vpclientnosuccessListener()
    {
        return new BaseListener<VPClientNoResult>(this)
        {
            public void successResponse(VPClientNoResult paramAnonymousVPClientNoResult)
            {
                QueryDialog.this.adapter = new Adapter<VPClientNoInfo>(QueryDialog.this, paramAnonymousVPClientNoResult.getResult(), R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymous2ViewHolder, VPClientNoInfo paramAnonymous2VPClientNoInfo, int paramAnonymous2Int)
                    {
                        paramAnonymous2ViewHolder.setText(R.id.tv_clientid, paramAnonymous2VPClientNoInfo.getCode());
                    }
                };
                QueryDialog.this.lv_dialoglist.setAdapter(QueryDialog.this.adapter);
                QueryDialog.this.pdg.dismiss();
                for (int i = 0; i < QueryDialog.this.lv_dialoglist.getCount(); i++)
                    QueryDialog.this.arr.add(new DialogText(((VPClientNoInfo)paramAnonymousVPClientNoResult.getResult().get(i)).getId(), ((VPClientNoInfo)paramAnonymousVPClientNoResult.getResult().get(i)).getCode().toString()));
            }
        };
    }

    public void AccountProperty()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new AccountMessage("", "", "", "", "非总账户", "0"));
        localArrayList.add(new AccountMessage("", "", "", "", "资金池总账户(财司)", "1"));
        localArrayList.add(new AccountMessage("", "", "", "", "资金池总账户(资管)", "2"));
        localArrayList.add(new AccountMessage("", "", "", "", "在外账户", "3"));
        localArrayList.add(new AccountMessage("", "", "", "", "资金池子账户(财司)", "4"));
        localArrayList.add(new AccountMessage("", "", "", "", "资金池子账户(资管)", "5"));
        this.adapter = new Adapter<AccountMessage>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, AccountMessage paramAnonymousAccountMessage, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousAccountMessage.getAccountProperty());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((AccountMessage)localArrayList.get(i)).getPropertyid(), ((AccountMessage)localArrayList.get(i)).getAccountProperty().toString()));
    }

    public void AccountStatus()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new AccountMessage("", "", "销户", "0", "", ""));
        localArrayList.add(new AccountMessage("", "", "正常", "1", "", ""));
        localArrayList.add(new AccountMessage("", "", "销户处理中", "2", "", ""));
        localArrayList.add(new AccountMessage("", "", "变更处理中", "3", "", ""));
        this.adapter = new Adapter<AccountMessage>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, AccountMessage paramAnonymousAccountMessage, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousAccountMessage.getAccountStatus());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((AccountMessage)localArrayList.get(i)).getStatusid(), ((AccountMessage)localArrayList.get(i)).getAccountStatus().toString()));
    }

    public void AccountType()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new AccountMessage("活期账户", "0", "", "", "", ""));
        localArrayList.add(new AccountMessage("定期账户", "1", "", "", "", ""));
        this.adapter = new Adapter<AccountMessage>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, AccountMessage paramAnonymousAccountMessage, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousAccountMessage.getAccountType());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((AccountMessage)localArrayList.get(i)).getTypeid(), ((AccountMessage)localArrayList.get(i)).getAccountType().toString()));
    }

    public void Accountbelong()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new AccountBelong("0", "成员单位"));
        localArrayList.add(new AccountBelong("1", "财务公司"));
        this.adapter = new Adapter<AccountBelong>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, AccountBelong paramAnonymousAccountBelong, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousAccountBelong.getOwnerType());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((AccountBelong)localArrayList.get(i)).getId(), ((AccountBelong)localArrayList.get(i)).getOwnerType().toString()));
    }

    public void Drafttype()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new DraftType("1", "银行承兑汇票"));
        localArrayList.add(new DraftType("2", "商业承兑汇票"));
        this.adapter = new Adapter<DraftType>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, DraftType paramAnonymousDraftType, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousDraftType.getDrafttype());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((DraftType)localArrayList.get(i)).getId(), ((DraftType)localArrayList.get(i)).getDrafttype().toString()));
    }

    public void Inouts()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new InOut("0", "境外"));
        localArrayList.add(new InOut("1", "境内"));
        this.adapter = new Adapter<InOut>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, InOut paramAnonymousInOut, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousInOut.getIo());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((InOut)localArrayList.get(i)).getCode(), ((InOut)localArrayList.get(i)).getIo()));
    }

    public void PaymentMethod()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new TranDetailPayment("0", "付"));
        localArrayList.add(new TranDetailPayment("1", "收"));
        this.adapter = new Adapter<TranDetailPayment>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, TranDetailPayment paramAnonymousTranDetailPayment, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousTranDetailPayment.getStr());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((TranDetailPayment)localArrayList.get(i)).getRequest(), ((TranDetailPayment)localArrayList.get(i)).getStr().toString()));
    }

    public void ReStatus()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new TranDetailReStatus("0", "其他交易"));
        localArrayList.add(new TranDetailReStatus("1", "入账处理"));
        localArrayList.add(new TranDetailReStatus("2", "入账成功"));
        localArrayList.add(new TranDetailReStatus("3", "入账失败"));
        localArrayList.add(new TranDetailReStatus("4", "待入账"));
        localArrayList.add(new TranDetailReStatus("5", "以手工入账"));
        localArrayList.add(new TranDetailReStatus("6", "入账状态未知"));
        localArrayList.add(new TranDetailReStatus("7", "结算系统撤销复核交易"));
        localArrayList.add(new TranDetailReStatus("8", "结算系统删除入账交易"));
        localArrayList.add(new TranDetailReStatus("9", "待审核，待入账"));
        this.adapter = new Adapter<TranDetailReStatus>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, TranDetailReStatus paramAnonymousTranDetailReStatus, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousTranDetailReStatus.getStr());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((TranDetailReStatus)localArrayList.get(i)).getRequest(), ((TranDetailReStatus)localArrayList.get(i)).getStr().toString()));
    }

    protected void init()
    {
        View localView = View.inflate(this, R.layout.dialog_query, null);
        ButterKnife.bind(this, localView);
        setContentView(localView);
        this.iv_closedialog.setOnClickListener(this);
        this.iv_dialogsearch.setOnClickListener(this);
        setFinishOnTouchOutside(false);
        Intent localIntent = getIntent();
        this.Clickname = localIntent.getExtras().getString("clickName");
        this.QueryName = localIntent.getExtras().getString("queryName");
        this.ParamName = localIntent.getExtras().getString("paramName");
    }

    protected void initView() {
        this.pdg = new ProgressDialog(this, R.style.dialog);
        this.pdg.setCancelable(false);
        this.pdg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.pdg.show();
        String str = this.Clickname;
        System.out.println("====" + str);
        System.out.println(str.hashCode());
        int i;
        switch (str.toString()) {
            default:
                i = -1;
            case "office":
                i = 0;
                break;
            case "currency":
                i = 1;
                break;
            case "bankname":
                i = 2;
                break;
            case "openbank":
                i = 3;
                break;
            case "belong":
                i = 4;
                break;
            case "clientname":
                i = 5;
                break;
            case "inout":
                i = 6;
                break;
            case "accountstate":
                i = 7;
                break;
            case "accountnature":
                i = 8;
                break;
            case "accounttype":
                i = 9;
                break;
//            case -671781126:
//                i=10;
//            case -962590849:
//                i=11;
//            case -283359969:
//                i=12;
            case "isDirectlink":
                i = 13;
                break;
//            case 1466660337:
//                i=14;
//            case -1122977817:
//                i=15;
//            case 1813987907:
//                i=16;
//            case -166503005:
//                i=17;
//            case 464617052:
//                i=18;
//            case -1992240981:
//                i=19;
//            case -1072944730:
//                i=20;
//            case -899453153:
//                i=21;
//            case 2134688346:
//                i=22;
//            case -697851454:
//                i=23;
//            case -1904074789:
//                i=24;
//            case -1323899231:
//                i=25;
//            case -130296183:
//                i=26;
//            case -1650756277:
//                i=27;
//            case -1294465821:
//                i=28;
//            case -738789988:
//                i=29;
            case "insidepcurrency":
                i = 30;
                break;
            case "insidepclientnums":
                i = 31;
                break;
            case "insidepclientnume":
                i = 32;
                break;
            case "insideaccountnums":
                i = 33;
                break;
            case "insideaccountnume":
                i = 34;
                break;
//            case 1572751118:
//                i=35;
//            case 793382201:
//                i=36;
//            case -119858318:
//                i=37;
//            case -762088279:
//                i=38;
//            case -762088293:
//                i=39;
//            case 1735386584:
//                i=40;
//            case 395087825:
//                i=41;
//            case -1572314108:
//                i=42;
//            case 1053111739:
//                i=43;
        }
        switch (i) {
            default:
                return;
            case 0:
                this.tv_dialogtitle.setText("机构");
                ServerManager.getQueryOffice(officesuccessListener(), errorListener());
                break;
            case 1:
                this.tv_dialogtitle.setText("币种");
                ServerManager.getQueryCurrency(currencysuccessListener(), errorListener());
                break;
            case 2:
                this.tv_dialogtitle.setText("银行类别");
                ServerManager.getQueryBankType(banktypesuccessListener(), errorListener());
                break;
            case 3:
                this.tv_dialogtitle.setText("开户银行");
                ServerManager.getQueryOAccountBank(BankAccountQuery.br.getBankTypeId(), oaccountbanksuccessListener(), errorListener());
                break;
            case 4:
                this.tv_dialogtitle.setText("账户归属");
                Accountbelong();
                break;
            case 5:
                this.tv_dialogtitle.setText("客户名称");
                ServerManager.getQueryClientMessage(clientmessagesuccessListener(), errorListener());
                break;
            case 6:
                this.tv_dialogtitle.setText("境内外");
                Inouts();
                break;
            case 7:
                this.tv_dialogtitle.setText("账户状态");
                AccountStatus();
                break;
            case 8:
                this.tv_dialogtitle.setText("账户属性");
                AccountProperty();
                break;
            case 9:
                this.tv_dialogtitle.setText("账户类型");
                AccountType();
                break;
            case 10:
                this.tv_dialogtitle.setText("业务类型");
                break;
            case 11:
                this.tv_dialogtitle.setText("收付方向");
                PaymentMethod();
                break;
            case 12:
                this.tv_dialogtitle.setText("入账状态");
                ReStatus();
                break;
            case 13:
                this.tv_dialogtitle.setText("是否直连");
                isDirectlink();
                break;
            case 14:
                this.tv_dialogtitle.setText("借款单位编号");
                ServerManager.getBorrowUnitCodeQuery(borrowunitcodesuccessListener(), errorListener());
                break;
            case 15:
                this.tv_dialogtitle.setText("汇票种类");
                Drafttype();
                break;
            case 16:
                this.tv_dialogtitle.setText("外币币种");
                ServerManager.getFCurrencyQuery(fcurrencysuccessListener(), errorListener());
                break;
            case 17:
                this.tv_dialogtitle.setText("客户编号由");
                ServerManager.getDaiKeKeHuNoQuery(replacecnSuccessListener(), errorListener());
                break;
            case 18:
                this.tv_dialogtitle.setText("客户编号至");
                ServerManager.getDaiKeKeHuNoQuery(replacecnSuccessListener(), errorListener());
                break;
            case 19:
                this.tv_dialogtitle.setText("外币币种");
                ServerManager.getFCurrencyQuery(fcurrencysuccessListener(), errorListener());
                break;
            case 20:
                this.tv_dialogtitle.setText("人民币银行账户由");
                ServerManager.getrmbSelfAccountNoQuery(selfaccountnosuccessListener(), errorListener());
                break;
            case 21:
                this.tv_dialogtitle.setText("人民币银行账户至");
                ServerManager.getrmbSelfAccountNoQuery(selfaccountnosuccessListener(), errorListener());
                break;
            case 22:
                this.tv_dialogtitle.setText("外币银行账户由");
//            ServerManager.getSelfAccountNoQuery(SelfExchangeActivity.ser, selfaccountnosuccessListener(), errorListener());
                break;
            case 23:
                this.tv_dialogtitle.setText("外币银行账户至");
//            ServerManager.getSelfAccountNoQuery(SelfExchangeActivity.ser, selfaccountnosuccessListener(), errorListener());
                break;
            case 24:
                this.tv_dialogtitle.setText("客户编号");
                ServerManager.getCMclientnoQuery(cmclientnosuccessListener(), errorListener());
                break;
            case 25:
                this.tv_dialogtitle.setText("上级单位");
                ServerManager.getCMaboveunitQuery(cmaboveunitsuccessListener(), errorListener());
                break;
            case 26:
                this.tv_dialogtitle.setText("客户编号");
                ServerManager.getVPClientNoQuery(vpclientnosuccessListener(), errorListener());
                break;
            case 27:
                this.tv_dialogtitle.setText("账户编号");
                ServerManager.getVPAccountNoQuery(vpaccountnosuccessListener(), errorListener());
                break;
            case 28:
                this.tv_dialogtitle.setText("客户编号由");
                ServerManager.getcuClientNoQuery(cuclientnosuccessListener(), errorListener());
                break;
            case 29:
                this.tv_dialogtitle.setText("客户编号至");
                ServerManager.getcuClientNoQuery(cuclientnosuccessListener(), errorListener());
                break;
            case 30:
                this.tv_dialogtitle.setText("币种");
                ServerManager.getQueryCurrency(currencysuccessListener(), errorListener());
                break;
            case 31:
                this.tv_dialogtitle.setText("客户编号由");
                ServerManager.getiaclientnoQuery(iaclientnumsuccessListener(), errorListener());
                break;
            case 32:
                this.tv_dialogtitle.setText("客户编号至");
                ServerManager.getiaclientnoQuery(iaclientnumsuccessListener(), errorListener());
                break;
            case 33:
                this.tv_dialogtitle.setText("账户编号由");
                ServerManager.getiaaccountnoQuery(iaaccountnumsuccessListener(), errorListener());
                break;
            case 34:
                this.tv_dialogtitle.setText("账户编号至");
                ServerManager.getiaaccountnoQuery(iaaccountnumsuccessListener(), errorListener());
                break;
            case 35:
                this.tv_dialogtitle.setText("定期存款单据号");
//            ServerManagergetRDTicketQuery(QueryDetailActivity.rdr, RDticketsuccessListener(), errorListener());
                break;
            case 36:
                this.tv_dialogtitle.setText("定期存款期限 由");
                ServerManager.getRDLimitQuery(RDlimitsuccessListener(), errorListener());
                break;
            case 37:
                this.tv_dialogtitle.setText("定期存款期限 至");
                ServerManager.getRDLimitQuery(RDlimitsuccessListener(), errorListener());
                break;
            case 38:
                this.tv_dialogtitle.setText("通知存款单号 由");
                ServerManager.NoticeDepositNumQueru(NDNumQuerySuccessListener(), errorListener());
                break;
            case 39:
                this.tv_dialogtitle.setText("通知存款单号 至");
                ServerManager.NoticeDepositNumQueru(NDNumQuerySuccessListener(), errorListener());
                break;
            case 40:
                this.tv_dialogtitle.setText("通知存款金额 由");
                break;
            case 41:
                this.tv_dialogtitle.setText("通知存款金额 至");
                break;
            case 42:
                this.tv_dialogtitle.setText("金额 由");
                break;
            case 43:
                this.tv_dialogtitle.setText("金额 至");
                break;
        }
//        while (true) {
            this.lv_dialoglist.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                    QueryDialog.this.code = ((DialogText) QueryDialog.this.arr.get(paramAnonymousInt)).getRequest();
                    QueryDialog.this.name = ((DialogText) QueryDialog.this.arr.get(paramAnonymousInt)).getStr();
                    TextView localTextView = (TextView) paramAnonymousView.findViewById(R.id.tv_clientid);
                    QueryDialog.this.et_dialogmessage.setText(localTextView.getText().toString());
                    QueryDialog.this.setValue();
                    QueryDialog.this.finish();
                }
            });
        }
//    }

    public void isDirectlink()
    {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new TranDetailPayment("0", "否"));
        localArrayList.add(new TranDetailPayment("1", "是"));
        this.adapter = new Adapter<TranDetailPayment>(this, localArrayList, R.layout.item_clientid)
        {
            public void convert(ViewHolder paramAnonymousViewHolder, TranDetailPayment paramAnonymousTranDetailPayment, int paramAnonymousInt)
            {
                paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousTranDetailPayment.getStr());
            }
        };
        this.lv_dialoglist.setAdapter(this.adapter);
        this.pdg.dismiss();
        for (int i = 0; i < this.lv_dialoglist.getCount(); i++)
            this.arr.add(new DialogText(((TranDetailPayment)localArrayList.get(i)).getRequest(), ((TranDetailPayment)localArrayList.get(i)).getStr().toString()));
    }

    public void onClick(View paramView)
    {
        switch (paramView.getId())
        {
            case R.id.et_dialogmessage:
            default:
                return;
            case R.id.iv_closedialog:
                String str2 = this.et_dialogmessage.getText().toString();
                for (int j = 0; j < this.arr.size(); j++)
                    if (str2.equals(((DialogText)this.arr.get(j)).getRequest()))
                        setValue();
                finish();
                return;
            case R.id.iv_dialogsearch:
                if (this.et_dialogmessage.getText().toString().length() == 0)
                {
                    this.lv_dialoglist.setAdapter(this.adapter);
                    this.pdg.dismiss();
                    return;
                }
                this.newarr = new ArrayList();
                String str1 = this.et_dialogmessage.getText().toString();
                for (int i = 0; i < this.arr.size(); i++)
                    if (((DialogText)this.arr.get(i)).getStr().contains(str1))
                        this.newarr.add(this.arr.get(i));
                Adapter local2 = new Adapter<DialogText>(this, this.newarr, R.layout.item_clientid)
                {
                    public void convert(ViewHolder paramAnonymousViewHolder, DialogText paramAnonymousDialogText, int paramAnonymousInt)
                    {
                        paramAnonymousViewHolder.setText(R.id.tv_clientid, paramAnonymousDialogText.getStr());
                    }
                };
                this.lv_dialoglist.setAdapter(local2);
                this.pdg.dismiss();
        }

    }

    public void setValue()
    {
//        String str = this.QueryName;
//        int i = -1;
//        switch (str.hashCode())
//        {
//            default:
//                switch (i)
//                {
//                    default:
//                    case 0:
//                    case 1:
//                    case 2:
//                    case 3:
//                    case 4:
//                    case 5:
//                    case 6:
//                    case 7:
//                    case 8:
//                    case 9:
//                    case 10:
//                    case 11:
//                    case 12:
//                    case 13:
//                }
//                break;
//            case -856563631:
//            case 1312839961:
//            case -177845726:
//            case 376641907:
//            case -1795378953:
//            case -809781873:
//            case 1375197628:
//            case -991777337:
//            case 674534350:
//            case 982294161:
//            case 310642530:
//            case 1755710229:
//            case 2137669436:
//            case -1756982468:
//        }
//        do
//        {
//            return;
//            if (!str.equals("bankAccount"))
//                break;
//            i = 0;
//            break;
//            if (!str.equals("transDetail"))
//                break;
//            i = 1;
//            break;
//            if (!str.equals("loanContract"))
//                break;
//            i = 2;
//            break;
//            if (!str.equals("discountContract"))
//                break;
//            i = 3;
//            break;
//            if (!str.equals("replaceExchange"))
//                break;
//            i = 4;
//            break;
//            if (!str.equals("selfExchange"))
//                break;
//            i = 5;
//            break;
//            if (!str.equals("clientMessage"))
//                break;
//            i = 6;
//            break;
//            if (!str.equals("virtualPool"))
//                break;
//            i = 7;
//            break;
//            if (!str.equals("creditUse"))
//                break;
//            i = 8;
//            break;
//            if (!str.equals("insideAccount"))
//                break;
//            i = 9;
//            break;
//            if (!str.equals("regularDeposit"))
//                break;
//            i = 10;
//            break;
//            if (!str.equals("notifyDeposit"))
//                break;
//            i = 11;
//            break;
//            if (!str.equals("trandetail"))
//                break;
//            i = 12;
//            break;
//            if (!str.equals("tranrecord"))
//                break;
//            i = 13;
//            break;
//            BankAccountQuery.setTvContent(this.et_dialogmessage.getText().toString());
//            BankAccountQuery.br.setParameter(this.ParamName, this.code);
//        }
//        while (!this.Clickname.equals("bankname"));
//        BankAccountQuery.setTvContent2("");
//        return;
//        TranDetailActivity.setTvContents(this.et_dialogmessage.getText().toString());
//        return;
//        LoanContractActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        LoanContractActivity.setTvContent2(this.name);
//        LoanContractActivity.lcr.setParameter(this.ParamName, this.code);
//        return;
//        DiscountContractActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        DiscountContractActivity.dcr.setParameter(this.ParamName, this.code);
//        return;
//        ReplaceExchangeActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        if (this.Clickname.equals("replacefcurrency"))
//            setcurrenncy(this.name, "replace");
//        ReplaceExchangeActivity.rgr.setParameter(this.ParamName, this.code);
//        return;
//        SelfExchangeActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        if (this.Clickname.equals("selffcurrency"))
//            setcurrenncy(this.name, "self");
//        SelfExchangeActivity.ser.setParameter(this.ParamName, this.code);
//        return;
//        ClientMessageActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        ClientMessageActivity.cmr.setParameter(this.ParamName, this.code);
//        return;
//        VirtualPoolActivity.setTv_contents(this.et_dialogmessage.getText().toString());
//        VirtualPoolActivity.vpr.setParameter(this.ParamName, this.code);
//        return;
//        CreditUseQueryActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        CreditUseQueryActivity.cur.setParameter(this.ParamName, this.code);
//        return;
//        InsideAccountActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        InsideAccountActivity.iar.setParameter(this.ParamName, this.code);
//        return;
//        RegularDepositActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        QueryDetailActivity.rdr.setParameter(this.ParamName, this.code);
//        return;
//        NotifyDepositActivity.setTvContent(this.et_dialogmessage.getText().toString());
//        NotifyDepositActivity.request.setParameter(this.ParamName, this.code);
//        return;
//        TranDetailActivity.setTvContents(this.et_dialogmessage.getText().toString());
//        TranDetailActivity.tr.setParameter(this.ParamName, this.code);
//        return;
//        TranRecordActivity.setTvContent(this.et_dialogmessage.getText().toString());
    }

    public void setcurrenncy(String paramString1, String paramString2)
    {
//        int i = -1;
//        if (paramString2.equals("self"))
//        {
//            switch (paramString1.hashCode())
//            {
//                default:
//                case 66894:
//                case 84326:
//                case 69026:
//                case 65152:
//                case 65168:
//                case 65635:
//                case 66044:
//                case 66470:
//                case 66689:
//                case 67564:
//                case 67748:
//                case 68962:
//                case 69610:
//                case 69882:
//                case 70357:
//                case 71585:
//                case 72343:
//                case 72653:
//                case 72732:
//                case 72777:
//                case 72833:
//                case 73631:
//                case 73683:
//                case 74704:
//                case 74840:
//                case 76526:
//                case 76803:
//                case 76838:
//                case 77385:
//                case 77482:
//                case 77520:
//                case 77816:
//                case 79192:
//                case 79287:
//                case 81503:
//                case 81977:
//                case 82032:
//                case 82195:
//                case 83022:
//                case 83489:
//                case 83597:
//            }
//            while (true)
//                switch (i)
//                {
//                    default:
//                        return;
//                    if (paramString1.equals("CNY"))
//                    {
//                        i = 0;
//                        continue;
//                        if (paramString1.equals("USD"))
//                        {
//                            i = 1;
//                            continue;
//                            if (paramString1.equals("EUR"))
//                            {
//                                i = 2;
//                                continue;
//                                if (paramString1.equals("ATS"))
//                                {
//                                    i = 3;
//                                    continue;
//                                    if (paramString1.equals("AUD"))
//                                    {
//                                        i = 4;
//                                        continue;
//                                        if (paramString1.equals("BEF"))
//                                        {
//                                            i = 5;
//                                            continue;
//                                            if (paramString1.equals("BRL"))
//                                            {
//                                                i = 6;
//                                                continue;
//                                                if (paramString1.equals("CAD"))
//                                                {
//                                                    i = 7;
//                                                    continue;
//                                                    if (paramString1.equals("CHF"))
//                                                    {
//                                                        i = 8;
//                                                        continue;
//                                                        if (paramString1.equals("DEM"))
//                                                        {
//                                                            i = 9;
//                                                            continue;
//                                                            if (paramString1.equals("DKK"))
//                                                            {
//                                                                i = 10;
//                                                                continue;
//                                                                if (paramString1.equals("ESP"))
//                                                                {
//                                                                    i = 11;
//                                                                    continue;
//                                                                    if (paramString1.equals("FIM"))
//                                                                    {
//                                                                        i = 12;
//                                                                        continue;
//                                                                        if (paramString1.equals("FRF"))
//                                                                        {
//                                                                            i = 13;
//                                                                            continue;
//                                                                            if (paramString1.equals("GBP"))
//                                                                            {
//                                                                                i = 14;
//                                                                                continue;
//                                                                                if (paramString1.equals("HKD"))
//                                                                                {
//                                                                                    i = 15;
//                                                                                    continue;
//                                                                                    if (paramString1.equals("IDR"))
//                                                                                    {
//                                                                                        i = 16;
//                                                                                        continue;
//                                                                                        if (paramString1.equals("INR"))
//                                                                                        {
//                                                                                            i = 17;
//                                                                                            continue;
//                                                                                            if (paramString1.equals("IQD"))
//                                                                                            {
//                                                                                                i = 18;
//                                                                                                continue;
//                                                                                                if (paramString1.equals("IRR"))
//                                                                                                {
//                                                                                                    i = 19;
//                                                                                                    continue;
//                                                                                                    if (paramString1.equals("ITL"))
//                                                                                                    {
//                                                                                                        i = 20;
//                                                                                                        continue;
//                                                                                                        if (paramString1.equals("JOD"))
//                                                                                                        {
//                                                                                                            i = 21;
//                                                                                                            continue;
//                                                                                                            if (paramString1.equals("JPY"))
//                                                                                                            {
//                                                                                                                i = 22;
//                                                                                                                continue;
//                                                                                                                if (paramString1.equals("KRW"))
//                                                                                                                {
//                                                                                                                    i = 23;
//                                                                                                                    continue;
//                                                                                                                    if (paramString1.equals("KWD"))
//                                                                                                                    {
//                                                                                                                        i = 24;
//                                                                                                                        continue;
//                                                                                                                        if (paramString1.equals("MOP"))
//                                                                                                                        {
//                                                                                                                            i = 25;
//                                                                                                                            continue;
//                                                                                                                            if (paramString1.equals("MXN"))
//                                                                                                                            {
//                                                                                                                                i = 26;
//                                                                                                                                continue;
//                                                                                                                                if (paramString1.equals("MYR"))
//                                                                                                                                {
//                                                                                                                                    i = 27;
//                                                                                                                                    continue;
//                                                                                                                                    if (paramString1.equals("NLG"))
//                                                                                                                                    {
//                                                                                                                                        i = 28;
//                                                                                                                                        continue;
//                                                                                                                                        if (paramString1.equals("NOK"))
//                                                                                                                                        {
//                                                                                                                                            i = 29;
//                                                                                                                                            continue;
//                                                                                                                                            if (paramString1.equals("NPR"))
//                                                                                                                                            {
//                                                                                                                                                i = 30;
//                                                                                                                                                continue;
//                                                                                                                                                if (paramString1.equals("NZD"))
//                                                                                                                                                {
//                                                                                                                                                    i = 31;
//                                                                                                                                                    continue;
//                                                                                                                                                    if (paramString1.equals("PHP"))
//                                                                                                                                                    {
//                                                                                                                                                        i = 32;
//                                                                                                                                                        continue;
//                                                                                                                                                        if (paramString1.equals("PKR"))
//                                                                                                                                                        {
//                                                                                                                                                            i = 33;
//                                                                                                                                                            continue;
//                                                                                                                                                            if (paramString1.equals("RUB"))
//                                                                                                                                                            {
//                                                                                                                                                                i = 34;
//                                                                                                                                                                continue;
//                                                                                                                                                                if (paramString1.equals("SEK"))
//                                                                                                                                                                {
//                                                                                                                                                                    i = 35;
//                                                                                                                                                                    continue;
//                                                                                                                                                                    if (paramString1.equals("SGD"))
//                                                                                                                                                                    {
//                                                                                                                                                                        i = 36;
//                                                                                                                                                                        continue;
//                                                                                                                                                                        if (paramString1.equals("SLL"))
//                                                                                                                                                                        {
//                                                                                                                                                                            i = 37;
//                                                                                                                                                                            continue;
//                                                                                                                                                                            if (paramString1.equals("THB"))
//                                                                                                                                                                            {
//                                                                                                                                                                                i = 38;
//                                                                                                                                                                                continue;
//                                                                                                                                                                                if (paramString1.equals("TWD"))
//                                                                                                                                                                                {
//                                                                                                                                                                                    i = 39;
//                                                                                                                                                                                    continue;
//                                                                                                                                                                                    if (paramString1.equals("TZS"))
//                                                                                                                                                                                        i = 40;
//                                                                                                                                                                                }
//                                                                                                                                                                            }
//                                                                                                                                                                        }
//                                                                                                                                                                    }
//                                                                                                                                                                }
//                                                                                                                                                            }
//                                                                                                                                                        }
//                                                                                                                                                    }
//                                                                                                                                                }
//                                                                                                                                            }
//                                                                                                                                        }
//                                                                                                                                    }
//                                                                                                                                }
//                                                                                                                            }
//                                                                                                                        }
//                                                                                                                    }
//                                                                                                                }
//                                                                                                            }
//                                                                                                        }
//                                                                                                    }
//                                                                                                }
//                                                                                            }
//                                                                                        }
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    break;
//                    case 0:
//                    case 1:
//                    case 2:
//                    case 3:
//                    case 4:
//                    case 5:
//                    case 6:
//                    case 7:
//                    case 8:
//                    case 9:
//                    case 10:
//                    case 11:
//                    case 12:
//                    case 13:
//                    case 14:
//                    case 15:
//                    case 16:
//                    case 17:
//                    case 18:
//                    case 19:
//                    case 20:
//                    case 21:
//                    case 22:
//                    case 23:
//                    case 24:
//                    case 25:
//                    case 26:
//                    case 27:
//                    case 28:
//                    case 29:
//                    case 30:
//                    case 31:
//                    case 32:
//                    case 33:
//                    case 34:
//                    case 35:
//                    case 36:
//                    case 37:
//                    case 38:
//                    case 39:
//                    case 40:
//                }
//            SelfExchangeActivity.setTvContent2("￥");
//            SelfExchangeActivity.setTvContent3("￥");
//            return;
//            SelfExchangeActivity.setTvContent2("$");
//            SelfExchangeActivity.setTvContent3("$");
//            return;
//            SelfExchangeActivity.setTvContent2("EUR");
//            SelfExchangeActivity.setTvContent3("EUR");
//            return;
//            SelfExchangeActivity.setTvContent2("ATS");
//            SelfExchangeActivity.setTvContent3("ATS");
//            return;
//            SelfExchangeActivity.setTvContent2("AUD");
//            SelfExchangeActivity.setTvContent3("AUD");
//            return;
//            SelfExchangeActivity.setTvContent2("BEF");
//            SelfExchangeActivity.setTvContent3("BEF");
//            return;
//            SelfExchangeActivity.setTvContent2("BRL");
//            SelfExchangeActivity.setTvContent3("BRL");
//            return;
//            SelfExchangeActivity.setTvContent2("CAD");
//            SelfExchangeActivity.setTvContent3("CAD");
//            return;
//            SelfExchangeActivity.setTvContent2("CHF");
//            SelfExchangeActivity.setTvContent3("CHF");
//            return;
//            SelfExchangeActivity.setTvContent2("DEM");
//            SelfExchangeActivity.setTvContent3("DEM");
//            return;
//            SelfExchangeActivity.setTvContent2("DKK");
//            SelfExchangeActivity.setTvContent3("DKK");
//            return;
//            SelfExchangeActivity.setTvContent2("ESP");
//            SelfExchangeActivity.setTvContent3("ESP");
//            return;
//            SelfExchangeActivity.setTvContent2("FIM");
//            SelfExchangeActivity.setTvContent3("FIM");
//            return;
//            SelfExchangeActivity.setTvContent2("FRF");
//            SelfExchangeActivity.setTvContent3("FRF");
//            return;
//            SelfExchangeActivity.setTvContent2("GBP");
//            SelfExchangeActivity.setTvContent3("GBP");
//            return;
//            SelfExchangeActivity.setTvContent2("HKD");
//            SelfExchangeActivity.setTvContent3("HKD");
//            return;
//            SelfExchangeActivity.setTvContent2("IDR");
//            SelfExchangeActivity.setTvContent3("IDR");
//            return;
//            SelfExchangeActivity.setTvContent2("INR");
//            SelfExchangeActivity.setTvContent3("INR");
//            return;
//            SelfExchangeActivity.setTvContent2("IQD");
//            SelfExchangeActivity.setTvContent3("IQD");
//            return;
//            SelfExchangeActivity.setTvContent2("IRR");
//            SelfExchangeActivity.setTvContent3("IRR");
//            return;
//            SelfExchangeActivity.setTvContent2("ITL");
//            SelfExchangeActivity.setTvContent3("ITL");
//            return;
//            SelfExchangeActivity.setTvContent2("JOD");
//            SelfExchangeActivity.setTvContent3("JOD");
//            return;
//            SelfExchangeActivity.setTvContent2("JPY");
//            SelfExchangeActivity.setTvContent3("JPY");
//            return;
//            SelfExchangeActivity.setTvContent2("KRW");
//            SelfExchangeActivity.setTvContent3("KRW");
//            return;
//            SelfExchangeActivity.setTvContent2("KWD");
//            SelfExchangeActivity.setTvContent3("KWD");
//            return;
//            SelfExchangeActivity.setTvContent2("MOP");
//            SelfExchangeActivity.setTvContent3("MOP");
//            return;
//            SelfExchangeActivity.setTvContent2("MXN");
//            SelfExchangeActivity.setTvContent3("MXN");
//            return;
//            SelfExchangeActivity.setTvContent2("MYR");
//            SelfExchangeActivity.setTvContent3("MYR");
//            return;
//            SelfExchangeActivity.setTvContent2("NLG");
//            SelfExchangeActivity.setTvContent3("NLG");
//            return;
//            SelfExchangeActivity.setTvContent2("NOK");
//            SelfExchangeActivity.setTvContent3("NOK");
//            return;
//            SelfExchangeActivity.setTvContent2("NPR");
//            SelfExchangeActivity.setTvContent3("NPR");
//            return;
//            SelfExchangeActivity.setTvContent2("NZD");
//            SelfExchangeActivity.setTvContent3("NZD");
//            return;
//            SelfExchangeActivity.setTvContent2("PHP");
//            SelfExchangeActivity.setTvContent3("PHP");
//            return;
//            SelfExchangeActivity.setTvContent2("PKR");
//            SelfExchangeActivity.setTvContent3("PKR");
//            return;
//            SelfExchangeActivity.setTvContent2("RUB");
//            SelfExchangeActivity.setTvContent3("RUB");
//            return;
//            SelfExchangeActivity.setTvContent2("SEK");
//            SelfExchangeActivity.setTvContent3("SEK");
//            return;
//            SelfExchangeActivity.setTvContent2("SGD");
//            SelfExchangeActivity.setTvContent3("SGD");
//            return;
//            SelfExchangeActivity.setTvContent2("SLL");
//            SelfExchangeActivity.setTvContent3("SLL");
//            return;
//            SelfExchangeActivity.setTvContent2("THB");
//            SelfExchangeActivity.setTvContent3("THB");
//            return;
//            SelfExchangeActivity.setTvContent2("TWD");
//            SelfExchangeActivity.setTvContent3("TWD");
//            return;
//            SelfExchangeActivity.setTvContent2("TZS");
//            SelfExchangeActivity.setTvContent3("TZS");
//            return;
//        }
//        label2064: int j;
//        switch (paramString1.hashCode())
//        {
//            default:
//                j = i;
//            case 66894:
//            case 84326:
//            case 69026:
//            case 65152:
//            case 65168:
//            case 65635:
//            case 66044:
//            case 66470:
//            case 66689:
//            case 67564:
//            case 67748:
//            case 68962:
//            case 69610:
//            case 69882:
//            case 70357:
//            case 71585:
//            case 72343:
//            case 72653:
//            case 72732:
//            case 72777:
//            case 72833:
//            case 73631:
//            case 73683:
//            case 74704:
//            case 74840:
//            case 76526:
//            case 76803:
//            case 76838:
//            case 77385:
//            case 77482:
//            case 77520:
//            case 77816:
//            case 79192:
//            case 79287:
//            case 81503:
//            case 81977:
//            case 82032:
//            case 82195:
//            case 83022:
//            case 83489:
//            case 83597:
//        }
//        while (true)
//            switch (j)
//            {
//                default:
//                    return;
//                case 0:
//                    ReplaceExchangeActivity.setTvContent2("￥");
//                    ReplaceExchangeActivity.setTvContent3("￥");
//                    return;
//                if (!paramString1.equals("CNY"))
//                    break label2064;
//                j = 0;
//                continue;
//                if (!paramString1.equals("USD"))
//                    break label2064;
//                j = 1;
//                continue;
//                if (!paramString1.equals("EUR"))
//                    break label2064;
//                j = 2;
//                continue;
//                if (!paramString1.equals("ATS"))
//                    break label2064;
//                j = 3;
//                continue;
//                if (!paramString1.equals("AUD"))
//                    break label2064;
//                j = 4;
//                continue;
//                if (!paramString1.equals("BEF"))
//                    break label2064;
//                j = 5;
//                continue;
//                if (!paramString1.equals("BRL"))
//                    break label2064;
//                j = 6;
//                continue;
//                if (!paramString1.equals("CAD"))
//                    break label2064;
//                j = 7;
//                continue;
//                if (!paramString1.equals("CHF"))
//                    break label2064;
//                j = 8;
//                continue;
//                if (!paramString1.equals("DEM"))
//                    break label2064;
//                j = 9;
//                continue;
//                if (!paramString1.equals("DKK"))
//                    break label2064;
//                j = 10;
//                continue;
//                if (!paramString1.equals("ESP"))
//                    break label2064;
//                j = 11;
//                continue;
//                if (!paramString1.equals("FIM"))
//                    break label2064;
//                j = 12;
//                continue;
//                if (!paramString1.equals("FRF"))
//                    break label2064;
//                j = 13;
//                continue;
//                if (!paramString1.equals("GBP"))
//                    break label2064;
//                j = 14;
//                continue;
//                if (!paramString1.equals("HKD"))
//                    break label2064;
//                j = 15;
//                continue;
//                if (!paramString1.equals("IDR"))
//                    break label2064;
//                j = 16;
//                continue;
//                if (!paramString1.equals("INR"))
//                    break label2064;
//                j = 17;
//                continue;
//                if (!paramString1.equals("IQD"))
//                    break label2064;
//                j = 18;
//                continue;
//                if (!paramString1.equals("IRR"))
//                    break label2064;
//                j = 19;
//                continue;
//                if (!paramString1.equals("ITL"))
//                    break label2064;
//                j = 20;
//                continue;
//                if (!paramString1.equals("JOD"))
//                    break label2064;
//                j = 21;
//                continue;
//                if (!paramString1.equals("JPY"))
//                    break label2064;
//                j = 22;
//                continue;
//                if (!paramString1.equals("KRW"))
//                    break label2064;
//                j = 23;
//                continue;
//                if (!paramString1.equals("KWD"))
//                    break label2064;
//                j = 24;
//                continue;
//                if (!paramString1.equals("MOP"))
//                    break label2064;
//                j = 25;
//                continue;
//                if (!paramString1.equals("MXN"))
//                    break label2064;
//                j = 26;
//                continue;
//                if (!paramString1.equals("MYR"))
//                    break label2064;
//                j = 27;
//                continue;
//                if (!paramString1.equals("NLG"))
//                    break label2064;
//                j = 28;
//                continue;
//                if (!paramString1.equals("NOK"))
//                    break label2064;
//                j = 29;
//                continue;
//                if (!paramString1.equals("NPR"))
//                    break label2064;
//                j = 30;
//                continue;
//                if (!paramString1.equals("NZD"))
//                    break label2064;
//                j = 31;
//                continue;
//                if (!paramString1.equals("PHP"))
//                    break label2064;
//                j = 32;
//                continue;
//                if (!paramString1.equals("PKR"))
//                    break label2064;
//                j = 33;
//                continue;
//                if (!paramString1.equals("RUB"))
//                    break label2064;
//                j = 34;
//                continue;
//                if (!paramString1.equals("SEK"))
//                    break label2064;
//                j = 35;
//                continue;
//                if (!paramString1.equals("SGD"))
//                    break label2064;
//                j = 36;
//                continue;
//                if (!paramString1.equals("SLL"))
//                    break label2064;
//                j = 37;
//                continue;
//                if (!paramString1.equals("THB"))
//                    break label2064;
//                j = 38;
//                continue;
//                if (!paramString1.equals("TWD"))
//                    break label2064;
//                j = 39;
//                continue;
//                if (!paramString1.equals("TZS"))
//                    break label2064;
//                j = 40;
//                case 1:
//                case 2:
//                case 3:
//                case 4:
//                case 5:
//                case 6:
//                case 7:
//                case 8:
//                case 9:
//                case 10:
//                case 11:
//                case 12:
//                case 13:
//                case 14:
//                case 15:
//                case 16:
//                case 17:
//                case 18:
//                case 19:
//                case 20:
//                case 21:
//                case 22:
//                case 23:
//                case 24:
//                case 25:
//                case 26:
//                case 27:
//                case 28:
//                case 29:
//                case 30:
//                case 31:
//                case 32:
//                case 33:
//                case 34:
//                case 35:
//                case 36:
//                case 37:
//                case 38:
//                case 39:
//                case 40:
//            }
//        ReplaceExchangeActivity.setTvContent2("$");
//        ReplaceExchangeActivity.setTvContent3("$");
//        return;
//        ReplaceExchangeActivity.setTvContent2("EUR");
//        ReplaceExchangeActivity.setTvContent3("EUR");
//        return;
//        ReplaceExchangeActivity.setTvContent2("ATS");
//        ReplaceExchangeActivity.setTvContent3("ATS");
//        return;
//        ReplaceExchangeActivity.setTvContent2("AUD");
//        ReplaceExchangeActivity.setTvContent3("AUD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("BEF");
//        ReplaceExchangeActivity.setTvContent3("BEF");
//        return;
//        ReplaceExchangeActivity.setTvContent2("BRL");
//        ReplaceExchangeActivity.setTvContent3("BRL");
//        return;
//        ReplaceExchangeActivity.setTvContent2("CAD");
//        ReplaceExchangeActivity.setTvContent3("CAD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("CHF");
//        ReplaceExchangeActivity.setTvContent3("CHF");
//        return;
//        ReplaceExchangeActivity.setTvContent2("DEM");
//        ReplaceExchangeActivity.setTvContent3("DEM");
//        return;
//        ReplaceExchangeActivity.setTvContent2("DKK");
//        ReplaceExchangeActivity.setTvContent3("DKK");
//        return;
//        ReplaceExchangeActivity.setTvContent2("ESP");
//        ReplaceExchangeActivity.setTvContent3("ESP");
//        return;
//        ReplaceExchangeActivity.setTvContent2("FIM");
//        ReplaceExchangeActivity.setTvContent3("FIM");
//        return;
//        ReplaceExchangeActivity.setTvContent2("FRF");
//        ReplaceExchangeActivity.setTvContent3("FRF");
//        return;
//        ReplaceExchangeActivity.setTvContent2("GBP");
//        ReplaceExchangeActivity.setTvContent3("GBP");
//        return;
//        ReplaceExchangeActivity.setTvContent2("HKD");
//        ReplaceExchangeActivity.setTvContent3("HKD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("IDR");
//        ReplaceExchangeActivity.setTvContent3("IDR");
//        return;
//        ReplaceExchangeActivity.setTvContent2("INR");
//        ReplaceExchangeActivity.setTvContent3("INR");
//        return;
//        ReplaceExchangeActivity.setTvContent2("IQD");
//        ReplaceExchangeActivity.setTvContent3("IQD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("IRR");
//        ReplaceExchangeActivity.setTvContent3("IRR");
//        return;
//        ReplaceExchangeActivity.setTvContent2("ITL");
//        ReplaceExchangeActivity.setTvContent3("ITL");
//        return;
//        ReplaceExchangeActivity.setTvContent2("JOD");
//        ReplaceExchangeActivity.setTvContent3("JOD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("JPY");
//        ReplaceExchangeActivity.setTvContent3("JPY");
//        return;
//        ReplaceExchangeActivity.setTvContent2("KRW");
//        ReplaceExchangeActivity.setTvContent3("KRW");
//        return;
//        ReplaceExchangeActivity.setTvContent2("KWD");
//        ReplaceExchangeActivity.setTvContent3("KWD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("MOP");
//        ReplaceExchangeActivity.setTvContent3("MOP");
//        return;
//        ReplaceExchangeActivity.setTvContent2("MXN");
//        ReplaceExchangeActivity.setTvContent3("MXN");
//        return;
//        ReplaceExchangeActivity.setTvContent2("MYR");
//        ReplaceExchangeActivity.setTvContent3("MYR");
//        return;
//        ReplaceExchangeActivity.setTvContent2("NLG");
//        ReplaceExchangeActivity.setTvContent3("NLG");
//        return;
//        ReplaceExchangeActivity.setTvContent2("NOK");
//        ReplaceExchangeActivity.setTvContent3("NOK");
//        return;
//        ReplaceExchangeActivity.setTvContent2("NPR");
//        ReplaceExchangeActivity.setTvContent3("NPR");
//        return;
//        ReplaceExchangeActivity.setTvContent2("NZD");
//        ReplaceExchangeActivity.setTvContent3("NZD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("PHP");
//        ReplaceExchangeActivity.setTvContent3("PHP");
//        return;
//        ReplaceExchangeActivity.setTvContent2("PKR");
//        ReplaceExchangeActivity.setTvContent3("PKR");
//        return;
//        ReplaceExchangeActivity.setTvContent2("RUB");
//        ReplaceExchangeActivity.setTvContent3("RUB");
//        return;
//        ReplaceExchangeActivity.setTvContent2("SEK");
//        ReplaceExchangeActivity.setTvContent3("SEK");
//        return;
//        ReplaceExchangeActivity.setTvContent2("SGD");
//        ReplaceExchangeActivity.setTvContent3("SGD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("SLL");
//        ReplaceExchangeActivity.setTvContent3("SLL");
//        return;
//        ReplaceExchangeActivity.setTvContent2("THB");
//        ReplaceExchangeActivity.setTvContent3("THB");
//        return;
//        ReplaceExchangeActivity.setTvContent2("TWD");
//        ReplaceExchangeActivity.setTvContent3("TWD");
//        return;
//        ReplaceExchangeActivity.setTvContent2("TZS");
//        ReplaceExchangeActivity.setTvContent3("TZS");
    }
}