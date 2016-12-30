package com.isoftstone.finance.cwgsapp.manage;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.isoftstone.finance.cwgsapp.requestBean.LoginRequest;
import com.isoftstone.finance.cwgsapp.requestBean.RegularDepositRequest;
import com.isoftstone.finance.cwgsapp.responseBean.BankTypeResult;
import com.isoftstone.finance.cwgsapp.responseBean.BorrowUnitCodeResult;
import com.isoftstone.finance.cwgsapp.responseBean.CMAboveUnitResult;
import com.isoftstone.finance.cwgsapp.responseBean.CMClientnoResult;
import com.isoftstone.finance.cwgsapp.responseBean.CUClientNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.ClientMessageResult;
import com.isoftstone.finance.cwgsapp.responseBean.CurrencyResult;
import com.isoftstone.finance.cwgsapp.responseBean.FCurrencyResult;
import com.isoftstone.finance.cwgsapp.responseBean.FxCustomEntityForAppResult;
import com.isoftstone.finance.cwgsapp.responseBean.HisBindResult;
import com.isoftstone.finance.cwgsapp.responseBean.IAAccountNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.IAClientNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.LoginResult;
import com.isoftstone.finance.cwgsapp.responseBean.NoticeDepositNumResult;
import com.isoftstone.finance.cwgsapp.responseBean.OAccountBankResult;
import com.isoftstone.finance.cwgsapp.responseBean.OfficeResult;
import com.isoftstone.finance.cwgsapp.responseBean.RDLimitResult;
import com.isoftstone.finance.cwgsapp.responseBean.RDTicketResult;
import com.isoftstone.finance.cwgsapp.responseBean.SelfAccountNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.VPAccountNoResult;
import com.isoftstone.finance.cwgsapp.responseBean.VPClientNoResult;

import java.util.HashMap;

public class ServerManager
{
  public static void getHisBind(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "setbinding/hisBind";
    HashMap hashMap = new HashMap();
    hashMap.put("sessionId", Constant.SESSIONID);
    hashMap.put("requestType", "UserSetting");
    hashMap.put("userId", Constant.USERID);
    GsonRequest gsonRequest = new GsonRequest(1, str, HisBindResult.class, hashMap, paramListener, paramErrorListener);
    CwgsApplication.getRequestQueue().add(gsonRequest);
  }

  public static void getLoginQuery(String paramString, LoginRequest paramLoginRequest, Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "login/in";
    HashMap hashMap = new HashMap();
    hashMap.put("requesType", "login");
    hashMap.put("username", paramLoginRequest.getUsername());
    hashMap.put("password", paramLoginRequest.getPassword());
    hashMap.put("bindingNumber", paramLoginRequest.getBindingNumber());
    hashMap.put("deviceName", paramString);
    GsonRequest gsonRequest = new GsonRequest(1, str, LoginResult.class, hashMap, paramListener, paramErrorListener);
    gsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(gsonRequest);
  }

