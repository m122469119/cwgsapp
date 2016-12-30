package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:15
*/
public class CUClientNoResult extends BaseResult
{
  public String requestTye;
  public ArrayList<CUClientNoInfo> result;

  public String getRequestTye()
  {
    return this.requestTye;
  }

  public ArrayList<CUClientNoInfo> getResult()
  {
    return this.result;
  }

  public void setRequestTye(String paramString)
  {
    this.requestTye = paramString;
  }

  public void setResult(ArrayList<CUClientNoInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}