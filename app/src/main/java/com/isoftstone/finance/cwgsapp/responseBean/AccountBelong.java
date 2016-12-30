package com.isoftstone.finance.cwgsapp.responseBean;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:13
*/
public class AccountBelong
{
  String id;
  String ownerType;

  public AccountBelong(String paramString1, String paramString2)
  {
    this.id = paramString1;
    this.ownerType = paramString2;
  }

  public String getId()
  {
    return this.id;
  }

  public String getOwnerType()
  {
    return this.ownerType;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public void setOwnerType(String paramString)
  {
    this.ownerType = paramString;
  }
}