package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

public class HisBindResult extends BaseResult
{
  public String requestType;
  public ArrayList<HisBindInfo> result;

  public ArrayList<HisBindInfo> getResult()
  {
    return this.result;
  }

  public void setResult(ArrayList<HisBindInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}