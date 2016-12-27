package com.isoftstone.finance.cwgsapp.manage;

import com.android.volley.Response;
import com.isoftstone.finance.cwgsapp.responseBean.HisBindResult;

import java.util.HashMap;

public class ServerManager
{
  public static void getHisBind(Response.Listener paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = Constant.REQUEST_ADDRESS + "setbinding/hisBind";
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionId", Constant.SESSIONID);
    localHashMap.put("requestType", "UserSetting");
    localHashMap.put("userId", Constant.USERID);
    GsonRequest gsonRequest = new GsonRequest(1, str, HisBindResult.class, localHashMap, paramListener, paramErrorListener);
    CwgsApplication.getRequestQueue().add(gsonRequest);
  }
}