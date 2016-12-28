package com.isoftstone.finance.cwgsapp.manage;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.isoftstone.finance.cwgsapp.requestBean.LoginRequest;
import com.isoftstone.finance.cwgsapp.responseBean.HisBindResult;
import com.isoftstone.finance.cwgsapp.responseBean.LoginResult;

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
}