package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:15
*/
public class CurrencyResult extends BaseResult
{
  public String requestType;
  public ArrayList<CurrencyInfo> result;

  public ArrayList<CurrencyInfo> getResult()
  {
    return this.result;
  }

  public void setResult(ArrayList<CurrencyInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}