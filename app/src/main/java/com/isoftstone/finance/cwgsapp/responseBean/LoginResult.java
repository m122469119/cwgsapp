package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

public class LoginResult extends BaseResult
{
  public String requestType;
  public ArrayList<LoginInfo> result;

  public String getRequestType()
  {
    return this.requestType;
  }

  public ArrayList<LoginInfo> getResult()
  {
    return this.result;
  }

  public void setRequestType(String paramString)
  {
    this.requestType = paramString;
  }

  public void setResult(ArrayList<LoginInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}