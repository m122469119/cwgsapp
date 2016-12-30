package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

public class ImplementPlanResult extends BaseResult
{
  public String requestType;
  public ArrayList<ImplementPlanInfo> result;

  public String getRequestType()
  {
    return this.requestType;
  }

  public ArrayList<ImplementPlanInfo> getResult()
  {
    return this.result;
  }

  public void setRequestType(String paramString)
  {
    this.requestType = paramString;
  }

  public void setResult(ArrayList<ImplementPlanInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}