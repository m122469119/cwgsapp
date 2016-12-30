package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:14
*/
public class BorrowUnitCodeResult extends BaseResult
{
  public String requestType;
  public ArrayList<BorrowUnitCodeInfo> result;

  public String getRequestType()
  {
    return this.requestType;
  }

  public ArrayList<BorrowUnitCodeInfo> getResult()
  {
    return this.result;
  }

  public void setRequestType(String paramString)
  {
    this.requestType = paramString;
  }

  public void setResult(ArrayList<BorrowUnitCodeInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}