package com.isoftstone.finance.cwgsapp.responseBean;
/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:16
*/
public class TranDetailReStatus
{
  String request;
  String str;

  public TranDetailReStatus(String paramString1, String paramString2)
  {
    this.request = paramString1;
    this.str = paramString2;
  }

  public String getRequest()
  {
    return this.request;
  }

  public String getStr()
  {
    return this.str;
  }

  public void setRequest(String paramString)
  {
    this.request = paramString;
  }

  public void setStr(String paramString)
  {
    this.str = paramString;
  }
}