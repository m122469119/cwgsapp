package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:16
*/
public class OfficeResult extends BaseResult
{
  public String requestType;
  public ArrayList<OfficeInfo> result;

  public ArrayList<OfficeInfo> getResult()
  {
    return this.result;
  }

  public void setResult(ArrayList<OfficeInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}