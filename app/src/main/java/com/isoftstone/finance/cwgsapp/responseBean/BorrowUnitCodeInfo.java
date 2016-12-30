package com.isoftstone.finance.cwgsapp.responseBean;


/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:14
*/
public class BorrowUnitCodeInfo
{
  public String clientCode;
  public String clientId;
  public String clientName;

  public String getClientCode()
  {
    return this.clientCode;
  }

  public String getClientId()
  {
    return this.clientId;
  }

  public String getClientName()
  {
    return this.clientName;
  }

  public void setClientCode(String paramString)
  {
    this.clientCode = paramString;
  }

  public void setClientId(String paramString)
  {
    this.clientId = paramString;
  }

  public void setClientName(String paramString)
  {
    this.clientName = paramString;
  }
}