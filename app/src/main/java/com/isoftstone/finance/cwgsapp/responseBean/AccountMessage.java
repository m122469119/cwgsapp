package com.isoftstone.finance.cwgsapp.responseBean;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:14
*/
public class AccountMessage
{
  String accountProperty;
  String accountStatus;
  String accountType;
  String propertyid;
  String statusid;
  String typeid;

  public AccountMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.accountType = paramString1;
    this.typeid = paramString2;
    this.accountStatus = paramString3;
    this.statusid = paramString4;
    this.accountProperty = paramString5;
    this.propertyid = paramString6;
  }

  public String getAccountProperty()
  {
    return this.accountProperty;
  }

  public String getAccountStatus()
  {
    return this.accountStatus;
  }

  public String getAccountType()
  {
    return this.accountType;
  }

  public String getPropertyid()
  {
    return this.propertyid;
  }

  public String getStatusid()
  {
    return this.statusid;
  }

  public String getTypeid()
  {
    return this.typeid;
  }

  public void setAccountProperty(String paramString)
  {
    this.accountProperty = paramString;
  }

  public void setAccountStatus(String paramString)
  {
    this.accountStatus = paramString;
  }

  public void setAccountType(String paramString)
  {
    this.accountType = paramString;
  }

  public void setPropertyid(String paramString)
  {
    this.propertyid = paramString;
  }

  public void setStatusid(String paramString)
  {
    this.statusid = paramString;
  }

  public void setTypeid(String paramString)
  {
    this.typeid = paramString;
  }
}