  public static void getQueryOffice(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "bankBalance/officeList";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "office");
    localHashMap.put("language", "ZH");
    GsonRequest localGsonRequest = new GsonRequest(1, str, OfficeResult.class, localHashMap, paramListener, paramErrorListener);
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getQueryCurrency(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "bankBalance/currencyList";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "currency");
    GsonRequest localGsonRequest = new GsonRequest(1, str, CurrencyResult.class, localHashMap, paramListener, paramErrorListener);
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getQueryBankType(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "bankInfo/bankTypeList";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "bankType");
    localHashMap.put("language", "ZH");
    GsonRequest localGsonRequest = new GsonRequest(1, str, BankTypeResult.class, localHashMap, paramListener, paramErrorListener);
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getQueryOAccountBank(String paramString, Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "bankInfo/openBankList";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "openBank");
    localHashMap.put("language", "ZH");
    if ((paramString != null) && (!paramString.equals("")))
      localHashMap.put("bankTypeId", paramString);
    GsonRequest localGsonRequest = new GsonRequest(1, str, OAccountBankResult.class, localHashMap, paramListener, paramErrorListener);
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getQueryClientMessage(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "clientInfo/list";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "clientMassage");
    localHashMap.put("language", "ZH");
    GsonRequest localGsonRequest = new GsonRequest(1, str, ClientMessageResult.class, localHashMap, paramListener, paramErrorListener);
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getBorrowUnitCodeQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "loanquery/clientCode";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "LOAN_CLIENTCODE");
    GsonRequest localGsonRequest = new GsonRequest(1, str, BorrowUnitCodeResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getFCurrencyQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "currencyInfo/list";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "currency");
    GsonRequest localGsonRequest = new GsonRequest(1, str, FCurrencyResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getDaiKeKeHuNoQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "fxSettlement/getClient";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "fxmanageForApp_queryClient");
    localHashMap.put("statusId", "21");
    GsonRequest localGsonRequest = new GsonRequest(1, str, FxCustomEntityForAppResult.class, localHashMap, paramListener, paramErrorListener);
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getrmbSelfAccountNoQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "selfRate/currentBankAccount";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "fxmanageForApp_selfRate_currentBankAccount");
    localHashMap.put("currencyId", "1");
    GsonRequest localGsonRequest = new GsonRequest(1, str, SelfAccountNoResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

//  public static void getSelfAccountNoQuery(SelfExchangeRequest paramSelfExchangeRequest, Response.Listener paramListener, Response.ErrorListener paramErrorListener)
//  {
//    String str = ZHConsant.REQUEST_ADDRESS + "selfRate/currentBankAccount";
//    HashMap localHashMap = new HashMap();
//    localHashMap.put("sessionId", ZHConsant.SESSIONID);
//    localHashMap.put("requestType", "fxmanageForApp_selfRate_currentBankAccount");
//    if (paramSelfExchangeRequest.getCurrencyId() != null)
//      localHashMap.put("currencyId", paramSelfExchangeRequest.getCurrencyId());
//    GsonRequest localGsonRequest = new GsonRequest(1, str, SelfAccountNoResult.class, localHashMap, paramListener, paramErrorListener);
//    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
//    CwgsApplication.getRequestQueue().add(localGsonRequest);
//  }

  public static void getCMclientnoQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "settleQuery/clientList";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "settle_query_client");
    GsonRequest localGsonRequest = new GsonRequest(1, str, CMClientnoResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getCMaboveunitQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "inner/client/parentClient";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "isubclient_parent");
    GsonRequest localGsonRequest = new GsonRequest(1, str, CMAboveUnitResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getVPClientNoQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "capitalPool/customerNo";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "settle_capitalPool_customerNo");
    GsonRequest localGsonRequest = new GsonRequest(1, str, VPClientNoResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getVPAccountNoQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "capitalPool/accountNo";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "settle_capitalPool_accountNo");
    GsonRequest localGsonRequest = new GsonRequest(1, str, VPAccountNoResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getcuClientNoQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "credit/clinetInfo";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "CreditAssist_client");
    GsonRequest localGsonRequest = new GsonRequest(1, str, CUClientNoResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getiaclientnoQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "settleQuery/clientList";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "settle_query_client");
    GsonRequest localGsonRequest = new GsonRequest(1, str, IAClientNoResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getiaaccountnoQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "settleQuery/bankAccount";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "settle_query_bankAccount");
    GsonRequest localGsonRequest = new GsonRequest(1, str, IAAccountNoResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getRDTicketQuery(RegularDepositRequest paramRegularDepositRequest, Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "settleQuery/fixedNo";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "settle_fixedno");
    localHashMap.put("accountId", paramRegularDepositRequest.getAccountId());
    GsonRequest localGsonRequest = new GsonRequest(1, str, RDTicketResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void getRDLimitQuery(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "settleQuery/fixedTerm";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    GsonRequest localGsonRequest = new GsonRequest(1, str, RDLimitResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }

  public static void NoticeDepositNumQueru(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "settleQuery/depositNo";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "settle_depositNo");
    GsonRequest localGsonRequest = new GsonRequest(1, str, NoticeDepositNumResult.class, localHashMap, paramListener, paramErrorListener);
    localGsonRequest.setRetryPolicy(new DefaultRetryPolicy(200000, 1, 1.0F));
    CwgsApplication.getRequestQueue().add(localGsonRequest);
  }
}