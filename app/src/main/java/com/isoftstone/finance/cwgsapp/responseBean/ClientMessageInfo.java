package com.isoftstone.finance.cwgsapp.responseBean;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:14
*/
public class ClientMessageInfo
{
  public String clientCode;
  public String clientID;
  public String clientName;

  public String getClientCode()
  {
    return this.clientCode;
  }

  public String getClientID()
  {
    return this.clientID;
  }

  public String getClientName()
  {
    return this.clientName;
  }

  public void setClientCode(String paramString)
  {
    this.clientCode = paramString;
  }

  public void setClientID(String paramString)
  {
    this.clientID = paramString;
  }

  public void setClientName(String paramString)
  {
    this.clientName = paramString;
  }
}