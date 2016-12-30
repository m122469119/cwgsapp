package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;
/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:16
*/
public class RDLimitResult extends BaseResult
{
  public String requestType;
  public ArrayList<RDLimitInfo> result;

  public String getRequestType()
  {
    return this.requestType;
  }

  public ArrayList<RDLimitInfo> getResult()
  {
    return this.result;
  }

  public void setRequestType(String paramString)
  {
    this.requestType = paramString;
  }

  public void setResult(ArrayList<RDLimitInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}